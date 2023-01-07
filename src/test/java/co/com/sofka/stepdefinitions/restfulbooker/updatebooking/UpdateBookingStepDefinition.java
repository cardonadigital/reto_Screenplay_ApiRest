package co.com.sofka.stepdefinitions.restfulbooker.updatebooking;

import co.com.sofka.models.BookignRoot;
import co.com.sofka.models.Booking;
import co.com.sofka.models.Post;
import co.com.sofka.setup.SetUp;
import co.com.sofka.util.CreateBooking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static co.com.sofka.questions.AssertNotNull.assertNotNull;
import static co.com.sofka.questions.AssertText.assertText;
import static co.com.sofka.tasks.DoPut.doPut;
import static co.com.sofka.tasks.GetToken.getToken;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateBookingStepDefinition extends SetUp {
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    CreateBooking createBooking = new CreateBooking();
    private static String actorName = "Pepe";
    private static String token;
    private static String response;
    private Map<String, String> headerWithToken = new HashMap<>() {{
        put("Content-Type", "application/json");
    }};
    private String resource = "/booking/1";
    private static Booking currentObject;
    private static Booking objectResponse;

    @Dado("el usuario ingresa a la pagina")
    public void elUsuarioIngresaALaPagina() {
        try {
            setUpLog4j2();
            actor = Actor.named(actorName);
            actor.can(CallAnApi.at(URL_BASE_RESTFUL_BOOKER));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Cuando("ingresa el usuario y la contraseña correspondientes")
    public void ingresaElUsuarioYLaContraseñaCorrespondientes() throws JsonProcessingException {
        try {
            actor.attemptsTo(
                    getToken()
            );
            response = SerenityRest.lastResponse().getBody().asString();
            token = String.valueOf(new ObjectMapper().readValue(response, ObjectNode.class).get("token"));
            headerWithToken.put("Cookie", "token=" + token);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Entonces("podrá obtener el token")
    public void podráObtenerElToken() {
        try {
            actor.should(
                    seeThat(assertNotNull().is(token) , equalTo(true))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }


    @Dado("que el cliente ingreso a la pagina")
    public void queElClienteIngresoALaPagina() {
        try {
            actor = Actor.named(actorName);
            actor.can(CallAnApi.at(URL_BASE_RESTFUL_BOOKER));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Cuando("ingrese los datos necesarios para la actualización de la reserva especifica")
    public void ingreseLosDatosNecesariosParaLaActualizaciónDeLaReservaEspecifica() throws JsonProcessingException {
        try {
            currentObject = createBooking.create();
            String booking = objectMapper.writeValueAsString(currentObject);
            actor.attemptsTo(
                    doPut()
                            .usingTheResource(resource)
                            .withHeaders(headerWithToken)
                            .andBodyRequest(booking)
            );
            response = SerenityRest.lastResponse().getBody().asString();
            objectResponse = objectMapper.readValue(response, Booking.class);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Entonces("el sistema debera mostrar la reserva actualizada")
    public void elSistemaDeberaMostrarLaReservaActualizada() {
        try {
            actor.should(
                    seeThatResponse("El código de respuesta debe ser" + HttpStatus.SC_OK,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)),
                    seeThat(assertText().is(objectResponse.getFirstname(), currentObject.firstname), equalTo(true))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }

    }

}
