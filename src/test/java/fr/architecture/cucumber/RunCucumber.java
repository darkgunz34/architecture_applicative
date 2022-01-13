package fr.architecture.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;


@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"},
		features = "classpath:features")
@CucumberContextConfiguration
public class RunCucumber {

}
