package discordBot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerProc {

	/**
	 * This method is used to check if a Server is Previously Known
	 * 
	 * @author Poklj
	 */
	Databake db = null;

	public ServerProc(Databake db) {
		this.db = db;
	}

	public void Check_Is_Known_server(String ServerID, String GuildName) {
		// String BuiltQuery = "Select * from Servers where ServerID="+ ServerID+";";
		ResultSet returned = null;
		try {
			PreparedStatement stmt = db.conn.prepareStatement("Select * from Servers where ServerID=?");
			stmt.setString(1, ServerID);
			returned = stmt.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ResultSet returnd = db.ExecuteQuery(BuiltQuery, true);

		try {
			if (returned.next()) {
				System.out.println(ServerID + " Exists");
			} else {
				System.out.println(ServerID + " Does Not Exist");
				Log_New_server(ServerID, GuildName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used to Create a new entry into the Database, logging the server name and
	 * 
	 * @author Poklj
	 */
	public void Log_New_server(String ServerID, String GuildName) {

		try {
			PreparedStatement stmt = db.conn.prepareStatement("Insert Into Servers values(?, ?)");
			stmt.setString(1, ServerID);
			stmt.setString(2, GuildName);
			stmt.execute();
			db.conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
