package Steps;

import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import qa.driverfactory.DriverFactory;
import qa.util.ConfigReader;

public class AppHooks {
	WebDriver driver;
	Properties prop;
	DriverFactory driverFactory;
	ConfigReader configReader;

	
	@Before(order=0)
	public void init_prop() {
		 prop=new Properties();
		 
		configReader = new ConfigReader();
		prop=configReader.init_properties();
		
	}
	
	@Before(order=1)
	public void launchapp() throws MalformedURLException {
		String browsername =prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver=driverFactory.init_driver(browsername);
		
	}
	
@After(order=0)
	public void quict() {
		driver.quit();
	}
@After(order=1)
public void tearDown(Scenario sc) {
	if(sc.isFailed()) {
	String scenarioName = sc.getName().replaceAll(" ", "_");
	byte[] sorcepath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	sc.attach(sorcepath, "image/png", scenarioName);
	}
	
}

}
