package com.aequilibrium.test.transformers.controllers;

import java.util.List;

import javax.xml.transform.Transformer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/transformer-service")
public class TransformersController {

    @PostMapping(path = "create")
    public Transformer createTransformers(){
        return null;
    }

    @PutMapping(path = "update")
    public Transformer updateTransformers(){
        return null;
    }

    @DeleteMapping(path = "delete")
    public boolean deleteTransformers(){
        return null;
    }

    @GetMapping(path="get-list")
    public List<Transformer> getTransformerList(){
        return null;
    }


    
}
