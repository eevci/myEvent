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
			
			}
		catch(Exception e){
			return e.getMessage();
		}
		finally {
	        if (session!=null && session.isOpen()) {
	            session.close();
	            
	        }
		}
		return "success...";
	}
	
	public Object get(Class<?> type, Serializable id) {
		
		Session session=null;
		
		try{
			session=createSession();
			Query query = session.createQuery("from "+type.getName()+" where id = :id ");
			query.setParameter("id", id);
			List resultSet=query.list();
			session.close();
			return resultSet.get(0);
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
	
	public void delete(Class<?> type, Serializable id) {
		
		Session session=null;
		
		try{
			session=createSession();
			Query query = session.createQuery("delete "+type.getName()+" where id = :id");
			query.setParameter("id",id);
			
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
