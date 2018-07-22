package Alpha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ManageDriver {
	public static WebDriver driver;

	private static final String processname = "geckodriver.exe";
	private static final String DEFAULT_GECKO_DRIVER_LIBRARY = "C:\\Users\\me\\work\\official\\Julia\\gecko_driver";
	static public String parentWindowHandler;
	static public String newWindowHandler;
	static Iterator<String> windowHandlesIterator;
	static public Set<String> handles;

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
		String currentWindowHandler = ManageDriver.driver.getWindowHandle();
		ManageDriver.handles = ManageDriver.driver.getWindowHandles(); // get all window handles
		if (ManageDriver.handles.size() < 2) {
			Logging.slog("Error: there is only one window : " + currentWindowHandler);
			return false;
		}
		ManageDriver.windowHandlesIterator = ManageDriver.handles.iterator();
		if (ManageDriver.windowHandlesIterator.hasNext()) {
			newWindowHandler = ManageDriver.windowHandlesIterator.next();
			if (!newWindowHandler.equals(currentWindowHandler)) {
				ManageDriver.driver.switchTo().window(newWindowHandler); // switch to
				// popup
				// window
				windowStatus(parentWindow);
				return true;
			} else {
				// fell on the same window - so move again
				if (ManageDriver.windowHandlesIterator.hasNext()) {
					newWindowHandler = ManageDriver.windowHandlesIterator.next();
					if (!newWindowHandler.equals(currentWindowHandler)) {
						ManageDriver.driver.switchTo().window(newWindowHandler); // switching
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
		ManageDriver.driver.close();
		ManageDriver.driver.switchTo().window(parentWindow);
		String newWindowHandler = ManageDriver.driver.getWindowHandle();
		Logging.slog("killed window and returned to  " + newWindowHandler);
		windowStatus(parentWindow);
		return true;
	}

	static public void windowStatus(String parentWindow) {
		try {
			Breath.breath();
		} catch (Exception e) {

		}
		String currentWindowHandler = ManageDriver.driver.getWindowHandle();
		String sonWindow = getSonWindowHandler(parentWindow);
		Logging.slog("Parent: " + ManageDriver.getParentWindowHandler(parentWindow) + " Son: "
				+ getSonWindowHandler(parentWindow));

		if (getParentWindowHandler(parentWindow).equals(currentWindowHandler)) {
			Logging.slog("Now on PARENT");
		} else {

			Logging.slog("Now on SON");
		}
		// ManageDriver.driver.getWindowHandle();
		Logging.slog("Parent: " + ManageDriver.getParentWindowHandler(parentWindow) + " Son: "
				+ getSonWindowHandler(parentWindow));
		return;
	}

	static public void windowStatus2() {
		ManageDriver.handles = ManageDriver.driver.getWindowHandles(); // get all window handles
		StringBuilder builder = new StringBuilder();
		for (String s : ManageDriver.handles) {
			builder.append(s + ",");
		}
		String allHandles = new String("[");
		allHandles += new String(builder.toString());
		allHandles += new String("] ");
		String currentWindowHandler = ManageDriver.driver.getWindowHandle();
		Logging.slog(allHandles + " on: " + currentWindowHandler);
	}

	static public String getParentWindowHandler(String parentWindow) {
		if (parentWindow.length() > 1) {
			return parentWindow;
		}
		Logging.slog("Error finding Parent holder");
		return ("");
	}

	static public String getCurrentWindow() {
		int errorsNum = 0;
		String currentWindow = new String("");
		while (currentWindow.length() < 1) {
			try {

				WebDriverWait wait = new WebDriverWait(ManageDriver.driver, 5);
				currentWindow = new String(ManageDriver.driver.getWindowHandle());
				if (currentWindow.length() > 1) {
					return (currentWindow);
				}
				 
			} catch (Exception e) {
				Logging.slog("exception on getCurrentWindow()");

			}
			if(errorsNum++ > 50) {
				Logging.slog(" 50 times bad window. exit");
				return ("");
			}
			
		}
	}

	static public String getSonWindowHandler(String parentWindow) {
		String newWindowHandler;
		String currentWindowHandler = new String("");
		int errorsNum = 0;
		// testing
		while (currentWindowHandler.length() < 1) {
			try {
				Breath.breath();
				currentWindowHandler = new String(getCurrentWindow());
			} catch (Exception e) {
				if (errorsNum++ > 50) {
					return ("");
				}

				Logging.slog("exception on getWindowHandler");

			}

		}
		ManageDriver.handles = ManageDriver.driver.getWindowHandles(); // get all window handles
		ManageDriver.windowHandlesIterator = ManageDriver.handles.iterator();

		switch (ManageDriver.handles.size()) {
		case 1:
			windowStatus2();
			return ("");
		case 2: {
			if (!currentWindowHandler.equals(getParentWindowHandler(parentWindow))) {
				return currentWindowHandler;
			} else {
				// finding out what the other window handler is
				if (ManageDriver.windowHandlesIterator.hasNext()) {
					newWindowHandler = new String(ManageDriver.windowHandlesIterator.next());
					if (!newWindowHandler.equals(getParentWindowHandler(parentWindow))) {
						return newWindowHandler;
					} else {
						//
						if (ManageDriver.windowHandlesIterator.hasNext()) {
							newWindowHandler = ManageDriver.windowHandlesIterator.next();
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

	static public void logMyExternalIP() {
		String ExternalIP = getMyExternalIP();
		Logging.slog(new String("External IP: ").concat(ExternalIP));
	}

	static public void logMyInternalIP() {
		String InternalIP = getMyInternalIP();
		Logging.slog(new String("Internal IP: ").concat(InternalIP));
	}

	static public String getMyInternalIP() {
		String myIp;
		try {
			myIp = new String(Inet4Address.getLocalHost().getHostAddress());
		} catch (Exception e) {
			myIp = new String("IP -not found");
			Logging.slog(e.getMessage());
		}

		return (new String(myIp));

	}

	static public String getMyExternalIP() {
		String myExternalIP = "";

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
			myExternalIP = new String(ExternalIP);

			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return myExternalIP;

	}

	private static String fillLeftZeroMonth(int data) {
		// returns the String value of data. If data is only one digit , adds a '0' to
		// the left of the digit.. 11:10:07
		String stringMonth = "";
		String rightDigit = new String(String.valueOf(data + 1));

		if (data < 9) {

			stringMonth = (new String("0")).concat(rightDigit);
		} else {
			stringMonth = new String(rightDigit);
		}

		return stringMonth;
	}

	private static String fillLeftZeroDay(int data) {
		// returns the String value of data. If data is only one digit , adds a '0' to
		// the left of the digit.. 11:10:07
		String stringMonth = "";
		String rightDigit = new String(String.valueOf(data));

		if (data < 9) {

			stringMonth = (new String("0")).concat(rightDigit);
		} else {
			stringMonth = new String(rightDigit);
		}

		return stringMonth;
	}

	public static Timestamp findLATimeNow() {
//	final Instant instant = module.getAnalysisDate().toInstant();

		// Timestamp.from(instant);
		TimeZone timeZone = TimeZone.getTimeZone("GMT");

		Calendar timeWithZone = Calendar.getInstance(timeZone);

		Timestamp ts = new Timestamp(timeWithZone.getTimeInMillis());
		Calendar calNewYork = Calendar.getInstance();
		calNewYork.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		int NYhour = calNewYork.get(Calendar.HOUR_OF_DAY);
		ts.setHours(NYhour);

		ts.setNanos(0);

		return ts;
	}

	public static Timestamp updateToLATime(Timestamp sysTime) {

		TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");

		Calendar timeWithZone = Calendar.getInstance(timeZone);

		Timestamp ts = new Timestamp(timeWithZone.getTimeInMillis());

		/*
		 * TimeZone.setDefault(TimeZone.getTimeZone("GMT-1"));
		 * Timestamp.valueOf("2016-10-26 23:00:00").getTime();
		 */
		// java.sql.Timestamp ts2 = new
		// Timestamp(OffsetDateTime.of(2016,10,26,23,0,0,0,ZoneOffset.UTC).toInstant.toEpochMilli).getTime();

		// ZoneId zoneId = ZoneId.of ( "America/New_York" );
//	ZonedDateTime zdtNewYork = ZonedDateTime.of ( localDateTime , zoneId );
//	ZonedDateTime zdtUtc = zdtNewYork.withZoneSameInstant ( ZoneOffset.UTC );
//	Instant instant = zdtNewYork.toInstant ();
//	java.sql.Timestamp ts = java.sql.Timestamp.from( zdtNewYork.toInstant () );

		return ts;
	}

	public static boolean compareToLAHour(Timestamp syshour) {
		Calendar calNewYork = Calendar.getInstance();
		calNewYork.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		int NYhour = calNewYork.get(Calendar.HOUR_OF_DAY);

		if ((syshour.getHours() == NYhour) || (((syshour.getHours() + 1) == NYhour))) {
			return true;
		}
		System.out.println("Error in hours NY time");
		return false;
	}

	public static String oldfindNYTimeNow() {
		// 2012-06-30 11:10:07
		int i = 7;
		String stringMonth = "";
		String stringDay = "";
		Calendar calNewYork = Calendar.getInstance();
		calNewYork.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		int year = calNewYork.get(Calendar.YEAR);

		int month = calNewYork.get(Calendar.MONTH);
		stringMonth = new String(fillLeftZeroMonth(month));

		int day = calNewYork.get(Calendar.DAY_OF_MONTH);
		stringDay = new String(fillLeftZeroDay(day));

		String timeMark = new String();
		timeMark = timeMark.concat(String.valueOf(year));
		timeMark = timeMark.concat("-");
		timeMark = timeMark.concat(stringMonth);
		timeMark = timeMark.concat("-");
		timeMark = timeMark.concat(stringDay);
		timeMark = timeMark.concat(" ");

		int hour = calNewYork.get(Calendar.HOUR_OF_DAY);
		String stringHourTwoDigits = new String(fillLeftZeroDay(hour));
		timeMark = ((timeMark)).concat(stringHourTwoDigits);

		timeMark = timeMark.concat(":");
		int min = calNewYork.get(Calendar.MINUTE);
		String stringMinTwoDigits = new String(fillLeftZeroDay(hour));
		timeMark = timeMark.concat(stringMinTwoDigits);
		timeMark = timeMark.concat(":00");

		return timeMark;
	}

	public static String completeZero(String number) {
		String twoDigits = new String("0");
		if (number.length() < 2) {
			return (twoDigits.concat(number));
		}
		return number;
	}

	public static String findLATime() {
		Calendar calNewYork = Calendar.getInstance();

		calNewYork.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		String timeMark = new String(String.valueOf(calNewYork.get(Calendar.DAY_OF_MONTH)));
		timeMark = timeMark.concat("/");
		timeMark = ((timeMark)).concat(String.valueOf(calNewYork.get(Calendar.HOUR_OF_DAY)));
		timeMark = timeMark.concat(":");
		timeMark = timeMark.concat(String.valueOf(calNewYork.get(Calendar.MINUTE)));
		return timeMark;
	}

	public static String old_findNYTime() {
		String la = findLATime();
		int i = 8;
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

		calNewYork.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));

		int hourTime = calNewYork.get(Calendar.HOUR_OF_DAY);

		if ((hourTime > 2) && (hourTime < 6)) {
			// now is between 2am and 6 am in NY
			return true;
		}
		return false;
	}

	static public void killFirefoxAndOpenNew() {
		WebDriver tempDriver = driver;
		driver = new FirefoxDriver();
		tempDriver.quit();
	}

}
