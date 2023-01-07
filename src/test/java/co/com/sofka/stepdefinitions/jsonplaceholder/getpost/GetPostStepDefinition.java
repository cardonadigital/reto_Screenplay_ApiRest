package co.com.sofka.stepdefinitions.jsonplaceholder.getpost;

import co.com.sofka.models.Post;
import co.com.sofka.setup.SetUp;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.http.HttpStatus;

import java.io.IOException;

import static co.com.sofka.questions.AssertInteger.assertInteger;
import static co.com.sofka.questions.AssertText.assertText;
import static co.com.sofka.tasks.DoGet.doGet;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetPostStepDefinition extends SetUp {
    ObjectMapper objectMapper = new ObjectMapper();
    private static String actorName = "Pepe";
    private static Integer id;
    private static String response;
    private static Post objectResponse;


    @Dado("que el cliente ingreso a la pagina")
    public void queElClienteIngresoALaPagina() {
        try {
            setUpLog4j2();
            actor = Actor.named(actorName);
            actor.can(CallAnApi.at(URL_BASE_JSON_PLACE_HOLDER));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Cuando("consulte el post por id: {int}")
    public void consulteElPostPorId(Integer id) throws IOException {
        try {
            GetPostStepDefinition.id = id;
            String resource = RESOURCE_JSON_PLACE_HOLDER + "/" + id;
            actor.attemptsTo(
                    doGet().usingTheResource(resource)
            );
            response = SerenityRest.lastResponse().getBody().asString();
            objectResponse = objectMapper.readValue(response, Post.class);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Entonces("obtendrá la información del post correspondiente")
    public void obtendráLaInformaciónDelPostCorrespondiente() {
        try {
            actor.should(
                    seeThatResponse("El código de respuesta debe ser" + HttpStatus.SC_OK,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)),
                    seeThat(assertInteger().is(objectResponse.getId(), 1), equalTo(true))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
