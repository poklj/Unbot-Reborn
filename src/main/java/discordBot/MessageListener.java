package discordBot;

import java.util.regex.Pattern;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

// For Voice Synthisis use Dectalk
public class MessageListener extends ListenerAdapter {
	private static boolean print = true;
	Command cmd = new Command();

	public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
		ReadToken rToken = new ReadToken();
		String token = rToken.readToken();
		JDA jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
		jda.addEventListener(new MessageListener());

		Databake db = new Databake("testDB.db");

		// Setup Server Table
		String Server = "CREATE TABLE IF NOT EXISTS SERVERS(ServerID INTEGER NOT NULL PRIMARY KEY, ServerName INTEGER NOT NULL);"
				+ "COMMIT; ";
		db.ExecuteStatement(Server);
		/*
		 * TODO: Create the function to add Guilds to the database (Either prejoined or
		 * Not)
		 */
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.isFromType(ChannelType.PRIVATE)) {
			System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());
		} else {
			String Message = event.getMessage().getContentDisplay();
			if (Message.matches("^(!).*")) {
				System.out.println("Command!");
				cmd.Comd(Message);
			}

			System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(), event.getTextChannel().getName(),
					event.getMember().getEffectiveName(), event.getMessage().getContentDisplay());
		}
	}

	@Override
	public void onMessageDelete(MessageDeleteEvent event) {
		if (event.isFromType(ChannelType.PRIVATE)) {

		}
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		/*
		 * TODO: Setup what to do if someone Joins the Guild ( setup using the Database
		 * Allowing each server to Select what to do on a user join (Sending a Server
		 * Message or Direct message))
		 */
	}

	public void SendMessage(String MessageContent) {

	}
}
