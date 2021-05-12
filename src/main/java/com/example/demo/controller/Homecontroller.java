package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.LocationStats;
import com.example.demo.services.CoronaVirusDataServices;

@Controller
public class Homecontroller {
	
	@Autowired
	CoronaVirusDataServices CoronaVirusData;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<LocationStats>allstats=CoronaVirusData.getAllstats();
		int totalreportedcases= allstats.stream().mapToInt(stat->stat.getLatesttotalcases()).sum();
		int totalnewcases=allstats.stream().mapToInt(stat->stat.getNewCasesToday()).sum();
		
		allstats.forEach(stat -> {
			if(stat.getState() == null || stat.getState().isEmpty())
				stat.setState("NA");
		});
		model.addAttribute("locationstats",allstats);
		model.addAttribute("totalreportedcases",totalreportedcases);
		model.addAttribute("totalnewcases",totalnewcases);
		return "home";
	}
}
