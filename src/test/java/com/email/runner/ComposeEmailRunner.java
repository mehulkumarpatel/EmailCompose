package com.email.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions( tags = {"@compose_email"},glue = {"com.email.stepdefs"}, plugin = {"html:target/cucumber-reports/ComposeEmail/cucumber-pretty","json:target/json-cucumber-reports/ComposeEmail/cukejson.json",
		"testng:target/testng-cucumber-reports/ComposeEmail/cuketestng.xml" }, features = {"src/test/resources/features"})
public class ComposeEmailRunner extends AbstractTestNGCucumberParallelTests {
	
	@BeforeClass
	public static void before() {
		System.out.println("Before - "+System.currentTimeMillis());
	}
	
	@AfterClass
	public static void after() {
		System.out.println("After - "+System.currentTimeMillis());
	}

}
