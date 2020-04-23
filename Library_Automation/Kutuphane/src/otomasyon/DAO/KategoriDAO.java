package otomasyon.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import otomasyon.DbSQLQuery;
import otomasyon.model.Kategori;
import otomasyon.model.Personel;

public class KategoriDAO{
	
	public List<Kategori> getAll() {
		
		List<Kategori> kategoriler = new ArrayList<Kategori>();
		ResultSet resultSet = DbSQLQuery.select("SELECT * FROM Kategori");
		
		try {
			
			if(resultSet == null) {
				return kategoriler;
			}
			
			while(resultSet.next()) {
				kategoriler.add(createKategori(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return kategoriler;
		
	}
	
	public Kategori save(Kategori kategori) {
		
		String sql = "INSERT INTO Kategori VALUES(?,?)";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setInt(1, kategori.getKategoriId());
			preparedStatement.setString(2, kategori.getKategoriAd());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return kategori;
	}
	
	public Kategori update(Kategori kategori) {
		
		String sql = "UPDATE Kategori SET katAd = ? WHERE katId = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, kategori.getKategoriAd());
			preparedStatement.setInt(2, kategori.getKategoriId());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return kategori;
	}
	
	public boolean delete(int katId) {
		
		String sql = "DELETE FROM Kategori WHERE katId = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			preparedStatement.setLong(1, katId);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public int getMaxId() {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT MAX(katId) FROM Kategori");
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
	
	public List<Kategori> findKategoriByName(String katAd) {
		
		List<Kategori> kategoriler = new ArrayList<Kategori>();
		String sql = "select * from Kategori where katAd like '%"+katAd+"%'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return kategoriler;
			}
			
			while(resultSet.next()) {
				kategoriler.add(createKategori(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return kategoriler;
	}
	
	public Kategori findKategoriById(int katId) {
		
		String sql = "SELECT * FROM Kategori WHERE katId=" + katId;
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return null;
			}
			
			while(resultSet.next()) {
				return createKategori(resultSet);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int KategoriById(String katAd) {
		
		String sql = "SELECT * FROM Kategori WHERE katAd= '"+katAd+"'";
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
	private Kategori createKategori(ResultSet resultSet) throws SQLException {
		
		Kategori kategori=new Kategori();
		kategori.setKategoriId(resultSet.getInt("katId"));
		kategori.setKategoriAd(resultSet.getString("katAd"));
		return kategori;
		
	}
	
}
