package todocucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/report/htmlReport.html", "json:target/report/jsonReport.json"},
        glue = "stepdefinitions",
        features = "src/test/resources/todocucumber"
)
public class RunCucumberTest {

}
