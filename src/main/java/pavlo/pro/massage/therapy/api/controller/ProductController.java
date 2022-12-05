package pavlo.pro.massage.therapy.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

    @GetMapping("/")
    public String index() {
        return "Product controller works!";
    }

}
