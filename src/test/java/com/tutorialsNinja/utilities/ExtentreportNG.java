package com.tutorialsNinja.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentreportNG {

	public static ExtentReports reporter;
	public static ExtentSparkReporter spark;

	public static ExtentReports generateExtentreport() {
		reporter = new ExtentReports();
		String Reportpath = System.getProperty("user.dir") + "\\Reports\\reports.html";
		spark = new ExtentSparkReporter(Reportpath);
        spark.config().setReportName("TurorialsNinjaAppTesting");
		spark.config().setDocumentTitle("TutorialsNinjaQAReport");
		spark.config().setTheme(Theme.DARK);
		reporter.attachReporter(spark);
		reporter.setSystemInfo("QATester", "Nandha");
		return reporter;
	}
}
