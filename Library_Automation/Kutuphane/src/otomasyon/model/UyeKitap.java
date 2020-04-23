package otomasyon.model;

import java.util.Date;

public class UyeKitap {

	private int uyeKitapId;
	private String uyeTckn;
	private String uyeAd;
	private int kitapId;
	private String kitapAd;
	private Date alisTrh;
	private Date verisTrh;
	private String kitapDurum;
	private String uyeRezTckn;

	
	public int getUyeKitapId() {
		return uyeKitapId;
	}

	public void setUyeKitapId(int uyeKitapId) {
		this.uyeKitapId = uyeKitapId;
	}

	public String getUyeTckn() {
		return uyeTckn;
	}

	public void setUyeTckn(String uyeTckn) {
		this.uyeTckn = uyeTckn;
	}

	public int getKitapId() {
		return kitapId;
	}

	public void setKitapId(int kitapId) {
		this.kitapId = kitapId;
	}

	public String getUyeAd() {
		return uyeAd;
	}

	public void setUyeAd(String uyeAd) {
		this.uyeAd = uyeAd;
	}

	public String getKitapAd() {
		return kitapAd;
	}

	public void setKitapAd(String kitapAd) {
		this.kitapAd = kitapAd;
	}

	public Date getAlisTrh() {
		return alisTrh;
	}

	public void setAlisTrh(Date alisTrh) {
		this.alisTrh = alisTrh;
	}

	public Date getVerisTrh() {
		return verisTrh;
	}

	public void setVerisTrh(Date verisTrh) {
		this.verisTrh = verisTrh;
	}

	public String getKitapDurum() {
		return kitapDurum;
	}

	public void setKitapDurum(String kitapDurum) {
		this.kitapDurum = kitapDurum;
	}

	public String getUyeRezTckn() {
		return uyeRezTckn;
	}

	public void setUyeRezTckn(String uyeRezTckn) {
		this.uyeRezTckn = uyeRezTckn;
	}

}
