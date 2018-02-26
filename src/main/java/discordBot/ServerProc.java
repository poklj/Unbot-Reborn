package discordBot;


public class ServerProc {

	/**
	 * This method is used to check if a Server is Previously Known
	 * @author Poklj
	 */
	Databake db = new Databake("testDB.db");
	public void Check_Is_Known_server(String ServerID, String GuildName) {
		String BuiltQuery = "Select * from Servers where ServerID=" + ServerID+";";
		
		if (!db.ExecuteTryFailStatement(BuiltQuery)) {
			Log_New_server(ServerID, GuildName);
			System.out.println("Server Logged("+ServerID+")");
		} else {
			System.out.println("Server Exists("+ServerID+")");
		}
	}
	
	/**
	 * Used to Create a new entry into the Database, logging the server name and 
	 * @author Poklj
	 */
	public void Log_New_server(String ServerID, String GuildName ) {
		String BuiltQuery = "Insert Into Servers values("+ServerID+","+GuildName+");";
		db.ExecuteStatement(BuiltQuery);
	}
}
