package Domain.Spring_Shopping_Mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class productController {

    @GetMapping("/productEnter")
    public String productEnter(){
        return "function/productEnter";
    }
}
