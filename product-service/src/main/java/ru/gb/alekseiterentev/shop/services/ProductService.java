package ru.gb.alekseiterentev.shop.services;

import org.springframework.data.domain.Page;
import ru.gb.alekseiterentev.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Product> findAll(int pageIndex, int pageSize);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Long id);
    List<Product> filterByPrice(Integer minPrice, Integer maxPrice);
}
