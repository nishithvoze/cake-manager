package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repo.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeManager
{
	@Autowired
	CakeRepository cakeRepository;

	@Autowired
	CakeUtils cakeUtils;

	public void addNewCake(CakeEntity cake) {
		cakeRepository.save(cake);
	}

	public List<CakeEntity> findAllCakes() {
		return cakeRepository.findAll();
	}

	public CakeEntity findCakeByTitle(String title) {
		return cakeRepository.findByTitle(title);
	}

	public void readAllCakes(){
		try {
			cakeUtils.readAllCakes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StringBuffer getCakesList(){
		return cakeUtils.showAllCakesList();
	}

	public StringBuffer getSearchCake(CakeEntity cakeEntity){
		return cakeUtils.showSearchedCake(cakeEntity);
	}
}
