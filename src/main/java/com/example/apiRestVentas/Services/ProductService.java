package com.example.apiRestVentas.Services;

import com.example.apiRestVentas.Models.Product;
import com.example.apiRestVentas.Repositories.ProductRepository;

import com.example.apiRestVentas.Utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MathUtils mathUtils;

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> getByID(Long id){
        return productRepository.findById(id);
    }

    public Product updateById(Product request, Long id){
        // TODO hacer com Optional la comprobacion que exista
        Product product = productRepository.findById(id).get();
        product.setProductPrice(request.getProductPrice());
        product.setProductName(request.getProductName());
        product.setProductStock(request.getProductStock());
        return product;
    }
    public void increaseAllPrices(double growth){
        List<Product> products = productRepository.findAll();
        for (Product product: products) {
            product.setProductPrice((long)(mathUtils.calculatePercentage(product.getProductPrice(),growth)));
            log.info("Product with id {} was successfully updated its price", product.getProductId());
        }

    }
    public void increasePriceForId(List<Long> productIds,double growth){
            List<Product> products = productRepository.findAll();
        for (Product product:products) {
            if (productIds.contains(product.getProductId())){
                product.setProductPrice((long)(mathUtils.calculatePercentage(product.getProductPrice(),growth)));
            }
        }
    }
    public void increasePriceById(Long id,Long newPrice){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            product.get().setProductPrice(newPrice);
            log.info("Product with id {} was increased price", id);
        }else{
            log.warn("Product with id {} doesn't exists", id);
        }
    }
    public void deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
            log.info("Product with id {} was deleted", id);
        }else{
            log.warn("Product with id {} doesn't exists", id);
        }
    }
}
