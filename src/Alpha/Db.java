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

		//	String SQL = "SELECT TOP 1 [config_id],[run_status] ,[origin_site],[offer_type],[sleep_counter],[breath_sec] ,[haha_time] ,[gecko_wait_time]   ,[only_top_productions] ,[auto_submit_cart]  ,[out_logs] ,[grecko_driver_path]  FROM [malena].[dbo].[run_vars]";
		
			
			String SQL = "SELECT TOP 1 [keep_alive].[config_id],[keep_alive].[actor_id],[last_inter],[run_status] ,[origin_site],[offer_type],[sleep_counter],[breath_sec] ,[haha_time] ,[gecko_wait_time]   ,[only_top_productions] ,[cold_time_min],[auto_submit_cart]  ,[out_logs] ,[grecko_driver_path]   FROM [malena].[dbo].[keep_alive],[malena].[dbo].[run_vars]  WHERE keep_alive.config_id = run_vars.config_id  ORDER BY last_inter;";
			
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
			Timestamp testTimeStamp = 	rs.getTimestamp("last_inter");
				int config_id = rs.getInt("config_id");
				int actor_id =  rs.getInt("actor_id");		 
				String run_status = rs.getString("run_status");
				String origin_site = rs.getString("origin_site");
				String offer_type = rs.getString("offer_type");
				String sleep_counter = rs.getString("sleep_counter");
				String breath_sec = rs.getString("breath_sec");
				String haha_time = rs.getString("haha_time");
				String gecko_wait_time = rs.getString("gecko_wait_time");
				String only_top_productions = rs.getString("only_top_productions");
				String cold_time_min = rs.getString("cold_time_min");
				String out_logs = rs.getString("out_logs");
				String grecko_driver_path = rs.getString("grecko_driver_path");
				String autoSubmitCart = rs.getString("auto_submit_cart");
				// Date * dateCreated = rs.getDate("date_created");
				
				

				if (validateAndInit(config_id,actor_id, testTimeStamp,run_status, origin_site, offer_type, sleep_counter, out_logs, grecko_driver_path,
						haha_time, breath_sec, gecko_wait_time, only_top_productions, cold_time_min, autoSubmitCart)) {
				System.out.println("Successful loading running vars from DB");
					operation_res = true;
					
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
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
	
	 
	public static boolean getClientFromDB(int actor_id) {
		String connectionUrl = "jdbc:sqlserver://" + getDBName() + ":1433;" + "databaseName=malena;";

		boolean operation_res = false;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs1 = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");

			String SQL = "SELECT TOP 1 [id] ,[name] ,[phone] ,[email],[billing_ack],[cn_username] ,[cn_password] ,[aa_username] ,[aa_password],[bs_username] ,[bs_password] ,[is_night_shift] ,[human_is_male] ,[human_ethnicity] ,[union_status] ,[human_sizes] ,[min_acting_age] ,[max_acting_age] ,[default_photo] ,[default_video] ,[default_notes_cn] ,[default_notes_aa] ,[target_regions] ,[black_list],[only_sag_productions],[already_booked_dates]  FROM [malena].[dbo].[actors] WHERE id='"+ actor_id +"';";
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
				String bs_username = rs1.getString("bs_username");
				String bs_password = rs1.getString("bs_password");
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
				String only_sag_productions = rs1.getString("only_sag_productions");
				String already_booked_dates = rs1.getString("already_booked_dates");
			 
				if (ClientsMngt.validateClient(id, name, phone, email, billing_ack, cn_username, cn_password, aa_username,
						aa_password,bs_username, bs_password, is_night_shift, human_is_male, human_ethnicity, union_status, human_sizes,
						min_acting_age, max_acting_age, default_photo, default_video, default_notes_cn,
						default_notes_aa, target_regions, black_list, only_sag_productions,already_booked_dates)) {
					System.out.println(new String("Successful loading from DB client: ").concat(String.valueOf(id)));
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
 

	public static boolean updateLastInteraction(String config_id, String actor_id, String nyTime) {

		if ((nyTime.length() < 1) || (actor_id.length() < 1)|| (config_id.length() < 1)) {
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
/*
			String SQL = "UPDATE  [malena].[dbo].[interactions2] SET last_inter=CONVERT(DATETIME, '" + nyTime
					+ "') WHERE actor_id='" + actor_id + "';";
*/
			
			String SQL = "UPDATE [malena].[dbo].[keep_alive]  SET last_inter = CONVERT(DATETIME, '" + nyTime + "')  WHERE config_id = '" + config_id + "' AND actor_id = '" + actor_id + "';";
			
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

	static public boolean validateAndInit(int config_id, int actor_id,Timestamp last_time ,String run_status, String site, String offerType, String sleepCounter,
			String outLogs, String greckoDriverPath, String hahaTime, String breathSec, String geckoWaitTime,
			String onlyTopProductions, String cold_time, String autoSubmitCart) {
		
		if(config_id <0){
			System.out.println("Error reading config_id");
			return false;
		}else{
			ClientsMngt.config_id = config_id;
		}
		
		if(actor_id <0){
			System.out.println("Error reading actor_id");
			return false;
		}else{
			ClientsMngt.client_id = actor_id;
		}
		
 
		ClientsMngt.last_interaction = last_time;
		
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
		
		if((Integer.parseInt(cold_time))> ClientsMngt.MAX_TO_COLD){
			ClientsMngt.ACTUAL_COLD_TIME = ClientsMngt.MAX_TO_COLD;
		}else
		{
			ClientsMngt.ACTUAL_COLD_TIME = Integer.parseInt(cold_time);
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
			ClientsMngt.site = 1;
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
		
		if (site.equals(new String("CL"))) {
			ClientsMngt.site = 2;
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
			ClientsMngt.site = 0;
			Beta.isCastingNetworks = false;
			return true;
		}
		
		if (site.equals(new String("BS"))) {
			ClientsMngt.site = 3;
			Beta.isCastingNetworks = false;
			return true;
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
	
	
	public static boolean runIt(String configId, String actorId) {

		if ((configId.length()<1 )||(actorId.length()<1)){
			return false;
		}
    		String connectionUrl = "jdbc:sqlserver://" + getDBName() + ":1433;" + "databaseName=malena;";
			boolean operation_res = false;
			Connection con = null;
			Statement stmt = null;

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager.getConnection(connectionUrl, "administrator", "dGuy1234567");
	  
				
			//	String SQL = "UPDATE [malena].[dbo].[keep_alive]  SET last_inter = CONVERT(DATETIME, '" + nyTime + "')  WHERE config_id = '" + config_id + "' AND actor_id = '" + actor_id + "';";

				String SQL =  "UPDATE [malena].[dbo].[keep_alive] SET last_inter = CONVERT(DATETIME, '2000-11-11 11:11:11') WHERE config_id = '" +  configId + "' AND actor_id = '" + actorId + "';";
				 
				
				
				stmt = con.createStatement();
				stmt.execute(SQL);

				Logging.slog(new String("Successful updating DB 2000-11-11 for config id :").concat(configId));
				operation_res = true;

			} catch (Exception e) {
				e.printStackTrace();
			
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

 
}
