package co.com.sofka.stepdefinitions.jsonplaceholder.deletepost;

import co.com.sofka.setup.SetUp;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.http.HttpStatus;

import static co.com.sofka.questions.AssertText.assertText;
import static co.com.sofka.tasks.DoDelete.doDelete;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeletePostStepDefinition extends SetUp {
    private static String actorName = "Pepe";
    private static Integer id;
    private static String response;

    @Dado("que el cliente ingreso a la pagina")
    public void queElClienteIngresoALaPagina() {
        try {
            setUpLog4j2();
            actor = Actor.named(actorName);
            actor.can(CallAnApi.at(URL_BASE_JSON_PLACE_HOLDER));
        }catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Cuando("elimine el post por id: {int}")
    public void elimineElPostPorId(int id) throws JsonProcessingException {
        try {
            DeletePostStepDefinition.id = id;
            String resource = RESOURCE_JSON_PLACE_HOLDER + "/" + id;
            actor.attemptsTo(
                    doDelete().usingTheResource(resource)
            );
            response = SerenityRest.lastResponse().getBody().asString();
        }catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Entonces("el sistema debera retornan lo siguiente: {string}")
    public void elSistemaDeberaRetornanLoSiguiente(String message) {
        try {
            actor.should(
                    seeThatResponse("El código de respuesta debe ser" + HttpStatus.SC_OK,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)),
                    seeThat(assertText().is(response, message), equalTo(true))
            );
        }catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
