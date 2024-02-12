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

@Controller
public class LocationController {

	@Autowired
	LocationService service;

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

		return "createLocation";
	}

	// ModelMap =
	@GetMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {

		List<Location> locations = service.findAllLocations();
		modelMap.addAttribute("ocations", locations);

		return "displayLocations";
	}

	@DeleteMapping("deleteLocations")
	public String deleteLocation(@RequestParam("id") int id) {
		Location location = service.findLocationById(id);

		service.deleteLocation(location);

		return "displayLocations";
	}
}
