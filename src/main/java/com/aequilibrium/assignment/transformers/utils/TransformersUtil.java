package com.aequilibrium.assignment.transformers.utils;

import java.util.List;

import com.aequilibrium.assignment.transformers.entity.Transformer;

public class TransformersUtil {

	public static final String AUTOBOTS="A";
	public static final String DECEPTICONS="D";
	
	public static final String SIZE_VALIDATION_MESSAGE = "Value should be between  range of 1 and 10";
	public static final String TYPE_VALIDATION_MESSAGE = "Invalid object of having another type";
	
	public static void eliminateTransformer(List<Transformer> transformersList, int i) {
		transformersList.get(i).setEliminated(true);
	}

}
