package ru.gb.alekseiterentev.shop.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.exceptions.ProductNotFoundException;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;
import ru.gb.alekseiterentev.shop.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    public static final Function<Product, ProductDto> functionEntityToDto = se -> {
        ProductDto s = new ProductDto();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setPrice(se.getPrice());
        return s;
    };

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(functionEntityToDto).collect(toList());
    }

    @GetMapping("/filter")
    public List<ProductDto> filterByPrice(@RequestParam(required = false) Integer minPrice,
                                       @RequestParam(required = false) Integer maxPrice) {
        return productService.filterByPrice(minPrice, maxPrice).stream().map(functionEntityToDto).collect(toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findById(id).map(functionEntityToDto)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return Optional.of(productService.save(product)).map(functionEntityToDto).orElse(null);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return Optional.of(productService.save(product)).map(functionEntityToDto).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
