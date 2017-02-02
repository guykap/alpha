package Alpha;

import java.util.concurrent.TimeUnit;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import java.sql.*;
//import com.mysql.fabric.xmlrpc.Client;

public class Db {
	static private Connection conn = null;
	static private Statement stmt = null;
	static private ResultSet rs = null;
	static private ResultSet rs1 = null;
	static private Statement stmt1 = null;
	static private String dbName = "";

	public static void setDBName(String data) {
		dbName = new String(data);
	}

	public static String getDBName() {
		return new String(dbName);
	}

	public static boolean getRunningVars() {

		String connectionUrl = "jdbc:sqlserver://" + getDBName() + ":1433;" + "databaseName=malena;";
		boolean operation_res = false;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");

			String SQL = "SELECT TOP 1 [config_id],[run_status] ,[origin_site],[offer_type],[sleep_counter],[breath_sec] ,[haha_time]      ,[gecko_wait_time]   ,[only_top_productions] ,[auto_submit_cart]  ,[out_logs] ,[grecko_driver_path]  FROM [malena].[dbo].[run_vars]";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				int config_id = rs.getInt("config_id");
				String run_status = rs.getString("run_status");
				String origin_site = rs.getString("origin_site");
				String offer_type = rs.getString("offer_type");
				String sleep_counter = rs.getString("sleep_counter");
				String breath_sec = rs.getString("breath_sec");
				String haha_time = rs.getString("haha_time");
				String gecko_wait_time = rs.getString("gecko_wait_time");
				String only_top_productions = rs.getString("only_top_productions");
				String out_logs = rs.getString("out_logs");
				String grecko_driver_path = rs.getString("grecko_driver_path");
				String autoSubmitCart = rs.getString("auto_submit_cart");
				// Date * dateCreated = rs.getDate("date_created");

				if (validateAndInit(run_status, origin_site, offer_type, sleep_counter, out_logs, grecko_driver_path,
						haha_time, breath_sec, gecko_wait_time, only_top_productions, autoSubmitCart)) {
					Logging.slog("Successful loading running vars from DB");
					operation_res = true;
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

		return operation_res;

	}

	public static boolean getClientFromDB() {
		String connectionUrl = "jdbc:sqlserver://" + getDBName() + ":1433;" + "databaseName=malena;";

		boolean operation_res = false;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs1 = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");

			String SQL = "SELECT TOP 1 [id] ,[name] ,[phone] ,[email],[billing_ack],[cn_username] ,[cn_password] ,[aa_username] ,[aa_password] ,[is_night_shift] ,[human_is_male] ,[human_ethnicity] ,[union_status] ,[human_sizes] ,[min_acting_age] ,[max_acting_age] ,[default_photo] ,[default_video] ,[default_notes_cn] ,[default_notes_aa] ,[target_regions] ,[black_list]  FROM [malena].[dbo].[actors]";
			stmt = con.createStatement();
			rs1 = stmt.executeQuery(SQL);
			while (rs1.next()) {
				int id = rs1.getInt("id");

				String name = new String(rs1.getString("name"));
				String phone = rs1.getString("phone");
				String email = rs1.getString("email");
				String billing_ack = rs1.getString("billing_ack");
				String cn_username = rs1.getString("cn_username");
				String cn_password = rs1.getString("cn_password");
				String aa_username = rs1.getString("aa_username");
				String aa_password = rs1.getString("aa_password");
				String is_night_shift = rs1.getString("is_night_shift");
				String human_is_male = rs1.getString("human_is_male");
				String human_ethnicity = rs1.getString("human_ethnicity");
				String union_status = rs1.getString("union_status");
				String human_sizes = rs1.getString("human_sizes");
				String min_acting_age = rs1.getString("min_acting_age");
				String max_acting_age = rs1.getString("max_acting_age");
				String default_photo = rs1.getString("default_photo");
				String default_video = rs1.getString("default_video");
				String default_notes_cn = rs1.getString("default_notes_cn");
				String default_notes_aa = rs1.getString("default_notes_aa");
				String target_regions = rs1.getString("target_regions");
				String black_list = rs1.getString("black_list");

				if (validateClient(id, name, phone, email, billing_ack, cn_username, cn_password, aa_username,
						aa_password, is_night_shift, human_is_male, human_ethnicity, union_status, human_sizes,
						min_acting_age, max_acting_age, default_photo, default_video, default_notes_cn,
						default_notes_aa, target_regions, black_list)) {
					Logging.slog(new String("Successful loading from DB client: ").concat(String.valueOf(id)));
					operation_res = true;
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (rs1 != null)
				try {
					rs1.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

		return operation_res;

	}

	public static boolean updateLastInteraction(String actor_id, String nyTime) {

		if ((nyTime.length() < 1) || (actor_id.length() < 1)) {
			return false;
		}

		// FORMAT FOR NY TIME: 2012-06-30 11:10:00
		String connectionUrl = "jdbc:sqlserver://" + getDBName() + ":1433;" + "databaseName=malena;";
		boolean operation_res = false;
		Connection con = null;
		Statement stmt = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");

			String SQL = "UPDATE  [malena].[dbo].[interactions2] SET last_inter=CONVERT(DATETIME, '" + nyTime
					+ "') WHERE actor_id='" + actor_id + "';";

			stmt = con.createStatement();
			stmt.execute(SQL);

			Logging.slog(new String("Successful updating DB on last interaction at :").concat(String.valueOf(nyTime)));
			operation_res = true;

		} catch (Exception e) {
			e.printStackTrace();
			Logging.slog(new String("Error writing to DB the last interaction with time: ").concat(nyTime));
			Logging.slog(e.getMessage());
		} finally {

			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

		return operation_res;
	}

	public static boolean temp_sub(int offer_id, int actor_id, String data) {
		String connectionUrl = "jdbc:sqlserver://" + getDBName() + ":1433;" + "databaseName=malena;";
		boolean operation_res = false;
		Connection con = null;
		Statement stmt = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");
			String SQL = "INSERT INTO submittions_temp VALUES ('" + offer_id + "','" + actor_id + "','" + data + "');";

			stmt = con.createStatement();
			stmt.execute(SQL);
			Logging.slog(new String("Successful writing submittion to DB offer_id=").concat(String.valueOf(offer_id)));
			operation_res = true;
		} catch (Exception e) {
			e.printStackTrace();
			Logging.slog("Error writing to DB");
			Logging.slog(e.getMessage());
		}

		finally {

			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

		return operation_res;
	}

	static public boolean validateAndInit(String run_status, String site, String offerType, String sleepCounter,
			String outLogs, String greckoDriverPath, String hahaTime, String breathSec, String geckoWaitTime,
			String onlyTopProductions, String autoSubmitCart) {
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
			ClientsMngt.onlyTopProd = ClientsMngt.AA_DEFAULT_MAX_PROD_ON_PAGE;
		} else {
			ClientsMngt.onlyTopProd = Integer.parseInt(onlyTopProductions);
		}

		if ((autoSubmitCart.length() < 1) || (!ClientsMngt.stringIsABoolean(autoSubmitCart))) {
			ClientsMngt.autoSubmitCart = ClientsMngt.DEFUALT_AUTO_SUBMIT_CART;
		} else {
			ClientsMngt.autoSubmitCart = Boolean.parseBoolean(autoSubmitCart);
		}

		Breath.setChosenSleepCycle(Integer.parseInt(sleepCounter));
		ClientsMngt.setOutLogsPath(outLogs);
		ClientsMngt.setGecko_driver_path(greckoDriverPath);

		if (run_status.equals(new String("run"))) {
			Beta.runStatus = true;

		}

		if (run_status.equals(new String("stop"))) {
			Beta.runStatus = false;
			// Logging.slog((new String("Success reading from file running
			// Status = stop")));

		}

		if (site.equals(new String("CN"))) {
			if (offerType.equals(new String("both"))) {
				Beta.isCastingNetworks = true;
				Beta.offerType = new String("both");
				return true;
			}
			if (offerType.equals(new String("extras"))) {
				Beta.isCastingNetworks = true;
				Beta.offerType = new String("extras");
				return true;
			}
			if (offerType.equals(new String("principle"))) {
				Beta.isCastingNetworks = true;
				Beta.offerType = new String("principle");
				return true;
			}

		}
		if (site.equals(new String("AA"))) {
			Beta.isCastingNetworks = false;
			return true;
		}

		return false;
	}

	static public boolean validateClient(int id, String name, String phone, String email, String billing_ack,
			String cn_username, String cn_password, String aa_username, String aa_password, String is_night_shift,
			String human_is_male, String human_ethnicity, String union_status, String human_sizes,
			String min_acting_age, String max_acting_age, String default_photo, String default_video,
			String default_notes_cn, String default_notes_aa, String target_regions, String black_list) {

		String fillRegions = new String(target_regions);
		String fillDefaultPhoto = new String(default_photo);
		String fillDefaultVideo = new String(default_video);
		boolean fill_is_night_shift;
		boolean fill_human_is_male;
		int fill_min_acting_age;
		int fill_max_acting_age;
		if (target_regions.length() < 1) {
			Logging.slog("regions missing in DB. So choosing NY as default");
			fillRegions = new String("new york");
		}

		if (default_photo.equals(new String(""))||(default_photo.equals(new String("0")))) {
			fillDefaultPhoto = new String("1");
		}
		 

		if (is_night_shift.equals(new String("true"))) {
			fill_is_night_shift = false;
		} else {
			fill_is_night_shift = true;
		}

		if (is_night_shift.equals(new String("true"))) {
			fill_is_night_shift = false;
		} else {
			fill_is_night_shift = true;
		}

		if (human_is_male.equals(new String("true"))) {
			fill_human_is_male = true;
		} else if (human_is_male.equals(new String("false"))) {
			fill_human_is_male = false;
		} else {
			Logging.slog("Actor gender is not clear. Choosing male as default");
			;
			fill_human_is_male = true;
		}
		try {
			fill_min_acting_age = ClientsMngt.validateStringToInt(min_acting_age);
			fill_max_acting_age = ClientsMngt.validateStringToInt(max_acting_age);

			Actor tempActor = new Actor(String.valueOf(id), name, phone, email, billing_ack, cn_username, cn_password,
					aa_username, aa_password, fill_is_night_shift, fill_human_is_male, human_ethnicity, union_status,
					human_sizes, fill_min_acting_age, fill_max_acting_age, fillDefaultPhoto, fillDefaultVideo,
					default_notes_cn, default_notes_aa, fillRegions, black_list);

			Beta.client = tempActor;
			return true;
		} catch (Exception e) {
			Logging.slog("Error loading client info from DB");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	static public boolean submittion(int offer_id, int actor_id, String aa_internal, String time_submitted, String time_role_appeared,
			String site, String region, String background, String shoot_date, String type, String rate,
			String union_status, String production_name, String production_details, String location,
			String casting_director, String character_details, String talent_notes_filled, String ip_origin_submitted) {

		
		String connectionUrl = "jdbc:sqlserver://" + getDBName() + ":1433;" + "databaseName=malena;";
		boolean operation_res = false;
		Connection conn = null;
		Statement stmt = null;
		//ResultSet rs = null;
		try {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");

			stmt = conn.createStatement();
 
			String insertQuery = new String("INSERT INTO submissions VALUES ('");
			insertQuery = insertQuery.concat(offer_id + "','");
			insertQuery = insertQuery.concat(actor_id + "','");
			insertQuery = insertQuery.concat(aa_internal + "','");
			insertQuery = insertQuery.concat(time_submitted + "','");
			insertQuery = insertQuery.concat(time_role_appeared + "','");
			insertQuery = insertQuery.concat(site + "','");
			insertQuery = insertQuery.concat(region + "','");
			insertQuery = insertQuery.concat(background + "','");
			insertQuery = insertQuery.concat(shoot_date + "','");
			insertQuery = insertQuery.concat(type + "','");
			insertQuery = insertQuery.concat(rate + "','");
			insertQuery = insertQuery.concat(union_status + "','");
			insertQuery = insertQuery.concat(production_name + "','");
			insertQuery = insertQuery.concat(production_details + "','");
			insertQuery = insertQuery.concat(location + "','");
			insertQuery = insertQuery.concat(casting_director + "','");
			insertQuery = insertQuery.concat(character_details + "','");
			insertQuery = insertQuery.concat(talent_notes_filled + "','");
			insertQuery = insertQuery.concat(ip_origin_submitted + "');");

			
			stmt = conn.createStatement();
			stmt.execute(insertQuery);
			

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		} finally {

			conn = null;
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}

			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}

			}

		}
		return true;
	}

	public static boolean test_db_res(ResultSet rs) {
		if (rs == null) {
			return false;
		}
		return true;
	}

	public static String usableData(String data) {
		String newData3 = "";
		try {
			String newData = new String(data.replace((char) 39, ' '));
			String newData2 = new String(newData.replace((char) 34, ' '));
			newData3 = new String(newData2.replace((char) 47, ' '));
		} catch (Exception e) {
			Logging.slog("Error cleanning String data");
			return data;
		}
		return newData3;
	}
}
