package ee.kontrolltoo.backend.controller;

import ee.kontrolltoo.backend.entity.Product;
import ee.kontrolltoo.backend.repository.ProductRepository;
import ee.kontrolltoo.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("products")
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {
        String[] sortParts = sort.split(",");
        Sort.Direction direction = Sort.Direction.fromString(sortParts[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParts[0]));
        return productRepository.findAll(pageable);
    }

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("Cannot add product with id");
        }
        productService.validate(product);
        return productRepository.save(product);
    }

    @PutMapping("products")
    public Product editProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("Cannot add product without id");
        }
        productService.validate(product);
        return productRepository.save(product);
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}