package main.utility;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.java.models.EventUser;


/**
*
* @author enver
*/

public class HibernateUtility {

	private Session createSession(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session= sessionFactory.openSession();
		return session;
	}
	
	public String save(Object obj){
		
		Session session=null;
		
		try{
			session=createSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
			return "success...";
			}
		catch(Exception e){
			return e.getMessage();
		}
		finally {
	        if (session!=null && session.isOpen()) {
	            session.close();
	            
	        }
		}

	}
	
	public <T> List<T> get(Class<?> type, List<Serializable> valueList,List<String> columnNameList) {
		
		Session session=null;

		try{

			session=createSession();
			StringBuilder queryBuilder=new StringBuilder();
			queryBuilder.append("from "+type.getName()+" where ");
			for(int i=0;i<columnNameList.size()-1;i++){
				queryBuilder.append(columnNameList.get(i)+" = :"+columnNameList.get(i)+" and ");
			}
			queryBuilder.append(columnNameList.get(columnNameList.size()-1)+" = :"+columnNameList.get(columnNameList.size()-1));
			Query query = session.createQuery(queryBuilder.toString());
			for(int i=0;i<columnNameList.size();i++) {
				query.setParameter(columnNameList.get(i),valueList.get(i));
			}
			List resultSet=query.list();
			session.close();
			return resultSet;
		}
		catch(Exception e){
			System.err.print(e);
		}
		finally {
	        if (session!=null && session.isOpen()) {
	        	
	            session.close();	            
	        }
		}
		return null;
		
	}
public <T> List<T> get(Class<? extends T> clazz) {
		
		Session session=null;
		
		try{
			session=createSession();
			Query query = session.createSQLQuery("select * from "+clazz.getSimpleName()).addEntity(clazz);
			List<T> rows = (List<T>) query.list();
			session.close();
			return Collections.unmodifiableList(rows);
		}
		catch(Exception e){
			System.err.print(e);
		}
		finally {
	        if (session!=null && session.isOpen()) {
	        	
	            session.close();   
	        }
		}
		return null;
		
	}
	
	public void delete(Class<?> type, List<Serializable> valueList,List<String> columnNameList) {
		
		Session session=null;
		
		try{
			session=createSession();
			StringBuilder queryBuilder=new StringBuilder();
			queryBuilder.append("delete "+type.getName()+" where ");
			for(int i=0;i<columnNameList.size()-1;i++){
				queryBuilder.append(columnNameList.get(i)+" = :"+columnNameList.get(i)+" and ");
			}
			queryBuilder.append(columnNameList.get(columnNameList.size()-1)+" = :"+columnNameList.get(columnNameList.size()-1));
			Query query = session.createQuery(queryBuilder.toString());
			for(int i=0;i<columnNameList.size();i++) {
				query.setParameter(columnNameList.get(i),valueList.get(i));
			}

			int result = query.executeUpdate();
			
		}
		catch(Exception e){
			System.err.print(e);
		}
		finally {
	        if (session!=null && session.isOpen()) {
	        	
	            session.close();
	           
	        }
		}
		
	}
	
	
	public void update(Object obj){
		Session session=null;
		
		try{
			session=createSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			
			}
		catch(Exception e){
			System.err.print(e);
		}
		finally {
	        if (session!=null && session.isOpen()) {
	            session.close();
	        }
		}
	}

	public void addAdmin(String id) {
		Session session=null;
		
		try{
			session=createSession();
			Query query = session.createSQLQuery("insert into adminuser (userID) values ('"+id+"')");
			
			
			int result = query.executeUpdate();
			
		}
		catch(Exception e){
			System.err.print(e);
		}
		finally {
	        if (session!=null && session.isOpen()) {
	        	
	            session.close();
	            
	        }
		}
		
	}

	public boolean checkAdmin(String id) {
		Session session=null;
		try{
			session=createSession();
			Query query = session.createSQLQuery("select * from adminuser where userID='"+id+"'");
			List<String> rows = (List<String>) query.list();
			session.close();
			if(Collections.unmodifiableList(rows).isEmpty()){
				return false;
			}
			else return true;
			
		}
		catch(Exception e){
			System.err.print(e);
		}
		finally {
	        if (session!=null && session.isOpen()) {
	        	
	            session.close();
	            
	        }
		}
		return false;
		
	}
	
	
}
