package Alpha;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Esl {

	static public void readNoticeAA(Actor human, Job currentOfferBa) {
		// this reads the notice and sets all the Job params accordingly.
		String allData = new String(currentOfferBa.offerProductionDetails);
		String allDataLowerCase = new String(allData).toLowerCase();
		String allCharacterData = new String(currentOfferBa.offerCharacterDetails);
		String allCharacterDataLowerCase = new String(allCharacterData).toLowerCase();

		// SAG
		if ((currentOfferBa.getOfferUnionStatus()).contains("SAG")
				|| (currentOfferBa.getOfferUnionStatus()).contains("sag")
				|| (currentOfferBa.getOfferUnionStatus()).startsWith("UNION")
				|| (currentOfferBa.getOfferUnionStatus()).startsWith("union")) {
			currentOfferBa.setCharacterUnionDemand('u');
		}

		if ((allDataLowerCase.contains("\tsag")) || (allDataLowerCase.contains(" sag "))
				|| (allDataLowerCase.contains("aea ")) || (allDataLowerCase.startsWith("union"))
				|| (allDataLowerCase.contains("\tunion")) || (allDataLowerCase.contains(" union"))
				|| (allDataLowerCase.startsWith("union"))) {
			currentOfferBa.setCharacterUnionDemand('u');
		}

		// ETHNICITY
		// if the notice says a specific ethicity that isn't the actor's then
		// mark FALSE. otherwise mark TRUE
		// offer.setSeekingEthnicities(allDataLowerCase);
		Esl.calcEthnicity(currentOfferBa, allCharacterDataLowerCase);

		// AGE

		Esl.understandingAgeRange(currentOfferBa, allCharacterDataLowerCase, human);

		// GENDER
		Esl.understandingGender(currentOfferBa, allCharacterDataLowerCase);

		// BLACK_LIST
		Esl.processBlacklist(currentOfferBa, allCharacterDataLowerCase, human);

	}

	/*
	 * 
	 * static public String lookForRate(Job offer_find_rate){ //returns the
	 * digits right after the $ sign String foundRate = ""; String hint =
	 * offer_find_rate.getProductionDetails(); if(ClientsMngt.site == 3){ //
	 * foundRate = offer_find_rate. }
	 * 
	 * if(hint.contains("$")){ foundRate = new
	 * String(hint.substring((hint.indexOf("$")),10));
	 * 
	 * } return new String(foundRate); }
	 * 
	 */
	static public void processAlreadyBookedDates(Job offer, String data, Actor human) {
		// verify that this prod is ONLY NON UNION
		try {
			 
			String bookedDates = Beta.cleanString(human.getBookedDates());
			String shootingDate = Beta.cleanString(offer.getOfferShootDate());
			if ((bookedDates.length() < 1) || ((shootingDate.length() < 1))) {
				return;
			}

			if (bookedDates.contains(shootingDate)) {
				offer.setIsOnBlacklist(true);
				Logging.slog(new String("Already Booked that date: Found this shooting date:| ").concat(shootingDate)
						.concat(" | Client already booked out these dates : |").concat(bookedDates));
			}

		} catch (Exception e) {
			Logging.slog("Error found in already booked list");
		}
	}

	static public void processOnlySagProductions(Job offer, String data, Actor human) {
		try {
			// verify that this prod is ONLY NON UNION
			// boolean isOnlyNonUnion = false;
			if (offer.getCharacterUnionDemand() == 'b') {
				// this production is for both SAG and non union
				return;
			}

			if (offer.getCharacterUnionDemand() == 'u') {
				// this production is SAG
				return;
			}

			// Else - we assume that this is only non union
			String sagList = (human.getOnly_sag_productions()).toLowerCase();
			if (sagList.length() < 1) {
				return;
			}
			String proejctName = offer.getOfferProjectName().toLowerCase().trim();
			if (sagList.contains(proejctName)) {
				offer.setIsOnBlacklist(true);
				Logging.slog(new String("ONLY SAG LIST: Found this prod:| ").concat(proejctName)
						.concat(" | production name on ONLY_SAG_LIST: |").concat(sagList));
			}
		} catch (Exception e) {
			Logging.slog("Found error in only sag list");
		}
	}

	static public void processBlacklist(Job offer, String data, Actor human) {
		offer.setIsOnBlacklist(false);
		try {
			int i = 9;

			// check for student projects
			if (human.getBlackList().contains("student")) {
				if ((offer.getOfferTypeProject().toLowerCase()).contains("student")) {
					offer.setIsOnBlacklist(true);
					Logging.slog("Found offer on blacklist becuase of hint: student");
				}
			}

			// check for central casting
			if (human.getBlackList().contains("central casting")) {
				if (data.toLowerCase().contains("central casting")) {
					offer.setIsOnBlacklist(true);
					Logging.slog("Found offer on blacklist becuase of hint: central casting");
				}
			}

			// PRODUCTIONS

			// any Project name that appears in the blacklist will NOT BE
			// SUBMITTED
			// search for the
			String proejctName = offer.getOfferProjectName().toLowerCase().trim();
			String blackList = human.getBlackList().toLowerCase();
			if (blackList.contains(proejctName)) {
				offer.setIsOnBlacklist(true);
				Logging.slog(new String("BLACKLIST: Found this prod:| ").concat(proejctName)
						.concat(" | production name on blackList: |").concat(blackList));
			}

			/*
			 * if(human.getBlackList().contains("gotham")){
			 * if((data.contains("gotham"))||
			 * offer.getOfferProjectName().contains("gotham")){
			 * 
			 * offer.setIsOnBlacklist(true);
			 * Logging.slog("Found offer on blacklist becuase of hint: Gotham");
			 * } }
			 * 
			 */
			// PAY

			String rate = new String(offer.getOffertRate()).toLowerCase();
			if (rate.length() < 1) {
				// look for rate in the production details

				try {
					rate = lookForRate(offer);
					offer.setOffertRate(rate);
				} catch (Exception e) {

				}
			}
			if (human.getBlackList().contains("no pay")) {
				if ((rate.contains("no pay")) || (rate.contains("non paid")) || (rate.contains("non payment"))
						|| (rate.contains("Unpaid")) || (rate.contains("unpaid")) || (rate.contains("none pay"))
						|| (rate.equals(new String("imdb credit"))) || (rate.equals(new String("deferred")))
						|| (rate.contains("meals, gas")) || (rate.contains("copy, meal"))
						|| (rate.contains("credit, meals")) || (rate.contains("copy credit"))) {
					offer.setIsOnBlacklist(true);
					Logging.slog("Found offer on blacklist becuase of hint: no pay");
				}
			}

		} catch (Exception e) {
			Logging.slog("Error applying blacklist to offer");
		}
	}

	static public void understandingGender(Job currentOfferEa, String allCharacterDataLowerCase) {
		if (currentOfferEa.isBackgroundWork) {
			understandingGenderBG(currentOfferEa, allCharacterDataLowerCase);
		} else {
			understandingGenderPrinciple(currentOfferEa, allCharacterDataLowerCase);
		}
	}

	static public void understandingGenderPrinciple(Job currentOfferDa, String allCharacterDataLowerCase) {
		if ((allCharacterDataLowerCase.contains("male or female"))
				|| (allCharacterDataLowerCase.contains("female or male"))
				|| (allCharacterDataLowerCase.contains("he/she"))) {

			currentOfferDa.setCharacterGender('b');
			return;
		}

		if (((allCharacterDataLowerCase.contains(" he ")) || (allCharacterDataLowerCase.startsWith("he")))
				&& ((allCharacterDataLowerCase.contains(" she ")) || (allCharacterDataLowerCase.startsWith("she")))) {
			// this notice cointains also He and she so this is confusing. Lets
			// check gender only by name
			checkGenderOfName(currentOfferDa);
			return;
		}

		if ((allCharacterDataLowerCase.contains(" male")) || (allCharacterDataLowerCase.startsWith("male"))

				|| (allCharacterDataLowerCase.contains(" gay ")) || (allCharacterDataLowerCase.contains(" male"))
				|| (allCharacterDataLowerCase.startsWith("men"))
				|| (allCharacterDataLowerCase.toLowerCase().contains(" he "))) {
			currentOfferDa.setCharacterGender('m');
			return;
		}

		// HINT FOR A FEMALE CHARACTER IN DESCRIPTION
		if ((allCharacterDataLowerCase.contains(" female")) || (allCharacterDataLowerCase.startsWith("female"))
				|| (allCharacterDataLowerCase.contains(" woman ")) || (allCharacterDataLowerCase.contains(" lesbian "))
				|| (allCharacterDataLowerCase.startsWith("lesbian")) || (allCharacterDataLowerCase.contains("actress "))
				|| (allCharacterDataLowerCase.startsWith("women")) || (allCharacterDataLowerCase.startsWith(" she "))
				|| (allCharacterDataLowerCase.toLowerCase().contains(" she "))) {
			currentOfferDa.setCharacterGender('f');
			return;
		}

		if ((currentOfferDa.getCharacterGender() == 'm') || (currentOfferDa.getCharacterGender() == 'f')) {
			// found the hint for male or female. and determined the gender m or
			// f
			// Unknown
			return;
		} else {
			// Hint not found so lets use the API using the character name
			checkGenderOfName(currentOfferDa);
		}

	}

	static public String lookForRate(Job off) {
		int digitsAfterPosition = 15;
		String foundRate = "";
		String data = off.offerProductionDetails;
		String hintingText = "Rate of Pay";
		if (data.contains(hintingText)) {
			int ratePoint = data.indexOf(hintingText);
			foundRate = new String((data.subSequence(ratePoint + hintingText.length(),
					ratePoint + hintingText.length() + digitsAfterPosition).toString()));

		} else if (data.contains("$")) {
			foundRate = new String(data.substring((data.indexOf("$")), digitsAfterPosition));

		}

		return foundRate;
	}

	static public void understandingGenderBG(Job currentOfferDa, String allCharacterDataLowerCase) {
		// HINT FOR BOTH MALE OR FEMALE CHARACTER IN DESCRIPTION

		if ((allCharacterDataLowerCase.contains("male or female"))
				|| (allCharacterDataLowerCase.contains("female or male"))
				|| (allCharacterDataLowerCase.toLowerCase().contains("both genders"))) {
			currentOfferDa.setCharacterGender('b');
			return;
		}

		if (((allCharacterDataLowerCase.contains(" he ")) || (allCharacterDataLowerCase.startsWith("he")))
				&& ((allCharacterDataLowerCase.contains(" she ")) || (allCharacterDataLowerCase.startsWith("she")))) {
			// this notice cointains also He and she so this is confusing. Lets
			// check gender only by name
			checkGenderOfName(currentOfferDa);
			return;
		}

		// HINT FOR A MALE CHARACTER IN DESCRIPTION

		if ((allCharacterDataLowerCase.contains(" male")) || (allCharacterDataLowerCase.startsWith("male"))
				|| (allCharacterDataLowerCase.contains(" men")) || (allCharacterDataLowerCase.contains(" man "))
				|| (allCharacterDataLowerCase.contains(" gay ")) || (allCharacterDataLowerCase.contains(" male"))
				|| (allCharacterDataLowerCase.startsWith("men"))
				|| (allCharacterDataLowerCase.toLowerCase().contains(" he "))) {
			currentOfferDa.setCharacterGender('m');
			return;
		}

		// HINT FOR A FEMALE CHARACTER IN DESCRIPTION
		if ((allCharacterDataLowerCase.contains(" female")) || (allCharacterDataLowerCase.contains(" women"))
				|| (allCharacterDataLowerCase.startsWith("female")) || (allCharacterDataLowerCase.contains(" woman "))
				|| (allCharacterDataLowerCase.contains(" lesbian "))
				|| (allCharacterDataLowerCase.startsWith("lesbian")) || (allCharacterDataLowerCase.contains("actress "))
				|| (allCharacterDataLowerCase.startsWith("women")) || (allCharacterDataLowerCase.startsWith(" she "))
				|| (allCharacterDataLowerCase.toLowerCase().contains(" she "))) {
			currentOfferDa.setCharacterGender('f');
			return;
		}

		if ((currentOfferDa.getCharacterGender() == 'm') || (currentOfferDa.getCharacterGender() == 'f')) {
			// found the hint for male or female. and determined the gender m or
			// f
			// Unknown
			return;
		} else {
			// Hint not found so lets use the API using the character name
			checkGenderOfName(currentOfferDa);
		}
	}

	static public void parseProdDetailsLeftWithTimeRoleAdded(Job char_offer, String data) {
		Logging.slog("parse it");
		char_offer.addToProductionDetails(data);
		String delims = "['\n']";
		String[] tokens = data.split(delims);
		char_offer.setOfferTimeRoleAdded(tokens[0]);
		char_offer.setOfferTypeProject(tokens[2]);
		char_offer.setOfferUnionStatus(tokens[3]);
		// String details = tokens[1];
	}

	static public void parseProdDetialsRight(Job char_offer, String data) {
		// ALL parsing should be done with REGEX , but right now only store in
		// the DB all the production info as one long String
		Logging.slog("parse it");
		char_offer.addToProductionDetails(data);
	}

	static public void parseNameOfCharacterAndDetailsUnder(Job char_offer, String data) {
		Logging.slog("parse it");
		String delims = "\\[|\\]";
		String[] tokens = data.split(delims);
		String name = new String(tokens[1]);
		char_offer.setOfferCharacterName(name.trim());
		String details = new String(tokens[2]);
		char_offer.setOfferCharacterDetails(details.trim());
	}

	static public String parseAgeRange(String data) {
		String firstNum = "";
		String secondNum = "";
		String mydata = new String(data);
		if (mydata.length() < 0) {
			return new String("");
		}
		Pattern pattern2 = Pattern.compile("\\b\\d{2}\\b[-. ]?[-. ]?[-. ]?\\d{2}");
		Matcher matcher = pattern2.matcher(mydata);

		if (matcher.find()) {
			String foundRegex = matcher.group(0);
			return (new String(foundRegex));
		}
		Pattern pattern1 = Pattern.compile("\\b\\d{2}\\b");

		matcher = pattern1.matcher(mydata);
		if (matcher.find()) {
			firstNum = matcher.group(0);
			if (matcher.find()) {
				secondNum = matcher.group(0);
			} else {
				return (firstNum);
			}

			return ((new String(firstNum).concat("-")).concat(secondNum));
		}
		return ("");
	}

	static public boolean parseAgeRangeUnderTen(String data) {
		// lets weed out the possibility of age under 10 like "5-9"

		String firstNum = "";
		String secondNum = "";
		String mydata = new String(data);
		if (mydata.length() < 0) {
			return false;
		}
		Pattern pattern2 = Pattern.compile("\\b\\d{1}\\b[-. ]?[-. ]?[-. ]?\\d{1}");
		Matcher matcher = pattern2.matcher(mydata);

		if (matcher.find()) {
			String foundRegex = matcher.group(0);
			Logging.slog(new String("Found age range to be: ").concat(foundRegex));
			return true;

		}

		return false;
	}

	static private void understandingAgeRange(Job offer, String givenAgeData, Actor human) {
		String ageData = new String( givenAgeData);
		// read the AGE from data
		if (ageData.length() < 1) {
			 //check if there is info in the production details
			ageData = new String(offer.offerListingAgesHint);	
		}
		if (ageData.length() < 1) {
			ageData = new String( offer.getProductionDetails().toLowerCase());
		}
		
		
		if ((ageData.contains("20 - 30")) || (ageData.contains("20-30")) || (ageData.contains("20s-30s"))
				|| (ageData.contains("18+")) || (ageData.contains("20 - 40")) || (ageData.contains("20-40"))
				|| (ageData.contains("20s to 30s")) || (ageData.contains("20s-30s")) || (ageData.contains("early 40s"))
				|| (ageData.contains("early 30s")) || (ageData.contains("early 30’s")) || (ageData.contains("18 - 199"))
				|| (ageData.contains("18 - 99")) || (ageData.contains("30 something "))) {

			offer.setIsAge(true);
			return;
		}

		// case the data has the format : " %d - %d"
		String ageMin;
		String ageMax;
		String delims = "[-,'to']";
		String[] tokens = offer.offerListingAgesHint.split(delims);
		String regexResult;
		Double maybeAgeMin = new Double(0);
		Double maybeAgeMax = new Double(0);
		Double maybeAgeAverageTwice = new Double(0);
		Double avgCharacterAgeTwice = new Double(0);

		try {
			regexResult = new String(parseAgeRange(ageData));
			if (regexResult.length() > 3) {
				tokens = regexResult.split(delims);
			}
			if ((regexResult.length() > 1) && (regexResult.length() <= 3)) {
				// Only one number found .
				checkForSpecificActor(human, offer, Double.valueOf(regexResult.trim()),
						Double.valueOf(regexResult.trim()));
				return;

			}

			if (regexResult.length() < 1) {
				if (parseAgeRangeUnderTen(ageData)) {
					Logging.slog("Possible age is under ten years old");
					offer.setIsAge(false);
					return;
				}
			}

			if (tokens.length < 2) {
				Logging.slog("No age hint found. Lets submit!");
				offer.setIsAge(true);
				return;

			}
			ageMin = new String(tokens[0]);
			ageMax = new String(tokens[1]);

			// Weed out the agent fee percetage. usually 10%, 15%, 20% and is at
			// the end of the String

			if ((ageMin.length() < 1) && (ageMax.length() < 1)) {
				return;
			}

			try {

				maybeAgeMin = new Double(Double.parseDouble(ageMin.trim()));
				maybeAgeMax = new Double(Double.parseDouble(ageMax.trim()));
				// Weed out the agent fee percetage. usually 10%, 15%, 20% and
				// is at the end of the String

				if ((maybeAgeMin > maybeAgeMax) || (maybeAgeMax.equals(new Double(10)))
						|| (maybeAgeMax.equals(new Double(20))) || (maybeAgeMax.equals(new Double(15)))) {

					// we cannot trust the age Max so we will now return
					Logging.slog(
							"Failure in reading the age values. Might be confused with the agent fee. Should improve regex to find The percentage symbol");
					// for debug reasons at this point we will say that the age
					// is NOT appropriate
					offer.setIsAge(false);
					return;
				}

			} catch (Exception e) {
				Logging.slog(e.getMessage());
				Logging.slog("Math failure in reading the age values. Lets submit anyway. We are artists.");
				offer.setIsAge(true);

			}

			checkForSpecificActor(human, offer, maybeAgeMin, maybeAgeMax);

		} catch (Exception e) {
			// System.err.format("Age range - faliure in reading or calculating
			// age. Lets submit anyway.");
			Logging.slog(e.getMessage());
			Logging.slog("Age range - faliure in reading or calculating age. Lets submit anyway.");
			offer.setIsAge(true);
		}
	}

	static Double humanRealAge(int min, int max) {
		Double avg = new Double((min + max));
		avg = avg / 2;
		return avg;
	}

	static Double ageRange(int min, int max) {
		Double median = new Double(max - min);
		median = median / 2;
		return median;
	}

	static void checkForSpecificActor(Actor human, Job offer, Double maybeAgeMin, Double maybeAgeMax) {

		try {
			Double ageRange = new Double(ageRange(human.getHumansMinActingAge(), human.getHumansMaxActingAge()));

			Double ageLookLike = new Double(6);
			Double actorRealAge = new Double(
					humanRealAge(human.getHumansMinActingAge(), human.getHumansMaxActingAge()));

			if (maybeAgeMin.equals(new Double(maybeAgeMax))) {
				// there was probably only one age number found by the regex
				if ((Math.abs(actorRealAge - maybeAgeMin) <= ageLookLike)) {
					offer.setIsAge(true);
					return;
				}
			}
			Double maybeAgeAverageTwice = new Double(maybeAgeMin + maybeAgeMax);
			Double avgCharacterAgeTwice = new Double(human.getHumansMinActingAge() + human.getHumansMaxActingAge());

			// check if actor's age is in the range asked for:

			if ((actorRealAge >= maybeAgeMin) && (actorRealAge <= maybeAgeMax)) {
				offer.setIsAge(true);
			}
			// check if actor's age is near the average
			if ((Math.abs(maybeAgeAverageTwice - avgCharacterAgeTwice)) <= ageRange) {
				// the actor is in the age range
				offer.setIsAge(true);
			}

			if ((Math.abs(actorRealAge - maybeAgeMin) <= ageLookLike)
					|| (Math.abs(actorRealAge - maybeAgeMax) <= ageLookLike)) {
				offer.setIsAge(true);
			}

		} catch (Exception e) {
			Logging.slog(e.getMessage());
		}
	}

	static public void understandUnionStatus(Job offer) {
		String allData = (new String(offer.getOfferRole())).concat(" ").concat(offer.offerListing.toLowerCase());

		if ((offer.getOfferUnionStatus()).contains("sag") || (offer.getOfferUnionStatus()).contains("aftra")) {
			offer.setCharacterUnionDemand('u');
		}

		if ((allData.contains("\tsag")) || (allData.contains(" sag")) || (allData.startsWith("sag"))
				|| (allData.contains("\tunion")) || (allData.contains(" union")) || (allData.startsWith("union"))) {
			offer.setCharacterUnionDemand('u');
		}

		// checking whether the character is open for both union and non union
		if ((offer.getOfferUnionStatus()).contains("Union/Non-Union")
				|| (offer.getOfferUnionStatus()).contains("No Union Affiliation")) {
			offer.setCharacterUnionDemand('b');
		}

	}

	static public void understandingEthnicity(Job offer, Actor human) {
		offer.setIsEthnicityMatch(false);
		if ((offer.offerListingEthnicity).toLowerCase().contains("all ethnicities")) {
			offer.setSeekingEthnicities("all ethnicities");
			offer.setIsEthnicityMatch(true);
			return;
		}

		switch (human.getEthinicityChar()) {
		case 'a':
			if ((offer.offerListingEthnicity).toLowerCase().contains("african american")) {
				offer.setIsEthnicityMatch(true);
				return;
			}
		case 'c':
			if ((offer.offerListingEthnicity).toLowerCase().contains("caucasian")) {
				offer.setIsEthnicityMatch(true);
				return;
			}
		case 'l':
			if ((offer.offerListingEthnicity).toLowerCase().contains("latino")) {
				offer.setIsEthnicityMatch(true);
				return;
			}

		}

	}

	static public void understandingGender(Job offer) {
		String allData = (offer.getOfferRole()).concat(" ").concat(offer.offerListingNotes.toLowerCase());
		// BOTH MALE AND FEMALE
		if ((offer.offerListingSex).contains(" male or female") || (allData.startsWith("males and females"))) {
			offer.setCharacterGender('b');
			return;
		}

		// MALE

		if ((offer.offerListingSex).contains(" male") || (allData.startsWith("male"))) {
			offer.setCharacterGender('m');

		}
		if ((allData.contains(" male")) || (allData.startsWith("male")) || (allData.contains(" men"))
				|| (allData.contains(" man ")) || (allData.startsWith("men"))
				|| (allData.toLowerCase().contains(" male"))) {
			offer.setCharacterGender('m');

		}

		// FEMALE
		if ((offer.offerListingSex).contains(" female") || (allData.startsWith("female"))) {
			if (offer.getCharacterGender() == 'm') {
				// found both Male and Female so mark as BOTH
				offer.setCharacterGender('b');
			}

			offer.setCharacterGender('f');
		}
		if ((allData.contains(" female")) || (allData.startsWith("female")) || (allData.contains(" female "))
				|| (allData.contains("actress ")) || (allData.startsWith("women"))
				|| (allData.toLowerCase().contains(" female"))) {
			if (offer.getCharacterGender() == 'm') {
				// found both Male and Female so mark as BOTH
				offer.setCharacterGender('b');
			}
			offer.setCharacterGender('f');
		}

		// Here is good place to check for a male name here for the character
	}

	
	static public void understandingGenderBS(Job offer) {
		String sexData = new String((offer.offerListingSex).toLowerCase());
		// BOTH MALE AND FEMALE
		 
		if (sexData.contains("males or females")||(sexData.contains("males & females")) ){
			offer.setCharacterGender('b');
			return;
		}

		// MALE

		if ((sexData).contains(" male") || ((sexData).startsWith("male"))) {
			offer.setCharacterGender('m');

		}
 

		

		// FEMALE
		if ((sexData).contains(" female") || ((sexData).startsWith("female"))) {
			if (offer.getCharacterGender() == 'm') {
				// found both Male and Female so mark as BOTH
				offer.setCharacterGender('b');
				return;
			}

			offer.setCharacterGender('f');
		}
		
		if ((sexData.contains("actress ")) || (sexData.startsWith("women"))) {
			if (offer.getCharacterGender() == 'm') {
				// found both Male and Female so mark as BOTH
				offer.setCharacterGender('b');
				return;
			}
			offer.setCharacterGender('f');
		}

		
		//in the event nothing was found yet - lets look at Job.ProductionDetails
		String prod_hint = offer.getProductionDetails().toLowerCase();
		
		
		if (prod_hint.contains("males or females")||(prod_hint.contains("males & females")) ){
			offer.setCharacterGender('b');
			return;
		}

		// MALE

		if ((prod_hint).contains(" male") || ((prod_hint).startsWith("male")) || ((prod_hint).startsWith("men")) || ((prod_hint).contains(" men "))) {
			offer.setCharacterGender('m');

		}
 

		

		// FEMALE
		if ((prod_hint).contains(" female") || ((prod_hint).startsWith("female"))) {
			if (offer.getCharacterGender() == 'm') {
				// found both Male and Female so mark as BOTH
				offer.setCharacterGender('b');
				return;
			}

			offer.setCharacterGender('f');
		}
		
		if ((prod_hint.contains("actress ")) || (prod_hint.contains("women"))) {
			if (offer.getCharacterGender() == 'm') {
				// found both Male and Female so mark as BOTH
				offer.setCharacterGender('b');
			}
			offer.setCharacterGender('f');
		}
		
		
		// Here is good place to check for a male name here for the character
	}
	static public void readNotice(Actor human, Job offer) {
		// this reads the notice and sets all the Job params accordingly.
		try {
			String allData = (new String(offer.getOfferRole())).concat(" ")
					.concat(offer.offerListingNotes.toLowerCase());

			// SAG
			try{
			understandUnionStatus(offer);
			}catch(Exception e){
				Logging.slog("ESL reading error in UNION ");
			}
			// Gender
			try{
			understandingGender(offer);
			}catch(Exception e){
				Logging.slog("ESL reading error in GENDER ");
			}
			// AGE
			try{
			understandingAgeRange(offer, offer.offerListingAgesHint, human);
			}catch(Exception e){
				Logging.slog("ESL reading error in AGE RANGE ");
			}
			// ETHNICITY
			try{
			understandingEthnicity(offer, human);
			}catch(Exception e){
				Logging.slog("ESL reading error in ETHNICITYV ");
			}
			// BLACK_LIST
			try{
			Esl.processBlacklist(offer, allData, human);
			}catch(Exception e){
				Logging.slog("ESL reading error in BLACKLIST ");
			}
			// ONLY SAG LIST
			try{
			Esl.processOnlySagProductions(offer, allData, human);
			}catch(Exception e){
				Logging.slog("ESL reading error in SAG ONLY ");
			}
			// CLIENT BOOKED OUT ON THOSE DATES
			try{
			Esl.processAlreadyBookedDates(offer, allData, human);
			}catch(Exception e){
				Logging.slog("ESL reading error in BOOKED-DATES ");
			}
			// CAR

			if ((allData.contains(" car ")) || (allData.startsWith("car ")) || (allData.contains("w/cars"))
					|| (allData.contains("w/car")) || (allData.contains("mercedes")) || (allData.contains("vehicle"))
					|| (allData.contains("bmw")) || (allData.contains("color of your car"))
					|| (allData.startsWith("car ")) || (allData.contains("cars"))) {
				offer.setIsCar(true);
			}

			// tuxedo
			if ((allData.contains(" tuxido ")) || (allData.contains("tuxedo")) || (allData.contains(" tux "))
					|| (allData.contains("own a tux"))) {
				offer.setNeedTuxedo(true);
			}

			if ((allData.contains("cop uniform ")) || (allData.contains("own NYPD uni"))) {
				offer.setNeedPoliceUniform(true);
			}

			// Stand-in
			if ((offer.getOfferListing().contains("stand-in")) || (allData.contains("standing"))
					|| (allData.contains("stand-in")) || (allData.contains("stand in experience"))) {
				offer.isStandIn = true;

			}
		} catch (Exception e) {
			Logging.slog("ESL reading error");
		}
	}

	static public void readNoticeBS(Actor human, Job offer) {
		// this reads the notice and sets all the Job params accordingly.
		try {
		 

			 
			// Gender
			understandingGenderBS(offer);

			// AGE
			understandingAgeRange(offer, offer.offerListingAgesHint, human);

			// ETHNICITY
			offer.setSeekingEthnicities(offer.offerListingEthnicity);
	//		understandingEthnicity(offer, human);

			// BLACK_LIST
			Esl.processBlacklist(offer, offer.getOfferProjectName(), human);

			// SAG
			updateUnionStatus(offer);
			// ONLY SAG LIST
			Esl.processOnlySagProductions(offer, offer.getOfferProjectName(), human);

			// CLIENT BOOKED OUT ON THOSE DATES
			Esl.processAlreadyBookedDates(offer, offer.getOfferLocation(), human);

			// CAR
			String allData = new String (offer.getProductionDetails());
			if ((allData.contains(" car ")) || (allData.startsWith("car ")) || (allData.contains("w/cars"))
					|| (allData.contains("w/car")) || (allData.contains("mercedes")) || (allData.contains("vehicle"))
					|| (allData.contains("bmw")) || (allData.contains("color of your car"))
					|| (allData.startsWith("car ")) || (allData.contains("cars"))) {
				offer.setIsCar(true);
			}

			// tuxedo
			if ((allData.contains(" tuxido ")) || (allData.contains("tuxedo")) || (allData.contains(" tux "))
					|| (allData.contains("own a tux"))) {
				offer.setNeedTuxedo(true);
			}

			if ((allData.contains("cop uniform ")) || (allData.contains("own NYPD uni"))) {
				offer.setNeedPoliceUniform(true);
			}

			// Stand-in
			if ((offer.getOfferListing().contains("stand-in")) || (allData.contains("standing"))
					|| (allData.contains("stand-in")) || (allData.contains("stand in experience"))) {
				offer.isStandIn = true;

			}
		} catch (Exception e) {
			Logging.slog("ESL reading error");
		}
	}

	static public void fillTalentNoteAA(Actor human, Job currentOfferHa) {
		try {
			// String allData = (offer.getOfferRole()).concat("
			// ").concat((offer.getOfferListing()).toLowerCase());
			String allData = (new String(currentOfferHa.getOfferCharacterDetails()));
			// last time worked
			/*
			 * if ((allData.contains(" note last ")) ||
			 * (allData.contains("please note if you have worked")) ||
			 * (allData.contains("worked on the")) ||
			 * (allData.contains("must not have worked on this project")) ||
			 * (allData.contains("last time that you worked")) ||
			 * (allData.contains("if you've worked on this project before")) ||
			 * (allData.contains("do not submit if you have worked on this show"
			 * ))) { offer.addToMessage("I've never worked on the production.");
			 * }
			 */

			if ((currentOfferHa.getMessage().length() < 5)) {
				currentOfferHa.setMessage(human.getDefaultTalentNotesAA());

			}

		} catch (Exception e) {
			Logging.slog("Failed to fill talent note");
			currentOfferHa.setMessage("");

		}
	}

	static public void fillTalentNoteCN(Actor human, Job offer) {
		try {
			String allData = (offer.getOfferRole()).concat(" ").concat((offer.getOfferListing()).toLowerCase());
			// String allData = (new String(offer.getOfferCharacterDetails()));

			// last time worked

			if ((allData.contains(" note last ")) || (allData.contains("please note if you have worked"))
					|| (allData.contains("worked on the")) || (allData.contains("must not have worked on this project"))
					|| (allData.contains("last time that you worked")) || (allData.contains("last time you "))
					|| (allData.contains("if you've worked on this project before"))
					|| (allData.contains("state last date worked")) || (allData.contains("when you worked last"))
					|| (allData.contains("when last worked")) || (allData.contains("note last you worked"))
					|| (allData.contains("do not submit if you have worked on this show"))) {
				offer.addToMessage("I've never worked on the production.");
			}

			if ((allData.contains("must be able to self report")) || (allData.contains("can self report"))) {
				offer.addToMessage("I can self report.");
			}

			if ((allData.contains("note your sizes")) || (allData.contains("note all sizes"))
					|| (allData.contains("note sizes")) || (allData.contains("your sizes"))
					|| (allData.contains("note neck"))) {
				offer.addToMessage(human.getHumansSizes());
			}

			if ((allData.contains(" Please note if you can provide")) || (allData.contains("must own"))
					|| (allData.contains("own the wardrobe"))) {
				offer.addToMessage("I own the wardrobe.");
			}

			// tuxedo
			if (offer.getNeedTuxedo()) {
				offer.addToMessage("I own a tuxedo.");
			}

			if (offer.isStandIn) {
				offer.addToMessage("I worked as stand-in on Divorce, Limitless and on the Food Network.");
			}

			// improveMessage(offer);
			if ((offer.getMessage().length() < 5)) {
				offer.setMessage(human.getDefaultTalentNotesCN());
			}

		} catch (Exception e) {
			Logging.slog("Failed to fill talent note");
			offer.setMessage("");

		}
	}

	static public void improveMessage(Job offer) {
		// checks the length and if message is empty, then add the basic message
		/*
		 * if (offer.getMessage().length() < 5) { offer.
		 * setMessage("I would like to be considered for this production.\nThank you,\nGuy Kapulnik"
		 * ); }
		 */
		if (!(offer.getMessage().length() < 5)) {
			offer.addToMessage("Thanks");
		}
	}

	static public String firstName(String name) {
		try {
			StringTokenizer st = new StringTokenizer(name);
			if (st.countTokens() > 1) {
				return new String(st.nextToken());
			} else {
				return name;
			}
		} catch (Exception e) {
			return "";
		}

	}

	static public String secondName(String name) {
		try {
			StringTokenizer st = new StringTokenizer(name);
			if (st.countTokens() > 1) {
				st.nextToken(); // this is to move the tokenizer to the second
								// word in the name
				return new String(st.nextToken());
			} else {
				return name;
			}
		} catch (Exception e) {
			return "";
		}

	}

	static public String callNameApi(String name) {
		String gender = new String("");
		try {
			if (name.length() < 1) {
				return new String("unknown");
			}

			String myKey = ""; // "insert your server key here";
			URL url = new URL("https://gender-api.com/get?key=" + myKey + "&name=" + name);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Error: " + conn.getResponseCode());

			}

			InputStreamReader input = new InputStreamReader(conn.getInputStream());
			BufferedReader reader = new BufferedReader(input);

			Gson gson = new Gson();
			JsonObject json = gson.fromJson(reader, JsonObject.class);
			gender = json.get("gender").getAsString();
			Logging.slog(new String("API Character:").concat(name).concat(" : gender : ").concat(gender));
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			gender = new String("unknown");
		}
		return new String(gender);
	}

	public static boolean setGenderIfMaleOrFemale(Job currentOffer, String findGender) {
		if ((findGender).equals(new String("male"))) {
			currentOffer.setCharacterGender('m');
			return true;
		}

		if ((findGender).equals(new String("female"))) {
			currentOffer.setCharacterGender('f');
			return true;
		}
		return false;
	}

	static public boolean checkGenderOfName(Job currentOffer) {
		try {
			String characterName = new String(currentOffer.getOfferCharacterName()).trim();
			if (setGenderIfMaleOrFemale(currentOffer, callNameApi(characterName))) {
				return true;
			}
			if (setGenderIfMaleOrFemale(currentOffer, callNameApi(firstName(characterName)))) {
				return true;
			}

			if (setGenderIfMaleOrFemale(currentOffer, callNameApi(secondName(characterName)))) {
				// THIS is for cases link : "Tall Harry" or " Little Sara"
				return true;
			}
			currentOffer.setCharacterGender('u');
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	static public void calcEthnicity(Job currentOfferTa, String data) {
		// lighting up all the bits ethnicity that appear in the data String
		int i = 8;

		if ((data.contains("african american")) || (data.contains("africanam")) || (data.contains("african"))
				|| (data.contains("african - american"))) {
			currentOfferTa.setSeekingEthnicities("african american");
		}

		if (data.contains("caucasian")) {
			currentOfferTa.setSeekingEthnicities("caucasian");
		}

		// GROUP EASIANS
		if (data.contains("asian")) {
			currentOfferTa.setSeekingEthnicities("asian");
		}
		if (data.contains("korean")) {
			currentOfferTa.setSeekingEthnicities("asian");
		}
		if (data.contains("latin")) {
			currentOfferTa.setSeekingEthnicities("latin");
		}

		// GROUP MIDDLE EASTERNS
		if (data.contains("middle eastern") || (data.contains("mideastern")) || (data.contains("arab"))) {
			currentOfferTa.setSeekingEthnicities("middle eastern");
		}

		if ((data.contains("all ethnicities")) || (data.contains("race open")) || (data.contains("open ethnicity"))) {
			currentOfferTa.setSeekingEthnicities("all ethnicities");
		}

		if (!currentOfferTa.atLeastOneEthnicityChosen()) {
			Logging.slog("No Ethinicity hint appears in the notice - so assuming they seek ALL ETHNICITIES");
			currentOfferTa.setSeekingEthnicities("all ethnicities");
		}

	}

	static public void updateUnionStatus(Job offer_B) {
		// UNION status
		if (BsBooking.search_labels(offer_B, "UNION AND NONUNION")) {
			offer_B.setOfferUnionStatus("union and nonunion");
		}
		if (BsBooking.search_labels(offer_B, "UNION")) {
			offer_B.setOfferUnionStatus("union");
		}
		if (BsBooking.search_labels(offer_B, "NONUNION")) {
			offer_B.setOfferUnionStatus("non-sunion");
		}

	}
}
