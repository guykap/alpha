package Alpha;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.sql.Timestamp;
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
private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	static public List<Job> Jobs = new ArrayList<Job>();
	static Iterator<Job> jobIterator = Jobs.iterator();
	static public Job offer;

	
	public static boolean isCastingNetworks = true;
	
	public static boolean runStatus;
	
	static boolean isTargetRegion[];
	
	static public boolean longNaps = false;
	static String gecko_driver_path;
	static int loginCounter;
	static Breath takeBreath;
	static Logging bestLog;
	static Actor[] cast;

	

	public static void main(String[] args) throws Throwable {
		System.out.println("Hello");
		Db.setDBName("juliette.climy7kqhhvl.us-east-1.rds.amazonaws.com");

		if (args.length < 1) {
			System.out.println(
					"Need to add the DB on aws IP or name.So we will use the DB Juliette from January 2017 :)");
			Db.setDBName("juliette.climy7kqhhvl.us-east-1.rds.amazonaws.com");
		}

 
		try{
		while (!ClientsMngt.getLastRunningVars()) {
			Breath.powerNap();
		}
		}catch(Exception e){
			System.out.println("Error loading running vars DB");
			return;
		}
		
		try{
			updateLastInterNow(String.valueOf(ClientsMngt.config_id),String.valueOf(ClientsMngt.client_id));
			}catch(Exception e){
				System.out.println("Error updating last inter");
				 
			}
		
		if (!Db.getClientFromDB(ClientsMngt.client_id)) {
			System.out.println("Error loading client from DB");
			return;
		}
		
		
		// setJsonFilePath(args[0]);
		// ClientsMngt.loadRunningVarsFile();
		String fileoutLogs = new String(ClientsMngt.getOutLogsPath());
		String appendixFileName = (new String((new Long(System.currentTimeMillis())).toString())).concat(".txt");
		Logging.initLogging(new String(fileoutLogs).concat(appendixFileName));
		System.setProperty("webdriver.gecko.driver",
				(new String(ClientsMngt.getGecko_driver_path())).concat("geckodriver.exe"));

		// ClientsMngt.loadClientsFromFile();
		
		Logging.printAllRunningVars();
	
		if (!runStatus) {
			return;
			
		}
		CnBooking.seekBackgroundWork = true;
		JUnitCore jCore;
		try {
			Beta test = new Beta();
			if (ClientsMngt.site == 1) {
				test.testBetaCN();
			} else if (ClientsMngt.site == 0) {
				test.testBetaAA();
			}else if (ClientsMngt.site == 3){
				test.testBetaBS();
			}else{
				Logging.slog("Error loading corrent site");
				return;
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

	public void testBetaBS() throws Throwable {
		Logging.slog("Backstage");
		ManageDriver.logMyInternalIP();
		ManageDriver.logMyExternalIP();
		testBetaB();
	}

	public void testBetaAA() throws Throwable {
		Logging.slog("Actors Access");
		ManageDriver.logMyInternalIP();
		ManageDriver.logMyExternalIP();
		testBetaB();
	}

	@Test
	public void testBetaCN() throws Throwable {
		Logging.slog("Casting Networks");
		if(ClientsMngt.site)
		ManageDriver.logMyInternalIP();
		ManageDriver.logMyExternalIP();
		testBetaB();
	}

	public void testBetaB() throws Throwable {

		try {
			ManageDriver.driver = new FirefoxDriver();
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
				ManageDriver.killFirefoxAndOpenNew();
			}

			if ((loginCounter % 7) == 0) {
				Logging.slog("THIS IS a 7th LOGIN - kill gecko and close window as well ");
				if (ManageDriver.killGecko()) {
					System.setProperty("webdriver.gecko.driver",
							(new String(gecko_driver_path)).concat("geckodriver.exe"));
					ManageDriver.killFirefoxAndOpenNew();
					loginCounter++;
					continue;
				}

			}
			try {
				if (ClientsMngt.site ==1) {
					CnBooking.seekBackgroundWork = true;
					CnBooking.loginCN();
				} else if (ClientsMngt.site ==0) {
					AaBooking.loginAA();
				}else if (ClientsMngt.site ==3){
					BsBooking.loginBS();
				}
			} catch (Exception e) {
				Logging.slog(e.getMessage());
				Logging.slog("Something went during login -> So lets login again");
				loginCounter++;
				continue;
			}

			try {
				if (ClientsMngt.site ==1) {
					CnBooking.coreCastingNetworks();
				} else if (ClientsMngt.site ==0) {
					AaBooking.coreActorsAccess();
					AaBooking.logutAA();
				}else if (ClientsMngt.site ==3){
					BsBooking.coreBackstage();
					BsBooking.logoutBS();
				}
				
			} catch (Exception e) {
				Logging.slog(e.getMessage());
				Logging.slog("Something went wrong -> Back to Login");
				loginCounter++;
			}
		}
	}

	

	

	@After
	public void tearDown() throws Exception {
		ManageDriver.driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private void assertiveClicking(String[] optionStrings) {
		// works only with xPath links - sorry!
		for (int i = 0; i < optionStrings.length; ++i) {
			try {
				ManageDriver.driver.findElement(By.xpath(optionStrings[i])).click();
				Logging.slog("This Worked!!");
				Logging.slog(optionStrings[i]);

			} catch (Exception e) {
				Logging.slog(optionStrings[i]);
				Logging.slog(e.getMessage());

			}
		}
		ManageDriver.driver.switchTo().parentFrame();
	}

	

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	private boolean isElementPresent(By by) {
		try {
			ManageDriver.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			ManageDriver.driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = ManageDriver.driver.switchTo().alert();
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

	public static  boolean verifyLocation(String xpathTab, String verifyText) {
		// returns true only if the location of the xpath contains the
		// verifyText
		try {
			
			
			String locationTest1 = new String(ManageDriver.driver.findElement(By.xpath(xpathTab)).getText());
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

	
	static public boolean tryToClearTalentNotes() {
		try {
			ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpTalentNotesAA())).clear();
			return true;
		} catch (Exception e) {
			// no talent notes
			return false;
		}
	}

	static public String findCharacterOnRow(int charNum) {

		try {
			return (new String(ManageDriver.driver.findElement(By.xpath(XpathBuilder.tabCharNameAndDetails(charNum))).getText()));
		} catch (Exception e) {
			return "";
		}

	}

	static public void writeSubmittionToDB(Job offer) {
		try {

			
			Timestamp nowTime=new Timestamp(0);
			try{
				nowTime= ManageDriver.findNYTimeNow();
				}catch(Exception e){};
			String submission_text = new String("* Actor:" + offer.getActorIDSubmitted() + "|SubmittionTime:" + nowTime.toString() +"|Region:"
					+ offer.getRegion() + "|Offer:" + offer.getOfferId() + "|Background:" + offer.getIsBackgroundWork()
					+ "|Role added:" + offer.getOfferTimeRoleAdded() + "|Submittion time:"
					+ offer.getOfferSubmittionDateTime() + "|Found on row:" + offer.foundOnRow  + "|Shoot date:" + offer.getOfferShootDate() + "|age:"
					+ offer.getIsAge() + "|internal_AA_name:" + offer.getInternalAAname() + "|EthMatch:"
					+ offer.getIsEthnicityMatch() + "|GenderOfCharacter:" + offer.getCharacterGender() + "|GenderMatch:"
					+ offer.getIsGenderMatch() + "|Union:" + offer.getCharacterUnionDemand() + "|Guard:"
					+ offer.getIsGuard() + "|Tux:" + offer.getNeedTuxedo() + "|Uni:" + offer.getNeedPoiceUniform()
					+ "|Type:" + offer.getOfferTypeProject() + "|ReqSizes:" + offer.getReqSizes() + "|Paying:"
					+ offer.getOfferPaying() + "|Rate:" + offer.getOffertRate() + "|Name:" + offer.getOfferProjectName()
					+ "|Role:" + offer.getOfferRole() + "|Offer Listing:" + offer.getOfferListing()	+ "|  Talent Notes filled with:" + offer.getMessage());

			int actor_id = Integer.parseInt(offer.getActorIDSubmitted());
			// long offer_id = Integer.parseInt(offer.getOfferId());
			int last_ID6digits = Integer.parseInt((new String(offer.getOfferId())).substring(4));
//			Db.temp_sub(last_ID6digits, actor_id, cleanString(submission_text));
			
			String aa_internal = "";
			String time_submitted ="";
			String time_role_appeared="";
			String site ="";
			String region ="";
			String background="";
			String shoot_date="";
			String type = "";
			String rate = "";
			String union_status="";
			String production_name="";
			String production_details="";
			String location = "";
			String casting_director="";
			String character_details="";
			String talent_notes_filled="";
			String ip_origin_submitted=""; 
			
			
			//fill some data
		 
		 
			try{aa_internal = new String(offer.getInternalAAname());	}catch(Exception e){}
			try{time_submitted= new String( nowTime.toString());}catch(Exception e){}
			try{time_role_appeared=new String(  cleanString(offer.getOfferTimeRoleAdded()));}catch(Exception e){}
			try{
				if(isCastingNetworks)
					{site = new String("CN");}
				else{site = new String("AA");}
			}catch(Exception e){}
			try{region = new String(  String.valueOf(offer.getRegion()));}catch(Exception e){}
			try{background=new String( String.valueOf(offer.getIsBackgroundWork()));}catch(Exception e){}
			try{shoot_date= new String( cleanString(offer.getOfferShootDate()));}catch(Exception e){}
			try{type = new String( offer.getOfferTypeProject());}catch(Exception e){}
			try{rate = new String( cleanString(offer.getOffertRate()) );}catch(Exception e){}
			try{
				if(isCastingNetworks){
							union_status=new String( cleanString(String.valueOf(offer.getOfferUnionStatus())));
					}else{
							union_status=new String( cleanString( offer.getOfferUnionStatus()) );
					}
			}catch(Exception e){}
			try{production_name=new String( cleanString(offer.getOfferProjectName()));}catch(Exception e){}
		//try{production_details=new String(cleanString(offer.getOfferListing()));}catch(Exception e){}
		//	try{production_details=new String(cleanString(offer.getProductionDetails()));}catch(Exception e){}
			try{production_details=new String((cleanString(offer.getProductionDetails())).concat(cleanString(submission_text)));}catch(Exception e){}
			
			try{location = new String( cleanString(offer.getOfferLocation()));}catch(Exception e){}
			try{casting_director=new String( offer.getOfferCastingDirector());}catch(Exception e){}
		
			
			try{
			if(isCastingNetworks){
					character_details=new String( cleanString( offer.getOfferRole()) );
					}else{
					character_details=new String( cleanString( offer.getOfferCharacterDetails()) );
					}
				}catch(Exception e){}
			
			try{talent_notes_filled=new String(cleanString( offer.getMessage()));}catch(Exception e){}
			
			
			
			try{ip_origin_submitted = new String(ManageDriver.getMyExternalIP());}catch(Exception e){}
			Db.submittion(last_ID6digits, actor_id, aa_internal, time_submitted, time_role_appeared, site, region, background, shoot_date, type, rate, union_status, production_name, production_details, location, casting_director, character_details, talent_notes_filled, ip_origin_submitted);
		} catch (Exception e) {
			Logging.slog(new String("Error writing the submission to DB"));
			Logging.slog(new String("Number of offer Id that was NOT written is: ").concat(offer.getOfferId()));
		}
	}

	public static String cleanString(String data) {
		String cleanedString5 = "";
		try {
			int i =8;
			String cleanedString = new String(data.replace((char) 39, ' '));
			String cleanedString2 = new String(cleanedString.replace((char) 34, ' '));
			String cleanedString3 = new String(cleanedString2.replace((char) 47, '-'));
			String cleanedString4 = new String(cleanedString3.replaceAll("\n","-"));
			 cleanedString5 = new String(cleanedString4.replace((char) 92, '-'));
		} catch (Exception e) {
			Logging.slog(new String("Error cleaning String ").concat(data));
			return data;
		}

		return cleanedString5;
	}

	public static void updateLastInterNow(String config_id,String actor_id) {
		try {
			String currentNYTime = (ManageDriver.findNYTimeNow()).toString();
		//	Timestamp currentNYTime = new Timestamp();

			Db.updateLastInteraction(config_id,actor_id, currentNYTime);
		} catch (Exception e) {
			Logging.slog("Error updating last interaction now");
		}
	}

}
