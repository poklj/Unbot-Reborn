package discordBot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
	private static enum Commands {
		TESTCOMMAND
	}

	private Pattern pat = null;

	public Command() {
		/**
		 * 
		 * @author Zach
		 * 
		 */
		pat = Pattern.compile("^!(\\w*)");

	}

	public void Comd(String CommandString) {
		Matcher match = this.pat.matcher(CommandString);
		match.find();
		String command = match.group(1);
		System.out.println(command);
		try {
			switch (Commands.valueOf(command.toUpperCase())) {
			case TESTCOMMAND:
				this.TestCommand();
				break;
			}
		} catch (IllegalArgumentException e) {
			// Return back the fact that Someone entered an Invalid command and return it back or exit silently
			e.printStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void TestCommand() {
		System.out.println("Commands Work!");
	}

}
