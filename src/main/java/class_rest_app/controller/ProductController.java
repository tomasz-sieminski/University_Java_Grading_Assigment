package class_rest_app.controller;

import class_rest_app.entity.Product;
import class_rest_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public String all(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("products", service.getProducts());
        if(redirectAttributes.containsAttribute("searchProduct")) {
            model.addAttribute("searchResults",redirectAttributes.getFlashAttributes());
        }
        return "products/product";
    }

    @PostMapping("/add")
    public RedirectView addProduct(Product product) {
        service.saveProduct(product);
        return new RedirectView("/product");
    }

    @PostMapping("/add/many")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/find/id")
    public RedirectView findById(Model model, @RequestParam int id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("searchProduct",service.getProductById(id));
        return new RedirectView("/product");

    }

    @GetMapping("/find/name")
    public String findByName(Model model, @RequestParam String name) {

        model.addAttribute("searchProduct",service.getProductByName(name));
        return "products/product";
    }

    @PostMapping("/update")
    public RedirectView update( Product product) {
        service.updateProduct(product);
        return new RedirectView("/product");
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable int id) {

        service.deleteProduct(id);
        return new RedirectView("/product");
    }
}
