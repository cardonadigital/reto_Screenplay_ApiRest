package co.com.sofka.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class AssertInteger implements Question<Boolean> {
    private Integer actual;
    private Integer expected;

    public AssertInteger is(Integer actual, Integer expected){
        this.actual = actual;
        this.expected = expected;
        return this;
    }


    @Override
    public Boolean answeredBy(Actor actor) {
        return (actual.equals(expected));
    }

    public static AssertInteger assertInteger() {
        return new AssertInteger();
    }
}
