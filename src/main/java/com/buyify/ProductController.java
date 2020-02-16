package com.buyify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/productos/nuevoProducto")
    public String createProduct(Model model, @RequestParam String name, @RequestParam int price,
                                @RequestParam int stock, @RequestParam String category,
                                @RequestParam String url, @RequestParam String description) {

        Product newProduct = new Product(name, category, price, stock, description, url);
        productRepository.save(newProduct);

        model.addAttribute("id", newProduct.getId());

        return "product_added";
    }

}