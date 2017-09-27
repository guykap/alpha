package Alpha;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class Scapper {

	static public void parseRowOfferBS(Job offer, int rowNum) {
		try {
		 
			getLabelList(rowNum, offer);
			//---0
			try{
			String prod_name = new String(
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSProductionName(rowNum))).getText());
			Logging.slog(new String("prod_name= ").concat(prod_name));
			offer.setOfferProjectName(prod_name.trim());
			}catch(Exception e){}
			//-------
			try{
			String prod_firstBlob = new String(
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSProductionFirstBlob(rowNum))).getText());
			Logging.slog(new String("prod_firstBlob= ").concat(prod_firstBlob));
			offer.addToProductionDetails(prod_firstBlob.trim());
			
			}catch(Exception e){}
			//----------
			try{
			String time_locationTop = new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSProductionTimeLocationTop(rowNum))).getText());
			Logging.slog(new String("time_locationTop= ").concat(time_locationTop));
			offer.setOfferShootDate(time_locationTop);
			offer.addToProductionDetails(time_locationTop.trim());
			String time_locationBottom1 = new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSProductionTimeLocationBottom1(rowNum))).getText());
			Logging.slog(new String("time_locationBottom1= ").concat(time_locationBottom1));
			
			offer.addToProductionDetails(time_locationBottom1.trim());
			String time_locationBottom2 = new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSProductionTimeLocationBottom2(rowNum))).getText());
			Logging.slog(new String("time_locationBottom2= ").concat(time_locationBottom2));
			offer.addToProductionDetails(time_locationBottom2.trim());
			
		
			offer.setOfferLocation(time_locationTop.concat(" | Location: ").concat(time_locationBottom1).concat(time_locationBottom2));
			}catch(Exception e){}
			//--
		} catch (Exception e) {
			Logging.slog(new String("Error scrappping row").concat(String.valueOf(rowNum)));
		}

	}
	
	
	
	
	static public void frScrapProductionOpenPage(Job offer){
		try{
		 
			
			
			try{
			String shoot_dates =  new String(ManageDriver.driver
							.findElement(By.xpath(XpathBuilder.xpFRShootDates())).getText());
					Logging.slog(new String("shoot_dates= ").concat(shoot_dates));
					offer.addToProductionDetails(new String(" shoot_dates: ").concat(shoot_dates));
			}catch(Exception e){}
			//-----
		 
			
			
		}catch(Exception e){
			Logging.slog(new String("Error scrappping row").concat(offer.getOfferProjectName()));
		}
			
		}

		
		
	
	
	static public void bsScrapProductionOpenPage(Job offer){
	try{
		try{
		
		/*	
			String bsInnerRoleId =  new String(ManageDriver.driver
							.findElement(By.xpath(XpathBuilder.xpBSProductionExpires())).getText());
					
			Logging.slog(new String("bsInnerRoleId= ").concat(bsInnerRoleId));
			offer.internalAAname = (new String(bsInnerRoleId)).trim();
			//offer.addToProductionDetails(new String(" Expires: ").concat(submission_expires));
	*/
			}catch(Exception e){}
		
		
		try{
		String submission_expires =  new String(ManageDriver.driver
						.findElement(By.xpath(XpathBuilder.xpBSProductionExpires())).getText());
				Logging.slog(new String("submission_expires= ").concat(submission_expires));
				offer.addToProductionDetails(new String(" Expires: ").concat(submission_expires));
		}catch(Exception e){}
		//-----
		try{
				String offer_pay =  new String(ManageDriver.driver
						.findElement(By.xpath(XpathBuilder.xpBSProductionPay())).getText());
				Logging.slog(new String("offer_pay= ").concat(offer_pay));
				
			//	offer.setOfferUnionStatus(offer_pay); // DEBUG _ change this to figure out union / non-union
				  
				
				offer.setOfferPaying(offer_pay);
				offer.setOffertRate(offer_pay);
		}catch(Exception e){}
				//----
			try{	String offer_key_details =  new String(ManageDriver.driver
						.findElement(By.xpath(XpathBuilder.xpBSProductionPay())).getText());
				Logging.slog(new String("offer_key_details= ").concat(offer_key_details));
				offer.addToProductionDetails(offer_key_details);
			}catch(Exception e){}
				//---------
			try{	
				String offer_additional_instructions =  new String(ManageDriver.driver
						.findElement(By.xpath(XpathBuilder.xpBSAdditional_instructions())).getText());
				Logging.slog(new String("offer_additional_instructions= ").concat(offer_additional_instructions));
				
				offer.addToProductionDetails(offer_additional_instructions);
			}catch(Exception e){}
				//---
				
				
				 
		
		
	}catch(Exception e){
		Logging.slog(new String("Error scrappping row").concat(offer.getOfferProjectName()));
	}
		
	}

	
	
	 

	static public void bsScrapChracterDetails(Job offerBS,String roleId){
		try{
			
		 int i= 4;
		 
			
			 try{
			String characterName =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterName(roleId))).getText());
			Logging.slog(new String("characterName= ").concat(characterName));
			offerBS.setOfferCharacterName(characterName.trim());
			}catch(Exception e){}
	
			 
			try{
			String leadORsupporting =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterLeadOrSupportingId(roleId))).getText());
			Logging.slog(new String("leadORsupporting= ").concat(leadORsupporting));
			offerBS.setLeadOrSupporting(leadORsupporting.trim());
			offerBS.setOfferCharacterDetails(leadORsupporting.trim());
		
			}catch(Exception e){}
	//--
			try{
			String genderAndAge =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterGenderAndAgeId(roleId))).getText());
			Logging.slog(new String("genderAndAge= ").concat(genderAndAge));
			offerBS.setOfferBSGenderAndAge(genderAndAge.trim());
		
						}catch(Exception e){}
	//--
			
			
			try{
			String moreCharacterDetails =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterMoreDetailsId(roleId))).getText());
			Logging.slog(new String("moreCharacterDetails= ").concat(moreCharacterDetails));
			offerBS.addToCharacterDetails(moreCharacterDetails.trim());
			}catch(Exception e){}
	//--
			
			try{
			String characterEthnicity =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterRequestedEthnicityId(roleId))).getText());
			Logging.slog(new String("characterEthnicity= ").concat(characterEthnicity));
			 
			offerBS.setOfferListingEthnicity(characterEthnicity.trim());
			}catch(Exception e){}
			
	
			try{
			String requiredMedia =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSrequiredMediaId(roleId))).getText());
			Logging.slog(new String("requiredMedia= ").concat(requiredMedia));
			offerBS.addToCharacterDetails(new String("| Req media: ").concat(requiredMedia.trim()));
			}catch(Exception e){}
			
 
		}catch(Exception e){
			Logging.slog(new String("Error scrappping row").concat(offerBS.getOfferProjectName()));
		}
		
	}
	
	
	
	
	static public void bsScrapChracterDetails(Job offerBS,int roleNum){
		try{
			 try{
			String characterName =  new String(ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpBSCharacterName(roleNum))).getText());
			Logging.slog(new String("characterName= ").concat(characterName));
			offerBS.setOfferCharacterName(characterName.trim());
			}catch(Exception e){}
	//--
			try{
			String leadORsupporting =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterLeadOrSupporting(roleNum))).getText());
			Logging.slog(new String("leadORsupporting= ").concat(leadORsupporting));
			offerBS.setLeadOrSupporting(leadORsupporting.trim());
		
			}catch(Exception e){}
	//--
			try{
			String genderAndAge =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterGenderAndAge(roleNum))).getText());
			Logging.slog(new String("genderAndAge= ").concat(genderAndAge));
			offerBS.setOfferBSGenderAndAge(genderAndAge.trim());
		
						}catch(Exception e){}
	//--
			
			
			try{
			String moreCharacterDetails =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterMoreDetails(roleNum))).getText());
			Logging.slog(new String("moreCharacterDetails= ").concat(moreCharacterDetails));
			offerBS.addToCharacterDetails(moreCharacterDetails.trim());
			}catch(Exception e){}
	//--
			int k = 9;
			try{
			String characterEthnicity =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterRequestedEthnicity(roleNum))).getText());
			Logging.slog(new String("characterEthnicity= ").concat(characterEthnicity));
			 
			offerBS.setOfferListingEthnicity(characterEthnicity.trim());
			}catch(Exception e){}
			
	//--
			try{
			String requiredMedia =  new String(ManageDriver.driver
					.findElement(By.xpath(XpathBuilder.xpBSCharacterMoreDetails(roleNum))).getText());
			Logging.slog(new String("requiredMedia= ").concat(requiredMedia));
			offerBS.addToCharacterDetails(requiredMedia.trim());
			}catch(Exception e){}
			
			
	//--
			
		}catch(Exception e){
			Logging.slog(new String("Error scrappping row").concat(offerBS.getOfferProjectName()));
		}
		
	}
	
	
	static private void getLabelList(int prodRow, Job offer) {
		// .//*[@id='main__container']/div/div[3]/div/div[prodRow]/div[1]/div[1]/div[1]/div//text()
		offer.labels = new ArrayList<String>();
		String label1 = "";
		String label2 = "";
		String temp_label = "";
		int labelCount = 0;
		try {
			for (labelCount = 1; labelCount < 10; ++labelCount) {
				timeSaver();
				temp_label = new String(ManageDriver.driver
						.findElement(By.xpath(XpathBuilder.xpBSLabelList(prodRow, labelCount))).getText());
				offer.labels.add(temp_label);
			}

		} catch (Exception e) {
			// Logging.slog(new String(e.getMessage()));
			Logging.slog(new String("No more labels found after: ").concat(String.valueOf(labelCount - 1)));
		}

	}

	static private void timeSaver() {
		// find all the labels in one line , sum up the labels and compare to
		// the full String and when they equal return true;
		int i = 8;
		return;
	}

	static public void parseRowOfferAA(Job offer, int rowNum) {

		String leftPart = (new String("//div[@id='mainContent']/div[@class='list']/table/tbody/tr["))
				.concat(String.valueOf(rowNum + 2));
		String path;
		try {
			path = (new String(leftPart)).concat("]/td[2]");
			// Logging.slog(path);
			offer.setOfferPostedTime(new String(ManageDriver.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferPostedTime(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[3]/a[starts-with(@href,'/projects/')]");
			// Logging.slog(path);
			offer.setOfferProjectName(new String(ManageDriver.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferProjectName(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[4]");
			// Logging.slog(path);
			offer.setOfferTypeProject(new String(ManageDriver.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferTypeProject(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[5]");
			offer.setOfferCastingDirector(new String(ManageDriver.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferCastingDirector(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[6]");
			offer.setOfferShootDate(new String(ManageDriver.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferShootDate(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[7]");
			offer.setOfferUnionStatus(new String(ManageDriver.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferUnionStatus(new String(""));
		}
	}

	static public void handleBackgroundWorkOffer(Job offer, boolean isBackgroundWork, int row) {
		offer.setIsBackgroundWork(isBackgroundWork);
		// the EXTRA table has the shooting date .
		// the PRINCIPLE table does not

		String leftPart = (new String("//tr[")).concat(String.valueOf(row));
		try {

			try {

				String path = new String(leftPart.concat("]/td/a"));
				offer.setOfferRole(
						(new String(ManageDriver.driver.findElement(By.xpath(path)).getText())).toLowerCase());
			} catch (Exception e) {
				offer.setOfferRole(new String(""));
			}

			if (isBackgroundWork) {
				// BACKGROUND WORK
				try {
					String path = new String(leftPart.concat("]/td[2]/a"));
					offer.setOfferProjectName(
							(new String(ManageDriver.driver.findElement(By.xpath(path)).getText())).toLowerCase());
				} catch (Exception e) {
					offer.setOfferProjectName(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[3]/a"));
					offer.setOfferShootDate(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferShootDate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[4]/a"));
					offer.setOfferTypeProject(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferTypeProject(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[5]/a"));
					offer.setOffertRate(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOffertRate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[6]/a"));
					offer.setOfferPaying(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPaying(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[7]/a"));
					offer.setOfferUnionStatus(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferUnionStatus(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[8]/a"));
					offer.setOfferPostedTime(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPostedTime(new String(""));
				}

			} else {
				// PRINCIPLE WORK
				try {
					String path = new String(leftPart.concat("]/td[2]/a"));
					offer.setOfferProjectName(
							(new String(ManageDriver.driver.findElement(By.xpath(path)).getText())).toLowerCase());
				} catch (Exception e) {
					offer.setOfferProjectName(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[3]/a"));
					offer.setOfferTypeProject(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferTypeProject(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[4]/a"));
					offer.setOffertRate(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOffertRate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[5]/a"));
					offer.setOfferPaying(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPaying(new String(""));
				}
				try {
					String path = new String(leftPart.concat("]/td[6]/a"));
					offer.setOfferUnionStatus(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferUnionStatus(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[7]/a"));
					offer.setOfferPostedTime(
							new String(ManageDriver.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPostedTime(new String(""));
				}

			}
			try {
				String pathOfferListing = new String(
						((new String("//tr[")).concat(String.valueOf(row + 1))).concat("]/td"));
				offer.setOfferListing(new String(ManageDriver.driver.findElement(By.xpath(pathOfferListing)).getText())
						.toLowerCase());
			} catch (Exception e) {
				offer.setOfferListing(new String(""));
			}
			return;

		} catch (Exception e) {
			Logging.slog("Error parsing the current offer data into the Strings");
			// go back to login page
		}
	}

	static public String scrapTextAtXpath(String xpath) {
		// returns the text at the xPath tab
		String foundText = new String("");
		try {
			foundText = new String(new String(ManageDriver.driver.findElement(By.xpath(xpath)).getText()));
		} catch (Exception e) {
			Logging.slog((new String("No text found at: ")).concat(xpath));

		}

		return foundText;
	}

	static public String scrapAttributeAtXpath(String xpath, String attribute) {
		if ((xpath.length() < 1) || (attribute.length() < 1)) {
			Logging.slog((new String("Wrong values passed to scrapper: ")).concat(xpath).concat(attribute));

			return new String("");
		}
		// returns the text at the xPath tab
		String foundText = new String("");
		try {
			foundText = ManageDriver.driver.findElement(By.xpath(xpath)).getAttribute(attribute);
			// foundText = new String(new
			// String(ManageDriver.driver.findElement(By.xpath(xpath)).get));
		} catch (Exception e) {
			Logging.slog((new String("No attribute found at: ")).concat(xpath));

		}

		return foundText;
	}

	static public void parseRowOfferFR(Job offerFR, int offerRow) {
		try {
		  int rowTransf = offerRow;
		  String aa_internet_num; 
			try{
				aa_internet_num= new String(
						ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpFRInternalSubmissionNumber(rowTransf))).getAttribute("data-href"));
				}catch(Exception e){
					aa_internet_num= new String(
							ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpFRInternalSubmissionNumberXpathSecond(rowTransf))).getAttribute("data-href"));
					
				}
			String str = aa_internet_num.replaceAll("\\D+","");
			Logging.slog(new String("aa_internal_number= ").concat(str));
			offerFR.setInternalAAhref(str.trim());
			offerFR.setInternalAAname(str.trim());
			 
			 
			 
			String prod_name;	
			try{
			prod_name = new String(
					ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpFRProductionName(rowTransf))).getText());
		 
			}catch(Exception e){
			 prod_name = new String(
						ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpFRProductionNameXpathSecond(rowTransf))).getText());	
			}
			Logging.slog(new String("prod_name= ").concat(prod_name));
			offerFR.setOfferProjectName(prod_name.trim());
		 
		 
			
			//***
			
			
			String characterName; 
			 try{
				 characterName=  new String(ManageDriver.driver
							.findElement(By.xpath(XpathBuilder.xpFRCharacterName(rowTransf))).getText());
					}catch(Exception e){
						 characterName=  new String(ManageDriver.driver
									.findElement(By.xpath(XpathBuilder.xpFRCharacterNameXpathSecond(rowTransf))).getText());
								
						
					}
			 Logging.slog(new String("characterName= ").concat(characterName));
				offerFR.setOfferCharacterName(characterName.trim());
				
			 
			 
			 
				String projectType;
			 try{
				 projectType=  new String(ManageDriver.driver
							.findElement(By.xpath(XpathBuilder.xpFRProjectType(rowTransf))).getText());
					}catch(Exception e){
						 projectType=  new String(ManageDriver.driver
									.findElement(By.xpath(XpathBuilder.xpFRProjectTypeXpathSecond(rowTransf))).getText());
							}
			 Logging.slog(new String("projectType= ").concat(projectType));
				offerFR.setOfferTypeProject(projectType.trim());
				

					

				String unionOrNonUnion; 
					try{
						unionOrNonUnion =  new String(ManageDriver.driver
								.findElement(By.xpath(XpathBuilder.xpFRProjectUnionStatus(rowTransf))).getText());
				  		}catch(Exception e){
				  			unionOrNonUnion =  new String(ManageDriver.driver
									.findElement(By.xpath(XpathBuilder.xpFRProjectUnionStatusXpathSecond(rowTransf))).getText());
					  		
				  			
				  		}
				
					Logging.slog(new String("unionOrNonUnion= ").concat(unionOrNonUnion));
					offerFR.setOfferUnionStatus(unionOrNonUnion.trim()); 
				
			//--
					String genderAndAgeEthnicity; 
					try{
						
						genderAndAgeEthnicity =  new String(ManageDriver.driver
								.findElement(By.xpath(XpathBuilder.xpFRProjectGenderAgeEthnicity(rowTransf))).getText());
						}catch(Exception e){
							
							genderAndAgeEthnicity =  new String(ManageDriver.driver
									.findElement(By.xpath(XpathBuilder.xpFRProjectGenderAgeEthnicityXpathSecond(rowTransf))).getText());
						
						}
					Logging.slog(new String("genderAndAgeEthnicity= ").concat(genderAndAgeEthnicity));
					offerFR.setOfferFRGenderAndAgeEthnicity(genderAndAgeEthnicity.trim()); 
					
			//--

					String projectRate;
					try{
						  projectRate =  new String(ManageDriver.driver
									.findElement(By.xpath(XpathBuilder.xpFRProjectRate(rowTransf))).getText());
						}catch(Exception e){
							  projectRate =  new String(ManageDriver.driver
										.findElement(By.xpath(XpathBuilder.xpFRProjectRateXpathSecond(rowTransf))).getText());
					
						}
					Logging.slog(new String("projectRate= ").concat(projectRate));
					offerFR.setOffertRate(projectRate.trim()); 
			
					
					String moreCharacterDetails;
					try{
						
						 moreCharacterDetails =  new String(ManageDriver.driver
								.findElement(By.xpath(XpathBuilder.xpFRProjectDescription(rowTransf))).getText());
						}catch(Exception e){
							 moreCharacterDetails =  new String(ManageDriver.driver
										.findElement(By.xpath(XpathBuilder.xpFRProjectDescriptionXpathSecond(rowTransf))).getText());
						
						}

					Logging.slog(new String("moreCharacterDetails= ").concat(moreCharacterDetails));	
					offerFR.addToCharacterDetails(moreCharacterDetails.trim()); 
				
					
	try{
		/*
		String castingDates =  new String(ManageDriver.driver.findElement(By.xpath(XpathBuilder.xpFRCastingDates(rowTransf))).getText());
				 
		Logging.slog(new String("castingDates= ").concat(castingDates));
		offerFR.addToProductionDetails(new String(" | CastingDates = ").concat(castingDates.trim())); 
	*/
		}catch(Exception e){}

 
		 
		} catch (Exception e) {
			Logging.slog(new String("Error scrappping row").concat(String.valueOf(offerRow)));
		}

	}
	
	
	
}
 