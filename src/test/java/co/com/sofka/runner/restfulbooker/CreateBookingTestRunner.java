package co.com.sofka.runner.restfulbooker;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/restfulbooker/createBooking.feature"},
        glue = {"co.com.sofka.stepdefinitions.restfulbooker.createbooking"},
        strict = true
)
public class CreateBookingTestRunner {
}
