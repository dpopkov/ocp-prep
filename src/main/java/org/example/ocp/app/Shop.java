package org.example.ocp.app;

import org.example.ocp.data.Drink;
import org.example.ocp.data.Product;
import org.example.ocp.data.ProductManager;
import org.example.ocp.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Predicate;

public class Shop {

    public static void main(String[] args) {
        ProductManager pm = new ProductManager(Locale.US);
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        Product p2 = pm.createProduct(102, "Coffee", BigDecimal.valueOf(2.99), Rating.FOUR_STARS);
        final LocalDate tomorrow = LocalDate.now().plusDays(1);
        Product p3 = pm.createProduct(201, "Burger", BigDecimal.valueOf(3.99), Rating.THREE_STARS, tomorrow);
        /*Product p4 = pm.createProduct("Cake", BigDecimal.valueOf(4.99), Rating.FIVE_STARS, LocalDate.now());
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        Product p5 = pm.createProduct("Chocolate", BigDecimal.valueOf(3.5), Rating.FOUR_STARS, tomorrow);
        Product p6 = pm.createProduct("Chocolate", BigDecimal.valueOf(4.5), Rating.THREE_STARS);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p5.equals(p6));
        System.out.println(p2.applyRating(Rating.TWO_STARS));
        System.out.println(p4.applyRating(Rating.TWO_STARS));*/
        pm.parseReview("102,1,Where is the milk?");
        pm.parseReview("102,2,Where is the sugar?");
        pm.parseReview("102,5,It's perfect with 10 spoons of sugar");
        pm.parseReview("102,1,Did'n like it");
//        pm.printProductReport(p2);

        pm.changeLocalization("ru-RU");
        p3 = pm.reviewProduct(p3, Rating.THREE_STARS, "so so Burger (");
        p3 = pm.reviewProduct(p3, Rating.FIVE_STARS, "The best Burger!!!");
        p3 = pm.reviewProduct(p3, Rating.FOUR_STARS, "Nice Burger!");
//        pm.printProductReport(201);
//        pm.printProductReport(42);

        Predicate<Product> drinksOnly = p -> p instanceof Drink;
        Comparator<Product> sortByRating = Comparator.comparing(Product::getRating);
//        pm.printProducts(drinksOnly, sortByRating);
//        pm.printProducts(sortByRating);

        /*pm.printProductReport(101);
        pm.parseReview("101,4,Very nice hot cup of tea");
        pm.printProductReport(101);*/

        Product p = pm.parseProduct("D,103,Tea,1.99,5,");
        pm.printProductReport(p);

    }
}
