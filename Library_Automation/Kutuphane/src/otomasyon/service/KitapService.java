package otomasyon.service;

import java.util.List;

import otomasyon.DAO.KitapDAO;
import otomasyon.model.Kitap;

public class KitapService {

	private KitapDAO kitapDAO;
	
	public KitapService(KitapDAO kitapDAO) {
		this.kitapDAO = kitapDAO;
	}
	
	public Kitap save(Kitap kitap) {
			
		 return this.kitapDAO.save(kitap);
	}
	
	public List<Kitap> findKitapByKategori(String katAd) {
		
		return this.kitapDAO.findKitapByKategori(katAd);
	
	}
	
	public  List<Kitap> findKitapByYazar(String yazarAd) {
		
		return this.kitapDAO.findKitapByYazar(yazarAd);
	
	}
	
	public  List<Kitap> findKitapByName(String kitapAd) {
		
		return this.kitapDAO.findKitapByName(kitapAd);
	
	}
	
	public List<Kitap> getAll() {
		
		return this.kitapDAO.getAll();
	}
	
	public int countKitap() {
		return this.kitapDAO.countKitap();
	}
	
	public int getById(String kitapAd) {
		return this.kitapDAO.getById(kitapAd);
	}
	
	public int getMaxId() {
		return this.kitapDAO.getMaxId();
	}
	
	public Kitap update(int kitapId, String kitapAd,int kitapSyf,  int katId, int yazarId, int kitapRaf) {
		
		Kitap kitap = this.kitapDAO.findKitapById(kitapId);
		
		if(kitap == null) {
			return kitap;
		}
		
		kitap.setKitapId(kitapId);
		kitap.setKitapAd(kitapAd);
		kitap.setKitapSyf(kitapSyf);
		kitap.setKatId(katId);
		kitap.setYazarId(yazarId);
		kitap.setKitapRaf(kitapRaf);
		return this.kitapDAO.update(kitap);
	}
	
	public boolean delete(int kitapId) {
		
		return this.kitapDAO.delete(kitapId);
	}
	
}

