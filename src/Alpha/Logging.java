package Alpha;

import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.varia.NullAppender;

import com.mysql.fabric.xmlrpc.Client;

public class Logging {
	static private boolean logStateFull;
	public static Appender fh = null;
	public static Logger logger = Logger.getLogger("MyLog");

	public Logging() {
	}

	static public void initLogging(String fileOut) {
		try {
			logStateFull = true;
			org.apache.log4j.BasicConfigurator.configure(new NullAppender());
			fh = new FileAppender(new SimpleLayout(), fileOut);
			logger.addAppender(fh);
			fh.setLayout(new SimpleLayout());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Stopping. BAD LOGGING");
		}
	}

	static public boolean getLogState() {
		return logStateFull;
	};

	static public void setLogState(boolean newBit) {
		logStateFull = newBit;
	};

	public void log(String newLog) {
		if (newLog.length() < 1) {
			return;
		}

		System.out.println(newLog);
		try {

			logger.info(newLog);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	static public void slog(String newLog) {
		if(newLog == null){
		newLog = new String("");
		}
		if (newLog.length() < 1) {
			return;
		}

		System.out.println(newLog);
		try {

			logger.info((new String(ManageDriver.findLATime())).concat(": ").concat(newLog));
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	static public void printDecisionMakingVars(Job offer) {
		// this would print to log why the decision went down as it did

		slog("********Offer" + offer.getOfferId() + "\nposed:" + offer.getOfferPostedTime() + "|"
				+ offer.getOfferProjectName() + "|" + offer.getOfferRole() + "|"  + offer.getOfferListing() + "|");
		slog("Decision: " + offer.getDecisionSubmit() + "|CharGender: " + offer.getCharacterGender()
				+ "|isGenderMatch: " + offer.getIsGenderMatch() + "|isEthMatch: "
				+ offer.getIsEthnicityMatch()  + "|isAge: "
				+ offer.getIsAge()+ "|isUnionMatch: " + offer.getIsUnionMatch() + "|on Blacklist: " + offer.getIsOnBlacklist() + "|hasBeenSubmitted Before: " + offer.getHasBeenSubmitted()+ "|isCar: " + offer.getIsCar());

	}

	public static void printSubmittions(List<Job> Jobs) {
		slog("********ALL SUBMITTIONS********");
		for (Job offer : Jobs) {
			if (offer.getHasBeenSubmitted()) {
				printOffer(offer);
			}
		}
		slog("********END LIST OF SUBMITTIONS********");
	}

	public static void printOffer(Job offer) {
		if (offer == null)
			return;
		slog("\n* Actor:" + offer.getActorIDSubmitted() + "|Region:" + offer.getRegion() + "|Offer:"
				+ offer.getOfferId() + "|Background:" + offer.getIsBackgroundWork() + "|Role added:"
				+ offer.getOfferTimeRoleAdded() + "|Submittion time:" + offer.getOfferSubmittionDateTime()
				+ "|Shoot date:" + offer.getOfferShootDate() + "|age:" + offer.getIsAge() + "|car:" + offer.getIsCar()
				+ "|EthMatch:" + offer.getIsEthnicityMatch() + "|GenderOfCharacter:" + offer.getCharacterGender()
				+ "|GenderMatch:" + offer.getIsGenderMatch() + "|Union:" + offer.getCharacterUnionDemand() + "|Guard:"
				+ offer.getIsGuard() + "|Tux:" + offer.getNeedTuxedo() + "|Uni:" + offer.getNeedPoiceUniform()
				+ "|Type:" + offer.getOfferTypeProject() + "|ReqSizes:" + offer.getReqSizes() + "|Paying:"
				+ offer.getOfferPaying() + "|Rate:" + offer.getOffertRate() + "|Name:" + offer.getOfferProjectName()
				+ "|Role:" + offer.getOfferRole() + "|Offer Listing:" + offer.getOfferListing()
				+ " |  Talent Notes filled with:" + offer.getMessage());

	}

	static public void log(char stage) {
		// state - Full log - outputs the whole string
		// state - min log - outputs only the letter representing the stage
		if (!(getLogState())) {
			slog(Character.toString(stage));
		} else {
			switch (stage) {
			// case 'a':
			// this.log("A: Window handle Parent " + parentWindowHandler);
			// break;
			case 'c':
				slog("C: Location->Home Page");
				break;
			case 'e':
				slog("E: Location->Casting Billboard");
				break;
			case 'f':
				slog("F: Succ opening Casing Billboards and Extras link");
				break;
			case 'h':
				slog("H: Succ adding offer to Jobs list");
				break;
			// case 'i':
			// s.log(
			// "I: Begin submittion for top offer id " + offer.getOfferId() + "
			// : " + offer.getOfferRole());
			// break;
			case 'j':
				slog("J: Making sure there is no GREEN STAR/red check");
				break;

			case 'l':
				slog("L: Succ on openning window to choose photo and fill talent notes.");
				break;
			case 'm':
				slog("*******SUBMITTED:");
				/*
				 * slog("M: Succ Submitted: " + offer.getOfferProjectName() +
				 * " | " + offer.getOfferSubmittionDateTime() + " | " +
				 * offer.getOffertRate() + " | " + offer.getOfferTypeProject() +
				 * " | " + offer.getHasBeenSubmitted() + " | " +
				 * offer.getOfferListing() + "Talent Notes :" +
				 * offer.getMessage());
				 */
				break;
			case 'y':

				break;
			case 'z':
				slog("Z: Stopping");
				break;
			}

		}
	}

	static public void logTimeNY() {
		Logging.slog(ManageDriver.findLATime());
	}
	
	static public void printAllRunningVars(){
		Logging.slog(new String("**RUNNING VARS:\n"
				+ "config_id = " + ClientsMngt.config_id
				+ "geckoWaitTime = " + Breath.geckoWaitTime+
				"\nbreathSec = "+ Breath.breathSec+
				"\nhahaTime = " + Breath.hahaTime+ 
				"\nchosenSleepCycle = " + Breath.chosenSleepCycle + 
				"\nonlyTopProductions  = " + ClientsMngt.onlyTopProd+
				"\nautoSubmitCart  = " + ClientsMngt.autoSubmitCart+
				"\noutLogs = " + ClientsMngt.getOutLogsPath()+
				"\ngreckoDriverPath = " + ClientsMngt.getGecko_driver_path()));
		
		
		
	}
	
	
}
