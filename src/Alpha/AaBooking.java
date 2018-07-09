package Alpha;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.IFNULL;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AaBooking {
	static public String aaBaseUrl;

	static public void loginAA() throws Throwable {
		aaBaseUrl = "http://actorsaccess.com";
		ManageDriver.driver.manage().timeouts().implicitlyWait(Breath.geckoWaitTime, TimeUnit.SECONDS);
		ManageDriver.parentWindowHandler = ManageDriver.driver.getWindowHandle();
		Logging.slog("LOGIN-AA");
		Breath.makeZeroSilentCounter();
		// Logging.sLogging.log('a');
		Logging.slog("Window handle Parent " + ManageDriver.parentWindowHandler);
		Logging.slog(new String("Logining in username: ").concat(ClientsMngt.client.getAaUsername()));
		Breath.deepBreath();
		ManageDriver.driver.get(aaBaseUrl + "/");

		Breath.deepBreath();

		ManageDriver.driver.findElement(By.id("username")).clear();
		Breath.breath();
		ManageDriver.driver.findElement(By.id("username")).sendKeys(ClientsMngt.client.getAaUsername());
		Breath.breath();
		ManageDriver.driver.findElement(By.id("password")).clear();
		Breath.breath();
		ManageDriver.driver.findElement(By.id("password")).sendKeys(ClientsMngt.client.getAaPassword());
		Breath.breath();
		ManageDriver.driver.findElement(By.id("login-btn")).click();
		Breath.deepBreath();

		if (!Beta.verifyLocation("//p[@id='breadcrumb']", "breakdown services, ltd")) {
			Logging.slog("Can't login.");
			throw new Exception();
		}
		Logging.log('c');
	}

	static public void coreActorsAccess() throws Throwable {
		// go over the chosen regions and submit to each region
		Breath.makeZeroSilentCounter();

		while (ClientsMngt.runStatus()) {
			if (ManageDriver.nowIsNightTime()) {
				// we will sleep
				Breath.nap();
			}
			if ((Beta.runStatus) && (ClientsMngt.reloadTargetRegions(ClientsMngt.client))
					&& (ClientsMngt.client.atLeastSomeRegionChoosen())) {
				for (int regionNum = 0; regionNum < 15; regionNum++) {
					if (ClientsMngt.client.getTargetRegions()[regionNum]) {
						handleRegion(regionNum);
						Breath.nap();
					}
				}
			}
			Breath.silentCount();
		}
	}

	static private void handleRegion(int region) throws Throwable {
		String regionUrl = (new String(XpathBuilder.urlAABreakdownAndRegion())).concat(String.valueOf(region));
		ManageDriver.driver.get(regionUrl);
		Breath.breath();
		String tag = new String(ManageDriver.driver.findElement(By.xpath("//p[@id='breadcrumb']")).getText());
		if (!Beta.verifyLocation("//p[@id='breadcrumb']",
				(new String("home / breakdowns / ").concat(ClientsMngt.intToRegion(region))))) {
			Logging.slog("Can't find region ");
			return;
		}
		Logging.slog((new String("Region ").concat(ClientsMngt.intToRegion(region))));
		Beta.updateLastInterNow(String.valueOf(ClientsMngt.config_id), ClientsMngt.client.getActorId());
		Breath.breath();
		int productionRow = 0;
		boolean nextRowHasAnotherProd = true;

		// we only consider here the first page of productions. So in the future
		// add an option to nagivate to page 2 and 3
		while ((productionRow < ClientsMngt.onlyTopProd) && (nextRowHasAnotherProd)) {
			Logging.slog("Checking for red check at row number: " + productionRow);
			try {
				if (ManageDriver.isElementPresent(ManageDriver.driver,
						By.xpath(XpathBuilder.xpAAtabProductionInRow(productionRow)))) {
					// assertTrue(isElementPresent(By.xpath(XpathBuilder.tabProductionInRow(productionRow))));
					Logging.slog((new String("Found a production at row. So looking for red check on row: ")
							.concat(String.valueOf(productionRow))));
				} else {
					Logging.slog((new String("No production on row: ").concat(String.valueOf(productionRow))));
					nextRowHasAnotherProd = false;
					break;
				}
			} catch (Exception e) {
				Logging.slog((new String("No production on row: ").concat(String.valueOf(productionRow))));
				nextRowHasAnotherProd = false;
				Logging.slog("Error. shouldnlt reach this line. DEBUG");
				break;
			}

			try {
				Breath.breath();
				// make sure that there is another production at productionRow
				if (ManageDriver.isElementPresent(ManageDriver.driver,
						By.xpath(XpathBuilder.tabRedCheckBoxPos(productionRow)))) {
					Logging.slog((new String("Red check found on row:").concat(String.valueOf(productionRow))));
					productionRow++;
					continue;
				}
				// assertFalse(isElementPresent(By.xpath(XpathBuilder.tabRedCheckBoxPos(productionRow))));
			} catch (NoSuchElementException e) {
				Logging.slog((new String("Red check found.").concat(String.valueOf(productionRow))));
				productionRow++;
				continue;
			} catch (Exception e) {
				Logging.slog((new String("Element not found.This is wrong ").concat(String.valueOf(productionRow))));
				break;
			}
			Logging.slog(
					(new String("Lets submit. Cause NO red check at row: ").concat(String.valueOf(productionRow))));
			Beta.offer = new Job(ClientsMngt.client.getActorId());
			// some offers appear in several different regions but reffer to the
			// same role
			Beta.offer.setRegion(region);
			Scapper.parseRowOfferAA(Beta.offer, productionRow);
			if (Beta.offer.offerHasBeenConsideredBeforeAA(Beta.Jobs)) {
				productionRow++;
				continue;
			}

			try {
				ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpAALinkCharactersInProduction(productionRow)))
						.click();
			} catch (Exception e) {
				Logging.slog(
						(new String("Error: the link hasn't opened on row: ").concat(String.valueOf(productionRow))));
				continue;
			}
			Breath.breath();

			try {
				if (ManageDriver.isElementPresent(ManageDriver.driver,
						By.xpath(XpathBuilder.tabCharNameAndDetails(0)))) {
					Logging.slog("Success. We are now in characters table.");
				} else {
					Logging.slog("Error. We are not in the characters chart now. Lets return");
					ManageDriver.driver.navigate().back();
					Breath.breath();
				}

			} catch (Exception e) {
				Logging.slog("Error. shouldnlt reach this line. DEBUG");
				ManageDriver.driver.navigate().back();
				Breath.breath();
			}
			Beta.offer.foundOnRow = productionRow;
			int foundCharactersInThisProduction = totalOffersInThisProd(Beta.offer);

			// move back to window with char of productions
			Logging.slog((new String("Number of Characters found in this production: "))
					.concat(String.valueOf(foundCharactersInThisProduction)));
			Logging.slog(
					(new String("Characters added to the cart: ").concat(String.valueOf(Job.isThereSomethingInCart))));
			Breath.silentCount();
			if ((Job.isThereSomethingInCart) && (ClientsMngt.autoSubmitCart)) {
				if (submitCart()) {

					Logging.printSubmittions(Beta.Jobs);
					Breath.deepBreath();
					ManageDriver.driver.navigate().back();
					for (int i = 0; i < 3; i++) {
						Breath.deepBreath();
					}
				} else {
					Logging.slog("Didn't submit cart");

				}

				ManageDriver.driver.navigate().back();
				for (int i = 0; i < 3; i++) {
					Breath.deepBreath();
				}
			}
			ManageDriver.driver.navigate().back();
			Breath.breath();
			productionRow++;
		} // end of while loop
	}

	static private int totalOffersInThisProd(Job parentOffer) {
		// returns the number of offers created and added to Jobs list from the
		// found characters on the production
		Logging.slog("Entered character breakdown");
		// for each character - we open a new offer
		String nameOfCharacterAndDetailsUnder;
		String detailsOfCharacter;

		try {
			String prodDetailsLeftWithTimeRoleAdded = new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpProdDetailsLeftWithTimeRoleAdded())).getText());
			Logging.slog((new String("prodDetailsLeftWithTimeRoleAdded=")).concat(prodDetailsLeftWithTimeRoleAdded));
			parentOffer.addToProductionDetails(prodDetailsLeftWithTimeRoleAdded);
			Esl.parseProdDetailsLeftWithTimeRoleAdded(parentOffer, prodDetailsLeftWithTimeRoleAdded);
			parentOffer.addToProductionDetails(prodDetailsLeftWithTimeRoleAdded);
		} catch (Exception e) {
			Logging.slog(e.getMessage());
			return 0;

		}

		try {

			String prodDetialsRight = new String(
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpProdDetialsRight())).getText());
			Esl.parseProdDetialsRight(parentOffer, prodDetialsRight);

			Logging.slog((new String("prodDetailsRight:\n ")).concat(prodDetialsRight));
			parentOffer.addToProductionDetails(prodDetialsRight);
			Breath.silentCount();
		} catch (Exception e) {
			Logging.slog(e.getMessage());
			return 0;
		}

		// begin adding the characters
		Job currentOffer = parentOffer;
		int charNum = 0;
		boolean moreCharsAvil = true;

		while (moreCharsAvil) {

			try {
				String internalAAname = "";
				String internalAAhref = "";
				String internalAAclass = "";
				String nameOfCharacterandDetails = "";
				nameOfCharacterandDetails = Beta.findCharacterOnRow(charNum);

				if (nameOfCharacterandDetails.length() < 1) {
					Logging.slog(new String("No more characters after row number ").concat(String.valueOf(charNum)));
					return (charNum);
				}
				Esl.parseNameOfCharacterAndDetailsUnder(currentOffer, nameOfCharacterandDetails);
				internalAAname = Scapper.scrapAttributeAtXpath(XpathBuilder.tabAAname(charNum), "name");
				currentOffer.setInternalAAname(internalAAname.substring(5));
				internalAAhref = Scapper.scrapAttributeAtXpath((XpathBuilder.xpInternalAAhref(charNum)), "href");
				currentOffer.setInternalAAhref(internalAAhref);
				internalAAclass = Scapper.scrapAttributeAtXpath(XpathBuilder.tabAAclass(charNum), "class");
				if (!internalAAclass.contains("breakdown-open-add-role")) {
					Logging.slog("Some error - this role isn't open for submittion");
					charNum++;
					continue;
				}

				Logging.slog((new String("NameOfCharacterAndDetailsUnder = \n")).concat(nameOfCharacterandDetails));
				Esl.readNoticeAA(ClientsMngt.client, currentOffer);

				currentOffer.genderMatchingUpdate(ClientsMngt.client);
				currentOffer.ethnicityMatchingUpdate(ClientsMngt.client);
				currentOffer.unionMatchingUpdate(ClientsMngt.client);
				currentOffer.makeDecisionAA();
				Beta.Jobs.add(currentOffer);
				if ((currentOffer.getHasBeenSubmitted()) || (!currentOffer.getDecisionSubmit())) {
					Logging.printDecisionMakingVars(currentOffer);

					currentOffer = Job.renewOffer(currentOffer);
					charNum++;
					continue;
				}

				Esl.fillTalentNoteAA(ClientsMngt.client, currentOffer);
				ManageDriver.windowStatus2();
				Logging.slog("lets submit!");
				ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpCharacterLinkInCharactersPage(charNum)))
						.click();
				Breath.breath();
				ManageDriver.windowStatus2();

				Logging.slog(ManageDriver.driver.getCurrentUrl());
				ManageDriver.driver.switchTo()
						.window(ManageDriver.getSonWindowHandler(ManageDriver.driver.getWindowHandle()));
				Logging.slog(ManageDriver.driver.getCurrentUrl());

				// verify
				String ChoosingPhotoUrl = new String (ManageDriver.driver.getCurrentUrl());
				if (!ChoosingPhotoUrl.contains(currentOffer.getInternalAAname())) {
					Logging.slog(new String(("Error: the choosing window didn't open for AA internal role number:"))
							.concat(currentOffer.getInternalAAname()));
					ManageDriver.driver.navigate().back();
					charNum++;

					currentOffer = Job.renewOffer(currentOffer);

					continue;
				}

				choosePhotosAndSubmit(currentOffer);

				ManageDriver.driver.switchTo().window(ManageDriver.parentWindowHandler);

				// check if there is another character to be considered in the
				// next row
				if (Beta.verifyLocation(XpathBuilder.xpBetaCharacterName(charNum + 1), "")) {
					charNum++;
					moreCharsAvil = true;
					// create another offer with the that will only differ in
					// the name of character and character details.
					// Jobs.add(currentOffer);
					currentOffer = Job.renewOffer(currentOffer);
					Breath.breath();

				} else {
					return (charNum + 1);
				}

			} catch (Exception e) {
				Logging.slog("Failed to submit it. Maybe no more characters in chart to check.");
				Logging.slog(e.getMessage());
				return (charNum);
			}
		}
		return (charNum - 1);
	}

	static private boolean submitCart() {
		// goes to cart page and clicks submit
		// verify current page
		try {
			Breath.breath();

			// check that the cart isn't empty

			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpCartLogo())).click();
			Breath.deepBreath();
			if (Beta.verifyLocation(XpathBuilder.xpCartIsEmpty(), "Empty")) {
				Logging.slog("Opps the cart was actually empty.");
				Job.isThereSomethingInCart = false;
				return false;
			}

			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpSubmitCart())).click();
			// verify successful submition
			// Notify User/ DB about the submission.
			Breath.deepBreath();
			Logging.slog("Cart Submitted");
			Job.isThereSomethingInCart = false;

			return true;
		} catch (Exception e) {
			Job.isThereSomethingInCart = true;
			Logging.slog("Failed submitting cart");
			return false;
		}
	}

	static private void choosePhotosAndSubmit(Job currentOffer) {
		try {

			ManageDriver.driver.switchTo().defaultContent();

			ManageDriver.driver.switchTo().frame("main_window");

			if ((ClientsMngt.client.getDefaultPhoto()).equals(new String("0"))) {
				ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpChooseMySmilePhoto())).click();
			} else {
				// we will randomly choose between the two photos

				Random r = new Random();
				if (r.nextBoolean()) {
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpChooseMySeriousPhoto())).click();

				} else {
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpChooseMySmilePhoto())).click();
				}
			}

			Breath.breath();

			// ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpChooseCommercialVideo2())).click();
			Breath.breath();
			// ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpChooseBookstoreVideo1())).click();
			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpChooseHenry())).click();
			Breath.breath();
			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpChoosePark())).click();
			Breath.breath();
			if (!(ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpIncludeSizes())).isSelected())) {
				Breath.breath();
				ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpIncludeSizes())).click();
			}
			Breath.breath();
			
			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpChooseVoNarration())).click();
			Breath.breath();
			
			
			if (Beta.tryToClearTalentNotes()) {

				Breath.breath();
				ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpTalentNotesAA()))
						.sendKeys(currentOffer.getMessage());
			}
			Breath.breath();
			ManageDriver.driver.switchTo().defaultContent();
			ManageDriver.driver.switchTo().frame("buttons");
			Breath.breath();
			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpAddToCartAA())).click();
			// currentOffer.setPutInCart();
			currentOffer.setHasBeenSubmitted(true);
			currentOffer.calcTimeFromAddedToSubmitted();
			Job.isThereSomethingInCart = true;
			Beta.writeSubmittionToDB(currentOffer);
			Logging.printDecisionMakingVars(currentOffer);

			//
		} catch (Exception e) {
			Logging.slog("Failed to submit it.");
			Logging.slog(e.getMessage());
		}
	}

	static public void logutAA() throws Throwable {

		Logging.slog((new String("Logging out  ")));
		return;
	}

}
