package com.buyify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class ShopController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/productos")
    public String shop(Model model) {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getName));

        model.addAttribute("products", products);

        return "shop";
    }

    @GetMapping("/productos/{id}")
    public String viewProduct(Model model, @PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);

        model.addAttribute("product", product.get());

        return "product_details";
    }

}