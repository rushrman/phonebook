package com.plivo.phonebook.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

	private static final Logger instance = new Logger();

	public String logname = "simplelog";
	protected String env = System.getProperty("user.dir");
	private static File logFile;

	public static Logger getInstance() {
		return instance;
	}

	public static Logger getInstance(String withName) {
		instance.logname = withName;
		instance.createLogFile();
		return instance;
	}

	public void createLogFile() {

		File logsFolder = new File(env + '/' + "logs");
		if (!logsFolder.exists()) {
			System.err.println("INFO: Creating new logs directory in " + env);
			logsFolder.mkdir();

		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		logname = logname + '-' + dateFormat.format(cal.getTime()) + ".log";
		Logger.logFile = new File(logsFolder.getName(), logname);
		try {
			if (logFile.createNewFile()) {
				// New file made
				System.err.println("INFO: Creating new log file");
			}
		} catch (IOException e) {
			System.err.println("ERROR: Cannot create log file");
			System.exit(1);
		}
	}

	private Logger() {
		if (instance != null) {
			// Prevent Reflection
			throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
		}
		this.createLogFile();
	}

	public static void log(String message) {
		try {
			FileWriter out = new FileWriter(Logger.logFile, true);
			out.write(message.toCharArray());
			out.close();
		} catch (IOException e) {
			System.err.println("ERROR: Could not write to log file");
		}
	}
}
