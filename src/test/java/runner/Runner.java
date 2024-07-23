package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
/*        glue = {"steps", "com/devangeliste/testDemo"}*/
        glue = "steps"
        , tags = "@All"
/*        ,tags = "@Prueba_1"*/
/*        , tags = "@Student"*/
/*        , tags = "@FreeRangeTest"*/
/*        , tags = "@Login"*/
)
public class Runner {
}
