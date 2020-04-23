package otomasyon.service;

import java.util.List;

import otomasyon.DAO.UyeDAO;
import otomasyon.model.Uye;

public class UyeService {
	
	private UyeDAO uyeDAO;
	
	public UyeService(UyeDAO uyeDAO) {
		this.uyeDAO = uyeDAO;
	}
	
	public Uye save(Uye uye) {
	
		 return this.uyeDAO.save(uye);
	}
	
	public Uye findUyeByTckn(String uyeTckn) {
		
		return this.uyeDAO.findUyeByTckn(uyeTckn);
	}
	
	public List<Uye> getAll() {
		
		return this.uyeDAO.getAll();
	}
	
	
	public Uye update(String uyeTckn,String uyeAdSoyad,String uyeAdres,String uyeTel) {
		
		Uye uye = this.uyeDAO.findUyeByTckn(uyeTckn);
		
		if(uye == null) {
			return uye;
		}
		
		uye.setUyeTckn(uyeTckn);
		uye.setUyeAdSoyad(uyeAdSoyad);
		uye.setUyeAdres(uyeAdres);
		uye.setUyeTel(uyeTel);
		return this.uyeDAO.update(uye);
	}
	
	public int countUye() {
		return this.uyeDAO.countUye();
	}
	
	public boolean delete(String uyeTckn) {
		
		return this.uyeDAO.delete(uyeTckn);
	}
	
}

