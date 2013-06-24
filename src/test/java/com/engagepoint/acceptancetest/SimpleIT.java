package com.engagepoint.acceptancetest;

import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.jbehave.examples.client.ExchangeCurrenciesModel;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import net.thucydides.jbehave.ThucydidesJUnitStories;

@RunWith(Arquillian.class)
public class SimpleIT extends ThucydidesJUnitStories {
	
	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "test-client.war")
				.addPackage("org.jboss.arquillian.jbehave.domain")
				.addClass(ExchangeCurrenciesModel.class)
				.setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
				.addAsWebResource(new File("src/main/webapp/exchangeCurrencies.xhtml"))
				.addAsWebInfResource(new File("src/main/webapp/WEB-INF/faces-config.xml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return archive;
	}
	
}
