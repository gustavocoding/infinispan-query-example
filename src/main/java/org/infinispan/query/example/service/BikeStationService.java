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
package org.infinispan.query.example.service;

import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.infinispan.Cache;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.Search;
import org.infinispan.query.SearchManager;
import org.infinispan.query.example.datastorage.BikeStationsCache;
import org.infinispan.query.example.domain.BikeStation;
import org.infinispan.query.example.domain.BikeStationStatus;
import org.slf4j.Logger;

/**
 * The {@link BikeStation} Service.
 * 
 * @author Xavier Coulon
 * 
 */
@Singleton
@Startup
public class BikeStationService {

	@Inject
	private Logger logger;

	@Inject
	@BikeStationsCache
	private Cache<Integer, BikeStation> bikeStationsCache;

	/**
	 * Returns the {@link BikeStation} identified by the given {@code id} or {@code null} if none was found.
	 * 
	 * @param id
	 *            the {@link BikeStation} id
	 * @return the {@link BikeStation} with the given {@code id} or {@code null}.
	 */
	public BikeStation getBikeStation(final int id) {
		logger.debug("Returning bike station with id #{}", id);
		return bikeStationsCache.get(id);
	}

	/**
	 * @return a {@link List} containing all {@link BikeStation}s with {@link BikeStationStatus} equals to
	 *         {@link BikeStationStatus#IN_SERVICE}.
	 */
	public List<Object> getBikeStationsInService() {
		logger.debug("Returning all 'In-Service' bike stations");
      SearchManager sm = Search.getSearchManager(bikeStationsCache);
      QueryBuilder queryBuilder = sm.buildQueryBuilderForClass(BikeStation.class).get();
      Query query = queryBuilder.keyword().onField("status").matching(BikeStationStatus.IN_SERVICE).createQuery();

      CacheQuery cacheQuery = sm.getQuery(query);

      return cacheQuery.list();

   }

}
