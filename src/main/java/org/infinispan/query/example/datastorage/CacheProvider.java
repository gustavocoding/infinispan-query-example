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
package org.infinispan.query.example.datastorage;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.query.example.domain.BikeStation;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

/**
 * Cache Provider
 * 
 * @author Xavier Coulon
 *
 */
@Singleton
public class CacheProvider {

	public static final String BIKE_STATIONS_CACHE_NAME = "bikestations";

	public static final String ETAGS_CACHE_NAME = "etags";
	
	@Produces
	@BikeStationsCache
   @ApplicationScoped
	public Cache<Integer, BikeStation> getBikeStationsCache() throws IOException {
      DefaultCacheManager cacheManager = new DefaultCacheManager("cache.xml");
		return cacheManager.getCache(BIKE_STATIONS_CACHE_NAME);
	}

}
