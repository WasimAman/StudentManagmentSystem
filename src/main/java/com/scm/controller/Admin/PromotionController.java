package com.scm.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.service.admin.PromotionService;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @PutMapping("{year}")
    public ResponseEntity<String> promoteStudents(@PathVariable int year){
        String msg = promotionService.promoteStudents(year);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
