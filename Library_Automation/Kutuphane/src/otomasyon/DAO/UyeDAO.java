package otomasyon.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import otomasyon.DbSQLQuery;
import otomasyon.model.Uye;

public class UyeDAO{
	
	public List<Uye> getAll() {
		
		List<Uye> uyeler = new ArrayList<Uye>();
		ResultSet resultSet = DbSQLQuery.select("SELECT * FROM Uye");
		
		try {
			
			if(resultSet == null) {
				return uyeler;
			}
			
			while(resultSet.next()) {
				uyeler.add(createUye(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return uyeler;
		
	}
	
	public Uye save(Uye uye) {
		
		String sql = "INSERT INTO Uye(uyeTckn,uyeAdSyd,uyeAdres,UyeTel) VALUES(?,?,?,?)";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, uye.getUyeTckn());
			preparedStatement.setString(2, uye.getUyeAdSoyad());
			preparedStatement.setString(3, uye.getUyeAdres());
			preparedStatement.setString(4, uye.getUyeTel());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return uye;
	}
	
	public Uye update(Uye uye) {
		
		String sql = "UPDATE Uye SET uyeAdSyd = ?,uyeAdres = ?,uyeTel = ? WHERE uyeTckn = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, uye.getUyeAdSoyad());
			preparedStatement.setString(2, uye.getUyeAdres());
			preparedStatement.setString(3, uye.getUyeTel());
			preparedStatement.setString(4, uye.getUyeTckn());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return uye;
	}
	
	public boolean delete(String uyeTckn) {
		
		String sql = "DELETE FROM Uye WHERE uyeTckn = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			preparedStatement.setString(1, uyeTckn);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public Uye findUyeByTckn(String uyeTckn) {
		
		String sql = "SELECT * FROM Uye WHERE uyeTckn='"+uyeTckn+"'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return null;
			}
			
			while(resultSet.next()) {
				return createUye(resultSet);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countUye() {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT count(uyeTckn) FROM Uye");
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

	private Uye createUye(ResultSet resultSet) throws SQLException {
		
		Uye uye=new Uye();
		uye.setUyeTckn(resultSet.getString("uyeTckn"));
		uye.setUyeAdSoyad(resultSet.getString("uyeAdSyd"));
		uye.setUyeAdres(resultSet.getString("uyeAdres"));
		uye.setUyeTel(resultSet.getString("uyeTel"));
		return uye;
		
	}
		
}
