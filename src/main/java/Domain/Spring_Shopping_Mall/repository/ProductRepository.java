package Domain.Spring_Shopping_Mall.repository;

import Domain.Spring_Shopping_Mall.domain.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository {

    Product save(Product product); // 내 상품 등록
//    Optional<List<Product>> findAll();  // 상품 조회





}
