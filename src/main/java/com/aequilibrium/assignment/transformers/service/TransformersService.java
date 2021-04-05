package com.aequilibrium.assignment.transformers.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aequilibrium.assignment.transformers.entity.Transformer;
import com.aequilibrium.assignment.transformers.repository.TransformersRepository;
import com.aequilibrium.assignment.transformers.utils.TransformersUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransformersService{
	
	@Autowired
	private TransformersRepository transformersRepository;
	
	int eliminationCountA = 0;

	int eliminationCountD= 0 ;
	
	public Transformer createUpdateTransformers(Transformer transformer) {
		return this.transformersRepository.createUpdateTransformers(transformer);
	}
	
	public Transformer deleteTransformers(String id) {
		return this.transformersRepository.deleteTransformers(id);
	}
	
	public Set<Transformer> getTransformerList(){
		return this.transformersRepository.getTransformerList();
	}
	
	public Map<String, String> determineWiningTeam(ArrayList<Integer> tranformerIDList) throws Exception{
		
		Set<Transformer> allTransformers = getTransformerList();
		
		Set<Transformer> selectedTransformers = allTransformers.stream()
			.filter(e -> tranformerIDList.contains(e.getId()))
			.collect(Collectors.toSet());
			
		List<Transformer> autobotsTransformers = selectedTransformers.stream()
				.filter(e -> e.getType().equalsIgnoreCase(TransformersUtil.AUTOBOTS))
				.collect(Collectors.toList());
		List<Transformer> decepticonsTransformers = selectedTransformers.stream()
				.filter(e -> e.getType().equalsIgnoreCase(TransformersUtil.DECEPTICONS))
				.collect(Collectors.toList());
		

		Collections.sort(autobotsTransformers);
		Collections.sort(decepticonsTransformers);

		log.info("Transformers Autobots sorted by Rank:"+autobotsTransformers);
		log.info("Transformers Decepticons sorted by Rank:"+decepticonsTransformers);

		int numberOfBattles = (autobotsTransformers.size() < decepticonsTransformers.size())? autobotsTransformers.size() : decepticonsTransformers.size();
		log.info("Number of battles to fight:"+ numberOfBattles);
		
		applyBattleRules(autobotsTransformers,decepticonsTransformers, numberOfBattles);

		// winning team with largest elimination count is
		String winnerTeam = (eliminationCountA > eliminationCountD)? "Decepticons":"Autobots";
		String loosingTeam = (eliminationCountA > eliminationCountD)? "Autobots":"Decepticons";
		
		log.info("Winning Team:"+ winnerTeam);
		
		List<Transformer> loosingSurvivorsList = (winnerTeam.equalsIgnoreCase("Decepticons"))? autobotsTransformers : decepticonsTransformers;
		List<Transformer> winnersList = (winnerTeam.equalsIgnoreCase("Decepticons"))? decepticonsTransformers: autobotsTransformers ;
		
		List<String> loosingSurvivors = findLoosingSurvivors(loosingSurvivorsList);
		List <String> winners = findWinners(winnersList);
		
		Map battleOutput = new HashMap();
		battleOutput.put("numBattles", numberOfBattles);
		battleOutput.put("winningTeam", winnerTeam);
		battleOutput.put("loosingTeam",  loosingTeam);
		battleOutput.put("winners", winners);
		battleOutput.put("loosingSurvivors", loosingSurvivors);
		
		log.info("Loosing Team:"+ loosingTeam);
		return battleOutput;
	}
	
	private List<String> findWinners(List<Transformer> winnersList) {
		return winnersList.stream().filter( transformer -> transformer.isEliminated() == false).
				collect(Collectors.toList()).stream().map(transformer -> transformer.getName()).collect(Collectors.toList())
				;
	}


	private List<String> findLoosingSurvivors(List<Transformer> loosingSurvivorsList) {
		return loosingSurvivorsList.stream().filter( transformer -> transformer.isEliminated() == true).
				collect(Collectors.toList()).stream().map(transformer -> transformer.getName()).collect(Collectors.toList());
	}
	
	private void applyBattleRules(List transformersAList, List transformersDList, int numBattles) {
		log.info("In TransformerService.applyBattleRules()...");
		
		for(int i=0;i<numBattles;i++) {
			//face off condition
			Transformer ta =(Transformer) transformersAList.get(i);
			Transformer td = (Transformer) transformersDList.get(i);
			
			String taName = ta.getName();
			String tdName = td.getName();
			
			log.info("Based on Ranking Autobot "+taName+" is faced off with Decepticons "+ tdName);
			
			// condition when Prime and Predaking face each other / or duplicate of each other, end the game. 
			if(taName.equalsIgnoreCase("Optimus Prime") && tdName.equalsIgnoreCase("Predaking")
				|| taName.equalsIgnoreCase("Optimus Prime") && tdName.equalsIgnoreCase("Optimus Prime") 
				|| tdName.equalsIgnoreCase("Predaking") && taName.equalsIgnoreCase("Predaking")){
					break;
				}
				
				
			//if transfomer name is Prime / Predaking, he is winner ie. eliminate the opponent. 
			if(taName.equalsIgnoreCase("Optimus Prime") )
				TransformersUtil.eliminateTransformer(transformersDList,i);
			if(tdName.equalsIgnoreCase("Predaking")) 
				TransformersUtil.eliminateTransformer(transformersAList,i);
			
			
			if( ta.getCourage() < (td.getCourage() - 4 ) && ta.getStrength() < (td.getStrength() - 3)) {
				// ta is eliminated by opponent so remove it from list
				TransformersUtil.eliminateTransformer(transformersAList,i);
			}
			else {
				if( ta.getSkill() <= (td.getSkill() - 3) ) {
					// ta has less skills so it needs tobe removed
					TransformersUtil.eliminateTransformer(transformersAList, i);
					
				}else {

					// event of tie consider both transformers as destroyed.
					if(ta.getSkill() == td.getSkill()) {
						TransformersUtil.eliminateTransformer(transformersAList, i);
						TransformersUtil.eliminateTransformer(transformersDList,i);
					} else {
						if( ta.getSkill() < td.getSkill()) {
							TransformersUtil.eliminateTransformer(transformersAList, i);
						}
						else {
							TransformersUtil.eliminateTransformer(transformersDList,i);
						}
						
					}

				}

			}
		}
		
	}
	
}