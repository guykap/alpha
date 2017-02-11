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
		  		if (!Beta.verifyLocation(".//*[@id='bs-example-navbar-collapse-1']/div/div[2]/a", "Find Jobs")) {
			Logging.slog("Can't login.");
			throw new Exception();}
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

	while (moreProdAvail) {
		//get tabs into labellist
		getLabelList(prodRow);
		
		//get production  name
		String prod_name = new String(ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSProductionName(prodRow))).getText());
		 
		//get production details
		//get shooting dates and locations
	 
		//Esl.readOffer()
		
		//make decision
		
		//if decision submit{
	
				//click title
		//read all the details about roles and save to DB.
		
		//if there is a Apply button for a role - click it
		
		//fill talent notes
		
		//click APPLY NOW
		
	//	Esl.parseNameOfCharacterAndDetailsUnder(currentOffer, nameOfCharacterandDetails);
		
	    }	
	}

static private void getLabelList(int prodRow){
  //	.//*[@id='main__container']/div/div[3]/div/div[prodRow]/div[1]/div[1]/div[1]/div//text()
	List<String> labels = new ArrayList<String>();
	String label1="";
	String label2="";
	String temp_label="";
	int labelCount = 0;
	try{
		for(labelCount =1 ; labelCount<10;++labelCount){
			timeSaver();
			temp_label = new String(ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSLabelList(prodRow,labelCount))).getText());
			labels.add(temp_label);
		}
 
	}catch(Exception e){
		Logging.slog(new String(e.getMessage()));
		Logging.slog(new String("No more labels found after: ").concat(String.valueOf(labelCount-1)));
	}	
	 
}
	 
	static public void logoutBS() throws Throwable {

		Logging.slog((new String("Logging out  ")));
		return;
	}
	
	static private void timeSaver(){
		//find all the labels in one line , sum up the labels and compare to the full String and when they equal return true;
		int i =8;
		return;
	}
}
