package Alpha;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

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
		Breath.deepBreath();
		ManageDriver.driver.findElement(By.linkText("LOG IN")).click();
		 Breath.breath();
		 ManageDriver.driver.findElement(By.id("id_username")).clear();
		 Breath.breath();
		 ManageDriver.driver.findElement(By.id("id_username")).sendKeys("g.kapulnik@gmail.com");
		    Breath.breath();
		    ManageDriver.driver.findElement(By.id("id_password")).clear();
		    Breath.breath();
		    ManageDriver.driver.findElement(By.id("id_password")).sendKeys("bGuy1234567");
		    Breath.breath();
		    ManageDriver.driver.findElement(By.xpath("//input[@value='Log In']")).click();
		    
		
		  		if (!Beta.verifyLocation(".//*[@id='accountLabel']", "My Account")) {
			Logging.slog("Can't login.");
			throw new Exception();
		}
		Logging.log('c');
	}
	
	static public void logoutBS() throws Throwable {

		Logging.slog((new String("Logging out  ")));
		return;
	}
	
	
}
