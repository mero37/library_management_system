package otomasyon.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import otomasyon.DbSQLQuery;
import otomasyon.model.Kategori;
import otomasyon.model.Kitap;

public class KitapDAO{
	
	public List<Kitap> getAll() {
		
		List<Kitap> kitaplar = new ArrayList<Kitap>();
		ResultSet resultSet = DbSQLQuery.select("select * from Kitap kit INNER JOIN Kategori kat ON kit.katId=kat.katId INNER JOIN Yazar yaz ON kit.yazarId=yaz.yazarId");
		
		try {
			
			if(resultSet == null) {
				return kitaplar;
			}
			
			while(resultSet.next()) {
				kitaplar.add(createKitap(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return kitaplar;
		
	}
	
	public Kitap save(Kitap kitap) {
		
		String sql = "INSERT INTO Kitap VALUES(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setInt(1, kitap.getKitapId());
			preparedStatement.setString(2, kitap.getKitapAd());
			preparedStatement.setInt(3, kitap.getKitapSyf());
			preparedStatement.setInt(4, kitap.getKatId());
			preparedStatement.setInt(5, kitap.getYazarId());
			preparedStatement.setInt(6, kitap.getKitapRaf());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return kitap;
	}
	
	public Kitap update(Kitap kitap) {
		
		String sql = "UPDATE Kitap SET kitapAd = ?,katId  = ?,yazarId = ?,kitapSyf = ?,kitapRaf = ? WHERE kitapId = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, kitap.getKitapAd());
			preparedStatement.setInt(2, kitap.getKatId());
			preparedStatement.setInt(3, kitap.getYazarId());
			preparedStatement.setInt(4, kitap.getKitapSyf());
			preparedStatement.setInt(5, kitap.getKitapRaf());
			preparedStatement.setInt(6, kitap.getKitapId());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return kitap;
	}
	
	public boolean delete(int kitapId) {
		
		String sql = "DELETE FROM Kitap WHERE kitapId = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			preparedStatement.setInt(1, kitapId);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public List<Kitap> findKitapByKategori(String katAd) {
		
		List<Kitap> kitaplar = new ArrayList<Kitap>();
		String sql = "select * from Kitap kit INNER JOIN Kategori kat ON kit.katId=kat.katId INNER JOIN Yazar yaz ON kit.yazarId=yaz.yazarId where katAd='"+katAd+"'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return kitaplar;
			}
			
			while(resultSet.next()) {
				kitaplar.add(createKitap(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return kitaplar;
	}
	public List<Kitap> findKitapByYazar(String yazarAd) {
		
		List<Kitap> kitaplar = new ArrayList<Kitap>();
		String sql = "select * from Kitap kit INNER JOIN Kategori kat ON kit.katId=kat.katId INNER JOIN Yazar yaz ON kit.yazarId=yaz.yazarId where yazarAd='"+yazarAd+"'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return kitaplar;
			}
			
			while(resultSet.next()) {
				kitaplar.add(createKitap(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return kitaplar;
	}
	
	public Kitap findKitapById(int kitapId) {
		
		String sql = "select * from Kitap kit INNER JOIN Kategori kat ON kit.katId=kat.katId INNER JOIN Yazar yaz ON kit.yazarId=yaz.yazarId where kitapId='"+kitapId+"'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return null;
			}
			
			while(resultSet.next()) {
				return createKitap(resultSet);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Kitap> findKitapByName(String kitapAd) {
		
		List<Kitap> kitaplar = new ArrayList<Kitap>();
		String sql = "select * from Kitap kit INNER JOIN Kategori kat ON kit.katId=kat.katId INNER JOIN Yazar yaz ON kit.yazarId=yaz.yazarId where kitapAd LIKE '%"+kitapAd+"%'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return kitaplar;
			}
			
			while(resultSet.next()) {
				kitaplar.add(createKitap(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return kitaplar;
	}
	
	public int getMaxId() {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT MAX(kitapId) FROM Kitap");
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
	
	public int countKitap() {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT count(kitapId) FROM Kitap");
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
	
	public int getById(String kitapAd) {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT kitapId FROM Kitap where kitapAd='"+kitapAd+"'");
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
	
	private Kitap createKitap(ResultSet resultSet) throws SQLException {
		
		Kitap kitap=new Kitap();
		kitap.setKitapId(resultSet.getInt("kitapId"));
		kitap.setKitapAd(resultSet.getString("kitapAd"));
		kitap.setKitapSyf(resultSet.getInt("kitapSyf"));
		kitap.setKatId(resultSet.getInt("katId"));
		kitap.setKatAd(resultSet.getString("katAd"));
		kitap.setYazarId(resultSet.getInt("yazarId"));
		kitap.setYazarAd(resultSet.getString("yazarAd"));
		kitap.setKitapRaf(resultSet.getInt("kitapRaf"));
		kitap.setDolapNo(resultSet.getInt("katId"));
		return kitap;
	}

}
