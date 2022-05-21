package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.service.CakeManager;
import com.waracle.cakemgr.entity.CakeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class CakeController
{
    @Autowired
    CakeManager cakeManager;

    @PostConstruct
    private void init(){
        cakeManager.readAllCakes();
    }

    @GetMapping(value = {"/","/cakes"})
    public StringBuffer getCakes() {
        return cakeManager.getCakesList();
    }

    @GetMapping(value = "/cakes/{title}")
    public StringBuffer findCakeByName(@PathVariable String title) {
        CakeEntity cakeEntity = cakeManager.findCakeByTitle(title);
        return cakeManager.getSearchCake(cakeEntity);
    }

    @PostMapping(value = "/cakes")
    public ResponseEntity<StringBuffer> addCakes(@RequestBody CakeEntity cake) {
        cakeManager.addNewCake(cake);
        return ResponseEntity.ok(cakeManager.getCakesList());
    }

}
