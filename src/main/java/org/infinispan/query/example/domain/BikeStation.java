/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file 
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.infinispan.query.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.annotations.Longitude;
import org.hibernate.search.annotations.Spatial;

/**
 * A Bike Station with Search annotations support for text and geospatial searches.
 * 
 * @author Xavier Coulon
 *
 */
@XmlRootElement
@Indexed
@Spatial
public class BikeStation {
	
	@Field
	private int id;
	
	@Field
	private String stationName;
	
	@Field
	private int availableDocks;

	@Field
	private int totalDocks;

	@Field
	private int availableBikes;
	
	@Latitude
	private float latitude;

	@Longitude
	private float longitude;
	
	@Field
	private BikeStationStatus status;

	/**
	 * Empty Constructor
	 */
	public BikeStation() {
		
	}
	
	/**
	 * Full constructor
	 * @param id
	 * @param stationName
	 * @param availableDocks
	 * @param totalDocks
	 * @param availableBikes
	 * @param latitude
	 * @param longitude
	 * @param status
	 */
	public BikeStation(final int id, final String stationName, final int availableDocks, final int totalDocks, final int availableBikes,
			final float latitude, final float longitude, final BikeStationStatus status) {
		super();
		this.id = id;
		this.stationName = stationName;
		this.availableDocks = availableDocks;
		this.totalDocks = totalDocks;
		this.availableBikes = availableBikes;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public int getAvailableDocks() {
		return availableDocks;
	}

	public void setAvailableDocks(int availableDocks) {
		this.availableDocks = availableDocks;
	}

	public int getTotalDocks() {
		return totalDocks;
	}

	public void setTotalDocks(int totalDocks) {
		this.totalDocks = totalDocks;
	}

	public int getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(int availableBikes) {
		this.availableBikes = availableBikes;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public BikeStationStatus getStatus() {
		return status;
	}

	public void setStatus(BikeStationStatus status) {
		this.status = status;
	}
	
	
	
}
