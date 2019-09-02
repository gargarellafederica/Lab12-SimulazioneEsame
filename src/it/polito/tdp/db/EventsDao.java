package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> listaanni(){
		String sql = "SELECT distinct year(reported_date) as anno " + 
				"FROM `events` " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getInt("anno"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	
	public List<Integer> listavertici(){
		String sql = "SELECT distinct district_id AS dis " + 
				"FROM `events` " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getInt("dis"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Event> listaeventibyanno(Integer anno){
		String sql = "SELECT *  " + 
				"FROM `events` " + 
				"WHERE YEAR (reported_date) =? " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			st.setInt(1, anno);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public Integer medialon(int anno,int  distretto){
		String sql = "SELECT AVG(geo_lon) AS medialon " + 
				"FROM `events` " + 
				"WHERE YEAR (reported_date) =? AND district_id=? " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			int media=0;
			st.setInt(1,anno);
			st.setInt(2, distretto);
			
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				try {
					media=res.getInt("medialon");
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return media ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public Integer medialat(int anno,int  distretto){
		String sql = "SELECT AVG(geo_lat) AS medialat " + 
				"FROM `events` " + 
				"WHERE YEAR (reported_date) =? AND district_id=? " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			int media=0;
			st.setInt(1,anno);
			st.setInt(2, distretto);
			
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				try {
					media=res.getInt("medialat");
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return media ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
}
