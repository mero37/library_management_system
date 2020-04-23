package otomasyon.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import otomasyon.DbSQLQuery;
import otomasyon.model.UyeKitap;

public class UyeKitapDAO{
	
	public List<UyeKitap> getAll() {
		
		List<UyeKitap> uyeKitaplar = new ArrayList<UyeKitap>();
		ResultSet resultSet = DbSQLQuery.select("Select * from UyeKitap uktp INNER JOIN Uye uye On uktp.uyeTckn=uye.uyeTckn INNER JOIN Kitap ktp On uktp.kitapId=ktp.kitapId");
		
		try {
			
			if(resultSet == null) {
				return uyeKitaplar;
			}
			
			while(resultSet.next()) {
				uyeKitaplar.add(createUyeKitap(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return uyeKitaplar;
		
	}
	
	public List<UyeKitap> getUyeKitapByName(String kitapAd) {
		
		List<UyeKitap> uyeKitaplar = new ArrayList<UyeKitap>();
		ResultSet resultSet = DbSQLQuery.select("Select * from UyeKitap uktp INNER JOIN Uye uye On uktp.uyeTckn=uye.uyeTckn INNER JOIN Kitap ktp On uktp.kitapId=ktp.kitapId where kitapAd='"+kitapAd+"'");
		
		try {
			
			if(resultSet == null) {
				return uyeKitaplar;
			}
			
			while(resultSet.next()) {
				uyeKitaplar.add(createUyeKitap(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return uyeKitaplar;
		
	}
	
	public UyeKitap save(UyeKitap uyeKitap) {
		
		String sql = "INSERT INTO UyeKitap VALUES(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setInt(1, uyeKitap.getUyeKitapId());
			preparedStatement.setString(2, uyeKitap.getUyeTckn());
			preparedStatement.setInt(3, uyeKitap.getKitapId());
			preparedStatement.setDate(4, (java.sql.Date) uyeKitap.getAlisTrh());
			preparedStatement.setDate(5, (java.sql.Date) uyeKitap.getVerisTrh());
			preparedStatement.setString(6, uyeKitap.getKitapDurum());
			preparedStatement.setString(7, uyeKitap.getUyeRezTckn());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return uyeKitap;
	}
	
	public UyeKitap update(UyeKitap uyeKitap) {
		
		String sql = "UPDATE UyeKitap SET uyeRezerveTckn = ?,kitapDurum = ? WHERE uyeKitapId = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, uyeKitap.getUyeRezTckn());
			preparedStatement.setString(2, uyeKitap.getKitapDurum());
			preparedStatement.setInt(3, uyeKitap.getUyeKitapId());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return uyeKitap;
	}
	
	public boolean delete(int uyekitapId) {
		
		String sql = "DELETE FROM UyeKitap WHERE uyekitapId = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			preparedStatement.setInt(1, uyekitapId);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public List<UyeKitap> findUyeKitapOutOfDate(){
		
		List<UyeKitap> uyeKitaplar = new ArrayList<UyeKitap>();
		LocalDate now = LocalDate.now();  
		String sql = "Select * from UyeKitap uktp INNER JOIN Uye uye On uktp.uyeTckn=uye.uyeTckn INNER JOIN Kitap ktp On uktp.kitapId=ktp.kitapId where verisTrh < '"+now+"' and kitapDurum='Verildi'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return uyeKitaplar;
			}
			
			while(resultSet.next()) {
				uyeKitaplar.add(createUyeKitap(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return uyeKitaplar;
	}
	public List<UyeKitap> findUyeKitapTodayDate() {
		
		List<UyeKitap> uyeKitaplar = new ArrayList<UyeKitap>();
		LocalDate now = LocalDate.now();  
		String sql = "Select * from UyeKitap uktp INNER JOIN Uye uye On uktp.uyeTckn=uye.uyeTckn INNER JOIN Kitap ktp On uktp.kitapId=ktp.kitapId where verisTrh = '"+now+"'  and kitapDurum='Alındı'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return uyeKitaplar;
			}
			
			while(resultSet.next()) {
				
				uyeKitaplar.add(createUyeKitap(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return uyeKitaplar;
	}
	public UyeKitap findUyeKitapByTckn(String uyeTckn) {
		
		String sql = "Select * from UyeKitap uktp INNER JOIN Uye uye On uktp.uyeTckn=uye.uyeTckn INNER JOIN Kitap ktp On uktp.kitapId=ktp.kitapId where uyeTckn = '"+uyeTckn+"'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return null;
			}
			
			while(resultSet.next()) {
				return createUyeKitap(resultSet);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getMaxId() {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT MAX(uyeKitapId) FROM UyeKitap");
		try {
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public UyeKitap findUyeKitapById(int uyeKitapId) {
		
		String sql = "Select * from UyeKitap uktp INNER JOIN Uye uye On uktp.uyeTckn=uye.uyeTckn INNER JOIN Kitap ktp On uktp.kitapId=ktp.kitapId WHERE uyeKitapId=" + uyeKitapId;
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return null;
			}
			
			while(resultSet.next()) {
				return createUyeKitap(resultSet);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getUyeKitapId(String uyeTckn,String kitapAd) {
		ResultSet resultSet = DbSQLQuery.select("Select uyeKitapId from UyeKitap uktp INNER JOIN Uye uye On uktp.uyeTckn=uye.uyeTckn INNER JOIN Kitap ktp On uktp.kitapId=ktp.kitapId WHERE uktp.uyeTckn='"+uyeTckn+"' and ktp.kitapAd='"+kitapAd+"' and uktp.kitapDurum='Verildi'");
		try {
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	private UyeKitap createUyeKitap(ResultSet resultSet) throws SQLException {
		
		UyeKitap uyeKitap=new UyeKitap();
		uyeKitap.setUyeKitapId(resultSet.getInt("uyeKitapId"));
		uyeKitap.setUyeTckn(resultSet.getString("uyeTckn"));
		uyeKitap.setUyeAd(resultSet.getString("uyeAdSyd"));
		uyeKitap.setKitapId(resultSet.getInt("kitapId"));
		uyeKitap.setKitapAd(resultSet.getString("kitapAd"));
		uyeKitap.setAlisTrh( (java.sql.Date) resultSet.getDate("alisTrh"));
		uyeKitap.setVerisTrh( (java.sql.Date) resultSet.getDate("verisTrh"));
		uyeKitap.setKitapDurum(resultSet.getString("kitapDurum"));
		uyeKitap.setUyeRezTckn(resultSet.getString("uyeRezerveTckn"));
		return uyeKitap;
	}
	
      

}
