package Domain.Spring_Shopping_Mall.service;

import Domain.Spring_Shopping_Mall.domain.Product;
import Domain.Spring_Shopping_Mall.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void add(Product product){
        productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
            return productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));

    }
}
