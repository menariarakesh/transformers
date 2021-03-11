package com.aequilibrium.assignment.transformers.entity;

import javax.validation.constraints.Size;

import org.springframework.util.xml.TransformerUtils;

import com.aequilibrium.assignment.transformers.utils.TransformersUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Transformer<T> implements Comparable<T> {

    private String id;
	
    private String type;
    
    @Size(min = 1, max = 10, message = TransformersUtil.SIZE_VALIDATION_MESSAGE)
    private int strength;
    
    @Size(min = 1, max = 10, message = TransformersUtil.SIZE_VALIDATION_MESSAGE)
    private int intelligence;
    
    @Size(min = 1, max = 10, message = TransformersUtil.SIZE_VALIDATION_MESSAGE)
    private int speed;
    
    @Size(min = 1, max = 10, message = TransformersUtil.SIZE_VALIDATION_MESSAGE)
    private int endurance;
    
    @Size(min = 1, max = 10, message = TransformersUtil.SIZE_VALIDATION_MESSAGE)
    private int rank;
    
    @Size(min = 1, max = 10, message = TransformersUtil.SIZE_VALIDATION_MESSAGE)
    private int courage;
    
    @Size(min = 1, max = 10, message = TransformersUtil.SIZE_VALIDATION_MESSAGE)
    private int firepower;
    
    public int getSkill() {
    	return strength + intelligence + speed + endurance + firepower;
    }

	@Override
	public int compareTo(T o) {
		
		if(! (o instanceof Transformer<?>))
			throw new RuntimeException(TransformersUtil.TYPE_VALIDATION_MESSAGE);
		
		Transformer other = (Transformer) o;
		return this.getId().compareTo(other.getId());
	}
}
