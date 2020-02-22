package com.buyify.order;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.buyify.product.Product;
import com.buyify.product.ProductRepository;
import com.buyify.user.User;
import com.buyify.user.UserRepository;

@Controller
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/new_order")
    public String showProductList(Model model) {
        List<Product> product_list = productRepository.findAll();
        model.addAttribute("products", product_list);
        return "new_order";
    }
	
	@PostMapping("/realizado")
	public String order(Model model, @RequestParam(name="product_list") String[] productNames) {
		List<User> users = userRepository.findAll();
		User user = users.get(0);//Prueba
	    LocalDate localDate = java.time.LocalDate.now();
	    Date date = java.sql.Date.valueOf(localDate);
		List<Product> order_list = new ArrayList<>();
		Product p = null;
		Order order = null;
		for(int i = 0; i < productNames.length;i++) {
			p = productRepository.findByName(productNames[i]);
			order_list.add(p);
		}
		
		order = new Order(user,date,order_list);
		
		orderRepository.save(order);
        return "redirect:/productos";
    }
}