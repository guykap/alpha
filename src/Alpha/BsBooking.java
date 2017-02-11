package Alpha;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.mysql.jdbc.Driver;

public class BsBooking {
	static public String bsBaseUrl;

	static public void loginBS() throws Throwable {
		bsBaseUrl = "http://www.backstage.com/";
		ManageDriver.driver.manage().timeouts().implicitlyWait(Breath.geckoWaitTime, TimeUnit.SECONDS);
		ManageDriver.parentWindowHandler = ManageDriver.driver.getWindowHandle();
		Logging.slog("LOGIN-BS");
		Breath.makeZeroSilentCounter();
		// Logging.sLogging.log('a');
		Logging.slog("Window handle Parent " + ManageDriver.parentWindowHandler);
		Logging.slog(new String("Logining in username: ").concat(ClientsMngt.client.getAaUsername()));
		Breath.deepBreath();
		ManageDriver.driver.get(bsBaseUrl + "/");
		Breath.deepBreath();
		ManageDriver.driver.findElement(By.linkText("LOG IN")).click();
		Breath.breath();
		ManageDriver.driver.findElement(By.id("id_username")).clear();

		ManageDriver.driver.findElement(By.id("id_username")).sendKeys("g.kapulnik@gmail.com");
		Breath.breath();
		ManageDriver.driver.findElement(By.id("id_password")).clear();

		ManageDriver.driver.findElement(By.id("id_password")).sendKeys("bGuy1234567");
		Breath.breath();
		ManageDriver.driver.findElement(By.xpath("//input[@value='Log In']")).click();
		Breath.breath();
		/*
		 * if (!Beta.verifyLocation(
		 * ".//*[@id='bs-example-navbar-collapse-1']/div/div[2]/a",
		 * "Find Jobs")) { Logging.slog("Can't login."); throw new Exception();}
		 */

		Logging.log('c');
	}

static public void coreBackstage(){
	try{
	//click casting calls
		int i = 10;
	
		Breath.breath();
		//ManageDriver.driver.findElement(By.xpath(".//*[@id='bs-example-navbar-collapse-1']/ul/li[1]/a")).click();
		ManageDriver.driver.get(bsBaseUrl + "/casting/");
		Breath.breath();
	
	
	//click NY-search
	ManageDriver.driver.findElement(By.xpath(".//*[@id='main__container']/div/div[2]/div[2]/div/div/div/div/div[2]/a")).click();
	Breath.breath();
	
	//verify that I'm on correct page
	
	
	}catch(Exception e){
		Logging.slog("error.");
	}
	int prodRow = 0;
	boolean moreProdAvail = true;
	while ((prodRow < ClientsMngt.onlyTopProd)) {
		try {
		if (ManageDriver.isElementPresent(ManageDriver.driver, By.xpath(XpathBuilder.xpBStabProductionInRow(prodRow)))) {
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
		//get tabs into labellist
		try{
			Beta.offer = new Job(ClientsMngt.client.getActorId());
			Scapper.parseRowOfferBS(Beta.offer, prodRow);
			if (Beta.offer.offerHasBeenConsideredBeforeAA(Beta.Jobs)) {
				prodRow++;
				continue;
			}
		
		//lets open the production page
		
			Beta.offer.foundOnRow = prodRow;
			
			try {
				ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSLinkCharactersInProduction(prodRow))).click();
			} catch (Exception e) {
				Logging.slog(
						(new String("Error: the link hasn't opened on row: ").concat(String.valueOf(prodRow))));
				continue;
			}
			Breath.breath();

			try {
				if (ManageDriver.isElementPresent(ManageDriver.driver, By.xpath(XpathBuilder.xpBSOpennedProductionPage(prodRow)))) {
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
			int foundCharactersInThisProduction = totalOffersInThisProd(Beta.offer);

			// move back to window with char of productions
			Logging.slog((new String("Number of Characters found in this production: "))
								.concat(String.valueOf(foundCharactersInThisProduction)));
						
			//if this is a new offer - then click on the production name tab and collect the different offers for this production
			
			 
		 
		//if decision submit{
	
				//click title
		//read all the details about roles and save to DB.
		
		//if there is a Apply button for a role - click it
		
		//fill talent notes
		
		//click APPLY NOW
		
	//	Esl.parseNameOfCharacterAndDetailsUnder(currentOffer, nameOfCharacterandDetails);
			ManageDriver.driver.navigate().back();
			Breath.breath();
			prodRow++;
		}catch(Exception e){
			Logging.slog(new String("Error on row").concat(String.valueOf(prodRow)));
		}
	    }	
	}

	

static public int totalOffersInThisProd(Job parent_offer){
	int i =8;
	Logging.slog("Entered character breakdown");
	// for each character - we open a new offer
	String nameOfCharacterAndDetailsUnder;
	String detailsOfCharacter;
	try{
	Breath.breath();
	 
	Scapper.bsScrapProductionOpenPage(parent_offer);
	
	// begin adding the characters
			Job currentOffer = parent_offer;
			int charNum = 0;
			boolean moreCharsAvil = true;

			while (moreCharsAvil) {

				try {
					Scapper.bsScrapChracterDetails(charNum);
					Esl.readNoticeBS(ClientsMngt.client, currentOffer);
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
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpCharacterLinkInCharactersPage(charNum))).click();
					Breath.breath();
				
					
					currentOffer.calcTimeFromAddedToSubmitted();
					
					
					if (Beta.verifyLocation(XpathBuilder.xpBetaCharacterName(charNum + 1), "")) {
						charNum++;
						moreCharsAvil = true;
	
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
				
				
				
				
				
		//read role
		// add to Jobslist
		// decide whether to submit
		// if true   APPLY for role
		// DB update offer submitted
	}
	
	
	return charNum;
	}catch(Exception e){}
	
	return -1;
	
}

	static public void logoutBS() throws Throwable {

		Logging.slog((new String("Logging out  ")));
		return;
	}

	
}