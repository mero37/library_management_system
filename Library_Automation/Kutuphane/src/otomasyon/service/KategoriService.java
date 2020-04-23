package otomasyon.service;

import java.util.List;

import otomasyon.DAO.KategoriDAO;
import otomasyon.model.Kategori;

public class KategoriService {
	private KategoriDAO kategoriDAO;
	
	public KategoriService(KategoriDAO kategoriDAO) {
		this.kategoriDAO = kategoriDAO;
	}
	
	public Kategori save(Kategori kategori) {
		
		 return this.kategoriDAO.save(kategori);
		 
	}
	
	public List<Kategori> findByKategoriName(String katAd) {
		
		return this.kategoriDAO.findKategoriByName(katAd);
	}
	
	public List<Kategori> getAll() {
		
		return this.kategoriDAO.getAll();
	}
	
	public int KategoriById(String katAd) {
		
		return this.kategoriDAO.KategoriById(katAd);
		
	}
	
	public Kategori update(int katId, String katAd) {
		
		Kategori kategori = this.kategoriDAO.findKategoriById(katId);
		
		if(kategori == null) {
			return kategori;
		}
		
		kategori.setKategoriId(katId);
		kategori.setKategoriAd(katAd);
		return this.kategoriDAO.update(kategori);
	}
	
	public int getMaxId() {
		return this.kategoriDAO.getMaxId();
	}
	
	public boolean delete(int katId) {
		
		return this.kategoriDAO.delete(katId);
	}
	
}


