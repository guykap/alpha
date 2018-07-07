package Alpha;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CnBooking {
	static public String cnBaseUrl = "";
	static public boolean seekBackgroundWork;
	public static String offerType;

	static public void loginCN() throws Throwable {
		if (ClientsMngt.site == 1) {
			cnBaseUrl = new String("http://home.castingnetworks.com");
		} else if (ClientsMngt.site == 2) {
			cnBaseUrl = new String("https://login.lacasting.com/Login.aspx");
			
		//	cnBaseUrl = new String("http://home.lacasting.com/");
		}
		ManageDriver.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ManageDriver.parentWindowHandler = ManageDriver.driver.getWindowHandle();
		Breath.makeZeroSilentCounter();
		Logging.slog("LOGIN-CN");
		Logging.slog(new String("Logining in username: ").concat(ClientsMngt.client.getCnUsername())
				.concat(", ActorID: ").concat(ClientsMngt.client.getActorId()));
		seekBackgroundWork = true;
		Logging.slog("A: Window handle Parent " + ManageDriver.parentWindowHandler);
		ManageDriver.driver.get(cnBaseUrl + "/");
		Breath.deepBreath();
		ManageDriver.driver.findElement(By.id("login")).click();
		ManageDriver.driver.findElement(By.id("login")).clear();
		ManageDriver.driver.findElement(By.id("login")).sendKeys(ClientsMngt.client.getCnUsername());
		ManageDriver.driver.findElement(By.id("password")).clear();
		ManageDriver.driver.findElement(By.id("password")).sendKeys(ClientsMngt.client.getCnPassword());
		ManageDriver.driver.findElement(By.xpath("//input[@id='submit']")).click();
		Breath.breath();
		// debug - this is just for My agent :
		// driver.findElement(By.id("_ctl0_cphBody_rptProfiles__ctl1_lnkViewProfile2")).click();
		// check for welcome window:
		if (!Beta.verifyLocation(XpathBuilder.xpCNWelcomeHeader(), "Welcome")) {
			Logging.slog("Can't find Welcome Page");
			return;
		}
		Logging.log('c');
		Breath.breath();
	}

	static public void coreCastingNetworks() throws Throwable {
		Logging.slog("coreCastingNetworks");
		// first time in coreLoop - always begin with Extra chart
		ManageDriver.driver.findElement(By.xpath("//a[@id='_ctl0_cphBody_lnkExtrasRoles']")).click();
		Breath.deepBreath();
		if (!Beta.verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
			Logging.slog("Can't find Extras chart");
			throw new Exception();
		}
		Logging.slog("In Extras chart");
		while (true) {

			// for alternation between principle and BG use : seekBackgroundWork
			// ^= true;
			if (offerType.equals("both")) {
				seekBackgroundWork ^= true;
				if (seekBackgroundWork) {
					Logging.slog("ALTERNATE to BACKGROUND work");
				} else {
					Logging.slog("ALTERNATE to PRINCIPLE work");
				}
			}
			if (offerType.equals("extras")) {
				seekBackgroundWork = true;
				Logging.slog("Round again to BACKGROUND work");
				ManageDriver.driver.navigate().refresh();
			}

			if (offerType.equals("principle")) {
				seekBackgroundWork = false;
				Logging.slog("Round again to PRINCIPLE work");
				ManageDriver.driver.navigate().refresh();
			}
			// if (ClientsMngt.runStatus()) {
			if (Beta.runStatus) {
				Beta.updateLastInterNow(String.valueOf(ClientsMngt.config_id), ClientsMngt.client.getActorId());
				heartLoop();
				while (ManageDriver.nowIsNightTime()) {
					Breath.nap();
				}
			}
			// }

			Breath.nap();
		}
	}

	static private void heartLoop() throws Throwable {
		String originWindow = ManageDriver.driver.getWindowHandle();

		if (seekBackgroundWork) {
			if (!Beta.verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Extras")) {
				ManageDriver.driver
						.findElement(
								By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				// debug
				Breath.deepBreath();
				if (!Beta.verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Extras")) {
					Logging.slog("Can't find Extras chart");
					throw new Exception();
				}
			}
		} else {
			// We want to be in principle chart
			if (!Beta.verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Principals")) {
				ManageDriver.driver
						.findElement(
								By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				Breath.deepBreath();
				if (!Beta.verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Principals")) {
					Logging.slog("Can't find principle chart");
					throw new Exception();
				}

			}
		}
		new Select(ManageDriver.driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
		Breath.deepBreath();
		// int maxRows = ClientsMngt.CN_DEFAULT_PROD_MAX_ROWS;
		for (int rowNum = 0; rowNum < ClientsMngt.onlyTopProd; rowNum++) {
			Logging.slog("Checking for green star at row number: " + rowNum);
			int trStarRow = (3 * rowNum);
			trStarRow += 4;
			String starPos = XpathBuilder.xpCNStarPositionBG(trStarRow);
			String srcOfImg = "";
			try {
				srcOfImg = new String(ManageDriver.driver.findElement(By.xpath(starPos)).getAttribute("src"));
			} catch (Error e) {
				Logging.slog(e.getMessage());

			}
			if (srcOfImg.contains("spacer.gif")) {
				Logging.slog("No star on offer " + rowNum + " from top.  Let's try submitting.");
				Beta.offer = new Job(ClientsMngt.client.getActorId());

				Scapper.handleBackgroundWorkOffer(Beta.offer, seekBackgroundWork, (trStarRow - 1));
				if (Beta.offer.offerHasBeenConsideredBeforeCN(Beta.Jobs)) {
					continue;
				}
				Beta.Jobs.add(Beta.offer);
				// debug
				Breath.silentCount();

				Esl.readNotice(ClientsMngt.client, Beta.offer);
				Beta.offer.genderMatchingUpdate(ClientsMngt.client);
				Beta.offer.unionMatchingUpdate(ClientsMngt.client);
				Beta.offer.makeDecisionCN();

				if ((Beta.offer.getHasBeenSubmitted()) || (!Beta.offer.getDecisionSubmit())) {
					Logging.printDecisionMakingVars(Beta.offer);
					continue;
				}

				Logging.log('h');
				Esl.fillTalentNoteCN(ClientsMngt.client, Beta.offer);
				int trLinkToOfferRow = -1;
				trLinkToOfferRow = trStarRow - 1;
				Beta.offer.foundOnRow = rowNum;
				String linkOfferPos = ((new String("//tr[")).concat(String.valueOf(trLinkToOfferRow))).concat("]/td/a");
				ManageDriver.driver.findElement(By.xpath(linkOfferPos)).click();
				Breath.breath();
				ManageDriver.driver.switchTo().window(ManageDriver.getSonWindowHandler(originWindow));
				// add time of apperance to Beta.offer
				try {
					// Breath.breath();
					Beta.offer.setOfferTimeRoleAdded(new String(
							ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpCNRoleAddedTime())).getText()));
				} catch (Exception e) {
					Beta.offer.setOfferTimeRoleAdded(new String(""));
				}

				Breath.breath();
				try {
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpFindSubmitLink())).click();
				} catch (Exception e) {
				}
				// Breath.deepBreath();

				Logging.slog((new String("Green on ").concat(Beta.offer.getOfferCharacterName())));
				Breath.breathToMissleadThem();
				if (!Beta.verifyLocation("//span", "Customize your submission")) {
					Logging.slog("Error: You are on wrong window");
					ManageDriver.windowStatus(originWindow);
					throw new Exception();
				}
				Logging.log('l');
				
					choosePhoto(ClientsMngt.client, Beta.offer);
				 
				Breath.breath();
				ManageDriver.driver.findElement(By.id("TALENTNOTE")).clear();

				ManageDriver.driver.findElement(By.id("TALENTNOTE")).sendKeys(Beta.offer.getMessage());
				Breath.breath();
				ManageDriver.driver.findElement(By.cssSelector(XpathBuilder.cssCMSubmitButton())).click();
				Breath.deepBreath();
				if (!Beta.verifyLocation("//span", "Submission Successful")) {
					Logging.slog("Did NOT recieve final submittion successful");
					ManageDriver.windowStatus(originWindow);
					throw new Exception();
				}
				if (!ManageDriver.killSubWindowAndMoveToParentWindow(ManageDriver.parentWindowHandler)) {
					Logging.slog("Memory leak error: failed killing child window");
					throw new Exception();
				}
				Beta.offer.setHasBeenSubmitted(true);
				Breath.makeZeroSilentCounter();
				Logging.log('m');
				Logging.printSubmittions(Beta.Jobs);
				Beta.writeSubmittionToDB(Beta.offer);
			} else {
				Logging.slog("Found star on the offer " + rowNum + " from top");
				Breath.silentCount();
			}
		}
	}

	static private void choosePhoto(Actor human, Job offer) {

		int photoChoice =0;
		if (human.getDefaultPhoto().length() < 1) {
			Logging.slog("Error : data is not a number. Choosing the first photo as defualt");
			photoChoice = 0;

		} else {
			 if ((ClientsMngt.site ==1)){
			photoChoice = ClientsMngt.currentPhotoChoice(human.getDefaultPhoto(), offer);
			 }			 
		}
		
		if ((ClientsMngt.client.getDefaultPhoto()).equals(new String("0"))) {
			photoChoice =0;
		}else{
			//randomly choose between the two options
			Random r = new Random();
			if(r.nextBoolean()){
				photoChoice =0;
			}else{
				photoChoice =1;
			}
			Logging.slog("Randomly chose photo: " + photoChoice);
		}
		
		
		
		try {
			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpCNChoosePhoto(photoChoice))).click();
		} catch (Exception e) {
			Logging.slog("Error choosing photo");
		}

	}

	static private boolean searchLabels(Job offer, String search_label) {
		for (String label : offer.labels) {
			if (label.equals(new String(search_label.trim()))) {
				return true;
			}
		}
		return false;
	}

}
