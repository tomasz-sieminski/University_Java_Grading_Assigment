package class_rest_app.repository;

import class_rest_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findByName(String name);
}
