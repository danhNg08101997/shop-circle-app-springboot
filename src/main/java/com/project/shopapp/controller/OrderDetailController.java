package com.project.shopapp.controller;

import com.project.shopapp.dto.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order_details")
public class OrderDetailController {
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok().body("Post orderDetailDTO");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailWithId(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok().body("getOrderDetailWithId with id: " + id);
    }

    //Lấy ra danh sách các order_details của 1 order nào đó
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetailWithOrderId(@Valid @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body("getOrderDetailWithOrderId with orderId: " + orderId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetailWithId(
            @Valid @RequestBody OrderDetailDTO newOrderDetailDTO,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok().body("newOrderDetailDTO " + newOrderDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetailWithId(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.noContent().build();
    }
}
