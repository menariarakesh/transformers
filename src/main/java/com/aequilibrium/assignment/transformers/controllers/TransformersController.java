package com.aequilibrium.assignment.transformers.controllers;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aequilibrium.assignment.transformers.entity.Transformer;
import com.aequilibrium.assignment.transformers.service.TransformersService;

@RestController
@RequestMapping( "/client-api/v1/transformer")
public class TransformersController {

	@Resource
	private TransformersService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Transformer createTransformers(@RequestBody Transformer transformer){
        return this.service.createUpdateTransformers(transformer);
    }

    @PutMapping
    public Transformer updateTransformers(@RequestBody Transformer transformer){
        return this.service.createUpdateTransformers(transformer);
    }

    @DeleteMapping
    public Transformer deleteTransformers(@RequestBody Transformer transformer){
        return this.service.deleteTransformers(transformer.getId());
    }

    @GetMapping
    public Set<Transformer> getTransformerList(){
        return (Set<Transformer>) this.service.getTransformerList();
    }


    
}
