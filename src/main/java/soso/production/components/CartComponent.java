package soso.production.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import soso.production.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Данный компонент создается в момент инициализации сессии
 * и живет до того момента, пока юзер не разлогинется.
 * Предназначен для хранения информации о количестве товаров
 * в корзине для текущего пользователя и их общей стоимости.
 *
 * Передается как аттрибут в форму shop.jsp
 * и при добавлении или удалении товаров из корзины они
 * в первую очередь меняются здесь.
 */
@Component
@Scope("session")
public class CartComponent {
    private List<Product> productList;
    private BigDecimal totalPrice;

    public CartComponent() {
        productList = new ArrayList<>();
        totalPrice = new BigDecimal(0);
    }

    public void addProduct(Product product) {
        productList.add(product);
        this.totalPrice = totalPrice.add(product.getPrice());
    }

    public void removeProduct(Product product) {
        if (!productList.isEmpty()) {
            int x = _findProductIndex(product);
            if (x != -1) {
                productList.remove(x);
            }
            this.totalPrice = totalPrice.subtract(product.getPrice());
        }
    }

    private int _findProductIndex(Product product) {
        return IntStream.range(0, productList.size())
                .filter(i -> productList.get(i).getId().equals(product.getId()))
                .findFirst()
                .orElse(-1);
    }

    public void clear() {
        this.productList.clear();
        this.totalPrice = new BigDecimal(0);
    }

    // <editor-fold desc="getters & setters">
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    // </editor-fold>
}
