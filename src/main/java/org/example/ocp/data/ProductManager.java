package org.example.ocp.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductManager {
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    private static final Map<String, ResourceFormatter> FORMATTERS = Map.of(
            "en-GB", new ResourceFormatter(Locale.UK),
            "en-US", new ResourceFormatter(Locale.US),
            "ru-RU", new ResourceFormatter(new Locale("ru", "RU"))
    );

    public static Set<String> getSupportedLocales() {
        return FORMATTERS.keySet();
    }

    private final ResourceBundle config = ResourceBundle.getBundle("config");
    private final MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private final MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));

    private ResourceFormatter formatter;

    private final Map<Product, List<Review>> products = new HashMap<>();

    public ProductManager(String localizationTag) {
        changeLocalization(localizationTag);
    }

    public ProductManager(Locale locale) {
        formatter = new ResourceFormatter(locale);
    }

    public void printProductReport(int productId) {
        try {
            Product product = findProductById(productId);
            printProductReport(product);
        } catch (ProductManagerException e) {
            logger.log(Level.WARNING, "Error printing product report", e);
        }
    }

    public void printProductReport(Product product) {
        System.out.println(formatter.formatProduct(product));
        List<Review> reviews = products.get(product);
        if (reviews.isEmpty()) {
            System.out.println(formatter.getString("no.review"));
        } else {
            System.out.println(reviews.stream()
                    .sorted()
                    .map(formatter::formatReview)
                    .collect(Collectors.joining("\n")));
        }
    }

    public void changeLocalization(String localizationTag) {
        formatter = FORMATTERS.getOrDefault(localizationTag, FORMATTERS.get("ru-RU"));
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        return saveProduct(new Food(id, name, price, rating, bestBefore));
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        return saveProduct(new Drink(id, name, price, rating));
    }

    private Product saveProduct(Product product) {
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product reviewProduct(int productId, Rating rating, String comments) {
        try {
            Product product = findProductById(productId);
            return reviewProduct(product, rating, comments);
        } catch (ProductManagerException e) {
            logger.log(Level.WARNING, "Error reviewing product", e);
            return null;
        }
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {
        List<Review> reviews = products.get(product);
        products.remove(product);
        reviews.add(new Review(rating, comments));
        double averageNumStars = reviews.stream()
                .mapToDouble(r -> r.getRating().getNumStars())
                .average()
                .orElse(0.0);
        Product updatedProduct = product.applyRating(Rating.ofNumStars((int) Math.round(averageNumStars)));
        products.put(updatedProduct, reviews);
        return updatedProduct;
    }

    public Product findProductById(int id) throws ProductManagerException {
        return products.keySet().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductManagerException("Cannot find product by ID " + id));
    }

    public void printProducts(Predicate<Product> filter, Comparator<Product> comparator) {
        products.keySet().stream()
                .filter(filter)
                .sorted(comparator)
                .forEach(this::printProductReport);
    }

    public void printProducts(Comparator<Product> comparator) {
        products.keySet().stream()
                .sorted(comparator)
                .forEach(this::printProductReport);
    }

    public void parseReview(String text) {
        try {
            Object[] values = reviewFormat.parse(text);
            reviewProduct(Integer.parseInt((String) values[0]),
                    Rateable.convert(Integer.parseInt((String) values[1])),
                    (String) values[2]);
        } catch (ParseException | NumberFormatException e) {
            logger.log(Level.WARNING, "Error parsing review: " + text, e);
        }
    }

    public Product parseProduct(String text) {
        Product product = null;
        try {
            Object[] values = productFormat.parse(text);
            String type = (String) values[0];
            int id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = new BigDecimal((String) values[3]);
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));
            String dateStr = (String) values[5];
            if ("D".equals(type) || dateStr.isBlank() || "-".equals(dateStr)) {
                product = createProduct(id, name, price, rating);
            } else {
                product = createProduct(id, name, price, rating, LocalDate.parse(dateStr));
            }
        } catch (ParseException | NumberFormatException | DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing product: " + text, e);
        }
        return product;
    }

    private static class ResourceFormatter {
        private final ResourceBundle resourceBundle;
        private final DateTimeFormatter dateFormat;
        private final NumberFormat moneyFormat;
        private final String reviewPattern;
        private final String productPatterns;

        ResourceFormatter(Locale locale) {
            resourceBundle = ResourceBundle.getBundle("messages", locale);
            dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
            productPatterns = resourceBundle.getString("product");
            reviewPattern = resourceBundle.getString("review");
        }

        String formatProduct(Product product) {
            String bestBefore = product instanceof Food
                    ? dateFormat.format(((Food) product).getBestBefore())
                    : "-";

            return MessageFormat.format(productPatterns,
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    bestBefore);
        }

        String formatReview(Review review) {
            return MessageFormat.format(reviewPattern, review.getRating().getStars(), review.getComments());
        }

        String formatReviews(List<Review> reviews) {
            StringBuilder txt = new StringBuilder();
            for (Review review : reviews) {
                txt.append(formatReview(review));
                txt.append('\n');
            }
            if (reviews.isEmpty()) {
                txt.append(resourceBundle.getString("no.review"));
                txt.append('\n');
            }
            return txt.toString();
        }

        String getString(String key) {
            return resourceBundle.getString(key);
        }
    }
}
