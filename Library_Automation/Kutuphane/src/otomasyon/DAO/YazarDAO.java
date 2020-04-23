package otomasyon.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import otomasyon.DbSQLQuery;
import otomasyon.model.Yazar;

public class YazarDAO{
	
	public List<Yazar> getAll() {
		
		List<Yazar> yazarlar = new ArrayList<Yazar>();
		ResultSet resultSet = DbSQLQuery.select("SELECT * FROM Yazar");
		
		try {
			
			if(resultSet == null) {
				return yazarlar;
			}
			
			while(resultSet.next()) {
				yazarlar.add(createYazar(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return yazarlar;
		
	}
	
	public Yazar save(Yazar yazar) {
		
		String sql = "INSERT INTO Yazar VALUES(?,?)";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setInt(1, yazar.getYazarId());
			preparedStatement.setString(2, yazar.getYazarAd());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return yazar;
	}
	
	public Yazar update(Yazar yazar) {
		
		String sql = "UPDATE Yazar SET yazarAd = ? WHERE yazarId = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, yazar.getYazarAd());
			preparedStatement.setInt(2, yazar.getYazarId());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return yazar;
	}
	
	public boolean delete(int yazarId) {
		
		String sql = "DELETE FROM Yazar WHERE yazarId = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			preparedStatement.setInt(1, yazarId);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public int getMaxId() {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT MAX(yazarId) FROM Yazar");
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
	
	public List<Yazar> findYazarByName(String yazarAd) {
		
		List<Yazar> yazarlar = new ArrayList<Yazar>();
		String sql = "select * from Yazar where yazarAd like '%"+yazarAd+"%'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return yazarlar;
			}
			
			while(resultSet.next()) {
				yazarlar.add(createYazar(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return yazarlar;
	}
	
	public Yazar findYazarById(int yazarId) {
		
		String sql = "SELECT * FROM Yazar WHERE yazarId=" + yazarId;
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return null;
			}
			
			while(resultSet.next()) {
				return createYazar(resultSet);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int YazarById(String yazarAd) {
		
		String sql = "SELECT * FROM Yazar WHERE yazarAd= '"+yazarAd+"'";
		ResultSet resultSet = DbSQLQuery.select(sql);
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
	private Yazar createYazar(ResultSet resultSet) throws SQLException {
		
		Yazar yazar=new Yazar();
		yazar.setYazarId(resultSet.getInt("yazarId"));
		yazar.setYazarAd(resultSet.getString("yazarAd"));
		return yazar;
		
	}
}
