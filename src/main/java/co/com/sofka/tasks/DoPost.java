package co.com.sofka.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class DoPost implements Task {

    private String resource;
    private Map<String, String> headers;
    private String bodyRequest;

    public DoPost usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoPost withHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public DoPost andBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .headers("Content-Type", "application/json")
                                        .log().body()
                                        .body(bodyRequest)
                        )
        );


    }

    public static DoPost doPost() {
        return new DoPost();
    }
}
