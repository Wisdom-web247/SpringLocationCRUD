package com.bharath.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.location.entities.Location;
import com.bharath.location.service.LocationService;
import com.bharath.location.util.EmailUtil;

@Controller
public class LocationController {

	@Autowired
	LocationService service;
	
	@Autowired
	EmailUtil emailUtil;

	//createLocation
	@GetMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@PostMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {

		Location locationSaved = service.saveLocation(location);
		String msg = "Location saved with id: " + locationSaved.getId();

		// Relating to the JSP form
		modelMap.addAttribute("msg", msg);

		emailUtil.sendEmail("presidentseaford@gmail.com", "Location Saved", "Location Saved Succesfully about to return a response");
		return "createLocation";
	}
	
	//get the update JSP
	@GetMapping("/updateLocation")
	public String updateLocation() {

		return "updateLocation";
	}
	//viewAllLocations
	@GetMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {

		List<Location> locations = service.findAllLocations();
		modelMap.addAttribute("locations", locations);

		return "displayLocations";
	}

	@DeleteMapping("deleteLocations")
	public String deleteLocation(@RequestParam("id") Long id) {
		Location location = service.findLocationById(id);

		service.deleteLocation(location);

		return "displayLocations";
	}
	
//
//	@Autowired
//	LocationService locationService;
//	
//	@PostMapping("/createLocation")
//	public Location createLocation(@RequestBody Location location) {
//		
//		return locationService.saveLocation(location);
//	}
//	
//	@PostMapping("/updateLocation")
//	public Location updateLocation(@RequestBody Location location) {
//		
//		return locationService.updateLocation(location);
//	}
//	
//	@PostMapping("/viewAllLocations")
//	public List < Location> viewAllLocations(@RequestBody Location location) {
//		
//		return locationService.findAllLocations();
//	}
//	
//	@PostMapping("/viewLocationById")
//	public Location viewLocationById(Long id) {
//		
//		return locationService.findLocationById(id);
//	}
	
}
