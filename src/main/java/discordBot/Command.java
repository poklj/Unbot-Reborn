package discordBot;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
	private static enum Commands {
		TESTCOMMAND, SETDOUTPUT
	}

	private Pattern pat = null;
	Databake db = new Databake("DatabakeBase.db");
	public Command() {
		/**
		 * 
		 * @author Zach
		 * 
		 */
		pat = Pattern.compile("^!(\\w*)");

	}

	public void Comd(String CommandString, String GuildID) {
		Matcher match = this.pat.matcher(CommandString);
		match.find();
		String command = match.group(1);
		System.out.println(command);
		try {
			switch (Commands.valueOf(command.toUpperCase())) {
				case TESTCOMMAND:
					this.TestCommand();
					break;
				case SETDOUTPUT:
					this.SetDoutput(CommandString, GuildID);
					break;
			}
		} catch (IllegalArgumentException e) {
			// Return back the fact that Someone entered an Invalid command and return it back or exit silently
			e.printStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void SetDoutput(String CommandStr,String guildID) {
		Pattern Dout = Pattern.compile("^!\\w* (\\w*)");
		Matcher match = Dout.matcher(CommandStr);
		match.find();
		String channel = match.group(1);
		
		//Just kinda Allow people to designate a channel that doesn't exist
		try {
			PreparedStatement Stmt = db.conn.prepareStatement("Insert Into OutputChannel values(?,?)");
			Stmt.setString(1, guildID);
			Stmt.setString(2, channel);
			Stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void TestCommand() {
		System.out.println("Commands Work!");
	}

}
