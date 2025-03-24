package stepdefinitions;

import interactions.FinalizePurchaseInteraction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import questions.GeneralQuestions;
import questions.RamdonProductsQuestion;
import questions.SelectProductQuestion;
import questions.ValidateDetailProductCartQuestion;
import tasks.*;
import util.GeneralEnvironment;

import java.util.List;
import java.util.Map;

import static model.InfoPersonaModel.*;
import static model.ProductModel.getTotalSelectedProducts;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static ui.CartUI.*;

public class ShoppingCartStep {
    @Given("Ingreso a la pagina saucedemo")
    public void ingresoALaPaginaSaucedemo() {
        String environment = GeneralEnvironment.environmentVariables("environments.default.webdriver.base.url");
        theActorCalled("Andres").attemptsTo(NavigateTo.openPage(environment));
    }

    @When("Intento iniciar sesion con las credenciales")
    public void intentoIniciarSesionConLasCredenciales(List<Map<String, String>> list) {
        System.out.println("user " + list.get(0).get("user") + "pass " + list.get(0).get("password"));
        withCurrentActor(LoginTask.Login(list));
    }

    @And("Intento agregar {int} productos al carrito")
    public void intentoAgregarProductosAlCarrito(int products) {
        List<String> list_select_product = RamdonProductsQuestion.ProductsName(products).answeredBy(theActorCalled("Andres"));
        List<String> select_product = SelectProductQuestion.SelectProduct(list_select_product).answeredBy(theActorCalled("Andres"));
        withCurrentActor(ScrollElementJSTask.ScrollElementJS(ITEMS_CART_));
        Assert.assertEquals("La cantidad de item seleccionados no coincide con la cantidad de item del carrito de compras. ", getTotalSelectedProducts(), ITEMS_CART.resolveFor(theActorCalled("Andres")).getText());
        List<String> detail_product_cart = ValidateDetailProductCartQuestion.ValidateDetailProductCart(list_select_product).answeredBy(theActorCalled("Andres"));
        assertThat("error no coincide el detalle de los productos agregados con los del carrito de compras ", select_product, containsInAnyOrder(detail_product_cart.toArray()));
        withCurrentActor(ScrollElementJSTask.ScrollElementJS(BTN_CHECKOUT_));
        GeneralClickTask.GeneralClick(BTN_CHECKOUT);
    }

    @And("Ingreso informacion personal")
    public void ingresoInformacionPersonal(List<Map<String, String>> person_info) {
        setMap(person_info);
        for (Map<String, String> info : getlist()) {
            setFirstName(info.get("Nombre"));
            withCurrentActor(PersonInfoTask.PersonInfo(info));
        }
    }

    @And("Finalizo la compra")
    public void finalizoLaCompra() {
        withCurrentActor(FinalizePurchaseInteraction.FinalizePurchase());
    }

    @Then("El sistema debe de mostrame el mensaje {string}")
    public void elSistemaDebeDeMostrameElMensaje(String msg) {
        theActorInTheSpotlight().should(seeThat(GeneralQuestions.valueString(MSG_SUCCESS_SHOPPING), equalTo(msg)));
    }
}
