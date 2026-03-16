package Domain.Spring_Shopping_Mall.controller;

import Domain.Spring_Shopping_Mall.domain.Product;
import Domain.Spring_Shopping_Mall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String productEntered(ProductForm form, Model model,
                                 @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        if (form.getName() == null || form.getName().trim().isEmpty()) {
            model.addAttribute("errorMessage", "에러 : 상품명을 입력하세요.");
            return "function/productEnter";
        }

        // 가격 검사
        if (form.getPrice() == null) {
            model.addAttribute("errorMessage", "에러 : 가격을 입력하세요.");
            return "function/productEnter";
        }

        // 재고 검사
        if (form.getStockQuantity() == null) {
            model.addAttribute("errorMessage", "에러 : 재고를 입력하세요.");
            return "function/productEnter";
        }

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

    @GetMapping("/products")
    public String productList(Model model) {

        model.addAttribute("products",
                productService.findAllProducts());

        return "products";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {

        Product product = productService.findById(id);

        model.addAttribute("product", product);

        return "product-detail";
    }

}