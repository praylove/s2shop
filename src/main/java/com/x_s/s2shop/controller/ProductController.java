package com.x_s.s2shop.controller;

import com.x_s.s2shop.domain.Category;
import com.x_s.s2shop.domain.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController<Product>{

}
