package co.com.sofka.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class AssertNotNull implements Question<Boolean> {
    private String value;


    public AssertNotNull is(String value){
        this.value = value;
        return this;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return (!value.isEmpty());
    }

    public static AssertNotNull assertNotNull() {
        return new AssertNotNull();
    }
}
