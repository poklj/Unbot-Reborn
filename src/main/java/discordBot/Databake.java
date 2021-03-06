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

public class Databake {
	private String filename = null;
	private String current_path = "./db/";
	private String current_file = null;
	private File constructedPath = null;
	private File constructedFilePath = null;

	protected Connection conn = null;
	private Statement statement = null;
	private ResultSet rs = null;

	/**
	 * Creates the Requirements to Interact with a Database file. This will
	 * automatically create the Database file if it does not already exist.
	 * 
	 * @param Filename
	 *            The File to create (or access) the Database
	 * 
	 */
	public Databake(String Filename) {

		filename = Filename;
		current_file = "./db/" + filename;

		try {
			setConstructedPath(new File(current_path).getCanonicalFile());
			setConstructedFilePath(new File(current_file).getCanonicalFile());
			// Create Folder for Databases If it doesn't exist
			if (!constructedPath.exists()) {
				constructedPath.mkdirs();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Run the Creation of the Database File or Connect to the file If it already
		// exists
		if (!constructedFilePath.exists()) {
			this.CreateNewDB();
		} else {
			try {
				conn = DriverManager.getConnection("jdbc:sqlite://" + constructedFilePath.getAbsolutePath());
				statement = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(constructedPath);
		System.out.println(constructedFilePath);
	}

	public void CreateNewDB() {
		/*
		 * Creates The Database and Establishes a Connection
		 */

		try {
			constructedFilePath.createNewFile();
			conn = DriverManager.getConnection("jdbc:sqlite://" + constructedFilePath.getAbsolutePath());
			statement = conn.createStatement();
		} catch (IOException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Execute an sql query with no returns, Mainly for Table creation and
	 * Destruction
	 * 
	 * @Param sql A String of SQL to execute
	 */

	public void ExecuteStatement(String sql) {

		try {

			statement.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Execute an sql query, only attempting to see if It actually returns anything.
	 * Mostly for Existance checking
	 * 
	 * @Param sql A String of SQL to execute
	 */
	public boolean ExecuteTryFailStatement(String sql) {

		try {
			statement.execute(sql);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Execute a returnable Query
	 * 
	 * @param sql
	 *            A string of SQL to execute
	 * @param print
	 *            Boolean: Print to console the results of sql (Default: false)
	 * @return ResultSet
	 */
	public ResultSet ExecuteQuery(String sql, boolean Print) {

		if (!Print) {
			Print = false;
		}
		try {
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Print) {
			System.out.println(rs);
		}
		return rs;
	}

	public ResultSet getPreviousResultSet() {
		return rs;
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
