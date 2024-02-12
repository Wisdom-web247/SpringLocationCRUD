package com.bharath.location.service;

import java.util.List;

import com.bharath.location.entities.Location;

public interface LocationService {

	Location saveLocation(Location location);

	Location updateLocation(Location location);

	void deleteLocation(Location location);

	Location findLocationById(int id);

	List<Location> findAllLocations();

}
