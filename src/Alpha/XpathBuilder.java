package Alpha;

public class XpathBuilder {
	static public String xpRedcheckboxLocation(int rowNum) {
		if (rowNum == 0) {
			return (new String(
					".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[2]/td[1][@class='submitted']/img[@src='/gui/check.gif']"));
		}

		String leftPart = ".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[";
		String rightPart = "]/td[1][@class='submitted']/img[@src='/gui/check.gif']";
		String xPath = new String((new String(leftPart)).concat(String.valueOf(rowNum)).concat(rightPart));
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String tabCharNameAndDetails(int row) {
		// IMPORTANT : FOR the top character NAME + DETAILS below ( ROW ==0 )
		// you must parse the tokens and TAKE THE TOP TWO TOKENS //[|//>|//<
		// /html/body/div[2]/table[2]/tbody/tr/td/p[ (2 * (X)) ]/a
		int twiceRow = row * 2;
		if (row == 0) {
			// Logging.slog(".//*[@id='mainContent']/table[2]/tbody/tr/td");
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td"));
		}
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]";
		String xPath = new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		// Logging.slog(xPath);
		return (xPath);

	}

	static public String xpBetaCharacterName(int row) {
		if (row == 0) {
			// Logging.slog(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']");
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']"));
		}
		int twiceRow = row * 2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']";
		String xPath = new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		// Logging.slog(xPath);
		return (xPath);

	}

	static public String tabAAname(int row) {
		// returns the internal int key value assigned to the role in Actors
		// Access.
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']"));
		}
		int twiceRow = row * 2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']";
		String xPath = new String(((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart)));
		// Logging.slog(xPath);
		return (xPath);

	}

	static public String xpInternalAAhref(int row) {
		if (row == 0) {
			// return (new
			// String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']/@href"));
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']"));
		}
		int twiceRow = row * 2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		// String rightPart = "]/a[@class='breakdown-open-add-role']/@href";
		String rightPart = "]/a[@class='breakdown-open-add-role']";
		String xPath = new String(((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart)));
		// Logging.slog(xPath);
		return (xPath);

	}

	static public String tabAAclass(int row) {
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a"));
		}

		// The second character would have the class attribute at:
		// .//*[@id='mainContent']/table[2]/tbody/tr/td/p[2]/a
		int twiceRow = row * 2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a";
		String xPath = new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpAAtabProductionInRow(int row) {
		// ROW+2
		int rowPlusTwo = row + 2;
		String leftPart = ".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[";
		// String leftPart = ".//*[@id='mainContent']/div[5]/table/tbody/tr[";
		String rightPart = "]/td[1][@class='submitted']";
		String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart)));
		// // Logging.slog(xPath);
		return (xPath);

	}

	static public String tabRedCheckBoxPos(int row) {
		// ROW+2
		int rowPlusTwo = row + 2;
		String leftPart = ".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[";
		String rightPart = "]/td[1][@class='submitted']/img[@src='/gui/check.gif']";
		// Logging.slog((new
		// String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));
		String xPath = new String((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));
		return (xPath);
	}

	static public String xpAALinkCharactersInProduction(int row) {

		int rowPlusTwo = row + 2;
		String leftPart = ".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[";
		String rightPart = "]/td[3]/a[starts-with(@href,'/projects/')]";

		String xPath = new String((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpCNWelcomeHeader() {

		String xPath = "//div[@id='maininfo']/h2";

		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpBStabProductionInRow(int row) {
		 //  .//*[@id='main__container']/div/div[3]/div/div[@class='row casting__listing'][row]
		int rowPlusOne = row + 1;
		String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[@class='row casting__listing'][";
		 
		String rightPart = "]";
		String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
	//	Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpBSProductionFirstBlob(int row) {
	 	 
		int rowPlusOne = row + 1;
		String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
		String rightPart = "]/div[1]/div[@class='prod__desc']/span";
		String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
	//	Logging.slog(xPath);
		return (xPath);
	}
	
	
	static public String xpBSAppleidBefore(int row) {
	 	 //.//*[@id='main__container']/div/div[3]/div/div[2]/div[1]/div[3]/a
		int rowPlusOne = row + 1;
		String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
		String rightPart = "]/div[1]/div[@class='prod__desc']/span";
		String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
	//	Logging.slog(xPath);
		return (xPath);
	}
	
	
	static public String xpBSProductionTimeLocationTop(int row) {
	 	int rowPlusOne = row + 1;
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
			 
			String rightPart = "]/div[1]/ul[@class='prod__dates']/li[2]/span";
			String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
	//		 Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSProductionTimeLocationBottom1(int row) {
	 	int rowPlusOne = row + 1;
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
			 
			String rightPart = "]/div[1]/ul[@class='prod__dates']/li[3]/span[1]";
			String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
	//		Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSProductionTimeLocationBottom2(int row) {
	 	int rowPlusOne = row + 1;
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
			 
			String rightPart = "]/div[1]/ul[@class='prod__dates']/li[3]/span[2]";
			String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
	//		Logging.slog(xPath);
			return (xPath);
		}
	static public String xpBSLabelList(int prodRow, int numLabel){
int row = prodRow + 1;
 
//		".//*[@id='main__container']/div/div[3]/div/div[1]/div[1]/div[1]/div[1]/div/a[1]";
		String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
		String midPart = "]/div[1]/div[1]/div[1]/div/a[";
		String rightPart = "]";
		String xPath = new String((new String(leftPart)).concat(String.valueOf(row).concat(midPart).concat(String.valueOf(numLabel)).concat(rightPart)));
	//	 Logging.slog(xPath);
		return (xPath);
		 
	}
	
	static public String xpBSProductionName(int prodRow){
 
		int row= prodRow + 1;
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
			String rightPart = "]/div[1]/h3[@class='prod__title']/a[starts-with(@href,'/casting')]";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
		//	Logging.slog(xPath);
			return (xPath);
		}
	
	
	
	
	static public String xpBSLinkCharactersInProduction(int prodRow){
 		return xpBSProductionName(prodRow);
				}
	
	static public String xpBSOpennedProductionPage(){

					String xPath = new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/h3/span");
		//			Logging.slog(xPath);
					return (xPath);
				}
	
	

	static public String xpBSClickSearchJobs(int option){
	//	String xPath =new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/h3/span");
		String xPath = ""; 
		if(option==0){
			//click NY-search
			xPath = new String(".//*[@id='main__container']/div/div[2]/div[2]/div/div/div/div/div[2]/a");
		}
	//	Logging.slog(xPath);
		return (xPath);
	}

	
	
	
	
	
	static public String xpBSInputLoginButton(){

		String xPath = new String("//input[@value='Log In']");
	//	Logging.slog(xPath);
		return (xPath);
	}

	
	static public String xpBSVerifyLocationCharactersTableOp1(){
	//	String xPath =new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/h3/span");
		String xPath = new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[5]/div/div/h3/span");
	//	Logging.slog(xPath);
		return (xPath);
	}

	
	static public String xpBSVerifyLocationCharactersTableOp4(){
		String xPath =new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/h3");
	 	return (xPath);
	}
	
	
	
	static public String xpBSVerifyLocationCharactersTableOp3(){
		String xPath =new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[5]/div/div/h3");
	 	return (xPath);
	}
	
	static public String xpBSVerifyLocationCharactersTableOp2(){
		String xPath =new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/h3/span");
	//	String xPath = new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[5]/div/div/h3/span");
	//	Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpBSVerifySuccessfulSubmissionOKButton(){

		String xPath = new String(".//button[@id='clear-themes']");
		//Logging.slog(xPath);
		return (xPath);
	}

	static public String xpBSProductionExpires() {
		String xPath = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[2]/div/div/div[3]/span/span[2][@class='expires-text--date']";
		// Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpBSProductionPay() {
		//   .//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[7]/div/div/p/span
		String xPath = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[7]/div/div/p/span";
		// Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpBSAdditional_instructions() {
		String xPath = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/p/span";
		// Logging.slog(xPath);
		return (xPath);
	}
	 
	
	static public String xpBSClickNYSearchButton() {
		String xPath = new String("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3");
		return (xPath);
	}
	
	static public String xpBSClickBottomButton() {
		String xPath =  new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[11]/button");
                          //		.//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[10]/button[@class='btn-blue-lg']
		if(Beta.verifyLocation(".//div/button[@class='btn-blue-lg']", new String("Apply"))){
			xPath =  new String(".//div/button[@class='btn-blue-lg']");

		}
			return xPath;
		
	}
	


	static public String xpBSClickRightOfRoleAppplyButton(int roleID) {
		//   old    .//div[1]/div[starts-with(@id,'role')]/div/div/div[2]/a[contains(text(),'Apply')]

		//   .//*[@id='role600153']                   /div/div/div[2]/div/a[1]
		if (roleID <1)
			return "";
		int roleIdFound = roleID;
			String leftPart = ".//div[starts-with(@id,'role";
		//old 	String rightPart = "]/div[starts-with(@id,'role')]/div/div/div[2]/a[contains(text(),'Apply')]";
			String rightPart = "')]/div/div/div[2]/div/a[1][contains(text(),'Apply')]";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(roleIdFound)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		
	}
	
	

	static public String xpBSClickRightOfRoleAppplyButton(String roleID) {
		//   old    .//div[1]/div[starts-with(@id,'role')]/div/div/div[2]/a[contains(text(),'Apply')]

		//   .//*[@id='role600153']                   /div/div/div[2]/div/a[1]
		if (roleID.length() <1)
			return "";
		String roleIdFound = new String(roleID);
			String leftPart = ".//div[starts-with(@id,'";
		//old 	String rightPart = "]/div[starts-with(@id,'role')]/div/div/div[2]/a[contains(text(),'Apply')]";
			String rightPart = "')]/div/div/div[2]/div/a[1][contains(text(),'Apply')]";
		 
			String xPath = new String((new String(leftPart)).concat(roleIdFound).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		
	}
	
	
	static public String xpBSAlreadyLoggedIn(){

		String xPath = new String(".//*[@id='accountLabel']/text()");
		Logging.slog(xPath);
		return (xPath);
		
	}
static public String xpCNRoleAddedTime() {
		
		String xPath = new String("//table[5]/tbody/tr[3]/td");
		return (xPath);
	}
	
	
static public String xpTalentNotesBS() {
		
		String xPath = new String(".//div/textarea[starts-with(@class,'form-control')]");
		return (xPath);
	}
	
static public String xpFindRoleIDsBS(int roleRow) {
	int row = roleRow+1;
	// .//div[2]/div[starts-with(@id,'role')]
	
	
	String leftPart = ".//div[";
	String rightPart = "]/div[starts-with(@id,'role')]";
 
	String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
//	Logging.slog(xPath);
	return (xPath);

}

	
	

	
	static public String xpBSApplyNowButton() {
	// old	String xPath = new String(".//*[@id='main__container']/div[2]/div[2]/div[10]/button[2]");
		 	String xPath = new String(".//*[@id='main__container']/div[2]/div[2]/div[10]/button[2]");
		return (xPath);
	}
	
	
	
	
	
	static public String xpBSAppliedBefore(int rowNum){
		 	 //    .//*[@id='main__container']/div/div[3]/div/div[2]/div[1]/div[3]/a
		//         .//*[@id='main__container']/div/div[3]/div/div[4]/div[1]/div[3]/a
		
		String row_pos = String.valueOf(rowNum+1);
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";

			String rightPart = "]/div[1]/div[3]/a";
		 
			String xPath = new String((new String(leftPart)).concat(row_pos).concat(rightPart));
 
			return (xPath);
		
	  
	 
		}
	
	
	//  xpBSCharacterId
	
	static public String xpBSCharacterName(int roleId){
			if(roleId <1)
				return "";
			
			String role =  String.valueOf(roleId);
			
			return xpBSCharacterName(role);
	
	}
	
	static public String xpBSCharacterName(String roleId){
		//   .//button[@data-target='#role603204']/div/span/text()[1]
		//    .//button[@[@data-target='# 
		//          ']/div/span/text()
		if (roleId.length()< 1){
				return "";
			}
			String id = new String (roleId.trim());
				String leftPart = ".//button[@data-target='#";

				String rightPart = "']/div/span";
			 
				String xPath = new String((new String(leftPart)).concat(id).concat(rightPart));
	//			Logging.slog(xPath);
				return (xPath);
			}

	static public String xpBSCharacterLeadOrSupportingId(String roleId){
		//   .//button[@data-target='#role603217']/div/span[1]/text()[2]
		//    .//button[@[@data-target='# 
		//          ']/div[1]/span[1]/span[1]
		if (roleId.length()< 1){
				return "";
			}
			String id = new String (roleId.trim());
				String leftPart = ".//button[@data-target='#";

				String rightPart = "']/div/span";
				String xPath = new String((new String(leftPart)).concat(id).concat(rightPart));
		//		Logging.slog(xPath);
				return (xPath);
			}
	
	
	static public String xpBSrequiredMediaId(String roleId){
		//  .//*[@id='role604496']/div/div/div[1]/div[2]/p[2]/text()
	 	if (roleId.length()< 1){
				return "";
			}
			String id = new String (roleId.trim());
				String leftPart = ".//*[@id='";		 
				String rightPart = "']/div/div/div[1]/div[2]/p[2]";
				String xPath = new String((new String(leftPart)).concat(id).concat(rightPart));
		//		Logging.slog(xPath);
				return (xPath);
			}
	
	
	
	
	static public String xpBSCharacterGenderAndAgeId(String roleId){
//		.//button[@data-target='#role604510']/div/span[2]

		if (roleId.length()< 1){
				return "";
			}
			String id = new String (roleId.trim());
				String leftPart = ".//button[@data-target='#";

				String rightPart = "']/div/span[2]";
				String xPath = new String((new String(leftPart)).concat(id).concat(rightPart));
		//		Logging.slog(xPath);
				return (xPath);
			}
	
	
		 
	
	static public String xpBSCharacterLeadOrSupporting(int prodRow){
		 
		int row= prodRow + 1;
		String leftPart = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/div[";	
			String rightPart = "]/div[1]/button/div[1]/span[1]/span[2]";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
	//		Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSCharacterGenderAndAge(int prodRow){
		 
		int row= prodRow + 1;
			String leftPart = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/div[";
			String rightPart = "]/div[1]/button/div[1]/span[2][@class='details']";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
	//		Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSCharacterMoreDetails(int prodRow){
	//	.//*[@id='role545117']/div/div/div[1]/div[1]/span/span[starts-with(@data-reactid,'.')]
		int row= prodRow + 1;
			String leftPart = "//div[6]/div/div/div[";

			String rightPart = "]/div[2]/div/div/div/div/span/span[starts-with(@data-reactid,'.')]";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
		//	Logging.slog(xPath);
			return (xPath);
		}
	

	static public String xpBSCharacterMoreDetailsId(String roleId){
		
		
	//	./div/div/div[1]/div[1]/span 
		
		
	//   
		if (roleId.length()< 1){
			return "";
		}
		String id = new String (roleId);
			String leftPart = ".//*[@id='";
			String rightPart = "']/div/div/div[1]/div[1]/span";
			String xPath = new String((new String(leftPart)).concat(id).concat(rightPart));
			//Logging.slog(xPath);
			return (xPath);
		}

 
	
	
	static public String xpBSCharacterRequestedEthnicityId(String roleId){
//  	.//*[@id='role604496']/div/div/div[1]/div[2]/p[1]/text()
		if (roleId.length()< 1){
			return "";
		}
		String id = new String (roleId);
			String leftPart = ".//*[@id='";
			String rightPart = "']/div/div/div[1]/div[2]/p[1]";
			String xPath = new String((new String(leftPart)).concat(id).concat(rightPart));
		//	Logging.slog(xPath);
			return (xPath);
		}
	

	
	
	static public String xpBSCharacterRequestedEthnicity(int prodRow){
		//div[6]/div/div/div/div[2]/div/div/div[1]/div[2]/p[1]/span[2][starts-with(@data-reactid,'.')]

		int row= prodRow + 1;
			String leftPart = "//div[6]/div/div/div[";
			String rightPart = "]/div[2]/div/div/div[1]/div[2]/p[1]/span[2][starts-with(@data-reactid,'.')]";	 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
//			Logging.slog(xPath);
			return (xPath);
		}

	static public String xpBSCharacterApplyButton(int prodRow){
		//div[6]/div/div/div/div[2]/div/div/div[1]/div[2]/p[1]/span[2][starts-with(@data-reactid,'.')]

		int row= prodRow + 1;
			String leftPart = "//div[6]/div/div/div[";
			String rightPart = "]/div[2]/div/div/div[1]/div[2]/p[1]/span[2][starts-with(@data-reactid,'.')]";	 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
	//		Logging.slog(xPath);
			return (xPath);
		}
	
	
	static public String xpCNStarPositionBG(int row) {
		String xPath = ((new String("//div[@id='DirectCastMainDiv']/table/tbody/tr[")).concat(String.valueOf(row)))
				.concat("]/td/span/img");
		return (xPath);
	}

	static public String xpCNChoosePhoto(int photoNum) {
		String xPath = ((new String(".//*[@id='DISPLAY1']/table[3]/tbody/tr/td[")).concat(String.valueOf(photoNum)))
				.concat("]/label/input");
		return (xPath);
	}

	
	
	static public String xpCNVerifyProductionsPage() {
		String xPath = new String("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3");
		return (xPath);
	}

	static public String cssCMSubmitButton() {
		String xPath = new String("div > table > tbody > tr > td > a > img");
		return (xPath);
	}

	static public String xpChooseMySmilePhoto() {

		String xPath = ".//*[@id='photo_5002739']/table/tbody/tr/td/a[starts-with(@href,'javascript: highlightPhoto(500')][2]";
		// String xPath= " //a[contains(text(),'Use This Photo')])[4]";

		// Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpFindSubmitLink() {
 
		String xPath = "//a[contains(text(),'submit')]";
		// String xPath= " //a[contains(text(),'Use This Photo')])[4]";

		// Logging.slog(xPath);
		return (xPath);
	}
	
	
	
	static public String cssFindSubmitLink() {
	 
		String css = "css=a";
		// String xPath= " //a[contains(text(),'Use This Photo')])[4]";

		// Logging.slog(xPath);
		return (css);
	}
	

	static public String xpChooseMySeriousPhoto() {

		// .//*[@id='photo_4799184']/table/tbody/tr/td/a[2]
		String xPath = ".//*[@id='photo_4799184']/table/tbody/tr/td/a[starts-with(@href,'javascript: highlightPhoto(479')][2]";
		// String xPath =
		// ".//*[@id='photo_4799184']/table/tbody/tr/td/a[starts-with(@href,'javascript:
		// highlightPhoto(479')][2]";
		// String xPath= " //a[contains(text(),'Use This Photo')])[4]";

		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpChooseBookstoreVideo1() {
		 
		String xPath = ".//input[@name='video_to_use' and @value='2629412']";
		 
		return (xPath);
		 
	}
	
	static public String xpChooseHenry() {
		 
		String xPath = ".//input[@name='video_to_use' and @value='2983645']";
		 
		return (xPath);
		 
	}
	
	
	static public String xpChoosePark() {
		 // html/body/form/table[3]/tbody/tr[1]/td[3]/table/tbody/tr/td[1]/input
		String xPath = ".//input[@name='video_to_use' and @value='2983633']";
		 
		return (xPath);
		 
	}
	
	
	static public String xpCartIsEmpty(){
		String xPath = ".//*[@id='emptyMsg']"; 
		return (xPath);
	}

	static public String xpChooseCommercialVideo2() {
		// video num 2584865
		String xPath = ".//input[@name='video_to_use'][@value='2584865']";
		// String xPath= "//tbody/tr/td[1]/input[@value='2584865']";
//		Logging.slog(xPath);
		return (xPath);
	}

	static public String xpIncludeSizes() {
		String xPath = "//input[@id='include_sc_checkbox_id']";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpTalentNotesAA() {
		String xPath = ".//form/div[6]/textarea[@name='notes']";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpAddToCartAA() {
		String xPath = "//a[@id='add_to_cart']";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpCartLogo() {
		String xPath = "//ul[@id='greeting']/li[3]/a";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpSubmitCart() {
		String xPath = "//a[@id='cartsubmit']";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpProdDetailsLeftWithTimeRoleAdded() {
		String xPath = "//div[@id='mainContent']/table/tbody/tr/td[1]";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpProdDetialsRight() {
		String xPath = "//div[@id='mainContent']/table/tbody/tr/td[3]/p";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String xpLogout() {
		String xPath = "//div[@id='mainContent']/table/tbody/tr/td[3]/p";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String urlAABreakdownAndRegion() {
		String xPath = "http://www.actorsaccess.com/projects/?view=breakdowns&region=";
		// Logging.slog(xPath);
		return (xPath);
	}
	
	
	static public String frLoginUsername() {
		String xPath = ".//*[@id='user-login-form']/div[1]/input[@placeholder='Username']";
		// Logging.slog(xPath);
		return (xPath);
	}

	static public String frLoginPassword() {
		String xPath = "//input[@name='pass']";
		// Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpFRtabProductionInRow(int practicalRow){
	//	.//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[6]/td[1]

	 if(practicalRow<1)
		 return "";
			String leftPart = ".//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[";
			String rightPart = "]/td[1]";	 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(practicalRow)).concat(rightPart));
	//		Logging.slog(xPath);
			return (xPath);
		} 
	static public String xpFRAppliedBefore(int practicalRow){
		//	.//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[34]/td[1]/span/i[@class='fi-check']

		 if(practicalRow<1)
			 return "";
				String leftPart = ".//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[";
				String rightPart = "]/td[1]/span/i[@class='fi-check']";	 
				String xPath = new String((new String(leftPart)).concat(String.valueOf(practicalRow)).concat(rightPart));
		//		Logging.slog(xPath);
				return (xPath);
			} 
	
	static public String oldxpFRProductionName(int practicalRow){
		//	.//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[6]/td[3]

		 if(practicalRow<1)
			 return "";
				String leftPart = ".//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[";
				String rightPart = "]/td[3]";	 
				String xPath = new String((new String(leftPart)).concat(String.valueOf(practicalRow)).concat(rightPart));
		//		Logging.slog(xPath);
				return (xPath);
			} 
	
	static public String oldxpFRCharacterName(int practicalRow){
		//	.//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[2]/td[4]

		 if(practicalRow<1)
			 return "";
				String leftPart = ".//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[";
				String rightPart = "]/td[@class='col-role-name']";	 
				String xPath = new String((new String(leftPart)).concat(String.valueOf(practicalRow)).concat(rightPart));
		//		Logging.slog(xPath);
				return (xPath);
			} 
		
	 
		static public  String xpFRProjectType(int practicalRow){
			String leftPart = ".//tr[@class='";
			String rightPart = "']/td[@class='col-proj-type']/span";	 
			String mid = oddEvenLineTrans(practicalRow);
			String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
	//		Logging.slog(xPath);
			return (xPath);
		}

	
	
	static public String xpFRProjectUnionStatus(int practicalRow){
		String leftPart = ".//tr[@class='";
		String rightPart = "']/td[@class='col-union-type']";	 
		String mid = oddEvenLineTrans(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
//		Logging.slog(xPath);
		return (xPath);
			}
	   
	static public String xpFRProjectCastingDates(int practicalRow){
		String leftPart = ".//tr[@class='";
		String rightPart = "']/td[@class='col-cast-date']";	 
		String mid = oddEvenLineTrans(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
//		Logging.slog(xPath);
		return (xPath);
			}
	static public String xpFRProjectGenderAgeEthnicity(int practicalRow){
		
		String leftPart = ".//tr[@class='";
		String rightPart = "']/td[@class='role_info_bottom'][1]";	 
		String mid = oddEvenLineTrans(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
//		Logging.slog(xPath);
		return (xPath);
			}
	
	 
	static public String xpFRProjectRate(int practicalRow){
		//	role_info_bottom'
		String leftPart = ".//tr[@class='";
		String rightPart = "']/td[@class='role_info_bottom']";	 
		String mid = oddEvenLineTrans(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
//		Logging.slog(xPath);
		return (xPath);
			}
	
	
	static public String xpFRProjectDescription(int practicalRow){
		//	role_info_bottom'
		String leftPart = ".//tr[@class='";
		String rightPart = "']/td[@class='role_info_bottom']";	 
		String mid = oddEvenLineTrans(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
//		Logging.slog(xPath);
		return (xPath);
			}
	
	

	
	
	static public String xpFRCastingDates(int practicalRow){
		//	.//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[2]/td[@class='col-cast-date']

		 if(practicalRow<1)
			 return "";
				String leftPart = ".//*[@id='filter_form']/div/div/div/div/div/div/table/tbody/tr[";
				String rightPart = "]/td[@class='col-cast-date']";	 
				String xPath = new String((new String(leftPart)).concat(String.valueOf(practicalRow)).concat(rightPart));
		//		Logging.slog(xPath);
				return (xPath);
			}
	
	
	  static public String oddEvenLineTrans(int lineNum){
		//   'odd first-row role-row group-7' 
	    //   'even first-row role-row group-6' 
		
		String res;
		if((lineNum % 2)==0){
			res = (new String("even first-row role-row group-")).concat(String.valueOf(lineNum));
			return res;
		}else{
			res =  (new String("odd first-row role-row group-")).concat(String.valueOf(lineNum));
			return res;
		}
	}
	
	static public  String xpFRCharacterName(int practicalRow){
				String leftPart = ".//tr[@class='";
			//	String rightPart = "']//td[@class='col-role-name']";	
				String rightPart = "']//td[4]";	
				String mid = oddEvenLineTrans(practicalRow);
				String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
			Logging.slog(xPath);
				return (xPath);
			}
	
	
	static public  String xpFRProductionName(int practicalRow){
		String leftPart = ".//tr[@class='";
//		String rightPart = "']//td[@class='proj-name col-proj-name']";	 
		String rightPart = "']/td[3]";	 
		
		
		
		String mid = oddEvenLineTrans(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}

	
	
	
	static public  String xpFRProductionNameXpathSecond(int practicalRow){
		//  .//table[@class='submissions']/tbody/tr[2]/td[3]
		
		String leftPart = ".//table[@class='submissions']/tbody/tr[";
  
		String rightPart = "]/td[3]";	 
		String mid = secondMethod(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}
	
	static public  String xpFRCharacterNameXpathSecond(int practicalRow){
		//  .//table[@class='submissions']/tbody/tr[2]/td[4]
		
		String leftPart = ".//table[@class='submissions']/tbody/tr[";
		String rightPart = "]/td[4]";	 
		String mid = secondMethod(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}	
	
	static public  String xpFRProjectTypeXpathSecond(int practicalRow){
		 
		String leftPart = ".//table[@class='submissions']/tbody/tr[";
		String rightPart = "]/td[6]/span";	 
		String mid = secondMethod(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}	
	
	static public  String xpFRProjectUnionStatusXpathSecond(int practicalRow){
		 
		String leftPart = ".//table[@class='submissions']/tbody/tr[";
		String rightPart = "]/td[8]";	 
		String mid = secondMethod(practicalRow);
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}	
		
	static public  String xpFRProjectGenderAgeEthnicityXpathSecond(int practicalRow){
		 
		String leftPart = ".//table[@class='submissions']/tbody/tr[";
		String rightPart = "]/td[2]";	 
		String mid = String.valueOf(1+rowToNumber(practicalRow));
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}	
			
	
	static public  String xpFRProjectRateXpathSecond(int practicalRow){
		 
		String leftPart = ".//table[@class='submissions']/tbody/tr[";
		String rightPart = "]/td[2]";	 
		String mid = String.valueOf(2+rowToNumber(practicalRow));
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}	
	
	static public  String xpFRProjectDescriptionXpathSecond(int practicalRow){
		 
		String leftPart = ".//table[@class='submissions']/tbody/tr[";
		String rightPart = "]/td[2]";	 
		String mid = String.valueOf(3+rowToNumber(practicalRow));
		String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}	
	
	
	static public String secondMethod(int productionRow){
		int transLocation = rowToNumber(productionRow); 
		String t = String.valueOf(transLocation);
		return t;
	}
	
	static public int rowToNumber(int y){
		int res = (4*y);
		res = res +2;
		return res;
	}
	
	
	static public  String xpFRInternalSubmissionNumber(int practicalRow){
 	String leftPart = ".//tr[@class='";
				String rightPart = "']";	 
				String mid = oddEvenLineTrans(practicalRow);
				String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
			Logging.slog(xPath);
				return (xPath);
			}
	
	
	static public  String xpFRInternalSubmissionNumberXpathSecond(int practicalRow){
		 
	//  .//table[@class='submissions']/tbody/tr[2]/td[2]
		
			String leftPart = ".//table[@class='submissions']/tbody/tr[";
	  
			String rightPart = "]";	 
			String mid = secondMethod(practicalRow);
			String xPath = new String((new String(leftPart)).concat(mid).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
			}	
	
	static public String xpFRVerifyLocationCharacterSubmission(){
		//	.//*[@id='public_submissions_form']/div/div/h2

		 
				 
				String xPath = ".//*[@id='public_submissions_form']/div/div/h2";
				Logging.slog(xPath);
				return (xPath);
			}
	
	static public String xpFRShootDates(){
		//   .//*[@id='public_submissions_form']/div/div/section[1]/div[7]/div[2]
		
				String xPath = ".//*[@id='public_submissions_form']/div/div/section[1]/div[7]/div[2]";
		 		Logging.slog(xPath);
				return (xPath);
			}
	
	
	static public String xpFRTalentNotes(){
	 	
				String xPath = ".//*[@id='note']";
		 		Logging.slog(xPath);
				return (xPath);
			}
	
	
	static public String xpFRchoosePhotoZero(){
	 	
		String xPath = ".//*[@id='hs1']";
 		Logging.slog(xPath);
		return (xPath);
	}
	

	static public String xpFRchoosePhotoOne(){
	 	
		String xPath = ".//*[@id='hs2']";
 		Logging.slog(xPath);
		return (xPath);
	}
	
	
static public String xpFRchoosePhotoTwo(){
	 	
		String xPath = ".//*[@id='hs3']";
 		Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpFRApplyButton(){
	 	
		String xPath = ".//*[@id='public_submissions_form']/div/div/div/input";
 		Logging.slog(xPath);
		return (xPath);
	}

	static public String xpFRAlertSuccess(){
	 	
		String xPath = "html/body/div[1]/div/section[2]/div/div/div[@class='alert-box success']/div";
 		Logging.slog(xPath);
		return (xPath);
	}

	 
	
	
	static public String frDirectSubmitButton() {
		String xPath = ".//body/div[1]/div/section[1]/div[3]/div/div/div[2]/div[1]/nav/section/ul/li[1]/a";
		  Logging.slog(xPath);
		return (xPath);
	}
	
	
	
	static public String xpCharacterLinkInCharactersPage(int row) {
		if (row == 0) {
			// Logging.slog(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[starts-with(@href,
			// 'javascript:')]");
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[starts-with(@href, 'javascript:')]"));
		}
		// The second character would have the class attribute at:
		// .//*[@id='mainContent']/table[2]/tbody/tr/td/p[2]/a
		int twiceRow = row * 2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[starts-with(@href, 'javascript:')]";
		String xPath = new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		// Logging.slog(xPath);
		return (xPath);
	}

	public String clickCharacterName(String charInternalNameRole, String bid, int regionNum) {
		/*
		 * THIS IS THE JAVASCRIPT VERSION to create:
		 * http://www.actorsaccess.com/projects/?view=selectphoto&from=
		 * breakdowns&region=3&iid=3328530&bid=531436 function selectPhoto(iid,
		 * bid, el) { var editcart = ""; var winl = (screen.width - 800) / 2;
		 * var wint = (screen.height - 600) / 2; winprops =
		 * 'top='+wint+',left='+winl; if (typeof el !== 'undefined' &&
		 * el.tagName == 'A' && el.text.indexOf('CHANGE PHOTO') > -1){ editcart
		 * = "&editcart=1"; }
		 * window.open('/projects/?view=selectphoto&from=breakdowns&region=3&iid
		 * =' + iid + '&bid=' + bid + editcart, 'select_photo',
		 * 'scrollbars,resizable,width=800,height=600,' + winprops); }
		 */

		String url = "";
		url += new String("/projects/?view=selectphoto&from=breakdowns&region=");
		url += new String(ClientsMngt.intToRegion(regionNum));
		url += new String("&iid=");
		url += new String(charInternalNameRole);
		url += new String("&bid=");
		url += new String(bid);
		url += new String(", 'select_photo', 'scrollbars,resizable,width=800,height=600,' + winprops");
		return new String(url);

	}

}
// .//*[@id='mainContent']/table[1]/tbody/tr[1]/td[1]