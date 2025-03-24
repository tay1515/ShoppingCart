package interactions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.text.DecimalFormat;

import static model.ProductModel.getTotalPriceProducts;
import static net.serenitybdd.core.Serenity.getDriver;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static ui.CartUI.*;

public class FinalizePurchaseInteraction implements Interaction {


    @Override
    public <T extends Actor> void performAs(T actor) {

        try {
            Actions actions = new Actions(getDriver());

            //float total_products = Serenity.sessionVariableCalled("total_products");
            String total_products = getTotalPriceProducts();
            WebElementFacade lbl_total_price = BrowseTheWeb.as(actor).find(By.xpath("//div[@class='summary_subtotal_label']"));
            actions.moveToElement(lbl_total_price).perform();

            String total_purchase = SUBTOTAL_PRICE_PRODUCTS.resolveFor(actor).getText().replace("Item total: $", "");
            String tax_products = TAX_PRICE_PRODUCTS.resolveFor(actor).getText().replace("Tax: $", "");

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            float tax = (Float.parseFloat(total_purchase) * 8) / 100;
            System.out.println("Con ceil: " + decimalFormat.format(tax) + " tax: " + decimalFormat.format(Float.parseFloat(tax_products)));

            float total = (Float.parseFloat(total_purchase) + Float.parseFloat(tax_products));

            String total_pag = TOTAL_PRICE_PRODUCTS.resolveFor(actor).getText().replace("Total: $", "");

            Assert.assertEquals("Error el precio total de los productos seleccionados no coincide con el total del carrito de compras ", total_products, total_purchase);
            Assert.assertEquals("Error al calcular el impuesto de la compra ", decimalFormat.format(tax), decimalFormat.format(Float.parseFloat(tax_products)));
            Assert.assertEquals("Error con el precio total ", decimalFormat.format(total), decimalFormat.format(Float.parseFloat(total_pag)));

            //Finalizar la compra
            actor.attemptsTo(
                    WaitUntil.the(BTN_FINISH, isVisible()),
                    Click.on(BTN_FINISH)
            );

            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static FinalizePurchaseInteraction FinalizePurchase() {
        return instrumented(FinalizePurchaseInteraction.class);
    }

}
