package Alpha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ManageDriver {

	private static final String processname = "geckodriver.exe";
	private static final String DEFAULT_GECKO_DRIVER_LIBRARY = "C:\\Users\\me\\work\\official\\Julia\\gecko_driver";

	public static boolean killGecko() {
		try {
			Runtime.getRuntime().exec(new String("taskkill /F /IM " + processname));

			return true;
		} catch (Exception e) {
			Logging.slog("Error killing Gecko");
			return false;
		}
	}

	static public boolean moveToOtherWindow(String parentWindow) {
		String newWindowHandler = "";
		windowStatus(parentWindow);
		String currentWindowHandler = Beta.driver.getWindowHandle();
		Beta.handles = Beta.driver.getWindowHandles(); // get all window handles
		if (Beta.handles.size() < 2) {
			Logging.slog("Error: there is only one window : " + currentWindowHandler);
			return false;
		}
		Beta.windowHandlesIterator = Beta.handles.iterator();
		if (Beta.windowHandlesIterator.hasNext()) {
			newWindowHandler = Beta.windowHandlesIterator.next();
			if (!newWindowHandler.equals(currentWindowHandler)) {
				Beta.driver.switchTo().window(newWindowHandler); // switch to
																	// popup
				// window
				windowStatus(parentWindow);
				return true;
			} else {
				// fell on the same window - so move again
				if (Beta.windowHandlesIterator.hasNext()) {
					newWindowHandler = Beta.windowHandlesIterator.next();
					if (!newWindowHandler.equals(currentWindowHandler)) {
						Beta.driver.switchTo().window(newWindowHandler); // switching
						// to
						// popup
						// window
						windowStatus(parentWindow);
						return true;
					}
				}
			}
		}
		return false;
	}

	static public boolean killSubWindowAndMoveToParentWindow(String parentWindow) {
		// returns true onlyon a succesfull kill the sub window and return back
		// to parent window.
		Beta.driver.close();
		Beta.driver.switchTo().window(parentWindow);
		String newWindowHandler = Beta.driver.getWindowHandle();
		Logging.slog("killed window and returned to  " + newWindowHandler);
		windowStatus(parentWindow);
		return true;
	}

	static public void windowStatus(String parentWindow) {
		String currentWindowHandler = Beta.driver.getWindowHandle();
		String sonWindow = getSonWindowHandler(parentWindow);
		Logging.slog("Parent: " + ManageDriver.getParentWindowHandler(parentWindow) + " Son: "
				+ getSonWindowHandler(parentWindow));

		if (getParentWindowHandler(parentWindow).equals(currentWindowHandler)) {
			Logging.slog("Now on PARENT");
		} else {

			Logging.slog("Now on SON");
		}
		// Beta.driver.getWindowHandle();
		Logging.slog("Parent: " + ManageDriver.getParentWindowHandler(parentWindow) + " Son: "
				+ getSonWindowHandler(parentWindow));
		return;
	}

	static public void windowStatus2() {
		Beta.handles = Beta.driver.getWindowHandles(); // get all window handles
		StringBuilder builder = new StringBuilder();
		for (String s : Beta.handles) {
			builder.append(s + ",");
		}
		String allHandles = new String("[");
		allHandles += new String(builder.toString());
		allHandles += new String("] ");
		String currentWindowHandler = Beta.driver.getWindowHandle();
		Logging.slog(allHandles + " on: " + currentWindowHandler);
	}

	static public String getParentWindowHandler(String parentWindow) {
		if (parentWindow.length() > 1) {
			return parentWindow;
		}
		Logging.slog("Error finding Parent holder");
		return ("");
	}

	static public String getSonWindowHandler(String parentWindow) {
		String newWindowHandler;
		String currentWindowHandler = Beta.driver.getWindowHandle();
		Beta.handles = Beta.driver.getWindowHandles(); // get all window handles
		Beta.windowHandlesIterator = Beta.handles.iterator();

		switch (Beta.handles.size()) {
		case 1:
			windowStatus2();
			return ("");
		case 2: {
			if (!currentWindowHandler.equals(getParentWindowHandler(parentWindow))) {
				return currentWindowHandler;
			} else {
				// finding out what the other window handler is
				if (Beta.windowHandlesIterator.hasNext()) {
					newWindowHandler = new String(Beta.windowHandlesIterator.next());
					if (!newWindowHandler.equals(getParentWindowHandler(parentWindow))) {
						return newWindowHandler;
					} else {
						//
						if (Beta.windowHandlesIterator.hasNext()) {
							newWindowHandler = Beta.windowHandlesIterator.next();
							if (!newWindowHandler.equals(currentWindowHandler)) {
								return newWindowHandler;
							}
						}
					}
				}
			}
		}
		case 3:
			Logging.slog("Error there are 3 windows!");
			windowStatus2();
			return ("");
		}

		Logging.slog("Error finding SON");
		return ("");
	}

	static public boolean isElementPresent(WebDriver driver, By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	static public boolean verifyTitle(WebDriver driver, String wantedTitle) {
		String pageTitle = new String(driver.getTitle());
		if (pageTitle.contains(wantedTitle)) {
			return true;
		}
		return false;
	}

	static public void logMyIP() {
		String myIp;

		try {
			myIp = new String(Inet4Address.getLocalHost().getHostAddress());
		} catch (Exception e) {
			myIp = new String("IP -not found");
			Logging.slog(e.getMessage());
		}

		Logging.slog((new String("Internal IP: ").concat(myIp)));

		try {

			URL url = new URL("https://api.ipify.org?format=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Error: " + conn.getResponseCode());

			}

			InputStreamReader input = new InputStreamReader(conn.getInputStream());
			BufferedReader reader = new BufferedReader(input);

			Gson gson = new Gson();
			JsonObject json = gson.fromJson(reader, JsonObject.class);
			String ExternalIP = json.get("ip").getAsString();
			Logging.slog(new String("External IP: ").concat(ExternalIP));

			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

private static String fillLeftZeroMonth(int data){
	//returns the String value of data. If data is only one digit , adds a '0' to the left of the digit.. 11:10:07
	String stringMonth = "";
	String  rightDigit = new String (String.valueOf(data+1));
	
	if(data <9){
		 
		stringMonth = (new String ("0")).concat(rightDigit);
	}else{
		stringMonth = new String (rightDigit);
	}
	
	return stringMonth;
}	
	
private static String fillLeftZeroDay(int data){
	//returns the String value of data. If data is only one digit , adds a '0' to the left of the digit.. 11:10:07
	String stringMonth = "";
	String  rightDigit = new String (String.valueOf(data));
	
	if(data <9){
		
		stringMonth = (new String ("0")).concat(rightDigit);
	}else{
		stringMonth = new String (rightDigit);
	}
	
	return stringMonth;
}	
	
	
	public static String findNYTimeNow() {
		//    2012-06-30 11:10:07
		int i=7;
		String stringMonth = "";
		String stringDay = "";
		Calendar calNewYork = Calendar.getInstance();
		calNewYork.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		int year = calNewYork.get(Calendar.YEAR);
		
		int month = calNewYork.get(Calendar.MONTH);
		stringMonth = new String (fillLeftZeroMonth(month)); 
		
		int day = calNewYork.get(Calendar.DAY_OF_MONTH);
		stringDay= new String (fillLeftZeroDay(day));
		
		String timeMark = new String();
		timeMark = timeMark.concat(String.valueOf(year));
		timeMark = timeMark.concat("-");
		timeMark = timeMark.concat(stringMonth);
		timeMark = timeMark.concat("-");
		timeMark = timeMark.concat(stringDay);
		timeMark = timeMark.concat(" ");
		timeMark = ((timeMark)).concat(String.valueOf(calNewYork.get(Calendar.HOUR_OF_DAY)));
		timeMark = timeMark.concat(":");
		timeMark = timeMark.concat(String.valueOf(calNewYork.get(Calendar.MINUTE)));
		timeMark = timeMark.concat(":00");
		
		
		return timeMark;
	}
	
	
	public static String findNYTime() {
		Calendar calNewYork = Calendar.getInstance();

		calNewYork.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		String timeMark = new String(String.valueOf(calNewYork.get(Calendar.DAY_OF_MONTH)));
		timeMark = timeMark.concat("/");
		timeMark = ((timeMark)).concat(String.valueOf(calNewYork.get(Calendar.HOUR_OF_DAY)));
		timeMark = timeMark.concat(":");
		timeMark = timeMark.concat(String.valueOf(calNewYork.get(Calendar.MINUTE)));
		return timeMark;
	}

	static public boolean nowIsNightTime() {
		// returns true if the time is when every sane actor is a sleep so we
		// too pretend to sleep
		Calendar calNewYork = Calendar.getInstance();
		int hourTime = calNewYork.get(Calendar.HOUR_OF_DAY);

		if (hourTime < 6) {
			// now is between midnight and 6 am in NY
			return true;
		}
		return false;
	}

}
