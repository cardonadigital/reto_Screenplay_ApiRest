package co.com.sofka.stepdefinitions.restfulbooker.createbooking;

import co.com.sofka.models.BookignRoot;
import co.com.sofka.models.Booking;
import co.com.sofka.setup.SetUp;
import co.com.sofka.util.CreateBooking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.http.HttpStatus;


import static co.com.sofka.questions.AssertText.assertText;
import static co.com.sofka.tasks.DoPost.doPost;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;


public class CreateBookingStepDefinition extends SetUp {
    CreateBooking createBooking = new CreateBooking();
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static String actorName = "Pepe";
    private static String response;
    private static BookignRoot objectResponse;
    private static Booking currentObject;


    @Dado("que el cliente ingreso a la pagina")
    public void queElClienteIngresoALaPagina() {
        try {
            setUpLog4j2();
            actor = Actor.named(actorName);
            actor.can(CallAnApi.at(URL_BASE_RESTFUL_BOOKER));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Cuando("ingrese los datos necesarios para la creación de la reserva")
    public void ingreseLosDatosNecesariosParaLaCreaciónDeLaReserva() throws JsonProcessingException {
        try {
            currentObject = createBooking.create();
            String booking = objectMapper.writeValueAsString(currentObject);
            actor.attemptsTo(
                    doPost()
                            .usingTheResource(RESOURCE_RESTFUL_BOOKER)
                            .withHeaders(headers)
                            .andBodyRequest(booking)
            );
            response = SerenityRest.lastResponse().getBody().asString();
            objectResponse = objectMapper.readValue(response, BookignRoot.class);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Entonces("el sistema debera mostrar la nueva reserva")
    public void elSistemaDeberaMostrarLaNuevaReserva() {
        try {
            actor.should(
                    seeThatResponse("El código de respuesta debe ser" + HttpStatus.SC_OK,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)),
                    seeThat(assertText().is(objectResponse.getBooking().getFirstname(), currentObject.firstname), equalTo(true))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
