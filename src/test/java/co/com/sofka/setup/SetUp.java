package co.com.sofka.setup;

import net.serenitybdd.screenplay.Actor;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.HashMap;
import java.util.Map;

import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static com.google.common.base.StandardSystemProperty.USER_DIR;

public class SetUp {
    protected static final Logger LOGGER = Logger.getLogger(SetUp.class);

    protected static final String URL_BASE_JSON_PLACE_HOLDER = "https://jsonplaceholder.typicode.com";
    protected static final String RESOURCE_JSON_PLACE_HOLDER = "/posts";

    protected static final String URL_BASE_RESTFUL_BOOKER = "https://restful-booker.herokuapp.com";
    protected static final String RESOURCE_RESTFUL_BOOKER = "/booking";


    protected Actor actor;
    protected Map<String, String> headers = new HashMap<>() {{
        put("Content-Type", "application/json");
    }};

    protected void setUpLog4j2() {
        try {
            PropertyConfigurator.configure(USER_DIR.value() + LOG4J_PROPERTIES_FILE_PATH.getValue());
        }catch (Exception e){
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
