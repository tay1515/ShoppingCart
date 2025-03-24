package interactions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

import static net.serenitybdd.core.Serenity.getDriver;
import static net.serenitybdd.core.Serenity.webdriver;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static ui.CartUI.*;


public class SelectProductInteraction implements Interaction {

    private int products;

    public SelectProductInteraction(int products) {
        this.products = products;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        try {

            Thread.sleep(2000);
            int totalproducts = 0;
            //List<String> nameProduct = new ArrayList<String>();
            List<String> detailProduct = new ArrayList<String>();
            List<String> list_select_product = new ArrayList<String>();
            List<WebElementFacade> product_list = PRODUCT_LIST.resolveAllFor(actor);

            if (products == 0)
                Assert.fail("la cantidad de productos 0 no es permitida.");

            if (products <= product_list.size()) {
                //se almacena los numeros ids perteneciente a los item (en el ejemplo solo hay dos productos a seleccionar)
                while (true) {
                    Random random = new Random();
                    int num_random = random.nextInt(1 + product_list.size() - 1);
                    String item = product_list.get(num_random).getText();

                    if (!list_select_product.contains(item)) {
                        list_select_product.add(item);
                        if (list_select_product.size() == products)
                            break;
                    }
                }
            } else {
                Assert.fail("Ingrese una cantidad de productos menor o igual a la contenida en la pagina");
            }

            //int contador = 1;
            float total_products = 0;
            Thread.sleep(5000);

            for (String product_name : list_select_product) {
                System.out.print("\n" + product_name);

                String product_detail = DETAIL_PRODUCT.of(product_name, "inventory_item_desc").resolveFor(actor).getText();
                String product_price = DETAIL_PRODUCT.of(product_name, "inventory_item_price").resolveFor(actor).getText();

                System.out.println(" Name: " + product_name + " Desc: " + product_detail + "Detail: " + product_price);
                detailProduct.add(product_name);
                detailProduct.add(product_detail);
                detailProduct.add(product_price);

                //se guarda el nombre los productos agregados para buscar en el carrito de compra
                //nameProduct.add(product_name);

                Thread.sleep(4);
                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                WebElementFacade scroll = BrowseTheWeb.as(actor).find(By.xpath("//div[@class='inventory_item_name ' and text()='" + product_name + "']/ancestor::div[@class='inventory_item']//button"));
                js.executeScript("arguments[0].scrollIntoView();", scroll);
                scroll.click();
                //listProducts.get(productId).waitUntilClickable();

                total_products += Float.parseFloat(product_price.replace("$", ""));
                totalproducts++;
                //contador++;
                Thread.sleep(3000);

            }

            System.out.println("Total products " + totalproducts);
            System.out.println("Detalle productos " + detailProduct);
            //System.out.println("Name productos " + nameProduct);

            //se ubica en la posicion del icono del carrito de compras
            WebElementFacade iconCart = BrowseTheWeb.as(actor).find(By.xpath("//div[@id='shopping_cart_container']"));
            ScrollInteraction.to(iconCart);

            Thread.sleep(3000);

            Assert.assertEquals("La cantidad de item seleccionados no coincide con la cantidad de item del carrito de compras. ", String.valueOf(totalproducts), ITEMS_CART.resolveFor(actor).getText());

            Thread.sleep(1000);

            actor.attemptsTo(
                    WaitUntil.the(ITEMS_CART, isVisible()),
                    Click.on(ITEMS_CART)
            );

            // obtener cada item del producto con los nombres de obtenidos de la seleccion
            List<String> detailProductCart = new ArrayList<String>();

            for (String name : list_select_product) {
                Thread.sleep(2000);
                System.out.println("Name P " + name);
                detailProductCart.add(name);
                detailProductCart.add(PRODUCT_DESCRIP_CART.of(name).resolveFor(actor).getText());
                detailProductCart.add(PRODUCT_PRICE_CART.of(name).resolveFor(actor).getText());
            }

            System.out.println("List items cart: " + detailProductCart);

            Thread.sleep(3000);
            assertThat("error no coincide el detalle de los productos agregados con los del carrito de compras ", detailProduct, containsInAnyOrder(detailProductCart.toArray()));

            //continuar con la compra
            actor.attemptsTo(
                    WaitUntil.the(BTN_CHECKOUT, isVisible()),
                    Click.on(BTN_CHECKOUT)
            );


            Serenity.setSessionVariable("total_products").to(total_products);
            System.out.println("Total price products " + total_products);

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }


    public static SelectProductInteraction SelectProduct(int products) {
        return instrumented(SelectProductInteraction.class, products);
    }
}
