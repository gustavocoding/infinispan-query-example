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
package org.infinispan.query.example;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;

import java.net.URL;

import org.infinispan.Version;
import org.infinispan.query.example.util.TestPrinter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.spec.se.manifest.ManifestDescriptor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xavier Coulon
 * 
 */
@RunWith(Arquillian.class)
@RunAsClient
public class BikeStationsResourceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(BikeStationsResourceTestCase.class);

	public static final String WEBINF = "src/main/webapp/WEB-INF";  

	@Rule
	public TestPrinter watchman = new TestPrinter();

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive webarchive = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, BikeStationsResourceTestCase.class.getPackage())
				//.addAsWebInfResource(new File(WEBINF, "jboss-deployment-structure.xml"))
				.addAsResource(manifest(), "META-INF/MANIFEST.MF")
            .addAsResource("cache.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		LOGGER.info("WebArchive content:\n{}", webarchive.toString(true));
		return webarchive;
	}
	
	private static Asset manifest() {
		String manifest = Descriptors.create(ManifestDescriptor.class)
				.attribute("Dependencies",
				"org.infinispan:" + Version.MODULE_SLOT + " services, "
				+ "org.infinispan.query:" + Version.MODULE_SLOT + " services"
				+ "org.infinispan.commons:" + Version.MODULE_SLOT + " services"
				+ "org.hibernate.search.engine:" + Version.MODULE_SLOT + " services"
				)
				.exportAsString();
		
		LOGGER.info("MANIFEST.MF:\n{}", manifest.toString());
		return new StringAsset(manifest);
	}

	@Test
	@RunAsClient
	public void shouldRetrieveBikeStationsAgain(@ArquillianResource URL baseURL) {
		final String location = baseURL.toString() + "rest/stations";
		expect().statusCode(200).when().get(location);
	}

	

}
