package shoppingcart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.dao.ProductDao;
import shoppingcart.dto.ProductDto;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<ProductDto> findProducts() {
        return productDao.findProducts();
    }

    public Long addProduct(final ProductDto productDto) {
        return productDao.save(productDto);
    }

    public ProductDto findProductById(final Long productId) {
        return productDao.findProductById(productId);
    }

    public void deleteProductById(final Long productId) {
        productDao.delete(productId);
    }
}
