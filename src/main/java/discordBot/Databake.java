package discordBot;

import java.io.File;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import ch.qos.logback.core.layout.EchoLayout;

public class Databake {

	public boolean IsDBExist(String Filename) {
		boolean isPresent = false;
		try {
			File tfile = new File("./db/" + Filename).getCanonicalFile();
			isPresent = tfile.exists();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isPresent;
	}

	public void CreateDBFile(String Filename) {
		boolean tfile = false;
		try {
			boolean kip = new File("./db/").getCanonicalFile().mkdirs();
		}catch (Exception e) {
			e.getStackTrace();
		}
		try {
			
			tfile = new File("./db/" + Filename).getCanonicalFile().createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CreateDB(String Filename) {
		File tfile = null;
		try {
			tfile = new File("./db/").getCanonicalFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = ("jdbc:sqlite:" + tfile + Filename);

		try {
			Connection conn = DriverManager.getConnection(url);
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public  void DatabakeE(String Filename) {
		//load Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Default Filename
		if (Filename == null) {
			Filename = "FallbackDatabake1.db";
		}
		
		if (this.IsDBExist(Filename) != true) {
			this.CreateDBFile(Filename);
		}
		this.CreateDB(Filename);
	}

}
