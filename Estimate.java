package ezEstimatingPackageFX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Estimate {
	
	private static String reportID;
	private static Customer customer;
	private static Vehicle vehicle;
	private static String total, estimator, consoleOutput;
	
	public Estimate(Customer newCustomer, Vehicle newVehicle, String newTotal, String newEstimator, String newReport, String newConsoleOutput) {
		
		customer = newCustomer;
		vehicle = newVehicle;
		total = newTotal;
		estimator = newEstimator;
		reportID = newReport;
		consoleOutput = newConsoleOutput;
		
	}
	
	public static void save(Estimate newEstimate) {
		
		String user = System.getProperty("user.home");
		String dirName = new String(user + File.separator + "Documents" + File.separator + "EZEstimating" +  File.separator + "Estimates");
		Path path3 = Paths.get(dirName);
		if(!Files.exists(path3)) {
			try {
				Files.createDirectory(path3);
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			}
		
		String estimate = new String(user + File.separator + "Documents" + File.separator + "EZEstimating" + File.separator + "Estimates" + File.separator + (reportID + ".txt") );
		Path path4 = Paths.get(estimate);
		if(!Files.exists(path4))
		try {
			Files.createFile(path4);
			BufferedWriter writer = Files.newBufferedWriter(path4);
			writer.write(customer.getFirst());
			writer.newLine();
			writer.write(customer.getLast());
			writer.newLine();
			writer.write(customer.getAddress());
			
			writer.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		else {
			try {
				BufferedWriter writer = Files.newBufferedWriter(path4);
				writer.write(customer.getFirst());
				writer.newLine();
				writer.write(customer.getLast());
				writer.newLine();
				writer.write(customer.getAddress());
				writer.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void load()
	{
		
	}

}
