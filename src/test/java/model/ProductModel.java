package model;

public class ProductModel {


    private static String total_selected_products;
    private static String total_price_products;


    public static String getTotalSelectedProducts() {
        return total_selected_products;
    }

    public static void setTotalSelectedProducts(String totalSelectedProducts) {
        total_selected_products = totalSelectedProducts;
    }
    public static String getTotalPriceProducts() {
        return total_price_products;
    }

    public static void setTotalPriceProducts(String totalPriceProducts) {
        total_price_products = totalPriceProducts;
    }
}
