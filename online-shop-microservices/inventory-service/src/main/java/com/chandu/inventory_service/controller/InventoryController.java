package com.chandu.inventory_service.controller;


import com.chandu.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> isInStock(@PathVariable("sku-code") String skuCode) {
        return ResponseEntity.ok(inventoryService.isInStock(skuCode));
    }
}
