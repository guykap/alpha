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

		// Declare the JDBC objects.
		boolean operation_res = false;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// Establish the connection.

			// con = DriverManager.getConnection(connectionUrl);
			con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");

			// Create and execute an SQL statement that returns some data.
			String SQL = "SELECT TOP 1 [config_id],[run_status] ,[origin_site],[offer_type],[sleep_counter],[breath_sec] ,[haha_time]      ,[gecko_wait_time]   ,[only_top_productions] ,[auto_submit_cart]  ,[out_logs] ,[grecko_driver_path]  FROM [malena].[dbo].[run_vars]";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
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

		// Handle any errors that may have occurred.
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

		// Declare the JDBC objects.
		boolean operation_res = false;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs1 = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// Establish the connection.

			// con = DriverManager.getConnection(connectionUrl);
			con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");

			// Create and execute an SQL statement that returns some data.
			String SQL = "SELECT TOP 1 [id] ,[name] ,[phone] ,[email],[billing_ack],[cn_username] ,[cn_password] ,[aa_username] ,[aa_password] ,[is_night_shift] ,[human_is_male] ,[human_ethnicity] ,[union_status] ,[human_sizes] ,[min_acting_age] ,[max_acting_age] ,[default_photo] ,[default_video] ,[default_notes_cn] ,[default_notes_aa] ,[target_regions] ,[black_list]  FROM [malena].[dbo].[actors]";
			stmt = con.createStatement();
			rs1 = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
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

				if (validateClient(id, name, phone, email, billing_ack, cn_username, cn_password, aa_username, aa_password,
						is_night_shift, human_is_male, human_ethnicity, union_status, human_sizes, min_acting_age,
						max_acting_age, default_photo, default_video, default_notes_cn, default_notes_aa, target_regions,
						black_list)) {
					Logging.slog(new String("Successful loading from DB client: ").concat(String.valueOf(id)));
					operation_res = true;
				}

			}
		}

		// Handle any errors that may have occurred.
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

	
	
	public static boolean temp_sub(int offer_id, int actor_id, String data) {
		String connectionUrl = "jdbc:sqlserver://" + getDBName() + ":1433;" + "databaseName=malena;";

	//	String usableData = usableData(data);
		
		// Declare the JDBC objects.
		boolean operation_res = false;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs2 = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// Establish the connection.

			// con = DriverManager.getConnection(connectionUrl);
			con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");

			// Create and execute an SQL statement that returns some data.
			String SQL =  "INSERT INTO submittions_temp VALUES ('"+ offer_id +"','"+ actor_id  +"','"+data + "');";
 
			stmt = con.createStatement();
			stmt.execute(SQL);

			// Iterate through the data in the result set and display it.
			//if (rs2.next()) {
			 
					Logging.slog("Successful writing submittion to DB");
					operation_res = true;
				//}

			
		}

		// Handle any errors that may have occurred.
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

	public static boolean oldgetRunningVars() {

		try {
			// test();

			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(getDBName(), "administrator", "dGuy1234567");

			// conn =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/ink?autoReconnect=true&useSSL=false",
			// "root", "mGuy1234567");
			TimeUnit.SECONDS.sleep(2);
			stmt = conn.createStatement();
			TimeUnit.SECONDS.sleep(2);
			// rs = stmt.executeQuery("SELECT * FROM ink.run_vars;");
			TimeUnit.SECONDS.sleep(2);
			rs = stmt.executeQuery("SELECT * FROM malena.run_vars1;");
			// SELECT * FROM malena.actors;
			// while (rs.next()) {
			if (!rs.next()) {
				rs.beforeFirst();
			}

			// rs.next();

			int config_id = rs.getInt("config_id");
			System.out.println(config_id);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}
			String run_status = rs.getString("status");
			System.out.println(run_status);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}
			String site = rs.getString("site");
			System.out.println(site);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}
			String offer_type = rs.getString("offer_type");
			System.out.println(offer_type);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}
			String sleep_counter = rs.getString("sleep_counter");
			System.out.println(sleep_counter);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}
			String breath_sec = rs.getString("breath_sec");
			System.out.println(breath_sec);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}
			String haha_time = rs.getString("haha_time");
			System.out.println(haha_time);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}
			String gecko_wait_time = rs.getString("gecko_wait_time");
			System.out.println(gecko_wait_time);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}
			String only_top_productions = rs.getString("only_top_productions");
			System.out.println(only_top_productions);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(5);
				rs.beforeFirst();
			}

			String out_logs = rs.getString("out_logs");
			System.out.println(out_logs);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(2);
				rs.beforeFirst();
			}
			String grecko_driver_path = rs.getString("grecko_driver_path");
			System.out.println(grecko_driver_path);
			if (!rs.next()) {
				TimeUnit.SECONDS.sleep(2);
				rs.beforeFirst();
			}
			String autoSubmitCart = rs.getString("auto_submit_cart");
			System.out.println(autoSubmitCart);
			// Date * dateCreated = rs.getDate("date_created");

			if (validateAndInit(run_status, site, offer_type, sleep_counter, out_logs, grecko_driver_path, haha_time,
					breath_sec, gecko_wait_time, only_top_productions, autoSubmitCart)) {
				Logging.slog("Successful loading running vars from DB");
			}

		} catch (SQLException | InterruptedException ex) {
			// handle any errors
			/*
			 * System.out.println("SQLException: " + ex.getMessage());
			 * System.out.println("SQLState: " + ex.getSQLState());
			 * System.out.println("VendorError: " + ex.getErrorCode());
			 */
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
		Beta.setOutLogsPath(outLogs);
		Beta.setGecko_driver_path(greckoDriverPath);

		if (run_status.equals(new String("run"))) {
			Beta.runStatus = true;
			//Logging.slog((new String("Success reading from file running Status = run")));
		 
		}

		if (run_status.equals(new String("stop"))) {
			Beta.runStatus = false;
			//Logging.slog((new String("Success reading from file running Status = stop")));
		 
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

	public static boolean oldgetClientFromDB() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ink?autoReconnect=true&useSSL=false",
					"root", "mGuy1234567");

			/*
			 * conn1 = DriverManager.getConnection(
			 * "jdbc:mysql://malena.climy7kqhhvl.us-east-1.rds.amazonaws.com:3306/malena",
			 * "administrator", "dGuy1234567");
			 */
			stmt1 = conn.createStatement();
			rs1 = stmt1.executeQuery("SELECT * FROM ink.actors;");

			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			int id = rs1.getInt("id");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String name = new String(rs1.getString("name"));
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String phone = rs1.getString("phone");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String email = rs1.getString("email");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String billing_ack = rs1.getString("billing_ack");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String cn_username = rs1.getString("cn_username");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String cn_password = rs1.getString("cn_password");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String aa_username = rs1.getString("aa_username");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String aa_password = rs1.getString("aa_password");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String is_night_shift = rs1.getString("is_night_shift");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String human_is_male = rs1.getString("human_is_male");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String human_ethnicity = rs1.getString("human_ethnicity");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String union_status = rs1.getString("union_status");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String human_sizes = rs1.getString("human_sizes");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String min_acting_age = rs1.getString("min_acting_age");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String max_acting_age = rs1.getString("max_acting_age");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String default_photo = rs1.getString("default_photo");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String default_video = rs1.getString("default_video");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String default_notes_cn = rs1.getString("default_notes_cn");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String default_notes_aa = rs1.getString("default_notes_aa");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String target_regions = rs1.getString("target_regions");
			if (!rs1.next()) {
				rs1.beforeFirst();
			}
			String black_list = rs1.getString("black_list");

			if (validateClient(id, name, phone, email, billing_ack, cn_username, cn_password, aa_username, aa_password,
					is_night_shift, human_is_male, human_ethnicity, union_status, human_sizes, min_acting_age,
					max_acting_age, default_photo, default_video, default_notes_cn, default_notes_aa, target_regions,
					black_list)) {
				Logging.slog("Successful loading running vars from DB");
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		} finally {

			conn = null;
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException sqlEx) {
				}

			}

			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException sqlEx) {
				}

			}

			return true;
		}

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

		if (default_photo.equals(new String(""))) {
			fillDefaultPhoto = new String("0");
		}
		if (default_video.equals(new String(""))) {
			fillDefaultVideo = new String("0");
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

			Beta.clientCN = tempActor;
			return true;
		} catch (Exception e) {
			Logging.slog("Error loading client info from DB");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	static public boolean submittion(int offer_id, int actor_id, String time_submitted, String time_role_appeared,
			String site, String region, String background, String shoot_date, String type, String rate,
			String union_status, String production_name, String production_details, String location,
			String casting_director, String character_details, String talent_notes_filled, String ip_origin_submitted) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://malena.climy7kqhhvl.us-east-1.rds.amazonaws.com:3306/malena", "administrator",
					"dGuy1234567");

			stmt = conn.createStatement();

			String insertQuery = new String("INSERT INTO submittions VALUES ('");
			insertQuery = insertQuery.concat(offer_id + "','");
			insertQuery = insertQuery.concat(actor_id + "','");
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

			rs = stmt.executeQuery(insertQuery);
			test_db_res(rs);

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
	
	public static String usableData(String data){
		String newData3="";
		try{
		String newData = new String(data.replace((char)39, ' '));
		String newData2 =  new String(newData.replace((char)34, ' '));
		 newData3 =  new String(newData2.replace((char)47, ' '));
		}catch(Exception e){
			Logging.slog("Error cleanning String data");
			return data;
		}
		return newData3;
	}
}
