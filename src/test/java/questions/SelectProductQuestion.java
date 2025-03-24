package questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import tasks.ScrollElementJSTask;

import java.util.ArrayList;
import java.util.List;

import static model.ProductModel.setTotalPriceProducts;
import static model.ProductModel.setTotalSelectedProducts;
import static net.serenitybdd.core.Serenity.getDriver;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static ui.CartUI.DETAIL_PRODUCT;
import static ui.CartUI.ICON_CART_;

public class SelectProductQuestion implements Question<List<String>> {

    private static List<String> product_name;

    public SelectProductQuestion(List<String> product_name) {
        SelectProductQuestion.product_name = product_name;
    }

    @Override
    public List<String> answeredBy(Actor actor) {

        try {
            List<String> detailProduct = new ArrayList<String>();
            int total_products_select = 0;
            float total_price_products = 0;

            for (String product_name : product_name) {
                System.out.print("\n" + product_name);

                String product_detail = DETAIL_PRODUCT.of(product_name, "inventory_item_desc").resolveFor(actor).getText();
                String product_price = DETAIL_PRODUCT.of(product_name, "inventory_item_price").resolveFor(actor).getText();

                System.out.println(" Name: " + product_name + " Desc: " + product_detail + "Detail: " + product_price);
                detailProduct.add(product_name);
                detailProduct.add(product_detail);
                detailProduct.add(product_price);

                Thread.sleep(4);
                ScrollElementJSTask.ScrollElementJS(String.format(ICON_CART_, product_name)).performAs(actor);

                total_price_products += Float.parseFloat(product_price.replace("$", ""));
                total_products_select++;
                //contador++;
                Thread.sleep(3000);

            }

            System.out.println("Total products " + total_products_select);
            System.out.println("Detalle productos " + detailProduct);
            setTotalPriceProducts(String.valueOf(total_price_products));
            setTotalSelectedProducts(String.valueOf(total_products_select));
            Thread.sleep(1000);

            return detailProduct;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail("Error al seleccionar product");
        }

        return null;
    }

    public static SelectProductQuestion SelectProduct(List<String> product_name) {
        return new SelectProductQuestion(product_name);
    }
}
