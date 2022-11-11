package com.piano.assigment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class ReadAndMerge {

	private static String COMMA_DELIMITER=",";
	private static String pathA = "/home/vergovic/projects/piano/FileA.csv";
	private static String pathB = "/home/vergovic/projects/piano/FileB.csv";
	//private List<List<String>> records = new ArrayList<>();
	private HashMap records = new HashMap();
	
	public HashMap getRecords() {
		return records;
	}

	public void setRecords(HashMap records) {
		this.records = records;
	}

	public void execute() {
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathB))) {
		    String line;
		    br.readLine(); // skips header
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        User u = new User();
		        u.setUid(values[0]);
		        u.setFirstName(values[1]);
		        u.setLastName(values[2]);
		        records.put(values[0],u);
		        
		    }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathA))) {
		    String line;
		    br.readLine(); // skips header
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        
		        User u = null;
		        u=(User)records.get(values[0]);
		        u.setEmail(values[1]);
		        
		        records.put(values[0],u);
		        
		    }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
