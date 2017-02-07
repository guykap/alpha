package Alpha;

import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ClientsMngt {

	private static String jsonFilePath;
	public static int config_id;
	private static String outLogsPath;
	private static String geckoPath;
	public static int onlyTopProd;
	static public int AA_DEFAULT_MAX_PROD_ON_PAGE = 25;
	static public int CN_DEFAULT_PROD_MAX_ROWS = 14;
	static public boolean autoSubmitCart;
	public static boolean DEFUALT_AUTO_SUBMIT_CART = true;
	static Actor client;
	public static int client_id;
	public static Timestamp last_interaction;
	 
	public static int MAX_TO_COLD = 30;
	public static int ACTUAL_COLD_TIME;
	public static int site;
	
	
	
	public static boolean getLastRunningVars(){
		//all success is temporary
		boolean tempSucc;
		tempSucc = Db.getRunningVars();
		Timestamp currentNYTime = ManageDriver.findNYTimeNow();
		
		
		if (diffGreaterThanX(currentNYTime,last_interaction)){
			return tempSucc;
		}else{
			return false;
		}
		
		
	 
	}
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	
	public static boolean diffGreaterThanX(Timestamp currentNYTime,Timestamp last_date){
		long diff = currentNYTime.getTime() - last_date.getTime();
		
		if(diff < 0 ){
			System.out.println("Error found negative last interaction");
			return false;
		}
		
		long millisecondsMinToCold = ACTUAL_COLD_TIME *60000;
		
		if(diff > millisecondsMinToCold){
			return true;
		}
		
	 
		return false;
		
	}
	
	/*
	static public void waitForLeastInteracted(){
		//waits until there is a config_id,actor_id that is cold = no interaction last X min
		
		//While (true)
		// find the least interacted last time.
		//  if (time < X min)
		//	   then return;
		//	else
		//		wait();
		
		
		String last_interaction="";
		while (true){
			last_interaction = Db.getLeastInteracted();
			String currentNYTime = new String(ManageDriver.findNYTimeNow());
			if (diffGreaterThanX(currentNYTime,last_interaction)){
				return;
			}else{
				Breath.powerNap();
			}
		}
	}
*/
	static public String varToString(JSONObject client, String varJsonName) {
		String tempString = new String("");
		try {
			tempString = new String((String) client.get(varJsonName));

		} catch (Exception e) {
			Logging.slog(new String("Missing var:").concat(varJsonName));

		}
		return tempString;
	}

	static public Actor jsonToActor(JSONObject client) throws Throwable {
		try {

			String actorId = varToString(client, "actorId");
			String phone = varToString(client, "phone");
			String email = varToString(client, "email");
			String billing_ack = varToString(client, "billing_ack");

			String cnUsername = varToString(client, "cnUsername");
			String cnPassword = varToString(client, "cnPassword");
			String aaUserName = varToString(client, "aaUserName");
			String aaPassword = varToString(client, "aaPassword");
			String is_night_shift = varToString(client, "isNightShift");
			String human_is_male = varToString(client, "humanIsMale");
			String humanEthnicity = varToString(client, "humanEthnicity");
			String unionStatus = varToString(client, "unionStatus");
			String humanSizes = varToString(client, "humanSizes");
			String minActingAge = varToString(client, "minActingAge");
			String maxActingAge = varToString(client, "maxActingAge");
			String defaultPhoto = varToString(client, "defaultPhoto");
			String defaultVideo = varToString(client, "defaultVideo");
			String defaultNotesCN = varToString(client, "defaultNotesCN");
			String defaultNotesAA = varToString(client, "defaultNotesAA");
			String targetRegions = varToString(client, "targetRegions");
			String blackList = varToString(client, "blackList");
			return new Actor();
			/*
			 * String fillRegions = new String(target_regions); String
			 * fillDefaultPhoto = new String(default_photo); String
			 * fillDefaultVideo = new String(default_video); boolean
			 * fill_is_night_shift; boolean fill_human_is_male; if
			 * (targetRegions.length() < 1) {
			 * Logging.slog("regions missing in DB. So choosing NY as default");
			 * fillRegions = new String("new york"); }
			 * 
			 * if (defaultPhoto.equals(new String(""))) { fillDefaultPhoto = new
			 * String("0"); } if (defaultVideo.equals(new String(""))) {
			 * fillDefaultVideo = new String("0"); }
			 * 
			 * if (is_night_shift.equals(new String("true"))) {
			 * fill_is_night_shift = false; } else { fill_is_night_shift = true;
			 * }
			 * 
			 * if (is_night_shift.equals(new String("true"))) {
			 * fill_is_night_shift = false; } else { fill_is_night_shift = true;
			 * }
			 * 
			 * if (human_is_male.equals(new String("true"))) {
			 * fill_human_is_male = true; } else if (human_is_male.equals(new
			 * String("false"))) { fill_human_is_male = false; } else { Logging.
			 * slog("Actor gender is not clear. Choosing male as default"); ;
			 * fill_human_is_male = true; }
			 * 
			 * return new Actor(String.valueOf(id), name, phone, email,
			 * billing_ack, cn_username, cn_password, aa_username, aa_password,
			 * fill_is_night_shift, fill_human_is_male, human_ethnicity,
			 * union_status, human_sizes, Integer.parseInt(min_acting_age),
			 * Integer.parseInt(max_acting_age), fillDefaultPhoto,
			 * fillDefaultVideo, default_notes_cn, default_notes_aa,
			 * fillRegions, black_list);
			 */
		} catch (Exception e) {
			System.out.println("Error loading vars.");
			Logging.slog("Error loading Json for Client);");
			Logging.slog(e.getMessage());
			return new Actor();
		}

	}

	public static boolean validateStringToBool(String data) throws Throwable {
		if (data.length() < 1) {
			throw new Exception("Error : data is not a true/false string");
		}

		if (data.equals(new String("true"))) {
			return true;
		}

		return false;
	}

	public static int validateStringToInt(String data) throws Throwable {
		if (data.length() < 1) {
			throw new Exception("Error : data is not a number");
		}

		int age = Integer.parseInt(data);
		if (validateAcceptableAge(age)) {
			return age;
		}

		return (-1);
	}

	private static boolean validateAcceptableAge(int age) {
		if ((age > 0) && (age < 100)) {
			return true;
		}
		return false;
	};

	@SuppressWarnings("unchecked")
	static public void loadClientsFromFile() throws Throwable {
		JSONParser parser = new JSONParser();

		try {
			String filePath = (new String(getjsonFilePath()));
			Object obj = parser.parse(new FileReader(filePath));

			JSONObject jSONObject = (JSONObject) obj;
			JSONObject client0 = (JSONObject) jSONObject.get("actorZero");
			ClientsMngt.client = jsonToActor(client0);

			if (ClientsMngt.client == null) {
				System.out.println("Error loading client");
				throw new Exception("Error loading client");

			}

			Logging.slog((new String("Succ loading client from json file. ").concat(ClientsMngt.client.getAaUsername())));

			// Logging.logActorDetails(actor);
			// } else {
			// Logging.slog(actorId);

			// s}
			// String actorId = (String) client0.get("actorId");

		} catch (Exception e) {
			System.out.println("Error reading Clients from file.");
			e.printStackTrace();
		}
	}

	static public void loadRunningVarsFile() throws Throwable {
		JSONParser parser = new JSONParser();

		try {
			// String filePath = (new String(Beta.getjsonFilePath()));
			Object obj = parser.parse(new FileReader(getjsonFilePath()));

			JSONObject jSONObject = (JSONObject) obj;
			JSONObject vars = (JSONObject) jSONObject.get("runningVars");
			// JSONObject client1 = (JSONObject) jSONObject.get("actorOne");

			if (validateUpdateVars(vars)) {
				// String site = (String) vars.get("site");
				// String offerType = (String) vars.get("offerType");
				System.out.println((new String("Success loading running vars: site Casting Networks:"))
						.concat(String.valueOf(Beta.isCastingNetworks)));
				if (Beta.isCastingNetworks) {
					System.out.println((new String("| Offer Type: ")).concat(new String(CnBooking.offerType)));
				}
			} else {
				System.out.println("Error loading running vars from file");
				throw new Exception("Error loading running vars from file");

			}

			// Logging.logActorDetails(actor);
			// } else {
			// Logging.slog(actorId);

			// s}
			// String actorId = (String) client0.get("actorId");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public boolean validateUpdateVars(JSONObject vars) {
		String site = varToString(vars, "site");

		String offerType = varToString(vars, "offerType");
		String sleepCounter = varToString(vars, "sleepCounter");
		String outLogs = varToString(vars, "outLogs");
		String greckoDriverPath = varToString(vars, "greckoDriverPath");
		String hahaTime = varToString(vars, "hahaTime");
		String breathSec = varToString(vars, "breathSec");
		String geckoWaitTime = varToString(vars, "geckoWaitTime");
		String onlyTopProductions = varToString(vars, "onlyTopProductions");
		String autoSubmitCart = varToString(vars, "autoSubmitCart");

		/*
		 * String site = (String) vars.get("site");
		 * 
		 * String offerType = (String) vars.get("offerType"); String
		 * sleepCounter = (String) vars.get("sleepCounter"); String outLogs =
		 * (String) vars.get("outLogs"); String greckoDriverPath = (String)
		 * vars.get("greckoDriverPath");
		 */

		if (outLogs.length() < 1) {
			System.out.println("Error reading OutLogs Path");
			return false;
		}

		if (greckoDriverPath.length() < 1) {
			System.out.println("Error reading OutLogs Path");
			return false;
		}

		if ((Integer.parseInt(hahaTime) > 100) || (Integer.parseInt(hahaTime) < 6)) {
			Breath.hahaTime = Breath.DEFAULT_HAHA_TIME;
		} else {
			Breath.hahaTime = Integer.parseInt(hahaTime);
		}

		if (Integer.parseInt(breathSec) > 20) {
			Breath.breathSec = Breath.DEFAULT_BREATH_SEC;
		} else {
			Breath.breathSec = Integer.parseInt(breathSec);
		}

		if ((Integer.parseInt(geckoWaitTime) > 30) || (Integer.parseInt(geckoWaitTime) < 3)) {
			Breath.geckoWaitTime = Breath.DEFAULT_GECKO_WAIT_TIME;
		} else {
			Breath.geckoWaitTime = Integer.parseInt(geckoWaitTime);
		}

		if (Integer.parseInt(onlyTopProductions) > ClientsMngt.AA_DEFAULT_MAX_PROD_ON_PAGE) {
			onlyTopProd = ClientsMngt.AA_DEFAULT_MAX_PROD_ON_PAGE;
		} else {
			onlyTopProd = Integer.parseInt(onlyTopProductions);
		}

		if ((autoSubmitCart.length() < 1) || (!stringIsABoolean(autoSubmitCart))) {
			ClientsMngt.autoSubmitCart = ClientsMngt.DEFUALT_AUTO_SUBMIT_CART;
		} else {
			ClientsMngt.autoSubmitCart = Boolean.parseBoolean(autoSubmitCart);
		}

		Breath.setChosenSleepCycle(Integer.parseInt(sleepCounter));
		setOutLogsPath(outLogs);
		setGecko_driver_path(greckoDriverPath);

		if (site.equals(new String("CN"))) {
			if (offerType.equals(new String("both"))) {
				Beta.isCastingNetworks = true;
				CnBooking.offerType = new String("both");
				return true;
			}
			if (offerType.equals(new String("extras"))) {
				Beta.isCastingNetworks = true;
				CnBooking.offerType = new String("extras");
				return true;
			}
			if (offerType.equals(new String("principle"))) {
				Beta.isCastingNetworks = true;
				CnBooking.offerType = new String("principle");
				return true;
			}

		}
		if (site.equals(new String("AA"))) {
			Beta.isCastingNetworks = false;
			return true;
		}

		return false;
	}

	static public boolean runStatus() {
		return true;
	}

	public static void setOutLogsPath(String newPath) {
		if (newPath.length() < 1) {
			System.out.println("Error in log path");
		}
		outLogsPath = new String(newPath);
	}

	public static String getOutLogsPath() {
		return outLogsPath;
	}

	public static void setGecko_driver_path(String newPath) {
		if (newPath.length() < 1) {
			System.out.println("Error in log path");
		}
		geckoPath = new String(newPath);
	}

	public static String getGecko_driver_path() {
		return (new String(geckoPath));
	}

	public static void setJsonFilePath(String newPath) {
		if (newPath.length() < 1) {
			System.out.println("Error in log path");
		}
		jsonFilePath = new String(newPath);
	}

	public static String getjsonFilePath() {
		return jsonFilePath;
	}
 
	static public String intToRegion(int intRegion) {

		if (intRegion == 1)
			return new String("los angeles");
		if (intRegion == 2)
			return new String("new york");
		if (intRegion == 3)
			return new String("vancouver");
		if (intRegion == 4)
			return new String("chicago");
		if (intRegion == 5)
			return new String("florida");
		if (intRegion == 6)
			return new String("toronto");
		if (intRegion == 7)
			return new String("texas");
		if (intRegion == 8)
			return new String("hawaii");
		if (intRegion == 9)
			return new String("southeast");
		if (intRegion == 10)
			return new String("mountain region");
		// default
		return new String("");
	}

	static public int regionToInt(String region) {
		if (region.length() < 1) {
			Logging.slog("Error regionToInt");
			return (-1);
		}

		if (region.contains("los angeles")) {
			return 1;
		}
		if (region.contains("new york")) {
			return 2;
		}
		if (region.contains("vancouver")) {
			return 3;
		}
		if (region.contains("chicago")) {
			return 4;
		}
		if (region.contains("florida")) {
			return 5;
		}
		if (region.contains("toronto")) {
			return 6;
		}
		if (region.contains("texas")) {
			return 7;
		}
		if (region.contains("hawaii")) {
			return 8;
		}
		if (region.contains("southeast")) {
			return 9;
		}
		if (region.contains("mountain region")) {
			return 10;
		}

		return (-1);
	}

	static public boolean reloadTargetRegions(Actor human) {
		return true;
	}

	 
	static public boolean stringIsABoolean(String data) {
		if (data.equals(new String("true"))) {
			return true;
		}
		if (data.equals(new String("false"))) {
			return true;
		}
		return false;
	}

	static public int currentPhotoChoice(String options, Job offer) {
		
		
		if(offer.isGuard){
			return 1;
		}
		
		if(offer.getNeedTuxedo()){
			return 2;
		}
		
		if (options.length() == 1) {
			return Integer.parseInt(options);
		}
		

		// {"defualt_photo": "3","guardPhoto": "1","tuxPhoto": "2"}

		JSONParser parser = new JSONParser();

		try {
			String photoChoicesLine = (new String(options));
			Object obj = parser.parse(photoChoicesLine);
			JSONObject jSONObject = (JSONObject) obj;

			String defualtPhoto = varToString(jSONObject, "defualt_photo");
			String guardPhoto = varToString(jSONObject, "guard_photo");
			String tuxPhoto = varToString(jSONObject, "tux_photo");

			if (offer.getNeedTuxedo()) {
				return Integer.parseInt(tuxPhoto);
			}
			if (offer.getIsGuard()) {
				return Integer.parseInt(guardPhoto);
			}

			return Integer.parseInt(defualtPhoto);

		} catch (Exception e) {
			Logging.slog("Error reading the chosen photo. Choosing the first photo.");
			return 0;
		}
	}
}
