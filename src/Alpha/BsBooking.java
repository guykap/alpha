package Alpha;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.CPInstruction;
import org.openqa.selenium.By;

import com.mysql.jdbc.Driver;

public class BsBooking {
	static public String bsBaseUrl;

	static public void loginBS() throws Throwable {
		bsBaseUrl = "https://www.backstage.com";
		ManageDriver.driver.manage().timeouts().implicitlyWait(Breath.geckoWaitTime, TimeUnit.SECONDS);
		ManageDriver.parentWindowHandler = ManageDriver.driver.getWindowHandle();
		Logging.slog("LOGIN-BS");
		Breath.makeZeroSilentCounter();
		// Logging.sLogging.log('a');
		Logging.slog("Window handle Parent " + ManageDriver.parentWindowHandler);
		Logging.slog(new String("Logining in username: ").concat(ClientsMngt.client.getAaUsername()));
		Breath.deepBreath();
		ManageDriver.driver.get(bsBaseUrl + "/accounts/#");
		// Breath.deepBreath();
		// ManageDriver.driver.findElement(By.linkText("LOG IN")).click();
		// check whether we are already logged in.
		if (alreadyLoggedIn()) {

			Logging.log('c');
			return;
		}
		Breath.breath();
		ManageDriver.driver.findElement(By.id("id_username")).clear();

	//	ManageDriver.driver.findElement(By.id("id_username")).sendKeys("g.kapulnik@gmail.com");
		ManageDriver.driver.findElement(By.name("username")).sendKeys(ClientsMngt.client.getBsUsername());
		//ManageDriver.driver.findElement(By.id("id_username")).sendKeys();
		Breath.breath();
		ManageDriver.driver.findElement(By.id("id_password")).clear();

		ManageDriver.driver.findElement(By.id("id_password")).sendKeys("bGuy1234567");
		Breath.breath();
		ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSInputLoginButton())).click();
		Breath.breath();
		/*
		 * if (!Beta.verifyLocation(
		 * ".//*[@id='bs-example-navbar-collapse-1']/div/div[2]/a",
		 * "Find Jobs")) { Logging.slog("Can't login."); throw new Exception();}
		 */

		Logging.log('c');
	}

	static public void coreBackstage() throws Throwable {
		// go over the chosen regions and submit to each region
		Breath.makeZeroSilentCounter();

		while (ClientsMngt.runStatus()) {
			if (ManageDriver.nowIsNightTime()) {
				// we will sleep
				Breath.nap();
			}
			if (Beta.runStatus) {
				Beta.updateLastInterNow(String.valueOf(ClientsMngt.config_id), ClientsMngt.client.getActorId());
				searchJobsBackstage();
				Breath.nap();

			}
		}
		Breath.silentCount();
	}

	static public void searchJobsBackstage() {
		try {
			// click casting calls
			int i = 10;

			Breath.breath();
			Logging.slog(new String("Changing URL to casting tab "));
			// ManageDriver.driver.findElement(By.xpath(".//*[@id='bs-example-navbar-collapse-1']/ul/li[1]/a")).click();
			ManageDriver.driver.get(bsBaseUrl + "/casting/");
			Breath.breath();

			// click NY-search
			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSClickSearchJobs(0))).click();
			Breath.breath();

			// click

			// verify that I'm on correct page

		} catch (Exception e) {
			Logging.slog("error.");
		}
		int prodRow = 0;
		boolean moreProdAvail = true;
		while ((prodRow < ClientsMngt.onlyTopProd)) {
			try {
				Logging.slog((new String("Looking for production on row: ").concat(String.valueOf(prodRow))));
				if (ManageDriver.isElementPresent(ManageDriver.driver,
						By.xpath(XpathBuilder.xpBStabProductionInRow(prodRow)))) {
					// assertTrue(isElementPresent(By.xpath(XpathBuilder.tabProductionInRow(productionRow))));
					Logging.slog((new String("Found a production at row. So looking for red check on row: ")
							.concat(String.valueOf(prodRow))));
				} else {
					Logging.slog((new String("No production on row: ").concat(String.valueOf(prodRow))));

					break;
				}
			} catch (Exception e) {
				Logging.slog((new String("No production on row: ").concat(String.valueOf(prodRow))));

				Logging.slog("Error. shouldnlt reach this line. DEBUG");
				break;
			}
			Logging.slog("Checking for red check at row number: " + prodRow);
			// get tabs into labellist
			try {
				 //check if there is an Applied for role tag here 
				
				
				
				if (Beta.verifyLocation(XpathBuilder.xpBSAppliedBefore(prodRow), "You've applied")) {
					Logging.slog("We already applied for this production on row " + String.valueOf(prodRow));
					prodRow++;
					continue;
				}
					
				
				
				Beta.offer = new Job(ClientsMngt.client.getActorId());
				Scapper.parseRowOfferBS(Beta.offer, prodRow);
				if (Beta.offer.offerHasBeenConsideredBeforeAA(Beta.Jobs)) {
					prodRow++;
					continue;
				}

				// DEBUG - TEST TO SEE IF THIS SAVES TIME BY NOT GOING INTO
				// PRODUCTION PAGES THAT WERE ALREADY CHECEKD.
				Beta.Jobs.add(Beta.offer);
				// lets open the production page

				Beta.offer.foundOnRow = prodRow;

				try {
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSLinkCharactersInProduction(prodRow)))
							.click();
				} catch (Exception e) {
					Logging.slog(
							(new String("Error: the link hasn't opened on row: ").concat(String.valueOf(prodRow))));
					continue;
				}
				Breath.breath();

				try {
					if ((Beta.verifyLocation(XpathBuilder.xpBSVerifyLocationCharactersTableOp3(), "Roles"))|| (Beta.verifyLocation(XpathBuilder.xpBSVerifyLocationCharactersTableOp2(), "Roles")) || (Beta.verifyLocation(XpathBuilder.xpBSVerifyLocationCharactersTableOp1(), "Roles"))) {
					 
						Logging.slog("Success. We are now in characters table.");
					} else {
						Logging.slog("Error. We are not in the characters chart now. Lets return");
						// ManageDriver.driver.navigate().back();
						Breath.breath();
						prodRow++;
						continue;

					}

				} catch (Exception e) {
					Logging.slog("Error. shouldnlt reach this line. DEBUG");
					ManageDriver.driver.navigate().back();
					Breath.breath();
				}

			 
				 
				
				
				// UNION staus

				// productionConpensation(Beta.offer);
				int foundCharactersInThisProduction = totalOffersInThisProd(Beta.offer);
				if (foundCharactersInThisProduction == (-1)) {
					Logging.slog(new String("Becuase of the error - jumping out of the WHILE PRODUCTION loop "));
					break;
				}
				// move back to window with char of productions
				Logging.slog((new String("Number of Characters found in this production: "))
						.concat(String.valueOf(foundCharactersInThisProduction)));

				// if this is a new offer - then click on the production name
				// tab and collect the different offers for this production

				// if decision submit{

				// click title
				// read all the details about roles and save to DB.

				// if there is a Apply button for a role - click it

				// fill talent notes

				// click APPLY NOW

				// Esl.parseNameOfCharacterAndDetailsUnder(currentOffer,
				// nameOfCharacterandDetails);

				prodRow++;
			} catch (Exception e) {
				Logging.slog(new String("Error on row").concat(String.valueOf(prodRow)));
			}
		}
	}

	static public ArrayList<String> findRoleIds(Job parent_offer) {
		int totalRoles = 0;
		ArrayList<String> roleIDsList = new ArrayList<String>();
		int k = 0;
		// this.labels.add(new String(sameProductionOffer.labels.get(i)));

		try {
			while (true) {
				String roleId = ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpFindRoleIDsBS(totalRoles)))
						.getAttribute("id");

				roleIDsList.add(roleId.trim());
				totalRoles++;
			}
		} catch (Exception e) {
			Logging.slog(new String("Found number of roles here: ").concat(String.valueOf(totalRoles)));
			String roleIdFound = new String("");

			for (String roleId : roleIDsList) {
				roleIdFound = roleIdFound.concat(roleId).concat(",");
			}
			roleIdFound = roleIdFound.concat("|");
			parent_offer.addToProductionDetails(new String("| ROLE_IDs_IN_PROD: ").concat(roleIdFound));
			Logging.slog(roleIdFound);

		}
		return roleIDsList;
	}

	static public int totalOffersInThisProd(Job parent_offer) {

		int j=9;
		Logging.slog("Entered character breakdown");
		ArrayList<String> roleIDsList = findRoleIds(parent_offer);

		if (roleIDsList.size() < 1) {
			return 0;
		}

		try {
			Breath.breath();

			Scapper.bsScrapProductionOpenPage(parent_offer);

			Job currentOffer = parent_offer;
			int charNum = 0;

			for (String roleId : roleIDsList) {

				try {

					// Scapper.bsScrapChracterDetails(parent_offer,charNum);
					Scapper.bsScrapChracterDetails(currentOffer, roleId);
					Esl.readNoticeBS(ClientsMngt.client, currentOffer);
					currentOffer.genderMatchingUpdate(ClientsMngt.client);
					currentOffer.ethnicityMatchingUpdate(ClientsMngt.client);
					currentOffer.unionMatchingUpdate(ClientsMngt.client);
					currentOffer.makeDecisionBS();
					Beta.Jobs.add(currentOffer);
					if ((currentOffer.getHasBeenSubmitted()) || (!currentOffer.getDecisionSubmit())) {
						Logging.printDecisionMakingVars(currentOffer);

						currentOffer = Job.renewOffer(currentOffer);
						charNum++;

						continue;
					}
					currentOffer.setInternalAAname(roleId);
					Esl.fillTalentNoteAA(ClientsMngt.client, currentOffer);

					// click Apply on the right of the role
int i =9;
					try {
						ManageDriver.driver
								.findElement(By.xpath(XpathBuilder.xpBSClickRightOfRoleAppplyButton(roleId))).click();
						Breath.breath();
					} catch (Exception e) {
						Logging.slog("The APPLY button on the right of the role did NOT work!");

						ManageDriver.driver.navigate().back();
						Breath.breath();
						return (charNum);
						// ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSClickBottomButton())).click();
					}

					// verify that correct page openned

					if (ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpTalentNotesBS())).isDisplayed()) {
						ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpTalentNotesBS()))
								.sendKeys(currentOffer.getMessage());
					}
					Breath.breath();

					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSApplyNowButton())).click();

					// currentOffer.calcTimeFromAddedToSubmitted();
					Breath.breath();
					if (Beta.verifyLocation(XpathBuilder.xpBSVerifySuccessfulSubmissionOKButton(), "OK")) {

						currentOffer.setHasBeenSubmitted(true);

						Breath.makeZeroSilentCounter();
						Logging.log('m');
						Logging.printSubmittions(Beta.Jobs);
						Beta.writeSubmittionToDB(currentOffer);
						currentOffer = Job.renewOffer(currentOffer);

						charNum++;
					} else {
						Logging.slog("Failed to submit it.  Final  OK did not appear. we are in a picle.");

						return (-1);
					}

					if (charNum < roleIDsList.size()) {
						// there are more roles to check in this production
						ManageDriver.driver.navigate().back();
						Breath.breath();
					} else {
						ManageDriver.driver.navigate().back();
						Breath.breath();
						ManageDriver.driver.navigate().back();
						Breath.breath();
						return (charNum);
					}

				} catch (Exception e) {
					Logging.slog("Failed to submit it. Maybe no more characters in chart to check.");
					Logging.slog(e.getMessage());
					return (charNum);
				}
			}
			ManageDriver.driver.navigate().back();
			Breath.breath();
			return charNum;
		} catch (Exception e) {
			return -1;
		}

	}

	static public void productionConpensation(Job offerComp) {
		// finds the compensation for all the roles as appears at the bottom of
		// the characters chart

		offerComp.setOffertRate("-1");
	}

	static public void logoutBS() throws Throwable {

		Logging.slog((new String("Logging out  ")));
		return;
	}

	static public boolean search_labels(Job offer, String search_label) {
		if (search_label.length() < 1) {
			return false;
		}
		for (String label : offer.labels) {
			if (label.equals(new String(search_label))) {
				return true;
			}
		}
		return false;
	}

	static public boolean alreadyLoggedIn() {
		String manageAccountURL = "/manage/";
		String currURL = ManageDriver.driver.getCurrentUrl();
		if (currURL.contains(manageAccountURL)) {

			// if (Beta.verifyLocation(XpathBuilder.xpBSAlreadyLoggedIn(), "My
			// Account")) {
			return true;
		}
		return false;
	}

}