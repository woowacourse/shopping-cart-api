package shoppingcart.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.dao.ProductDao;
import shoppingcart.dto.Product;
import shoppingcart.dto.ProductDto;

@Service
@Transactional
public class ProductService {
    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findProducts() {
        return productDao.findProducts();
    }

    public Long addProduct(final ProductDto productDto) {
        return productDao.save(productDto);
    }

    public Product findProductById(final Long productId) {
        return productDao.findProductById(productId);
    }

    public void deleteProductById(final Long productId) {
        productDao.delete(productId);
    }
}
