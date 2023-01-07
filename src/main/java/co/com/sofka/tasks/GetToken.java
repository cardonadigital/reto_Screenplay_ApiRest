package co.com.sofka.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class GetToken implements Task {
    private static String bodyRequest = "{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";



    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/auth")
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .headers("Content-Type", "application/json")
                                        .log().body()
                                        .body(bodyRequest)
                        )
        );
    }

    public static GetToken getToken() {
        return new GetToken();
    }
}
