package otomasyon.service;

import java.util.List;

import otomasyon.DAO.UyeKitapDAO;
import otomasyon.model.UyeKitap;

public class UyeKitapService {

	private UyeKitapDAO uyeKitapDAO;
	
	public UyeKitapService(UyeKitapDAO uyeKitapDAO) {
		this.uyeKitapDAO = uyeKitapDAO;
	}
	
	public UyeKitap save(UyeKitap uyekitap) {
	
		 return this.uyeKitapDAO.save(uyekitap);
	}
	
	public List<UyeKitap> findUyeKitapOutOfDate() {
		
		return this.uyeKitapDAO.findUyeKitapOutOfDate();
	
	}
	
	public List<UyeKitap> getOne(String kitapAd) {
	
		
		return this.uyeKitapDAO.getUyeKitapByName(kitapAd);
	}
	
	public List<UyeKitap> findUyeKitapTodayDate() {
		
		return this.uyeKitapDAO.findUyeKitapTodayDate();
	
	}
	public UyeKitap  findUyeKitapByTckn(String uyeTckn) {
		
		return this.uyeKitapDAO.findUyeKitapByTckn(uyeTckn);
	
	}
	
	public List<UyeKitap> getAll() {
		
		return this.uyeKitapDAO.getAll();
	}
	
	
	public UyeKitap update(int uyeKitapId, String kitapdurum, String uyeRezTckn) {
		
		UyeKitap uyeKitap = this.uyeKitapDAO.findUyeKitapById(uyeKitapId);
		
		if(uyeKitap == null) {
			return uyeKitap;
		}
		
		uyeKitap.setUyeKitapId(uyeKitapId);
		uyeKitap.setKitapDurum(kitapdurum);
		uyeKitap.setUyeRezTckn(uyeRezTckn);
		return this.uyeKitapDAO.update(uyeKitap);
	}
	
	public int getMaxId() {
		return this.uyeKitapDAO.getMaxId();
	}
	public int getUyeKitapId(String uyeTckn,String kitapAd) {
		return this.uyeKitapDAO.getUyeKitapId(uyeTckn, kitapAd);
	}
	
	public boolean delete(int uyeKitapId) {
		
		return this.uyeKitapDAO.delete(uyeKitapId);
	}
	
}