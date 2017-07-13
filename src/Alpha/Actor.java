package Alpha;

public class Actor {
	static public int NUMBER_OF_REGIONS = 15;

	private String actorId;
	private String aaUsername;
	private String aaPassword;
	private String bsUsername;
	private String bsPassword;

	private String cnUsername;
	private String cnPassword;
	
	
	// private Logging actorsLog;

	public boolean isNightShift;
	private String name = "";
	private String email = "";
	private String phone = "";
	private String billing ="";
	public String dateOfBirth = "";
	public int minActingAge = 0;
	public int maxActingAge = 0;
	public boolean hasCar = false;
	public String carModel = "";
	public String carYear = "";
	public String standardTalentNote = "";
	private char ethnicity;
	private char unionStatus;
	public int paymentMin = 0;
	private boolean genderIsMale;
	private boolean confirmedBilling;
	private String humansSizes = "";

	private int humansMinActingAge = 0;
	private int humansMaxActingAge = 0;
	private String defaultTalentNotesCN = "";
	private String defaultTalentNotesAA = "";
	private String humanDefaultPhoto = "";
	private String humanDefaultVideo = "";
	private boolean[] targetRegions;
	private String blackList = "";
	private String onlySagProductions = "";
	private String bookedDates = "";
	
	

	public Actor(String actorId, String name, String phone, String email, String billingAck, String aaUserName,
			String aaPassword, String bsUserName, String bsPassword,  String cnUsername, String cnPassword,boolean isNightShift, boolean humanIsMale, String humanEthnicity, String unionStatus,
			String humanSizes, int minActingAge, int maxActingAge, String humanDefaultPhoto, String humanDefaultVideo,String defaultNotesCN,String defaultNotesAA,
			String targetRegions, String blackList, String only_sag_productions, String bookedDates) {
		this.actorId = actorId;
		this.setName(new String(name));
		this.setPhone(new String(phone));
		this.setEmail(new String(email));
		this.setBilling(new String(billingAck));
		
		this.setAaUsername(new String(aaUserName));
		this.setAaPassword(new String(aaPassword));
		this.setBsUsername(new String(bsUserName));
		this.setBsPassword(new String(bsPassword));	
		this.setCnUsername(new String(cnUsername));
		this.setCnPassword(new String(cnPassword));
		this.setIsNightShift(isNightShift);
		this.genderIsMale = humanIsMale;
		this.setEthinicity(humanEthnicity);
		this.setUnionStatus(unionStatus);
		this.humansSizes = new String(humanSizes);
		this.humansMinActingAge = minActingAge;
		this.humansMaxActingAge = maxActingAge;
		this.defaultTalentNotesCN = new String(defaultNotesCN);
		this.defaultTalentNotesAA = new String(defaultNotesAA);
		this.humanDefaultPhoto = new String(humanDefaultPhoto);
		this.humanDefaultVideo = new String(humanDefaultPhoto);
		
		this.targetRegions = new boolean[NUMBER_OF_REGIONS];
		this.setTargetRegions(targetRegions);
		this.blackList = new String(blackList);
		this.onlySagProductions = new String(only_sag_productions);
		this.bookedDates = new String(bookedDates);
	}

	public Actor() {
	};

	public Actor(Actor realActor) {
		// this copy C'tor is used to create a nightShift actor with the same
		// profile as the read actor
		this.isNightShift = true;
		this.dateOfBirth = new String(realActor.dateOfBirth);
		this.minActingAge = realActor.minActingAge;
		this.maxActingAge = realActor.maxActingAge;
		this.hasCar = realActor.hasCar;
		this.carModel = new String(realActor.carModel);
		this.carYear = new String(realActor.carYear);
		this.standardTalentNote = new String(realActor.standardTalentNote);
		this.ethnicity = realActor.ethnicity;
		this.paymentMin = realActor.paymentMin;
		this.genderIsMale = realActor.genderIsMale;
		this.confirmedBilling = realActor.confirmedBilling;
	}
	   
	public String getName() {
		return name;
	}

	public void setName(String data) {
		this.name = data;
	}
	
	public String getBilling() {
		return billing;
	}

	public void setBilling(String data) {
		this.billing = data;
	}
	
	
	

	public String getCnUsername() {
		return cnUsername;
	}

	public void setCnUsername(String cnUsername) {
		this.cnUsername = cnUsername;
	}

	public String getCnPassword() {
		return cnPassword;
	}

	public void setCnPassword(String cnPassword) {
		this.cnPassword = cnPassword;
	}

	public String getAaUsername() {
		return aaUsername;
	}

	public void setAaUsername(String aaUsername) {
		this.aaUsername = aaUsername;
	}

	public String getBsPassword() {
		return bsPassword;
	}

	public void setBsPassword(String bsPassword) {
		this.bsPassword = bsPassword;
	}

	
	
	public String getBsUsername() {
		return new String(bsUsername);
	}

	public void setBsUsername(String bsUsername) {
		this.bsUsername = bsUsername;
	}
	public String getActorId() {
		return actorId;
	}

	public void setActorId(String id) {
		this.actorId = id;
	}

	public String getDefaultTalentNotesCN() {
		return new String(this.defaultTalentNotesCN);
	}

	public void setDefaultTalentNotesCN(String data) {
		this.defaultTalentNotesCN = new String(data);
	}
	
	public String getDefaultTalentNotesAA() {
		return new String(this.defaultTalentNotesAA);
	}

	public void setDefaultTalentNotesAA(String data) {
		this.defaultTalentNotesAA = new String(data);
	}

	public String getAaPassword() {
		return aaPassword;
	}

	public void setAaPassword(String aaPassword) {
		this.aaPassword = aaPassword;
	}

	public String getHumansSizes() {
		return humansSizes;
	}

	public void getHumansSizes(String size) {
		this.humansSizes = new String(size);
	}

	public int getHumansMinActingAge() {
		return humansMinActingAge;
	}

	public void setHumansMinActingAge(int data) {
		this.humansMinActingAge = data;
	}

	public int getHumansMaxActingAge() {
		return humansMaxActingAge;
	}

	public void setHumansMaxActingAge(int data) {
		this.humansMaxActingAge = data;
	}

	public boolean getGenderIsMale() {
		return genderIsMale;
	}

	public void setGenderIsMale(boolean bitData) {
		this.genderIsMale = bitData;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String data) {
		this.email = cnUsername;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String data) {
		this.phone = data;
	}

	public String getDefaultPhoto() {
		return humanDefaultPhoto;
	}

	public void setDefaultPhoto(String data) {
		this.humanDefaultPhoto = data;
	}

	public String getBlackList() {
		return blackList;
	}

	public void setBlackList(String data) {
		this.blackList = data;
	}
	
	
	public String getOnly_sag_productions() {
		return new String(onlySagProductions);
	}

	public void setOnly_sag_productions(String data) {
		this.onlySagProductions = data;
	}
	
	public String getBookedDates() {
		return new String(bookedDates);
	}

	public void setBookedDates(String data) {
		this.bookedDates = data;
	}
	
	
	

	public String getEthinicity() {
		switch (this.ethnicity) {
		case 'a':
			return new String("african american");
		case 'c':
			return new String("caucasian");
		case 's':
			return new String("asian");
		case 'i':
			return new String("indian");

		case 'l':
			return new String("latin");
		case 'm':
			return new String("middle eastern");
		case 'z':
			return new String("all ethnicities");
		}
		return "";
	}

	public char getEthinicityChar() {
		switch (this.ethnicity) {
		case 'a':
			return this.ethnicity;
		case 'c':
			return this.ethnicity;
		case 's':
			return this.ethnicity;
		case 'i':
			return this.ethnicity;

		case 'l':
			return this.ethnicity;
		case 'm':
			return this.ethnicity;
		case 'z':
			return this.ethnicity;
		}
		return '_';
	}

	public void setEthinicity(String humanEthnicity) {

		if (humanEthnicity.equals(new String("african american"))) {
			this.ethnicity = 'a';
		}
		if (humanEthnicity.equals(new String("caucasian"))) {
			this.ethnicity = 'c';
		}
		if (humanEthnicity.equals(new String("asian"))) {
			this.ethnicity = 's';
		}
		if (humanEthnicity.equals(new String("indian"))) {
			this.ethnicity = 'i';
		}
		if (humanEthnicity.equals(new String("latin"))) {
			this.ethnicity = 'l';
		}
		if (humanEthnicity.equals(new String("middle eastern"))) {
			this.ethnicity = 'm';
		}
		if (humanEthnicity.equals(new String("all ethnicities"))) {
			this.ethnicity = 'z';
		}
	}

	public String getUnionStatusString() {
		switch (this.unionStatus) {
		case 'u':
			return new String("union");
		case 'n':
			return new String("non-union");
		case 'b':
			return new String("both");

		}
		return "";
	}

	public char getUnionStatusChar() {
		switch (this.unionStatus) {
		case 'u':
			return (this.unionStatus);
		case 'n':
			return (this.unionStatus);
		case 'b':
			return (this.unionStatus);

		}
		return ' ';
	}

	public void setUnionStatus(String unionStatus) {

		if (unionStatus.equals(new String("union"))) {
			this.unionStatus = 'u';
		}
		if (unionStatus.equals(new String("non-union"))) {
			this.unionStatus = 'n';
		}
		if (unionStatus.equals(new String("both"))) {
			this.unionStatus = 'b';
		}
	}

	public boolean getIsNightShift() {
		return isNightShift;
	}

	public void setIsNightShift(boolean bitData) {
		this.isNightShift = bitData;
	}

	public void setTargetRegions(String regions) {
		if (regions.length() < 1) {
			Logging.slog("No regions selected in JSON");
			return;
		}
		if (regions.contains("los angeles")) {
			targetRegions[ClientsMngt.regionToInt("los angeles")] = true;
		}
		if (regions.contains("new york")) {
			targetRegions[ClientsMngt.regionToInt("new york")] = true;
		}
		if (regions.contains("vancouver")) {
			targetRegions[ClientsMngt.regionToInt("vancouver")] = true;
		}
		if (regions.contains("chicago")) {
			targetRegions[ClientsMngt.regionToInt("chicago")] = true;
		}
		if (regions.contains("florida")) {
			targetRegions[ClientsMngt.regionToInt("florida")] = true;
		}
		if (regions.contains("toronto")) {
			targetRegions[ClientsMngt.regionToInt("toronto")] = true;
		}
		if (regions.contains("texas")) {
			targetRegions[ClientsMngt.regionToInt("texas")] = true;
		}
		if (regions.contains("hawaii")) {
			targetRegions[ClientsMngt.regionToInt("hawaii")] = true;
		}
		if (regions.contains("southeast")) {
			targetRegions[ClientsMngt.regionToInt("southeast")] = true;
		}
		if (regions.contains("vancouver")) {
			targetRegions[ClientsMngt.regionToInt("vancouver")] = true;
		}
		if (regions.contains("mountain region")) {
			targetRegions[ClientsMngt.regionToInt("mountain region")] = true;
		}
		
		

	}

	public boolean[] getTargetRegions() {
		return this.targetRegions;
	}

	public boolean atLeastSomeRegionChoosen() {
		boolean atLeastOneChoosen = false;
		
		for (int i = 0; i < Actor.NUMBER_OF_REGIONS; ++i) {
			if (this.targetRegions[i]) {
				atLeastOneChoosen = true;
			}
		}
		return atLeastOneChoosen;
	}
}
