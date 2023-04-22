package Model;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository {
    private User user;

    public UserRepository() {
    }

    public UserRepository(User user) {
        this.user = user;
    }

    public boolean saveUser() {
        boolean saved = false;
        Session session = Repository.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            tx.commit();
            saved = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return saved;
    }

    public boolean updateUser(User updatedUser) {
        boolean updated = false;
        Session session = Repository.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            User userToUpdate = session.get(User.class, updatedUser.getId());
            userToUpdate.setUser(updatedUser.getUser());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setRole(updatedUser.getRole());
            session.update(userToUpdate);
            tx.commit();
            updated = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return updated;
    }

    public boolean deleteUser() {
        boolean deleted = false;
        Session session = Repository.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(user);
            tx.commit();
            deleted = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return deleted;
    }

    public User searchuser(String username, String password) {
        Session session = Repository.getSessionFactory().openSession();
        try {
            Query<?> query = session.createQuery("FROM User WHERE user = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = (User) query.uniqueResult();
            return user;
        } finally {
            session.close();
        }
    }

    public List<User> getUsers() {
        Session session = Repository.getSessionFactory().openSession();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        session.close();
        return users;
    }

}
