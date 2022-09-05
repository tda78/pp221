package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class UserDaoImp implements UserDao {


   private SessionFactory sessionFactory;
   @Autowired
   public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void remove(User user) {
//      sessionFactory.getCurrentSession().persist(user);
      sessionFactory.getCurrentSession().remove(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery("from User where car_id.model = :model and car_id.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      User user = (User) query.getSingleResult();
      return user;
   }

}
