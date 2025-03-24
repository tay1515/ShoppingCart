package ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartUI {
    public static Target PRODUCT_LIST = Target.the("Lista productos").locatedBy("//div[@class='inventory_item_name ']");

    public static Target DETAIL_PRODUCT = Target.the("Detalle producto").locatedBy("//div[@class='inventory_item_name ' and text()='{0}']/ancestor::div[@class='inventory_item']//div[@class='{1}']");

    public static Target BUTTON_ADD_PRODUCT = Target.the("Boton añadir producto").locatedBy("(//button[@class='btn btn_primary btn_small btn_inventory '])[{0}]");

    public static final String ICON_CART = "shopping_cart_link";

    public static final String ICON_CART_ = "//div[@class='inventory_item_name ' and text()='%s']/ancestor::div[@class='inventory_item']//button";

    public static Target ITEMS_CART = Target.the("Cantidad de items carrito de compras").located(By.xpath("//div[@id='shopping_cart_container']//span"));

    public static String ITEMS_CART_ = "//div[@id='shopping_cart_container']";

    public static Target PRODUCT_NAME_CART = Target.the("Nombre producto carrito de compra").locatedBy("//div[@class='inventory_item_name' and contains(.,'{0}')]/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_name']");

    public static Target PRODUCT_DESCRIP_CART = Target.the("Detalle producto carrito de compra").locatedBy("//div[@class='inventory_item_name' and text()='{0}']/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_desc']");

    public static Target SUBTOTAL_PRICE_PRODUCTS = Target.the("Total de los productos").locatedBy("//div[@class='summary_subtotal_label']");

    public static Target TAX_PRICE_PRODUCTS = Target.the("Total de los productos").locatedBy("//div[@class='summary_tax_label']");

    public static Target TOTAL_PRICE_PRODUCTS = Target.the("Total de los productos").locatedBy("//div[@class='summary_total_label']");

    public static Target BTN_FINISH = Target.the("Boton de finalizar").locatedBy("//button[@id='finish']");

    public static Target MSG_SUCCESS_SHOPPING = Target.the("Mensaje de compra exitosa").locatedBy(" //h2[@class='complete-header']");

    public static Target PRODUCT_PRICE_CART = Target.the("Precio producto carrito de compra").locatedBy("//div[@class='inventory_item_name' and text()='{0}']/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']");

    public static Target BTN_CHECKOUT = Target.the("Boton Checkout").locatedBy("//button[@id='checkout']");

    public static String BTN_CHECKOUT_ = "//button[@id='checkout']";

    public static Target INPUT_FIRT_NAME = Target.the("Nombre").locatedBy("//input[@id='first-name']");

    public static Target INPUT_LAST_NAME = Target.the("Apellido").locatedBy("//input[@id='last-name']");

    public static Target INPUT_POSTAL_CODE = Target.the("Codigo postal").locatedBy("//input[@id='postal-code']");

    public static Target BTN_CONTINUE = Target.the("Boton continuar").locatedBy("//input[@id='continue']");

}
