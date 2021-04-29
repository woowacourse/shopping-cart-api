package shoppingcart.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.dao.ProductDao;
import shoppingcart.dto.Product;
import shoppingcart.dto.ProductRequestDto;

@Service
@Transactional
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    // 전체 상품 조회
    public List<Product> findProducts() {
        return productDao.findProducts();
    }

    // 상품 추가
    public Long addProduct(ProductRequestDto productRequestDto) {
        return productDao.save(productRequestDto);
    }

    // 단일 조회
    public Product findProductById(long productId) {
        return productDao.findProductById(productId);
    }

    // 단일 삭제
    public void deleteProductById(long productId) {
        productDao.delete(productId);
    }
}
