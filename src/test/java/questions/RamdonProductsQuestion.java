package questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static ui.CartUI.PRODUCT_LIST;

public class RamdonProductsQuestion implements Question<List<String>> {

    private static int quantity_products;

    public RamdonProductsQuestion(int quantity_products) {
        RamdonProductsQuestion.quantity_products = quantity_products;
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        try {

            Thread.sleep(2000);
            List<String> list_select_product = new ArrayList<String>();
            List<WebElementFacade> product_list = PRODUCT_LIST.resolveAllFor(actor);

            if (quantity_products == 0)
                Assert.fail("la cantidad de productos 0 no es permitida.");

            if (quantity_products <= product_list.size()) {
                //se almacena los numeros ids perteneciente a los item (en el ejemplo solo hay dos productos a seleccionar)
                while (true) {
                    Random random = new Random();
                    int num_random = random.nextInt(1 + product_list.size() - 1);
                    String item = product_list.get(num_random).getText();

                    if (!list_select_product.contains(item)) {
                        list_select_product.add(item);
                        if (list_select_product.size() == quantity_products)
                            break;
                    }
                }
                return list_select_product;
            } else {
                Assert.fail("Ingrese una cantidad de productos menor o igual a la contenida en la pagina");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail("Error al realizar el proceso de ramdon de producto");
        }

        return null;
    }

    public static RamdonProductsQuestion ProductsName(int quantity_products) {
        return new RamdonProductsQuestion(quantity_products);
    }
}
