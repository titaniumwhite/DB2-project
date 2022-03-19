package it.polimi.dbproject.services;

import it.polimi.dbproject.entities.*;
import it.polimi.dbproject.entities.queries.BestOptionalProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EmployeeService {

    @PersistenceContext
    private EntityManager em;

    Logger logger = Logger.getAnonymousLogger();

    public EmployeeEntity checkEmployee(String username, String password) throws NoResultException {
        EmployeeEntity emp;

        try {
            emp = em.createNamedQuery("Employee.loginEmployee", EmployeeEntity.class).setParameter("usn", username).setParameter("psw", password).getSingleResult();

            return emp;
        } catch (NoResultException e) {
            // No-one has such credentials
            return null;
        }

    }

    public AvailableServicePackEntity createAvailableServicePack(String name, List<ServiceEntity> services, List<PeriodEntity> periods, List<OptionalServiceEntity> optionalServices) {
        AvailableServicePackEntity asp = new AvailableServicePackEntity(name, services, periods, optionalServices);

        try{
            em.persist(asp);
            em.flush();
            return asp;
        } catch (EntityExistsException e) {
            logger.log(Level.INFO, "The service package already exists", e);
        } catch (IllegalArgumentException e) {
            logger.log(Level.INFO, "The instance is not an entity", e);
        } catch (Exception e) {
            logger.log(Level.INFO, "An exception was thrown", e);
        }

        return null;
    }

    public OptionalServiceEntity createOptionalService(String name, int fee) {
        OptionalServiceEntity os = new OptionalServiceEntity(name, fee, null, null);
        System.out.println(os.toString());

        try{
            em.persist(os);
            em.flush();
            return os;
        } catch (EntityExistsException e) {
            logger.log(Level.INFO, "The optional service already exists", e);
        } catch (IllegalArgumentException e) {
            logger.log(Level.INFO, "The instance is not an entity", e);
        } catch (Exception e) {
            logger.log(Level.INFO, "An exception was thrown", e);
        }

        return null;
    }

    public List<OptionalServiceEntity> getAllOptionalServices(){
        return em.createNamedQuery("OptionalService.findAll", OptionalServiceEntity.class).getResultList();
    }

    public List<PeriodEntity> getAllPeriods(){
        return em.createNamedQuery("Period.findAll", PeriodEntity.class).getResultList();
    }

    public List<AvailableServicePackEntity> retrieveAllAvailableServicePackages(){
        return em.createNamedQuery("AvailableServicePackage.findAll", AvailableServicePackEntity.class)
                .getResultList();
    }

    public List<UserEntity> retrieveAllInsolventusers(){
        return em.createNamedQuery("User.retrieveInsolventUser", UserEntity.class)
                .getResultList();
    }

    public List<OrderEntity> retrieveAllPendingOrders(){
        return em.createNamedQuery("Order.retrievePendingOrder", OrderEntity.class)
                .getResultList();
    }

    public List<ErrorEntity> retrieveAllErrors(){
        return em.createNamedQuery("Error.findAll", ErrorEntity.class)
                .getResultList();
    }

    public BestOptionalProduct retrieveBestOptionalProduct(){
        BestOptionalProduct bestOP = null;
        try {
            if(em.createNamedQuery("BestOptionalProduct.retrieveBest", BestOptionalProduct.class)
                    .getResultList().size() != 0)
                bestOP = em.createNamedQuery("BestOptionalProduct.retrieveBest", BestOptionalProduct.class).getSingleResult();
        }catch(NoResultException ignored){}

        return bestOP;
    }
}
