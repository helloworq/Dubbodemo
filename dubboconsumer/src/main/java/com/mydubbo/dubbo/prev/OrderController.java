package com.mydubbo.dubbo.prev;

import com.mydubbo.dubbo.Service.OrderService;
import com.mydubbo.dubbo.bean.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@ResponseBody
	@RequestMapping("/initOrder")
	public List<UserAddress> initOrder() {
		System.out.println(orderService.initOrder("1"));
		return orderService.initOrder("1");
	}

}
