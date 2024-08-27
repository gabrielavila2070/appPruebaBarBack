package com.example.apiRestVentas.Controllers;

import com.example.apiRestVentas.Models.Product;
import com.example.apiRestVentas.Services.ProductService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(value= "http://localhost:3000")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping
    public List<Product> getProducts(){
        return this.productService.getProduct();
    }
    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return this.productService.saveProduct(product);
    }
    @GetMapping(path = "/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return this.productService.getByID(id);
    }

    @Transactional
    @PutMapping (path = "/{id}")
    public Product updateProductById(@RequestBody Product request,@PathVariable  Long id){
        Product product = this.productService.updateById(request, id);
        log.info("Product with id {} successful updated with following data: {}", id, product);
        return product;
    }
    @Transactional
    @PutMapping(path = "/increasepricebyid/{id}")
    public void increasePriceById(@PathVariable Long id,@RequestBody Long newPrice){
        productService.increasePriceById(id,newPrice);
    }
    @Transactional
    @PutMapping(path = "/increaseprices/{growth}")
    public void increaseAllPrices(@PathVariable double growth){
        productService.increaseAllPrices(growth);
    }
    @Transactional
    @PutMapping(path = "/increasepricesbyids/{growth}")
    public void increasePricesByIds(@PathVariable double growth, @RequestBody List<Long> ids ){
        productService.increasePriceForId(ids,growth);  
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProductById(@PathVariable("id") Long id){
        this.productService.deleteProduct(id);
    }
}
