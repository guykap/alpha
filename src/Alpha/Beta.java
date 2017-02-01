package Alpha;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Beta {
	// THIS IS BETA2.4
	public static WebDriver driver;
	static public String cnBaseUrl;
	static public String aaBaseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	static private List<Job> Jobs = new ArrayList<Job>();
	static Iterator<Job> jobIterator = Jobs.iterator();
	private Job offer;

	String parentWindowHandler;
	String newWindowHandler;
	static Iterator<String> windowHandlesIterator;
	static public Set<String> handles;
	public static boolean isCastingNetworks = true;
	public static boolean runStatus;
	static public boolean seekBackgroundWork;
	static boolean isTargetRegion[];
	public static String offerType;

	static public boolean longNaps = false;
	static String gecko_driver_path;
	static int loginCounter;
	static Breath takeBreath;
	static Logging bestLog;
	static Actor[] cast;

	static Actor client;

	public static void main(String[] args) throws Throwable {
		System.out.println("Hello");
		Db.setDBName("juliette.climy7kqhhvl.us-east-1.rds.amazonaws.com");

		if (args.length < 1) {
			System.out.println(
					"Need to add the DB on aws IP or name.So we will use the DB Juliette from January 2017 :)");
			Db.setDBName("juliette.climy7kqhhvl.us-east-1.rds.amazonaws.com");
		}

		if (!Db.getRunningVars()) {
			System.out.println("Failed DB interaction");
			System.exit(0);

		}

		// setJsonFilePath(args[0]);
		// ClientsMngt.loadRunningVarsFile();
		String fileoutLogs = new String(ClientsMngt.getOutLogsPath());
		String appendixFileName = (new String((new Long(System.currentTimeMillis())).toString())).concat(".txt");
		Logging.initLogging(new String(fileoutLogs).concat(appendixFileName));
		System.setProperty("webdriver.gecko.driver",
				(new String(ClientsMngt.getGecko_driver_path())).concat("geckodriver.exe"));

		// ClientsMngt.loadClientsFromFile();
		if (!Db.getClientFromDB()) {
			Logging.slog("Error loading client from DB");
			return;
		}
		Logging.printAllRunningVars();
		// if (ClientsMngt.runStatus()) {
		if (!runStatus) {
			return;
			// }
		}
		seekBackgroundWork = true;

		JUnitCore jCore;
		// SETUP LOG

		try {

		} catch (Exception e) {
			Logging.slog("Error loading cast from json file");
			Logging.slog(e.getMessage());
			return;

		}
		try {
			Beta test = new Beta();
			if (isCastingNetworks) {
				test.testBetaCN();
			} else {
				test.testBetaAA();
			}

		} catch (Exception e) {
			Logging.slog(e.getMessage());
			Logging.slog("Error in program");
		}
		Logging.slog("Program ENDED - THANK YOU!");

	}

	@Before
	public void setUp() throws Exception {

	}

	public void testBetaAA() throws Throwable {
		Logging.slog("Actors Access");
		ManageDriver.logMyIP();
		testBetaB();
	}

	@Test
	public void testBetaCN() throws Throwable {
		Logging.slog("Casting Networks");
		ManageDriver.logMyIP();
		testBetaB();
	}

	public void testBetaB() throws Throwable {

		try {
			driver = new FirefoxDriver();
		} catch (Exception e) {
			Logging.slog("Error. Fire Fox driver not found.");
			Logging.slog(e.getMessage());
			return;
		}
		loginCounter = 1;
		while (networkWorking()) {
			Logging.slog("Login number " + loginCounter);
			if (loginCounter > 10) {
				Logging.slog("THIS IS 10TH LOGIN - stopping ");
				return;
			}
			if ((loginCounter % 3) == 0) {
				Logging.slog("THIS IS a 3rd LOGIN - THEN CLOSE WINDOW and start new Driver ");
				killFirefoxAndOpenNew();
			}

			if ((loginCounter % 7) == 0) {
				Logging.slog("THIS IS a 7th LOGIN - kill gecko and close window as well ");
				if (ManageDriver.killGecko()) {
					System.setProperty("webdriver.gecko.driver",
							(new String(gecko_driver_path)).concat("geckodriver.exe"));
					killFirefoxAndOpenNew();
					loginCounter++;
					continue;
				}

			}
			try {
				if (isCastingNetworks) {
					seekBackgroundWork = true;
					loginCN();
				} else {
					loginAA();
				}
			} catch (Exception e) {
				Logging.slog(e.getMessage());
				Logging.slog("Something went during login -> So lets login again");
				loginCounter++;
				continue;
			}

			try {
				if (isCastingNetworks) {
					coreCastingNetworks();
				} else {
					coreActorsAccess();
					logutAA();
				}
			} catch (Exception e) {
				Logging.slog(e.getMessage());
				Logging.slog("Something went wrong -> Back to Login");
				loginCounter++;
			}
		}
	}

	public void logutAA() throws Throwable {

		Logging.slog((new String("Logging out  ")));
		return;
	}

	public void loginAA() throws Throwable {
		aaBaseUrl = "http://actorsaccess.com";
		driver.manage().timeouts().implicitlyWait(Breath.geckoWaitTime, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		Logging.slog("LOGIN-AA");
		Breath.makeZeroSilentCounter();
		// Logging.sLogging.log('a');
		Logging.slog("Window handle Parent " + parentWindowHandler);
		Logging.slog(new String("Logining in username: ").concat(client.getAaUsername()));
		Breath.deepBreath();
		driver.get(aaBaseUrl + "/");
		Breath.deepBreath();
		Breath.deepBreath();

		driver.findElement(By.id("username")).clear();
		Breath.breath();
		driver.findElement(By.id("username")).sendKeys(client.getAaUsername());
		Breath.breath();
		driver.findElement(By.id("password")).clear();
		Breath.breath();
		driver.findElement(By.id("password")).sendKeys(client.getAaPassword());
		Breath.breath();
		driver.findElement(By.id("login-btn")).click();

		// Breath.deepBreath();
		Breath.breath();
		if (!verifyLocation("//p[@id='breadcrumb']", "breakdown services, ltd")) {
			Logging.slog("Can't login.");
			throw new Exception();
		}
		Logging.log('c');
	}

	public void handleRegion(int region) throws Throwable {
		String regionUrl = (new String(XpathBuilder.urlAABreakdownAndRegion())).concat(String.valueOf(region));
		driver.get(regionUrl);
		Breath.breath();
		String tag = new String(driver.findElement(By.xpath("//p[@id='breadcrumb']")).getText());
		if (!verifyLocation("//p[@id='breadcrumb']",
				(new String("home / breakdowns / ").concat(ClientsMngt.intToRegion(region))))) {
			Logging.slog("Can't find region ");
			return;
		}
		Logging.slog((new String("Region ").concat(ClientsMngt.intToRegion(region))));
		updateLastInterNow(client.getActorId());
		Breath.breath();
		int productionRow = 0;
		boolean nextRowHasAnotherProd = true;
		 

		// we only consider here the first page of productions. So in the future
		// add an option to nagivate to page 2 and 3
		while ((productionRow < ClientsMngt.onlyTopProd) && (nextRowHasAnotherProd)) {
			Logging.slog("Checking for red check at row number: " + productionRow);
			try {
				if (ManageDriver.isElementPresent(driver, By.xpath(XpathBuilder.tabProductionInRow(productionRow)))) {
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
				if (ManageDriver.isElementPresent(driver, By.xpath(XpathBuilder.tabRedCheckBoxPos(productionRow)))) {
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
			offer = new Job(client.getActorId());
			// some offers appear in several different regions but reffer to the
			// same role
			offer.setRegion(region);
			Scapper.parseRowOfferAA(offer, productionRow);
			if (offer.offerHasBeenConsideredBeforeAA(Jobs)) {
				productionRow++;
				continue;
			}

			try {
				driver.findElement(By.xpath(XpathBuilder.xpLinkCharactersInProduction(productionRow))).click();
			} catch (Exception e) {
				Logging.slog(
						(new String("Error: the link hasn't opened on row: ").concat(String.valueOf(productionRow))));
				continue;
			}
			Breath.breath();

			try {
				if (ManageDriver.isElementPresent(driver, By.xpath(XpathBuilder.tabCharNameAndDetails(0)))) {
					Logging.slog("Success. We are now in characters table.");
				} else {
					Logging.slog("Error. We are not in the characters chart now. Lets return");
					driver.navigate().back();
					Breath.breath();
				}

			} catch (Exception e) {
				Logging.slog("Error. shouldnlt reach this line. DEBUG");
				driver.navigate().back();
				Breath.breath();
			}
			int foundCharactersInThisProduction = totalOffersInThisProd(offer);

			// move back to window with char of productions
			Logging.slog((new String("Number of Characters found in this production: "))
					.concat(String.valueOf(foundCharactersInThisProduction)));
			Logging.slog(
					(new String("Characters added to the cart: ").concat(String.valueOf(Job.isThereSomethingInCart))));
			Breath.silentCount();
			if ((Job.isThereSomethingInCart) && (ClientsMngt.autoSubmitCart)) {
				if (submitCart()) {

					Logging.printSubmittions(Jobs);
					Breath.deepBreath();
					driver.navigate().back();
					for (int i = 0; i < 3; i++) {
						Breath.deepBreath();
					}
				} else {
					Logging.slog("Didn't submit cart");

				}

				driver.navigate().back();
				for (int i = 0; i < 3; i++) {
					Breath.deepBreath();
				}
			}
			driver.navigate().back();
			Breath.breath();
			productionRow++;
		} // end of while loop
	}

	public void coreActorsAccess() throws Throwable {
		// go over the chosen regions and submit to each region
		Breath.makeZeroSilentCounter();

		while (ClientsMngt.runStatus()) {
			if (ManageDriver.nowIsNightTime()) {
				// we will sleep
				Breath.nap();
			}
			if ((runStatus) && (ClientsMngt.reloadTargetRegions(client)) && (client.atLeastSomeRegionChoosen())) {
				for (int regionNum = 0; regionNum < 15; regionNum++) {
					if (client.getTargetRegions()[regionNum]) {
						handleRegion(regionNum);
						Breath.nap();
					}
				}
			}
			Breath.silentCount();
		}
	}

	public void loginCN() throws Throwable {
		cnBaseUrl = "http://home.castingnetworks.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		Breath.makeZeroSilentCounter();
		Logging.slog("LOGIN-CN");
		Logging.slog(new String("Logining in username: ").concat(client.getCnUsername()).concat(", ActorID: ")
				.concat(client.getActorId()));
		seekBackgroundWork = true;
		Logging.slog("A: Window handle Parent " + parentWindowHandler);
		driver.get(cnBaseUrl + "/");
		Breath.deepBreath();
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(client.getCnUsername());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(client.getCnPassword());
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		Breath.breath();
		// debug - this is just for My agent :
		// driver.findElement(By.id("_ctl0_cphBody_rptProfiles__ctl1_lnkViewProfile2")).click();
		// check for welcome window:
		if (!verifyLocation(XpathBuilder.xpCNWelcomeHeader(), "Welcome")) {
			Logging.slog("Can't find Welcome Page");
			return;
		}
		Logging.log('c');
		Breath.breath();
	}

	public void coreCastingNetworks() throws Throwable {
		Logging.slog("coreCastingNetworks");
		// first time in coreLoop - always begin with Extra chart
		driver.findElement(By.xpath("//a[@id='_ctl0_cphBody_lnkExtrasRoles']")).click();
		Breath.deepBreath();
		if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
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
				driver.navigate().refresh();
			}

			if (offerType.equals("principle")) {
				seekBackgroundWork = false;
				Logging.slog("Round again to PRINCIPLE work");
				driver.navigate().refresh();
			}
			// if (ClientsMngt.runStatus()) {
			if (runStatus) {

				heartLoop();
				while (ManageDriver.nowIsNightTime()) {
					Breath.nap();
				}
			}
			// }

			Breath.nap();
		}
	}

	public void heartLoop() throws Throwable {
		String originWindow = driver.getWindowHandle();

		if (seekBackgroundWork) {
			if (!verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Extras")) {
				driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				// debug
				Breath.deepBreath();
				if (!verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Extras")) {
					Logging.slog("Can't find Extras chart");
					throw new Exception();
				}
			}
		} else {
			// We want to be in principle chart
			if (!verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Principals")) {
				driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				Breath.deepBreath();
				if (!verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Principals")) {
					Logging.slog("Can't find principle chart");
					throw new Exception();
				}

			}
		}
		new Select(driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
		Breath.deepBreath();
		for (int rowNum = 0; rowNum < ClientsMngt.CN_DEFAULT_PROD_MAX_ROWS; rowNum++) {
			Logging.slog("Checking for green star at row number: " + rowNum);
			int trStarRow = (3 * rowNum);
			trStarRow += 4;
			String starPos = XpathBuilder.xpCNStarPositionBG(trStarRow);
			String srcOfImg = "";
			try {
				srcOfImg = new String(driver.findElement(By.xpath(starPos)).getAttribute("src"));
			} catch (Error e) {
				Logging.slog(e.getMessage());
				verificationErrors.append(e.toString());
			}
			if (srcOfImg.contains("spacer.gif")) {
				Logging.slog("No star on offer " + rowNum + " from top.  Let's try submitting.");
				offer = new Job(client.getActorId());

				Scapper.handleBackgroundWorkOffer(offer, seekBackgroundWork, (trStarRow - 1));
				if (offer.offerHasBeenConsideredBeforeCN(Jobs)) {
					continue;
				}
				Jobs.add(offer);
				// debug
				Breath.silentCount();

				Esl.readNotice(client, offer);
				offer.genderMatchingUpdate(client);
				offer.unionMatchingUpdate(client);
				offer.makeDecisionCN();

				if ((offer.getHasBeenSubmitted()) || (!offer.getDecisionSubmit())) {
					Logging.printDecisionMakingVars(offer);
					continue;
				}

				Logging.log('h');
				Esl.fillTalentNoteCN(client, offer);
				int trLinkToOfferRow = -1;
				trLinkToOfferRow = trStarRow - 1;
				String linkOfferPos = ((new String("//tr[")).concat(String.valueOf(trLinkToOfferRow))).concat("]/td/a");
				driver.findElement(By.xpath(linkOfferPos)).click();
				Breath.deepBreath();
				driver.switchTo().window(ManageDriver.getSonWindowHandler(originWindow));
				// add time of apperance to offer
				try {
					Breath.breath();
					offer.setOfferTimeRoleAdded(
							new String(driver.findElement(By.xpath("//table[5]/tbody/tr[3]/td")).getText()));
				} catch (Exception e) {
					offer.setOfferTimeRoleAdded(new String(""));
				}
				/*
				 * try{ driver.findElement(By.cssSelector(XpathBuilder.
				 * cssFindSubmitLink())).click();} catch(Exception e){}
				 */

				Breath.deepBreath();
				try {
					driver.findElement(By.xpath(XpathBuilder.xpFindSubmitLink())).click();
				} catch (Exception e) {
				}
				Breath.deepBreath();

				Logging.slog((new String("Green on ").concat(offer.getOfferCharacterName())));
				Breath.breathToMissleadThem();
				if (!verifyLocation("//span", "Customize your submission")) {
					Logging.slog("Error: You are on wrong window");
					ManageDriver.windowStatus(originWindow);
					throw new Exception();
				}
				Logging.log('l');

				// choosePhoto(client, offer);

				driver.findElement(By.id("TALENTNOTE")).clear();

				driver.findElement(By.id("TALENTNOTE")).sendKeys(offer.getMessage());
				Breath.deepBreath();
				driver.findElement(By.cssSelector(XpathBuilder.cssCMSubmitButton())).click();
				Breath.deepBreath();
				if (!verifyLocation("//span", "Submission Successful")) {
					Logging.slog("Did NOT recieve final submittion successful");
					ManageDriver.windowStatus(originWindow);
					throw new Exception();
				}
				if (!ManageDriver.killSubWindowAndMoveToParentWindow(parentWindowHandler)) {
					Logging.slog("Memory leak error: failed killing child window");
					throw new Exception();
				}
				offer.setHasBeenSubmitted(true);
				Breath.makeZeroSilentCounter();
				Logging.log('m');
				Logging.printSubmittions(Jobs);
				writeSubmittionToDB(offer);
			} else {
				Logging.slog("Found star on the offer " + rowNum + " from top");
				Breath.silentCount();
			}
		}
	}

	public boolean submitCart() {
		// goes to cart page and clicks submit
		// verify current page
		try {
			Breath.breath();

			// check that the cart isn't empty

			driver.findElement(By.xpath(XpathBuilder.xpCartLogo())).click();
			Breath.deepBreath();
			if (verifyLocation(XpathBuilder.xpCartIsEmpty(), "Empty")) {
				Logging.slog("Opps the cart was actually empty.");
				Job.isThereSomethingInCart = false;
				return false;
			}

			driver.findElement(By.xpath(XpathBuilder.xpSubmitCart())).click();
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

	public void choosePhoto(Actor human, Job offer) {

		int photoChoice;
		if (human.getDefaultPhoto().length() < 1) {
			Logging.slog("Error : data is not a number. Choosing the first photo as defualt");
			photoChoice = 0;
		} else {
			photoChoice = ClientsMngt.currentPhotoChoice(human.getDefaultPhoto(), offer);
			Integer.parseInt(human.getDefaultPhoto());
		}
		driver.findElement(By.xpath(XpathBuilder.xpCNChoosePhoto(photoChoice))).click();
	}

	public void killFirefoxAndOpenNew() {
		WebDriver tempDriver = driver;
		driver = new FirefoxDriver();
		tempDriver.quit();
	}

	public void aaDecideToSubmit() {

	}

	private int totalOffersInThisProd(Job parentOffer) {
		// returns the number of offers created and added to Jobs list from the
		// found characters on the production
		Logging.slog("Entered character breakdown");
		// for each character - we open a new offer
		String nameOfCharacterAndDetailsUnder;
		String detailsOfCharacter;

		try {
			String prodDetailsLeftWithTimeRoleAdded = new String(
					driver.findElement(By.xpath(XpathBuilder.xpProdDetailsLeftWithTimeRoleAdded())).getText());
			Logging.slog((new String("prodDetailsLeftWithTimeRoleAdded=")).concat(prodDetailsLeftWithTimeRoleAdded));
			Esl.parseProdDetailsLeftWithTimeRoleAdded(parentOffer, prodDetailsLeftWithTimeRoleAdded);
		} catch (Exception e) {
			Logging.slog(e.getMessage());
			return 0;

		}

		try {

			String prodDetialsRight = new String(
					driver.findElement(By.xpath(XpathBuilder.xpProdDetialsRight())).getText());
			Esl.parseProdDetialsRight(parentOffer, prodDetialsRight);

			Logging.slog((new String("prodDetailsRight:\n ")).concat(prodDetialsRight));
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
				nameOfCharacterandDetails = findCharacterOnRow(charNum);

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
				Esl.readNoticeAA(client, currentOffer);
				currentOffer.genderMatchingUpdate(client);
				currentOffer.ethnicityMatchingUpdate(client);
				currentOffer.unionMatchingUpdate(client);
				currentOffer.makeDecisionAA();
				Jobs.add(currentOffer);
				if ((currentOffer.getHasBeenSubmitted()) || (!currentOffer.getDecisionSubmit())) {
					Logging.printDecisionMakingVars(currentOffer);

					currentOffer = Job.renewOffer(currentOffer);
					charNum++;
					continue;
				}

				Esl.fillTalentNoteAA(client, currentOffer);
				ManageDriver.windowStatus2();
				Logging.slog("lets submit!");
				driver.findElement(By.xpath(XpathBuilder.xpCharacterLinkInCharactersPage(charNum))).click();
				Breath.breath();
				ManageDriver.windowStatus2();

				Logging.slog(driver.getCurrentUrl());
				driver.switchTo().window(ManageDriver.getSonWindowHandler(driver.getWindowHandle()));
				Logging.slog(driver.getCurrentUrl());

				// verify
				String ChoosingPhotoUrl = driver.getCurrentUrl();
				if (!ChoosingPhotoUrl.contains(currentOffer.getInternalAAname())) {
					Logging.slog(new String(("Error: the choosing window didn't open for AA internal role number:"))
							.concat(currentOffer.getInternalAAname()));
					driver.navigate().back();
					charNum++;

					currentOffer = Job.renewOffer(currentOffer);

					continue;
				}

				choosePhotosAndSubmit(currentOffer);
				currentOffer.calcTimeFromAddedToSubmitted();
				driver.switchTo().window(parentWindowHandler);

				// check if there is another character to be considered in the
				// next row
				if (verifyLocation(XpathBuilder.xpBetaCharacterName(charNum + 1), "")) {
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

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private void assertiveClicking(String[] optionStrings) {
		// works only with xPath links - sorry!
		for (int i = 0; i < optionStrings.length; ++i) {
			try {
				driver.findElement(By.xpath(optionStrings[i])).click();
				Logging.slog("This Worked!!");
				Logging.slog(optionStrings[i]);

			} catch (Exception e) {
				Logging.slog(optionStrings[i]);
				Logging.slog(e.getMessage());

			}
		}
		driver.switchTo().parentFrame();
	}

	public void choosePhotosAndSubmit(Job currentOffer) {
		try {

			driver.switchTo().defaultContent();
			Breath.breath();
			driver.switchTo().frame("main_window");
			Breath.breathToMissleadThem();
			// driver.findElement(By.xpath(XpathBuilder.xpChooseMySmilePhoto())).click();
			driver.findElement(By.xpath(XpathBuilder.xpChooseMySeriousPhoto())).click();

			Breath.breath();
			driver.findElement(By.xpath(XpathBuilder.xpChooseCommercialVideo2())).click();
			Breath.breath();
			driver.findElement(By.xpath(XpathBuilder.xpChooseBookstoreVideo1())).click();
			Breath.breath();
			if (!(driver.findElement(By.xpath(XpathBuilder.xpIncludeSizes())).isSelected())) {
				Breath.breath();
				driver.findElement(By.xpath(XpathBuilder.xpIncludeSizes())).click();
			}
			Breath.breath();

			if (tryToClearTalentNotes()) {

				Breath.breath();
				driver.findElement(By.xpath(XpathBuilder.xpTalentNotesAA())).sendKeys(currentOffer.getMessage());
			}
			Breath.breath();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("buttons");
			Breath.breath();
			driver.findElement(By.xpath(XpathBuilder.xpAddToCartAA())).click();
			// currentOffer.setPutInCart();
			currentOffer.setHasBeenSubmitted(true);
			Job.isThereSomethingInCart = true;
			writeSubmittionToDB(currentOffer);
			Logging.printDecisionMakingVars(currentOffer);

			//
		} catch (Exception e) {
			Logging.slog("Failed to submit it.");
			Logging.slog(e.getMessage());
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	private boolean verifyLocation(String xpathTab, String verifyText) {
		// returns true only if the location of the xpath contains the
		// verifyText
		try {
			String locationTest1 = new String(driver.findElement(By.xpath(xpathTab)).getText());
			if ((locationTest1.contains(verifyText))) {
				return true;
			}
		} catch (Exception e) {
			if (verifyText.length() > 1) {
				// this is an acutal text to be found. more than ""
				Logging.slog("Verify text " + verifyText + " Does NOT appear.");
			}
			return false;
		}
		return false;
	}

	static public boolean networkWorking() {
		// returns true if there is a network connection

		return true;
	}

	/*
	 * 
	 * public int countOffersInCart(List<Job> allJobs) { int inCart = 0; for
	 * (Job offer : allJobs) { if (offer.getPutInCart()) { inCart++; } } return
	 * inCart; }
	 * 
	 * public void emptyCart(List<Job> allJobs) {
	 * 
	 * for (Job offer : allJobs) { offer.takeOutOfCart(); }
	 * 
	 * }
	 */

	static public boolean tryToClearTalentNotes() {
		try {
			driver.findElement(By.xpath(XpathBuilder.xpTalentNotesAA())).clear();
			return true;
		} catch (Exception e) {
			// no talent notes
			return false;
		}
	}

	static public String findCharacterOnRow(int charNum) {

		try {
			return (new String(driver.findElement(By.xpath(XpathBuilder.tabCharNameAndDetails(charNum))).getText()));
		} catch (Exception e) {
			return "";
		}

	}

	public void writeSubmittionToDB(Job offer) {
		try {

			String submission_text = new String("* Actor:" + offer.getActorIDSubmitted() + "|Region:"
					+ offer.getRegion() + "|Offer:" + offer.getOfferId() + "|Background:" + offer.getIsBackgroundWork()
					+ "|Role added:" + offer.getOfferTimeRoleAdded() + "|Submittion time:"
					+ offer.getOfferSubmittionDateTime() + "|Shoot date:" + offer.getOfferShootDate() + "|age:"
					+ offer.getIsAge() + "|internal_AA_name:" + offer.getInternalAAname() + "|EthMatch:"
					+ offer.getIsEthnicityMatch() + "|GenderOfCharacter:" + offer.getCharacterGender() + "|GenderMatch:"
					+ offer.getIsGenderMatch() + "|Union:" + offer.getCharacterUnionDemand() + "|Guard:"
					+ offer.getIsGuard() + "|Tux:" + offer.getNeedTuxedo() + "|Uni:" + offer.getNeedPoiceUniform()
					+ "|Type:" + offer.getOfferTypeProject() + "|ReqSizes:" + offer.getReqSizes() + "|Paying:"
					+ offer.getOfferPaying() + "|Rate:" + offer.getOffertRate() + "|Name:" + offer.getOfferProjectName()
					+ "|Role:" + offer.getOfferRole() + "|Offer Listing:" + offer.getOfferListing()
					+ " |  Talent Notes filled with:" + offer.getMessage());

			int actor_id = Integer.parseInt(offer.getActorIDSubmitted());
			// long offer_id = Integer.parseInt(offer.getOfferId());
			int last_ID6digits = Integer.parseInt((new String(offer.getOfferId())).substring(4));
			Db.temp_sub(last_ID6digits, actor_id, cleanString(submission_text));
		} catch (Exception e) {
			Logging.slog(new String("Error writing the submission to DB"));
			Logging.slog(new String("Number of offer Id that was NOT written is: ").concat(offer.getOfferId()));
		}
	}

	public static String cleanString(String data) {
		String cleanedString3 = "";
		try {
			String cleanedString = new String(data.replace((char) 39, ' '));
			String cleanedString2 = new String(cleanedString.replace((char) 34, ' '));
			cleanedString3 = new String(cleanedString2.replace((char) 47, '-'));
		} catch (Exception e) {
			Logging.slog(new String("Error cleaning String ").concat(data));
			return data;
		}

		return cleanedString3;
	}

	public static void updateLastInterNow(String actor_id) {
		try {
			String currentNYTime = new String(ManageDriver.findNYTimeNow());

			Db.updateLastInteraction(actor_id, currentNYTime);
		} catch (Exception e) {
			Logging.slog("Error updating last interaction now");
		}
	}

}
