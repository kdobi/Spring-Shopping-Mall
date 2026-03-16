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

    public Product add(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
