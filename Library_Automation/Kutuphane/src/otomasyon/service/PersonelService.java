package otomasyon.service;

import java.util.List;

import otomasyon.DAO.PersonelDAO;
import otomasyon.model.Personel;

public class PersonelService {
	private PersonelDAO personelDAO;
	
	public PersonelService(PersonelDAO personelDAO) {
		this.personelDAO = personelDAO;
	}
	
	public Personel save(Personel personel) {
	
		 return this.personelDAO.save(personel);
	}

	
	public List<Personel> getAll() {
		
		return this.personelDAO.getAll();
	}
	
	public Personel findPersonelByTckn(String perTckn) {
		return this.personelDAO.findPersonelByTckn(perTckn);
	}
	
	public Personel update(String perTckn, String perAdSyd, String perSifre ) {
		
		Personel personel = this.personelDAO.findPersonelByTckn(perTckn);
		
		if(personel == null) {
			return personel;
		}
		
		personel.setPerTckn(perTckn);
		personel.setPerAdSyd(perAdSyd);
		personel.setPerSifre(perSifre);
		return this.personelDAO.update(personel);
	}
	
	public int countPersonel() {
		return this.personelDAO.countPersonel();
	}
	
	public boolean delete(String perTckn) {
		
		return this.personelDAO.delete(perTckn);
	}

}
