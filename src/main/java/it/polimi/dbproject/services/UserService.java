package it.polimi.dbproject.services;

import it.polimi.dbproject.entities.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    Logger logger = Logger.getAnonymousLogger();

    public UserEntity checkUser(String username, String password) throws NoResultException {
        UserEntity u;

        try {
            u = em.createNamedQuery("User.loginUser", UserEntity.class)
                    .setParameter("usn", username)
                    .setParameter("psw", password)
                    .getSingleResult();

            return u;
        } catch (NoResultException e) {
            // No-one has such credentials
            return null;
        }

    }

    public UserEntity createUser(String username, String first_name, String last_name, String email, String password) {
        UserEntity u = new UserEntity(username, first_name, last_name, email, password);

        try {
            em.persist(u);
            em.flush();
            return u;
        } catch (EntityExistsException e) {
            logger.log(Level.INFO, "The user already exists", e);
        } catch (IllegalArgumentException e) {
            logger.log(Level.INFO, "The instance is not an entity", e);
        } catch (Exception e) {
            logger.log(Level.INFO, "An exception was thrown", e);
        }

        return null;
    }

    public List<AvailableServicePackEntity> getAllServicePackages(){
        return em.createNamedQuery("AvailableServicePackage.findAll", AvailableServicePackEntity.class).getResultList();
    }
}
