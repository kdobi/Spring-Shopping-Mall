package Domain.Spring_Shopping_Mall.controller;

import Domain.Spring_Shopping_Mall.domain.Product;
import Domain.Spring_Shopping_Mall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/productEnter")
    public String productEnter() {
        return "function/productEnter";
    }

    @PostMapping("/productEnter")
    public String productEntered(ProductForm form,
                                 @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        Product product = new Product();

        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setStockQuantity(form.getStockQuantity());
        product.setDescription(form.getDescription());

        if (!imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();

            File uploadDir = new File(System.getProperty("user.dir"), "image/usersImage");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File saveFile = new File(uploadDir, fileName);
            imageFile.transferTo(saveFile);

            product.setImagePath("/image/usersImage/" + fileName);
        }

        productService.add(product);

        return "redirect:/home";
    }
}