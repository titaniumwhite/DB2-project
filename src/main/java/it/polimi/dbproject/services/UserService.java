package it.polimi.dbproject.services;

import it.polimi.dbproject.entities.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
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
            u = em.createNamedQuery("User.loginUser", UserEntity.class).setParameter("usn", username).setParameter("psw", password).getSingleResult();

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

    public List<AvailableServicePackEntity> getAllServicePackages() {
        return em.createNamedQuery("AvailableServicePackage.findAll", AvailableServicePackEntity.class).getResultList();
    }

    public void createError(ErrorEntity err) {
        try {
            em.persist(err);
            em.flush();
        } catch (ConstraintViolationException ignored) {

        }
    }

    public Optional<AvailableServicePackEntity> retrieveAvailableServicePackByID(int availableServicePackId) {
        return em.createNamedQuery("AvailableServicePackage.findByID",
                AvailableServicePackEntity.class)
                .setParameter("availableServicePackId", availableServicePackId)
                .getResultStream().findFirst();
    }

    public List<PeriodEntity> retrieveServicePeriodID(int availableServicePackId){
        return em.createNamedQuery("Period.findPeriodThroughPackage", PeriodEntity.class)
                .setParameter("availableServicePackId", availableServicePackId)
                .getResultList();
    }

    public List<OptionalServiceEntity> retrieveOptionalOfAvailablePackage(int availableServicePackId){
        return em.createNamedQuery("OptionalService.findOptionalThroughPackage", OptionalServiceEntity.class)
                .setParameter("availableServicePackId", availableServicePackId)
                .getResultList();
    }

    public Optional<PeriodEntity> retrievePeriodID(int periodId){
        return em.createNamedQuery("Period.findPeriodThroughID", PeriodEntity.class)
                .setParameter("periodId", periodId)
                .getResultStream().findFirst();
    }

    public Optional<OptionalServiceEntity> retrieveOptionalServicePackByID(int optionalService_id){
        return em.createNamedQuery("OptionalService.findServiceThroughID", OptionalServiceEntity.class)
                .setParameter("optionalService_id", optionalService_id)
                .getResultStream().findFirst();
    }
}
