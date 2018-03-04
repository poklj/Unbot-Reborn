package discordBot;

import java.awt.Event;
import java.util.EventListener;
import java.util.regex.Pattern;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

// For Voice Synthisis use Dectalk
public class MessageListener extends ListenerAdapter {
	private static boolean print = true;
	static Command cmd = null;
	static ServerProc sProc = null;
	protected static JDA jda = null;
	private static Databake db;

	public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
		ReadToken rToken = new ReadToken();
		String token = rToken.readToken();
		jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
		// JDA jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
		jda.addEventListener(new MessageListener());
		cmd = new Command(jda, db);
		db = new Databake("DatabakeBase.db");
		sProc = new ServerProc(db);

		// Setup Server Table
		String Server = "CREATE TABLE IF NOT EXISTS SERVERS(ServerID INTEGER NOT NULL PRIMARY KEY, ServerName INTEGER NOT NULL);";
		String OutputChannel = "CREATE TABLE IF NOT EXISTS OutputChannel(ServerID INTEGER NOT NULL, channelID INTEGER NOT NULL,FOREIGN KEY(ServerID) REFERENCES Servers(ServerID) );";
		db.ExecuteStatement(Server);
		db.ExecuteStatement(OutputChannel);
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
			sProc.Check_Is_Known_server(event.getGuild().getId(), event.getGuild().getName());
			if (Message.matches("^(!).*")) {
				System.out.println("Command!");
				cmd.Comd(Message, event.getGuild().getId());
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

	public void SendMessageByID(String ChannelID, String MessageContent) {
		TextChannel channel = jda.getTextChannelById(ChannelID);
		channel.sendMessage(MessageContent);
	}

	public Databake get_db() {
		return this.db;
	}
}
