package com.piano.assigment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		ReadAndMerge rm = new ReadAndMerge();
		rm.execute();
		HashMap hm = rm.getRecords();

		return args -> {
			Collection<User> values = hm.values();
			Iterator iter = values.iterator();
			String pathC = "/home/vergovic/projects/piano/FileOutJava.csv";
			Writer output;
			output = new BufferedWriter(new FileWriter(pathC));  //clears file every time
			
			output.append("user_id,email,first_name,last_name"+System.lineSeparator());
		
			while (iter.hasNext()) {
				User u = (User) iter.next();
				String email = u.getEmail();
				//log.info(email);
				String req = "?aid=o1sRRZSLlw&api_token=xeYjNEhmutkgkqCZyhBn6DErVntAKDx30FqFOS6D&email=" + email
						+ "&limit=1&offset=0";

				SearchResult result = restTemplate.getForObject(
						"https://sandbox.piano.io/api/v3/publisher/user/search" + req, SearchResult.class);

				
				//log.info (result.toString());
				LinkedHashMap lhm = null;
				try {
					lhm = (LinkedHashMap) result.getUsers().get(0);
					u.setUid((String)lhm.get("uid"));
//					log.info((String) lhm.get("email"));

					//log.info(lhm.toString());
			
				}
				catch (Exception e) {
			//		e.printStackTrace();
				}
				String line = u.getUid()+","+u.getEmail()+","+u.getFirstName()+"," +u.getLastName()+System.lineSeparator();
				output.append(line);
				log.info(line);
			}
			output.close();
			System.exit(0);
		};
	}
}
