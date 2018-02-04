package discordBot;

import java.io.File;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ch.qos.logback.core.layout.EchoLayout;

public class Databake  {
	private String filename = null;
	private String current_path = "./db/";
	private String current_file = null;
	private File constructedPath = null;
	private File constructedFilePath = null;
	
	public Databake(String Filename) {
		filename = Filename;
		current_file = "./db/" + filename;
		
		try {
			setConstructedPath(new File(current_path).getCanonicalFile());
			setConstructedFilePath(new File(current_file).getCanonicalFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public File getConstructedPath() {
		return constructedPath;
	}
	
	public File getConstructedFilePath() {
		return constructedFilePath;
	}

	public void setConstructedPath(File constructedPath) {
		this.constructedPath = constructedPath;
	}

	

	public void setConstructedFilePath(File constructedFilePath) {
		this.constructedFilePath = constructedFilePath;
	}
	

}
