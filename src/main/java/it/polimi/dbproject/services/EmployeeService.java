package it.polimi.dbproject.services;

import it.polimi.dbproject.entities.*;
import it.polimi.dbproject.entities.queries.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;
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

    public List<UserEntity> retrieveAllInsolventUsers(){
        return em.createNamedQuery("User.retrieveInsolventUsers", UserEntity.class)
                .getResultList();
    }

    public List<OrderEntity> retrieveAllPendingOrders(){
        return em.createNamedQuery("Order.retrieveAllPendingOrders", OrderEntity.class)
                .getResultList();
    }

    public List<ErrorEntity> retrieveAllErrors(){
        return em.createNamedQuery("Error.findAll", ErrorEntity.class)
                .getResultList();
    }

    public BestOptionalService retrieveBestOptionalProduct(){
        try {
            return em.createNamedQuery("BestOptionalService.retrieveBest", BestOptionalService.class)
                    .getResultList().stream().findFirst().get();
        }catch(NoResultException ignored){
            return null;
        }

    }

    public List<PeriodEntity> retrieveAllPeriods(){
        return em.createNamedQuery("Period.findAll", PeriodEntity.class)
                .getResultList();
    }

    public Optional<AvailableServicePackEntity> retrieveAvailablePackageThroughID(int servicePackId) {
        return em.createNamedQuery("AvailableServicePackage.findByID", AvailableServicePackEntity.class)
                .setParameter("availableServicePackId", servicePackId)
                .getResultStream().findFirst();
    }

    public AVG_numOptionServPerServPack retrieveAverageOptionalProductsPerPackage (int package_id) {

        try {
            return em.createNamedQuery("AVG_numOptionServPerServPack.findByPackageId", AVG_numOptionServPerServPack.class)
                    .setParameter("package_id", package_id)
                    .getResultList().stream().findFirst().get();
        } catch (NoSuchElementException e) {
            return new AVG_numOptionServPerServPack(package_id, retrieveAvailablePackageThroughID(package_id).get());
        }

    }

    public SalesPerPackage retrieveSalesPerPackage(int package_id) {
        try{
            return em.createNamedQuery("SalesPerPackage.retrieveByPackageId", SalesPerPackage.class)
                    .setParameter("package_id", package_id)
                    .getResultList().stream().findFirst().get();
        }catch (NoSuchElementException exception){
            return new SalesPerPackage(package_id, retrieveAvailablePackageThroughID(package_id).get());
        }
    }

    public Optional<PeriodEntity> retrievePeriodById(int period_id) {
        return em.createNamedQuery("Period.findPeriodThroughID", PeriodEntity.class)
                .setParameter("periodId", period_id)
                .getResultStream().findFirst();
    }


    public PurchasesPerPackageAndPeriod retrievePurchasesPerPackageAndPeriod(int package_id, int period_id){
        try{
            return em.createNamedQuery("PurchasesPerPackageAndPeriod.retrieveByPackageAndPeriod", PurchasesPerPackageAndPeriod.class)
                    .setParameter("package_id", package_id)
                    .setParameter("period_id", period_id)
                    .getResultList().stream().findFirst().get();
        }catch (NoSuchElementException exception){
            AvailableServicePackEntity pack = retrieveAvailablePackageThroughID(package_id).get();
            System.out.println("il pack "+ pack);
            PeriodEntity period = retrievePeriodById(period_id).get();
            System.out.println("il period "+period);
            return new PurchasesPerPackageAndPeriod(package_id, pack, period_id, period);
        }
    }

    public PurchasesPerPackage purchasesPerPackage(int package_id){
        try {
            return em.createNamedQuery("PurchasesPerPackageEntity.retrievePurchasesByPackId", PurchasesPerPackage.class)
                    .setParameter("package_id", package_id).getResultList().stream().findFirst().get();
        } catch (NoSuchElementException exception){
            return new PurchasesPerPackage(package_id, retrieveAvailablePackageThroughID(package_id).get());
        }
    }
}
