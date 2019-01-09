package com.x_s.s2shop.controller;

import com.x_s.s2shop.domain.Category;
import com.x_s.s2shop.domain.OrderItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class OrderItemController extends BaseController<OrderItem>{

}
