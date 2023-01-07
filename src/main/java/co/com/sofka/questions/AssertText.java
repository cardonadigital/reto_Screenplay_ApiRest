package co.com.sofka.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class AssertText implements Question<Boolean> {
    private String actual;
    private String expected;

    public AssertText is(String actual, String expected){
        this.actual = actual;
        this.expected = expected;
        return this;
    }


    @Override
    public Boolean answeredBy(Actor actor) {
        return (actual.equals(expected));
    }

    public static AssertText assertText() {
        return new AssertText();
    }
}
