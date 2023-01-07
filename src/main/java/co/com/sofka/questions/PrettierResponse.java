package co.com.sofka.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.nio.charset.StandardCharsets;

public class PrettierResponse implements Question<String> {
    private Actor actor;

    @Override
    public String answeredBy(Actor actor) {
        this.actor = actor;
        return new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);
    }

    public static PrettierResponse prettierResponse(){
        return new PrettierResponse();
    }
}
