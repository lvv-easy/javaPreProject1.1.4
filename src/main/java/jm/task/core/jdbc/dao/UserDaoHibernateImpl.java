package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {

    private SessionFactory sf = getSessionFactory();
    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {
        try(Session s = sf.openSession()) {
            s.beginTransaction();
            s.createSQLQuery("create table if not exists Users (" +
                    "ID bigint primary key auto_increment," +
                    "Name varchar(15) not null," +
                    "LastName varchar(15)," +
                    "Age tinyint)").executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public void dropUsersTable() {
        try(Session s = sf.openSession()) {
            s.beginTransaction();
            s.createSQLQuery("drop table if exists Users").executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session s = sf.openSession()){
            s.beginTransaction();
            s.save(new User(name, lastName, age));
            s.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session s = sf.openSession()){
            s.beginTransaction();
            User u = new User();
            u.setId(id);
            s.delete(u);
            s.getTransaction().commit();
            System.out.println("User с ID – " + id + " удалён");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session s = sf.openSession()){
            s.beginTransaction();
            list = s.createQuery("from User").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session s = sf.openSession()){
            s.beginTransaction();
            s.createQuery("delete User").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
