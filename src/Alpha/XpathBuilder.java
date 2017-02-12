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
		Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpBSProductionFirstBlob(int row) {
	 	 
		int rowPlusOne = row + 1;
		String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
		String rightPart = "]/div[1]/div[@class='prod__desc']/span";
		String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
		Logging.slog(xPath);
		return (xPath);
	}
	

	
	static public String xpBSProductionTimeLocationTop(int row) {
	 	int rowPlusOne = row + 1;
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
			 
			String rightPart = "]/div[1]/ul[@class='prod__dates']/li[2]/span";
			String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
			 Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSProductionTimeLocationBottom1(int row) {
	 	int rowPlusOne = row + 1;
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
			 
			String rightPart = "]/div[1]/ul[@class='prod__dates']/li[3]/span[1]";
			String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
			Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSProductionTimeLocationBottom2(int row) {
	 	int rowPlusOne = row + 1;
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
			 
			String rightPart = "]/div[1]/ul[@class='prod__dates']/li[3]/span[2]";
			String xPath = new String(((new String(leftPart)).concat(String.valueOf(rowPlusOne)).concat(rightPart)));
			Logging.slog(xPath);
			return (xPath);
		}
	static public String xpBSLabelList(int prodRow, int numLabel){
int row = prodRow + 1;
 
//		".//*[@id='main__container']/div/div[3]/div/div[1]/div[1]/div[1]/div[1]/div/a[1]";
		String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
		String midPart = "]/div[1]/div[1]/div[1]/div/a[";
		String rightPart = "]";
		String xPath = new String((new String(leftPart)).concat(String.valueOf(row).concat(midPart).concat(String.valueOf(numLabel)).concat(rightPart)));
		 Logging.slog(xPath);
		return (xPath);
		 
	}
	
	static public String xpBSProductionName(int prodRow){
 
		int row= prodRow + 1;
			String leftPart = ".//*[@id='main__container']/div/div[3]/div/div[";
			String rightPart = "]/div[1]/h3[@class='prod__title']/a[starts-with(@href,'/casting')]";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		}
	
	
	
	
	static public String xpBSLinkCharactersInProduction(int prodRow){
 		return xpBSProductionName(prodRow);
				}
	
	static public String xpBSOpennedProductionPage(int prodRow){

					String xPath = new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/h3/span");
					Logging.slog(xPath);
					return (xPath);
				}
	
	
	static public String xpBSProductionExpires() {
		String xPath = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[3]/div/div/div[3]/span[2]/span[2][@class='expires-text--date']";
		// Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpBSProductionPay() {
		String xPath = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[8]/div/div/p/span/span";
		// Logging.slog(xPath);
		return (xPath);
	}
	
	static public String xpBSAdditional_instructions() {
		String xPath = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[9]/div/div/div[2]/p/span/span";
		// Logging.slog(xPath);
		return (xPath);
	}
	 
	
	static public String xpBSClickNYSearchButton() {
		String xPath = new String("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3");
		return (xPath);
	}
	
	static public String xpBSClickBottomButton() {
		String xPath = new String(".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[11]/button");
		return (xPath);
	}
	
	
	static public String xpBSApplyNowButton() {
		String xPath = new String(".//*[@id='main__container']/div[2]/div[2]/div[10]/button[2]");
		return (xPath);
	}
	
	
	
	
	
	static public String xpBSCharacterName(int roleRow){
		 
		int row= roleRow + 1;
			String leftPart = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/div[";	
			String rightPart = "]/div[1]/button/div[1]/span[1]/span[1]"; 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSCharacterLeadOrSupporting(int prodRow){
		 
		int row= prodRow + 1;
		String leftPart = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/div[";	
			String rightPart = "]/div[1]/button/div[1]/span[1]/span[2]";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSCharacterGenderAndAge(int prodRow){
		 
		int row= prodRow + 1;
			String leftPart = ".//*[@id='main__container']/div/div[5]/div/div/div/div/div[2]/div[6]/div/div/div[";
			String rightPart = "]/div[1]/button/div[1]/span[2][@class='details']";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSCharacterMoreDetails(int prodRow){
 
		int row= prodRow + 1;
			String leftPart = "//div[6]/div/div/div[";

			String rightPart = "]/div[2]/div/div/div/div/span/span[starts-with(@data-reactid,'.')]";
		 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		}
	
	static public String xpBSCharacterRequestedEthnicity(int prodRow){
		//div[6]/div/div/div/div[2]/div/div/div[1]/div[2]/p[1]/span[2][starts-with(@data-reactid,'.')]

		int row= prodRow + 1;
			String leftPart = "//div[6]/div/div/div[";
			String rightPart = "]/div[2]/div/div/div[1]/div[2]/p[1]/span[2][starts-with(@data-reactid,'.')]";	 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		}

	static public String xpBSCharacterApplyButton(int prodRow){
		//div[6]/div/div/div/div[2]/div/div/div[1]/div[2]/p[1]/span[2][starts-with(@data-reactid,'.')]

		int row= prodRow + 1;
			String leftPart = "//div[6]/div/div/div[";
			String rightPart = "]/div[2]/div/div/div[1]/div[2]/p[1]/span[2][starts-with(@data-reactid,'.')]";	 
			String xPath = new String((new String(leftPart)).concat(String.valueOf(row)).concat(rightPart));
			Logging.slog(xPath);
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