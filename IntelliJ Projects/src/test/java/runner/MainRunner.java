package runner;

import cucumber.api.CucumberOptions;

/*@RunWith(Cucumber.class)
// Add the cucumber options*/
@CucumberOptions (
        features = {"src/test/java/features"},
        glue = {"steps"},
        monochrome = true,
        tags = {},
        plugin = {"pretty", "html:target/site/cucumber",
                "json:target/cucumber.json",
                "com.cucumber.listener.ExtentCucumberFormatter:target/report.html"
        }
)
public class MainRunner {

}