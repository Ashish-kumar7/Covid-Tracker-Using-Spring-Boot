package com.example.demo.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.LocationStats;
import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

// The @Service annotation is also a specialization of the component annotation. ...
// It Indicates that an annotated class is a "Service" (e.g. a business service facade).
@Service
public class CoronaVirusDataServices {
	
	//	whenever the application loads this gone a make a call to the specified url
	//	and load all the data from there.
	private String Virus_Data_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> allstats= new ArrayList<>();
	
	public List<LocationStats> getAllstats() {
		return allstats;
	}

	//	When we annotate a method in Spring Bean with @PostConstruct annotation, it gets executed after the spring bean is initialized.
	//	We can have only one method annotated with @PostConstruct annotation.
	// @Scheduled annotation can be used to configure and schedule tasks.
	//inside the parameter we write cron that specifies the second min hr day month yr.
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchvirusData() throws IOException, InterruptedException {
		List<LocationStats> newstats = new ArrayList<>();
		
		// Httpclient is used to make the http call.
		HttpClient client = HttpClient.newHttpClient();
		
		// Httprequest allows us to use the new builder pattern.
		// URI.create will convert the string type of URL to uri format.
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Virus_Data_URL))
				.build();
		
		//client.send the url and returning to the format of dtring to tthe httpresponse.
		HttpResponse<String> httpResponse=client.send(request,HttpResponse.BodyHandlers.ofString());
		
		// System.out.println(httpResponse.body());	
		
		//creating the Reader class object to be passed.
		StringReader csvbodyreader=new StringReader(httpResponse.body());
		// Commons library functionality beign used here to access and work on the csv file which we get. for parsing the CSV
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyreader);
		
		for (CSVRecord record : records) {
			LocationStats locationstat =new LocationStats();
			locationstat.setCountry(record.get("Country/Region"));
			locationstat.setState(record.get("Province/State"));
			locationstat.setLatesttotalcases(Integer.parseInt(record.get(record.size()-1)));
			locationstat.setNewCasesToday(Integer.parseInt(record.get(record.size()-1)) - Integer.parseInt(record.get(record.size()-2)));
			newstats.add(locationstat);
		}
		this.allstats=newstats;
	}
}
