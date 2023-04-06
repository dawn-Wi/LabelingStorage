package com.gausslab.labelingstorage.controller;

import com.gausslab.labelingstorage.domain.Item;
import com.gausslab.labelingstorage.service.ItemService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {
    private final ItemService itemService;

    @GetMapping("items")
    public ResponseEntity<List<Item>> getItemList(){
        try{
            return ResponseEntity.ok(itemService.getItemList());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("item")
    public ResponseEntity<Item> submitItem(@RequestBody Item toAdd){
        try{
            return ResponseEntity.ok(itemService.submitItem(toAdd));
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("item/delete")
    public ResponseEntity<Item> deleteItem(@RequestBody Item deleteItem){
        try{
            return ResponseEntity.ok(itemService.deleteItem(deleteItem));
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("item/edit")
    public ResponseEntity<Item> editItem(@RequestBody Item editItem){
        try{
            return ResponseEntity.ok(itemService.editItem(editItem));
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
