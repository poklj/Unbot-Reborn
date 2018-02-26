package discordBot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerProc {

	/**
	 * This method is used to check if a Server is Previously Known
	 * @author Poklj
	 */
	Databake db = new Databake("DatabakeBase.db");
	public void Check_Is_Known_server(String ServerID, String GuildName) {
		String BuiltQuery = "Select * from Servers where ServerID="+ ServerID+";";
		ResultSet returnd = db.ExecuteQuery(BuiltQuery, true);
		try {
			if(returnd.next()) {
				System.out.println(ServerID +"Exists");
			} else {
				System.out.println(ServerID +"Does Not Exist");
				Log_New_server(ServerID, GuildName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used to Create a new entry into the Database, logging the server name and 
	 * @author Poklj
	 */
	public void Log_New_server(String ServerID, String GuildName ) {
		
		try {
			PreparedStatement stmt = db.conn.prepareStatement("Insert Into Servers values(?, ?)");
			stmt.setString(1, ServerID);
			stmt.setString(2, GuildName);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
