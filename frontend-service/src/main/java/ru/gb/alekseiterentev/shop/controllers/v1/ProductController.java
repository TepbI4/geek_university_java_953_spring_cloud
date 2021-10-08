package ru.gb.alekseiterentev.shop.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.clients.ProductServiceClient;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceClient productServiceClient;

    @GetMapping
    List<ProductDto> findAll() {
        return productServiceClient.findAll();
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productServiceClient.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productServiceClient.deleteProduct(id);
    }
}
