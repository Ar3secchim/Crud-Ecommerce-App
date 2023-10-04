package com.crud.controller;

import com.crud.controller.dto.OrderItem.OrderItemRequest;
import com.crud.controller.dto.OrderItem.OrderItemResponse;
import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Order;
import com.crud.service.CustomerService;
import com.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired
  OrderService orderService;
  @Autowired
  CustomerService customerService;

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
    OrderResponse orderResponse = orderService.create(orderRequest);
    return ResponseEntity
            .created(URI.create("/order/"+orderResponse.getId()))
            .body(orderResponse);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> listOrder(){
    return ResponseEntity.ok(orderService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> listOrderById(@PathVariable Integer id){
    return ResponseEntity.ok(orderService.findById(id));
  }

  @PostMapping("/{id}")
  public ResponseEntity<OrderItemResponse> addItemOrder(
          @PathVariable Integer id,
          @RequestBody OrderItemRequest orderItemRequest
  ){
    OrderItemResponse orderItemResponse = orderService.addItem(id, orderItemRequest);
    return ResponseEntity
            .created(URI.create("/order/"+orderItemResponse.getOrder()))
            .body(orderItemResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderItemResponse>removeItemOrder(
          @PathVariable Integer id,
          @RequestBody OrderItemRequest orderItemRequest
  ){
    orderService.removeItem(id, orderItemRequest.getProduct());
   return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<OrderResponse> updateOrder(@PathVariable Integer id){
    orderService.update(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Integer id){
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
