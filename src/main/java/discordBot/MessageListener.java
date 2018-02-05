package discordBot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	private static boolean print = true;
	public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
		ReadToken rToken = new ReadToken();
		String token = rToken.readToken();
		JDA jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
		jda.addEventListener(new MessageListener());

		Databake db = new Databake("testDB.db");
		db.ExecuteStatement("CREATE TABLE IF NOT EXISTS SERVERS(id INTEGER NOT NULL, serverID INTEGER NOT NULL, ServerName INTEGER NOT NULL");
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.isFromType(ChannelType.PRIVATE)) {
			System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());
		} else {
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
		 * TODO: Setup what to do if someone Joins the Guild
		 * 		( setup using the Database Allowing each server to Select what to do on a user join (Sending a Server Message or Direct message))
		 */
	}
}
