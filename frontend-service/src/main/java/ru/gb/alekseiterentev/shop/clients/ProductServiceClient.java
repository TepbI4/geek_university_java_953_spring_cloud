package ru.gb.alekseiterentev.shop.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;

import java.util.List;

@FeignClient("product-service")
public interface ProductServiceClient {

    @GetMapping("api/v1/products")
    public List<ProductDto> findAll();

    @PutMapping("api/v1/products")
    public ProductDto updateProduct(@RequestBody ProductDto productDto);

    @DeleteMapping("api/v1/products/{id}")
    public void deleteProduct(@PathVariable Long id);
}
