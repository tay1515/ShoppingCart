package questions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static ui.CartUI.PRODUCT_DESCRIP_CART;
import static ui.CartUI.PRODUCT_PRICE_CART;

public class ValidateDetailProductCartQuestion implements Question<List<String>> {

    private static List<String> item;

    public ValidateDetailProductCartQuestion(List<String> item) {
        ValidateDetailProductCartQuestion.item = item;
    }

    @Override
    public List<String> answeredBy(Actor actor) {

        try {

            List<String> detailProductCart = new ArrayList<String>();

            for (String name : item) {
                Thread.sleep(2000);
                System.out.println("Name P " + name);
                detailProductCart.add(name);
                detailProductCart.add(PRODUCT_DESCRIP_CART.of(name).resolveFor(actor).getText());
                detailProductCart.add(PRODUCT_PRICE_CART.of(name).resolveFor(actor).getText());
            }

            System.out.println("List items cart: " + detailProductCart);
            Thread.sleep(1000);
            return  detailProductCart;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail("Error al realizar el proceso de ramdon de producto");
        }
        return null;
    }


    public static ValidateDetailProductCartQuestion ValidateDetailProductCart(List<String> item) {
        return new ValidateDetailProductCartQuestion(item);
    }
}
