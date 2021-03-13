package com.aequilibrium.assignment.transformers.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.aequilibrium.assignment.transformers.entity.Transformer;
import com.aequilibrium.assignment.transformers.utils.TransformersUtil;

@Repository
public class TransformersRepository {
    
	private final Map<String, Transformer> tranformerDataStore = new HashMap<>();
    
    public Transformer createUpdateTransformers(Transformer transformer) {
    	
    	if(transformer == null)
    		throw new RuntimeException("Transformer can not be null");
    	
    	
    	return tranformerDataStore.put(transformer.getId(),transformer);
    	
    	
    }
    
    public Transformer deleteTransformers(String id) {
    	
    	if(id == null)
    		throw new RuntimeException("Transformer ID can not be null");
    	
    	return tranformerDataStore.remove(id);
    }
    
    public Set<Transformer> getTransformerList(){
    	return (Set<Transformer>) Stream.of(tranformerDataStore.entrySet()) 
                .flatMap(x -> x.stream()) 
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet()); 
    }
}
