package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession();){
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (Id BIGINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(50), LastName VARCHAR(50), Age TINYINT);").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void removeUserById(long id) {
        User user = new User();
        user.setId(id);
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            list = (ArrayList)session.createCriteria(User.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
        }
            return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }
}
