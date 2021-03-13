package com.aequilibrium.assignment.transformers.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aequilibrium.assignment.transformers.entity.Transformer;
import com.aequilibrium.assignment.transformers.repository.TransformersRepository;

@Service
public class TransformersService{
	
	@Autowired
	private TransformersRepository transformersRepository;
	
	public Transformer createUpdateTransformers(Transformer transformer) {
		return this.transformersRepository.createUpdateTransformers(transformer);
	}
	
	public Transformer deleteTransformers(String id) {
		return this.transformersRepository.deleteTransformers(id);
	}
	
	public Set<Transformer> getTransformerList(){
		return this.transformersRepository.getTransformerList();
	}
	
}