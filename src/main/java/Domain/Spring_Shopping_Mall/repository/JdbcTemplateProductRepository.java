package Domain.Spring_Shopping_Mall.repository;

import Domain.Spring_Shopping_Mall.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
            jdbcTemplate.update(
                "INSERT INTO products " +
                        "(name, price, stock_quantity, image_path, description) VALUES (?, ?, ?, ?, ?)",
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImagePath(),
                product.getDescription()
        );
        return product;
    }

}
