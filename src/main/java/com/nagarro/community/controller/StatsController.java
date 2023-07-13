package com.nagarro.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.community.dto.StatsDto;
import com.nagarro.community.service.StatsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    StatsService service;
    

    @GetMapping("")
    public ResponseEntity<StatsDto> getStats(){
        try{
            StatsDto stats = service.getStats();
            return new ResponseEntity<StatsDto>(stats,null,  HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<StatsDto>(null,null,  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 

}
