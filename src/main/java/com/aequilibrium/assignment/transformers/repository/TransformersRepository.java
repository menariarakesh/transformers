package com.aequilibrium.assignment.transformers.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.aequilibrium.assignment.transformers.entity.Transformer;
import com.aequilibrium.assignment.transformers.utils.TransformersUtil;

@Repository
public class TransformersRepository {
    
	private final Set<Transformer> autobotsDataStore = new TreeSet<>();
	private final Set<Transformer> decepticonsDataStore = new TreeSet<>();
    
    public boolean createUpdateTransformers(Transformer transformer) {
    	
    	if(transformer == null)
    		throw new RuntimeException("Transformer can not be null");
    	
    	if(transformer.getType() == null)
    		throw new RuntimeException("Transformer type can not be null");
    	
    	if(transformer.getType().equalsIgnoreCase(TransformersUtil.AUTOBOTS)) {
    		return autobotsDataStore.add(transformer);
    	} else {
    		return decepticonsDataStore.add(transformer);
    	}
    	
    }
    
    public boolean deleteTransformers(Transformer transformer) {
    	
    	if(transformer == null)
    		throw new RuntimeException("Transformer can not be null");
    	
    	if(transformer.getType() == null)
    		throw new RuntimeException("Transformer type can not be null");
    	
    	if(transformer.getType().equalsIgnoreCase(TransformersUtil.AUTOBOTS)) {
    		return autobotsDataStore.remove(transformer);
    	} else {
    		return decepticonsDataStore.remove(transformer);
    	}
    	
    }
    
    public Set<Transformer> getTransformerList(){
    	return Stream.of(autobotsDataStore, decepticonsDataStore) 
                .flatMap(x -> x.stream()) 
                .collect(Collectors.toSet()); 
    }
}
