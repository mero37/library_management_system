package otomasyon.service;

import java.util.List;

import otomasyon.DAO.YazarDAO;
import otomasyon.model.Yazar;

public class YazarService {
	private YazarDAO yazarDAO;
	
	public YazarService(YazarDAO yazarDAO) {
		this.yazarDAO = yazarDAO;
	}
	
	public Yazar save(Yazar yazar) {
	
		 return this.yazarDAO.save(yazar);
	}
	
	public List<Yazar> findYazarByName(String yazarAd) {
		
		return this.yazarDAO.findYazarByName(yazarAd);
	}
	
	public List<Yazar> getAll() {
		
		return this.yazarDAO.getAll();
	}
	
	public int YazarById(String yazarAd) {
		
		return this.yazarDAO.YazarById(yazarAd);
		
	}
	
	
	public Yazar update(int yazarId, String yazarAd) {
		
		Yazar yazar = this.yazarDAO.findYazarById(yazarId);
		
		if(yazar == null) {
			return yazar;
		}
		
		yazar.setYazarId(yazarId);
		yazar.setYazarAd(yazarAd);
		return this.yazarDAO.update(yazar);
	}
	
	public int getMaxId() {
		return this.yazarDAO.getMaxId();
	}
	public boolean delete(int yazarId) {
		
		return this.yazarDAO.delete(yazarId);
	}
	
}

