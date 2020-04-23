package otomasyon.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import otomasyon.DbSQLQuery;
import otomasyon.model.Personel;

public class PersonelDAO{
	
	public List<Personel> getAll() {
		
		List<Personel> personeller = new ArrayList<Personel>();
		ResultSet resultSet = DbSQLQuery.select("SELECT * FROM Personel");
		
		try {
			
			if(resultSet == null) {
				return personeller;
			}
			
			while(resultSet.next()) {
				personeller.add(createPersonel(resultSet));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return personeller;
		
	}
	
	public Personel save(Personel personel) {
		
		String sql = "INSERT INTO Personel(perTckn,perAdSyd,perSifre) VALUES(?,?,?)";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, personel.getPerTckn());
			preparedStatement.setString(2, personel.getPerAdSyd());
			preparedStatement.setString(3, personel.getPerSifre());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return personel;
	}
	
	public Personel update(Personel personel) {
		
		String sql = "UPDATE Personel SET perTckn = ?,perAdSyd = ?,perSifre = ? WHERE perTckn = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, personel.getPerTckn());
			preparedStatement.setString(2, personel.getPerAdSyd());
			preparedStatement.setString(3, personel.getPerSifre());
			preparedStatement.setString(4, personel.getPerTckn());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return personel;
	}
	
	public boolean delete(String perTckn) {
		
		String sql = "Delete From Personel where perTckn = ? ";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			preparedStatement.setString(1, perTckn);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public Personel findPersonelByTckn(String perTckn) {
		
		String sql = "SELECT * FROM Personel WHERE perTckn = '"+perTckn+"'";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return null;
			}
			
			while(resultSet.next()) {
				return createPersonel(resultSet);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countPersonel() {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT count(perTckn) FROM Personel");
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
	
	private Personel createPersonel(ResultSet resultSet) throws SQLException {
		
		Personel personel=new Personel();
		personel.setPerTckn(resultSet.getString("perTckn"));
		personel.setPerAdSyd(resultSet.getString("perAdSyd"));
		personel.setPerSifre(resultSet.getString("perSifre"));
		return personel;
		
	}
	
}
