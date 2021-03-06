package Alpha;

import java.io.BufferedReader;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class Job {

	static String filename = "C:\\temp\\code\\castingNetworks\\test_file1";
	static int dailyMinPay = 30;
	// static int savgCharacterAge = 30;
	public static int SIZE_OF_ETHINICITIES_BUS = 26;
	public static boolean isThereSomethingInCart;

	// PRIVATE:
	String offerId;
	String actorIDSubmitted;
	boolean offerHasBeenSubmitted;
	String notice = "";
	String noticeLowerCase;
	String currentOffer;
	String offerRole;
	String offerCharacterName = "";
	String offerCharacterDetails = "";
	String offerProjectName;
	String offerShootDate="";
	String offerTypeProject;
	String offerRate="";
	String offerPaying;
	String offerCastingDirector;
	String offerAssociateCastingDirector;
	String offerAssistant;
	String offerProductionStartDate;
	String offerUnionStatus;
	String offerLocation;
	String offerPostedTime;
	String offerListing;
	String offerListingFirst;
	String offerListingSex = "";
	String offerListingEthnicity = "";
	String offerListingNotes = "";
	String offerLeadOrSupporting = "";
	String offerListingAgesHint = "";
	String offerTimeRoleAdded = "";
	String offerSubmittionDateTime = "";
	String message = "";

	String offerProductionDetails = "";
	String internalAAname;
	String internalAAhref;
	boolean[] seekingEthnicities;
	boolean isSag;
	boolean isAge;
	boolean isBackgroundWork;
	boolean isPayingEnough;
	boolean isCar;
	boolean isGuard;
	boolean isStandIn;
	boolean reqSizes;
	boolean needTuxedo;
	boolean needPoliceUniform;
	boolean decisionSubmit;
	int numberOfCharactersOnThisProduction;
	int region;
	int totalAddedToCart;
	boolean putInCart;
	boolean isEthnicityMatch;
	boolean isGenderMatch;
	boolean isUnionMatch;
	boolean isOnBlacklist;
	private char characterGender;
	private char characterUnionDemand;
	int foundOnRow;
	List<String> labels;
	
	

	public Job() {
		// String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		// this.offerId = new String (Timestamp(System.currentTimeMillis()));
		this.offerId = new String((new Long(System.currentTimeMillis())).toString());
	}

	public Job(String actorIdSubmitted) {
		this.actorIDSubmitted = new String(actorIdSubmitted);
		this.offerId = new String((new Long(System.currentTimeMillis())).toString());
		this.setCharacterGender('u');
		this.setCharacterUnionDemand('n'); // any Job starts as a NON Union
											// status
		this.seekingEthnicities = new boolean[SIZE_OF_ETHINICITIES_BUS];
		 this.foundOnRow = -1;
		 this.labels = new ArrayList<String>();
	}

	public Job(Job sameProductionOffer) {
		// PRIVATE:
		this.offerId = new String(sameProductionOffer.offerId);
		this.actorIDSubmitted = new String(sameProductionOffer.actorIDSubmitted);
		this.offerHasBeenSubmitted = sameProductionOffer.offerHasBeenSubmitted;
		this.notice = new String(sameProductionOffer.notice);
		this.noticeLowerCase = new String(sameProductionOffer.noticeLowerCase);
		this.currentOffer = new String(sameProductionOffer.currentOffer);
		this.offerRole = new String(sameProductionOffer.offerRole);
		this.offerCharacterName = new String(sameProductionOffer.offerCharacterName);
		this.offerCharacterDetails = new String(sameProductionOffer.offerCharacterDetails);
		this.offerProductionDetails = new String(sameProductionOffer.offerProductionDetails);

		this.offerProjectName = new String(sameProductionOffer.offerProjectName);
		this.offerShootDate = new String(sameProductionOffer.offerShootDate);
		this.offerTypeProject = new String(sameProductionOffer.offerTypeProject);
		this.offerRate = new String(sameProductionOffer.offerRate);
		this.offerPaying = new String(sameProductionOffer.offerPaying);
		this.offerCastingDirector = new String(sameProductionOffer.offerCastingDirector);
		this.offerAssociateCastingDirector = new String(sameProductionOffer.offerAssociateCastingDirector);
		this.offerAssistant = new String(sameProductionOffer.offerAssistant);
		this.offerProductionStartDate = new String(sameProductionOffer.offerProductionStartDate);
		this.offerUnionStatus = new String(sameProductionOffer.offerUnionStatus);
		this.offerLocation = new String(sameProductionOffer.offerLocation);
		this.offerPostedTime = new String(sameProductionOffer.offerPostedTime);
		this.offerListing = new String(sameProductionOffer.offerListing);
		this.offerListingFirst = new String(sameProductionOffer.offerListingFirst);
		this.offerListingSex = new String(sameProductionOffer.offerListingSex);
		this.offerListingEthnicity = new String(sameProductionOffer.offerListingEthnicity);
		this.offerListingNotes = new String(sameProductionOffer.offerListingNotes);
		this.offerListingAgesHint = new String(sameProductionOffer.offerListingAgesHint);
		this.offerTimeRoleAdded = new String(sameProductionOffer.offerTimeRoleAdded);
		this.offerSubmittionDateTime = new String(sameProductionOffer.offerSubmittionDateTime);
		this.message = new String(sameProductionOffer.message);
		this.isOnBlacklist = sameProductionOffer.isOnBlacklist;

		this.internalAAname = new String(sameProductionOffer.internalAAname);
		this.internalAAhref = new String(sameProductionOffer.internalAAhref);
		this.seekingEthnicities = new boolean[SIZE_OF_ETHINICITIES_BUS];
		for (int i = 0; i < SIZE_OF_ETHINICITIES_BUS; ++i) {
			this.seekingEthnicities[i] = sameProductionOffer.seekingEthnicities[i];
		}
		this.isSag = sameProductionOffer.isSag;
		this.isAge = sameProductionOffer.isAge;
		this.isBackgroundWork = sameProductionOffer.isBackgroundWork;
		this.isPayingEnough = sameProductionOffer.isPayingEnough;
		this.isCar = sameProductionOffer.isCar;
		this.isGuard = sameProductionOffer.isGuard;
		this.isStandIn = sameProductionOffer.isStandIn;
		this.reqSizes = sameProductionOffer.reqSizes;
		this.needTuxedo = sameProductionOffer.needTuxedo;
		this.needPoliceUniform = sameProductionOffer.needPoliceUniform;
		this.decisionSubmit = sameProductionOffer.decisionSubmit;
		this.numberOfCharactersOnThisProduction = sameProductionOffer.numberOfCharactersOnThisProduction;
		this.region = sameProductionOffer.region;
		this.totalAddedToCart = sameProductionOffer.totalAddedToCart;
		this.putInCart = sameProductionOffer.putInCart;
		this.isEthnicityMatch = sameProductionOffer.isEthnicityMatch;
		this.isGenderMatch = sameProductionOffer.isGenderMatch;
		this.isUnionMatch = sameProductionOffer.isUnionMatch;
		this.setCharacterGender('u');
		this.characterUnionDemand = sameProductionOffer.characterUnionDemand;

		// Copy Constructor . That returns a copy for the Job
		this.setActorIDSubmitted(sameProductionOffer.getActorIDSubmitted());
		this.setOfferPostedTime(sameProductionOffer.getOfferPostedTime());
		this.addToProductionDetails(sameProductionOffer.getProductionDetails());
		this.setOfferUnionStatus(sameProductionOffer.getOfferUnionStatus());
		this.setOfferLocation(sameProductionOffer.getOfferLocation());
		
		this.setOfferTypeProject(sameProductionOffer.getOfferTypeProject());
		this.setOfferTimeRoleAdded(sameProductionOffer.getOfferTimeRoleAdded());
		this.setOfferShootDate(sameProductionOffer.getOfferShootDate());
		this.setOfferTimeRoleAdded(sameProductionOffer.getOfferTimeRoleAdded());
		this.setOfferPostedTime(sameProductionOffer.getOfferPostedTime());
		this.setOfferCastingDirector(sameProductionOffer.getOfferCastingDirector());
		this.setRegion(sameProductionOffer.getRegion());
		this.seekingEthnicities = new boolean[SIZE_OF_ETHINICITIES_BUS];
		this.setCharacterGender('u');
		 this.labels = new ArrayList<String>();
		 for(int i=0; i<sameProductionOffer.labels.size(); ++i){
		 this.labels.add(new String(sameProductionOffer.labels.get(i)));
		 }	 
		 
		 
	}

	public Job copyOnlyProductionValues() {
		Job tempJob = new Job(this.getActorIDSubmitted());

		tempJob.setOfferPostedTime(this.getOfferPostedTime());
		tempJob.addToProductionDetails(this.getProductionDetails());
		tempJob.setOfferUnionStatus(this.getOfferUnionStatus());
		tempJob.setOfferLocation(this.getOfferLocation());
		 
		tempJob.setOfferTypeProject(this.getOfferTypeProject());
		tempJob.setOfferTimeRoleAdded(this.getOfferTimeRoleAdded());
		tempJob.setOfferShootDate(this.getOfferShootDate());
		tempJob.setOfferTimeRoleAdded(this.getOfferTimeRoleAdded());
		tempJob.setOfferPostedTime(this.getOfferPostedTime());
		tempJob.setOfferCastingDirector(this.getOfferCastingDirector());
		tempJob.setRegion(this.getRegion());
		tempJob.seekingEthnicities = new boolean[SIZE_OF_ETHINICITIES_BUS];
		tempJob.setCharacterGender('u');
		tempJob.labels = new ArrayList<String>();
		 for(int i=0; i<this.labels.size(); ++i){
			 tempJob.labels.add(new String(this.labels.get(i)));
		 }	 
		return tempJob;
	}

	public String getOfferId() {
		return offerId;
	};

	public void setOfferId(String newData) {
		offerId = newData;
	};

	public String getActorIDSubmitted() {
		return actorIDSubmitted;
	};

	public void setActorIDSubmitted(String newData) {
		actorIDSubmitted = newData;
	};

	public String getOfferRole() {
		return offerRole;
	};

	public void setOfferRole(String newData) {
		offerRole = newData;
	};

	public String getOfferProjectName() {
		if(offerProjectName == null) {
			return new String("");
		}
		return offerProjectName;
	};

	public void setOfferProjectName(String newData) {
		offerProjectName = newData;
	};

	public String getOfferShootDate() {
		return offerShootDate;
	};

	public void setOfferShootDate(String newData) {
		offerShootDate = new String (newData);
	};

	public String getOfferTypeProject() {
		return offerTypeProject;
	};

	public void setOfferTypeProject(String newData) {
		offerTypeProject = newData;
	};

	public String getOffertRate() {
		if(offerRate.length()<1){
			return "";
		}
		return offerRate;
	};

	public void setOffertRate(String newData) {
		offerRate = newData;
	};

	public String getOfferPaying() {
		return offerPaying;
	};

	public void setOfferPaying(String newData) {
		offerPaying = newData;
	};

	public String getOfferCastingDirector() {
		return offerCastingDirector;
	};

	public void setOfferCastingDirector(String newData) {
		offerCastingDirector = newData;
	};

	public String getOfferUnionStatus() {
		return offerUnionStatus;
	};

	public void setOfferUnionStatus(String newData) {
		offerUnionStatus = newData;
	};

	public String getOfferLocation() {
		return offerLocation;
	};

	public void setOfferLocation(String newData) {
		offerLocation = newData;
	};

	
	public String getOfferPostedTime() {
		return offerPostedTime;
	};

	public void setOfferPostedTime(String newData) {
		offerPostedTime = newData;
	};

	public String getOfferListing() {
		return offerListing;
	};

	public void setOfferTimeRoleAdded(String newData) {
		offerTimeRoleAdded = newData;
	};

	public String getOfferTimeRoleAdded() {
		return offerTimeRoleAdded;
	};

	public void setOfferSubmittionDateTime(String newData) {
		offerSubmittionDateTime = newData;
	};

	public String getLeadOrSupporting() {
		return offerLeadOrSupporting;
	};

	public void setLeadOrSupporting(String newData) {
		offerLeadOrSupporting = new String(newData);
	};
	
	 
	public String getOfferSubmittionDateTime() {
		return offerSubmittionDateTime;
	};

	public void setOfferListing(String newData) {
		offerListing = newData;
		String delims = "[/,\n]";
		String[] tokens = newData.split(delims);
		offerListingFirst = new String(tokens[0]);
		offerListingSex = new String(tokens[1]);
		offerListingEthnicity = new String(tokens[2]);
		offerListingAgesHint = new String(tokens[3]);
		for (int i = 4; i < tokens.length; i++) {
			offerListingNotes += new String(tokens[i]);
		}
	};


	public void setOfferBSGenderAndAge(String newData) {
		if(newData.length() < 1)
			 return;
		offerListing = newData;
		String delims = "[,]";
		String[] tokens = newData.split(delims);
		offerListingSex = (new String(tokens[0])).trim();
		offerListingAgesHint = (new String(tokens[1])).trim();
		if((offerListingAgesHint.length()<1)&&(offerListingSex.length()>1)){
			offerListingAgesHint = new String(offerListingSex);
		}
		for (int i = 2; i < tokens.length; i++) {
			this.addToCharacterDetails(new String(tokens[i]));
		}
		
	 
	}


	public void setOfferFRGenderAndAgeEthnicity(String newData) {
		 if(newData.length() < 1)
			 return;
		offerListing = newData;
		String delims = "[/]";
		String[] tokens = newData.split(delims);
		offerListingSex = (new String(tokens[1])).trim();
		this.setOfferListingEthnicity((new String(tokens[2])).trim());
		offerListingAgesHint = (new String(tokens[3])).trim();
		if((offerListingAgesHint.length()<1)&&(offerListingSex.length()>1)){
			offerListingAgesHint = new String(offerListingSex);
		}
		
			this.addToCharacterDetails(offerListing);
		
		
	 
	}

	
	public String getOfferCharacterName() {
		return offerCharacterName;
	};

	public void setOfferCharacterName(String data) {
		offerCharacterName = new String(data);
	};
	public String getOfferListingAgesHint() {
		return offerListingAgesHint;
	};

	public void setOfferListingAgesHint(String data) {
		offerListingAgesHint = new String(data);
	};
	public String getOfferCharacterDetails() {
		return offerCharacterDetails;
	};

	public void setOfferCharacterDetails(String data) {
		offerCharacterDetails = new String(data);
	};

	public String getNotice() {
		return notice;
	};

	public void setNotice(String newNotice) {
		notice = new String(newNotice);
	};

	public boolean getIsSag() {
		return isSag;
	};

	public void setIsSag(boolean newBit) {
		isSag = newBit;
	};

	public boolean getIsEthnicityMatch() {
		return isEthnicityMatch;
	};

	public void setIsEthnicityMatch(boolean newBit) {
		isEthnicityMatch = newBit;
	};

	public boolean getIsAge() {
		return isAge;
	};

	public void setIsAge(boolean newBit) {
		isAge = newBit;
	};
	
	public boolean getIsOnBlacklist() {
		return isOnBlacklist;
	};

	public void setIsOnBlacklist(boolean newBit) {
		isOnBlacklist = newBit;
	};
	
	

	public boolean getIsMatch() {
		return isGenderMatch;
	};

	public void setIsGenderMatch(boolean newBit) {
		isGenderMatch = newBit;
	};
	public boolean getIsGenderMatch() {
		return isGenderMatch;
	};
	
	public boolean getIsUnionMatch() {
		return isUnionMatch;
	};

	public void setIsUnionMatch(boolean newBit) {
		isUnionMatch = newBit;
	};

	public boolean getIsBackgroundWork() {
		return isBackgroundWork;
	};

	public void setIsBackgroundWork(boolean newBit) {
		isBackgroundWork = newBit;
	};

	public boolean getIsCar() {
		return isCar;
	};

	public void setIsCar(boolean newBit) {
		isCar = newBit;
	};

	public boolean getIsGuard() {
		return isGuard;
	};

	public void setIsGuard(boolean newBit) {
		isGuard = newBit;
	};

	public boolean getReqSizes() {
		return reqSizes;
	};

	public void setReqSizes(boolean newBit) {
		reqSizes = newBit;
	};

	public boolean getNeedTuxedo() {
		return needTuxedo;
	};

	public void setNeedTuxedo(boolean newBit) {
		needTuxedo = newBit;
	};

	public boolean getNeedPoiceUniform() {
		return needPoliceUniform;
	};

	public void setNeedPoliceUniform(boolean newBit) {
		needPoliceUniform = newBit;
	};

	public String getMessage() {
		return message;
	};

	public void setMessage(String newMessage) {
		message = newMessage;
	};

	public String getProductionDetails() {
		return offerProductionDetails;
	};

	public void addToProductionDetails(String data) {
		offerProductionDetails += (new String(data)).concat("| ");
	}

	public void addToCharacterDetails(String data) {
		offerCharacterDetails += (new String(data)).concat("| ");
	}
	
	public String getOfferListingEthnicity() {
		return offerListingEthnicity;
	}

	public void setOfferListingEthnicity(String data) {
		offerListingEthnicity = new String(data);
	}
		
	
	
	
	public String getInternalAAhref() {
		return message;
	};

	public void setInternalAAhref(String newMessage) {
		internalAAhref = new String(newMessage);
	};

	public String getInternalAAname() {
		return internalAAname;
	};

	public void setInternalAAname(String newMessage) {
		internalAAname = new String(newMessage);
	};

	public void addToMessage(String newMessage) {
		message += new String(message.concat(" ").concat(newMessage));
	};

	public boolean getDecisionSubmit() {
		return decisionSubmit;
	};

	public void setDecisionSubmit(boolean newBit) {
		decisionSubmit = newBit;
	};

	public boolean getPutInCart() {
		return putInCart;
	};

	public void setPutInCart() {
		putInCart = true;
	};

	public void takeOutOfCart() {
		putInCart = false;
	};

	/*
	 * public boolean getIsMaleCharacter() { return isMaleCharacter; };
	 * 
	 * public void setIsMaleCharacter(boolean newBit) { isMaleCharacter =
	 * newBit; }
	 */
	public boolean getHasBeenSubmitted() {
		return offerHasBeenSubmitted;
	};

	public void setHasBeenSubmitted(boolean newBit) {
		offerHasBeenSubmitted = newBit;
	};

	public int getNumberOfCharactersOnThisProduction() {
		return numberOfCharactersOnThisProduction;
	};

	public void setNumberOfCharactersOnThisProduction(int newBit) {
		numberOfCharactersOnThisProduction = newBit;
	};

	public int getRegion() {
		return region;
	};

	public void setRegion(int newBit) {
		region = newBit;
	};

	public int getTotalAddedToCart() {
		return totalAddedToCart;
	};

	public void setTotalAddedToCart(int newBit) {
		totalAddedToCart = newBit;
	};

	public char getCharacterGender() {
		return characterGender;
	};

	public void setCharacterGender(char gender) {
		switch (gender) {
		case 'm':
			this.characterGender = 'm';
			return;
		case 'f':
			this.characterGender = 'f';
			return;
		case 'b':
			this.characterGender = 'b';
			return;

		}
		characterGender = 'u';
	};

	public char getCharacterUnionDemand() {
		return characterUnionDemand;
	};

	public void setCharacterUnionDemand(char unionDemand) {
		switch (unionDemand) {
		case 'u':
			this.characterUnionDemand = 'u';
			return;
		case 'n':
			this.characterUnionDemand = 'n';
			return;
		case 'b':
			this.characterUnionDemand = 'b';
			return;

		}
		this.characterUnionDemand = 'n';
	};

	public void makeDecisionAA() {
		this.setDecisionSubmit(false);

		// DECISION PARAMS

		if ((this.getIsGenderMatch()) && (!this.getIsCar()) && (this.getIsEthnicityMatchCN())
				&& (this.getIsUnionMatch()) && (this.getIsAge())&& (!this.getIsOnBlacklist())) {

			this.setDecisionSubmit(true);

		}
	}

	public void makeDecisionBS(){
	//	this.setDecisionSubmit(true);
		// DECISION PARAMS
				if ((this.getIsGenderMatch()) && (!this.getIsCar()) && (this.getIsEthnicityMatchCN())
						&& (this.getIsUnionMatch()) && (this.getIsAge())&& (!this.getIsOnBlacklist())) {
					this.setDecisionSubmit(true);
				}
	
	
	}
	
	public void makeDecisionCN() {
		// this.setDecisionSubmit(true);

		// DECISION PARAMS
		if ((this.getIsGenderMatch()) && (!this.getIsCar()) && (this.getIsEthnicityMatchCN())
				&& (this.getIsUnionMatch()) && (this.getIsAge())&& (!this.getIsOnBlacklist())) {
			this.setDecisionSubmit(true);
		}
	}

	public void loadNoticesFromFile() {
		// read file

		List<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			reader.close();
			return;
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return;
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public boolean sameJob(Job otherJob) {
		// returns true if the two Jobs have the same first 15 chars notice.
		if ((this.notice).contains(otherJob.getNotice().subSequence(1, 100))) {
			return true;
		}
		return false;
	}

	public boolean isSameOfferCN(Job otherJob) {
		if (((this.getOfferProjectName()).equals(otherJob.getOfferProjectName()))
				&& ((this.getOfferRole()).equals(otherJob.getOfferRole()))) {
			return true;
		}
		return false;
	}

	public boolean isSameOfferAA(Job otherJob) {
		if (((this.getOfferPostedTime()).equals(otherJob.getOfferProjectName()))
				&& ((this.getOfferPostedTime()).equals(otherJob.getOfferRole()))) {
			return true;
		}

		return false;
	}

	public boolean offerHasBeenConsideredBeforeCN(List<Job> allJobs) {
		// checkcing in the list of Jobs for another offer with the same ROLE
		// and same PROJECT NAME values.

		for (Job offer : allJobs) {

			if (((this.getOfferProjectName()).equals(offer.getOfferProjectName()))
					&& ((this.getOfferRole()).equals(offer.getOfferRole())) && (!offer.getHasBeenSubmitted())
					&& ((this.getActorIDSubmitted()).contains(offer.getActorIDSubmitted()))) {
				Logging.slog(
						"Found that this Project and role has already been considered for this actor and decided NOT to submit. This is Why: ");
				Logging.printDecisionMakingVars(this);
				return true;
			}
		}
		return false;
	}

	public boolean offerHasBeenConsideredBeforeAA(List<Job> allJobs) {
		// checkcing in the list of Jobs for another offer with the PROJECT NAME
		// values and same ACTOR ID values.
		if(this.getOfferProjectName().length() <1){
			Logging.slog(
					"BUG - the project does not have a name. So we will act as if it was already considered before.");
		
			return true; 
		}
			
		for (Job offer : allJobs) {
			if (((this.getOfferProjectName()).equals(offer.getOfferProjectName())) && (!offer.getHasBeenSubmitted())
					&& ((this.getActorIDSubmitted()).contains(offer.getActorIDSubmitted()))) {
				Logging.slog(
						"Found that this Project has already been considered and decided NOT to submit. This is Why: ");
				Logging.printDecisionMakingVars(offer);
				return true;
			}
		}
		return false;
	}

	public void unionMatchingUpdate(Actor human) {
		this.setIsUnionMatch(false);
		switch (human.getUnionStatusChar()) {
		case 'u':
			if (((this.getCharacterUnionDemand()) == 'u') || ((this.getCharacterUnionDemand()) == 'b')) {
				this.setIsUnionMatch(true);
			}
			return;
		case 'n':
			if (((this.getCharacterUnionDemand()) == 'n') || ((this.getCharacterUnionDemand()) == 'b')) {
				this.setIsUnionMatch(true);
			}
			return;
		case 'b':
			this.setIsUnionMatch(true);
		}
	}

	public void genderMatchingUpdate(Actor human) {
	 
		this.setIsGenderMatch(false);
		switch (this.getCharacterGender()) {
		case 'm':
			if (human.getGenderIsMale()) {
				this.setIsGenderMatch(true);
			}
			return;
		case 'f':
			if (!human.getGenderIsMale()) {
				this.setIsGenderMatch(true);
			}
			return;
		case 'b':
			this.setIsGenderMatch(true);
			return;
		case 'u':
			// HERE we must decide upon the user profile settings. Should we
			// submit anyway?
			// For debug reasons : now we will say that Unknown gender IS a
			// match
			this.setIsGenderMatch(false);
		}
	}
	
	
	 
	public void ethnicityMatchingUpdate(Actor human) {

		if (this.seekingEthnicities[posOfChar('z')]) {
			Logging.slog("Welcoming all ethnicities.");
			this.setIsEthnicityMatch(true);
			return;
		}

		if (!this.atLeastOneEthnicityChosen()) {
			Logging.slog("Error - No ethnicity chosen for offer. So we will choose ALL ETHNICITIES");
			this.seekingEthnicities[posOfChar('z')] = true;
			this.setIsEthnicityMatch(true);
			return;
		}

		this.setIsEthnicityMatch(false);

		if (((human.getEthinicity()).contains("african american")) && (this.seekingEthnicities[posOfChar('a')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).contains("caucasian")) && (this.seekingEthnicities[posOfChar('c')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).equals("asian")) && (this.seekingEthnicities[posOfChar('s')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).contains("indian")) && (this.seekingEthnicities[posOfChar('i')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).contains("latin")) && (this.seekingEthnicities[posOfChar('l')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).contains("middle eastern")) && (this.seekingEthnicities[posOfChar('m')])) {
			this.setIsEthnicityMatch(true);
		}

	}

	public void setSeekingEthnicities(String UpperCaseData) {
		String data = (new String(UpperCaseData)).toLowerCase();
		if (data.contains("african american")) {
			this.seekingEthnicities[posOfChar('a')] = true;
		}
		if (data.contains("caucasian")) {
			this.seekingEthnicities[posOfChar('c')] = true;
		}
		if (data.contains("asian")) {
			this.seekingEthnicities[posOfChar('s')] = true;
		}
		if (data.contains("indian")) {
			this.seekingEthnicities[posOfChar('i')] = true;
		}
		if (data.contains("latin")) {
			this.seekingEthnicities[posOfChar('l')] = true;
		}
		if (data.contains("middle eastern")) {
			this.seekingEthnicities[posOfChar('m')] = true;
		}
		if (data.contains("all ethnicities")) {
			this.seekingEthnicities[posOfChar('z')] = true;
		}
	}

	private int posOfChar(char data) {
		int bitPos = (((int) data) - ((int) 'a'));
		return (bitPos);
	}

	public boolean atLeastOneEthnicityChosen() {
	 
		for (int i = 0; i < SIZE_OF_ETHINICITIES_BUS; ++i) {
			if ((this.seekingEthnicities[i]) == true) {
				return true;
			}
		}

		return false;

	}

	public boolean getIsEthnicityMatchCN() {
		return true; // this is for debug
	}

	static public Job renewOffer(Job currentOffer) {

		Job tempOffer = new Job(currentOffer.getActorIDSubmitted());
		tempOffer = currentOffer.copyOnlyProductionValues();
		// calling the Copy C'or of Job
		return tempOffer;

	}

	public void calcTimeFromAddedToSubmitted() {
		String roleAdded = new String(this.getOfferTimeRoleAdded());
		String roleSumitted = new String(this.getOfferPostedTime());
	
		String nowTime1 = new String(ManageDriver.findLATime());
		Logging.slog("CALC TIMES: LAtime:" + nowTime1 + "|roleAdded: " + roleAdded + "|rolePosed:" + roleSumitted);
	 	
	 
	}
}
