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
	
	public boolean createUpdateTransformers(Transformer transformer) {
		return this.transformersRepository.createUpdateTransformers(transformer);
	}
	
	public boolean deleteTransformers(Transformer transformer) {
		return this.transformersRepository.deleteTransformers(transformer);
	}
	
	public Set<Transformer> getTransformerList(){
		return this.transformersRepository.getTransformerList();
	}
	
}