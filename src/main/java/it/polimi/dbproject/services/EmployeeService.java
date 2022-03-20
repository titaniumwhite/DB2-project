package it.polimi.dbproject.services;

import it.polimi.dbproject.entities.*;
import it.polimi.dbproject.entities.queries.*;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
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

    public List<InsolventUsers> retrieveAllInsolventUsers(){
        return em.createNamedQuery("InolventUsers.retrieveInsolventUser", InsolventUsers.class)
                .getResultList();
    }

    public List<PendingOrders> retrieveAllPendingOrders(){
        return em.createNamedQuery("PendingOrders.retrievePendingOrder", PendingOrders.class)
                .getResultList();
    }

    public List<Errors> retrieveAllErrors(){
        return em.createNamedQuery("Errors.retrieveAll", Errors.class)
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

    public List<PeriodEntity> retrieveAllPeriods(){
        return em.createNamedQuery("Period.findAll", PeriodEntity.class)
                .getResultList();
    }

    public Optional<ServicePackEntity> retrievePackageThroughID(int servicePackId) {
        return em.createNamedQuery("ServicePack.retrievePackageThroughID", ServicePackEntity.class)
                .setParameter("servicePackId", servicePackId)
                .getResultStream().findFirst();
    }

    public AVG_numOptionServPerServPack retrieveAverageOptionalProductsPerPackage (int package_id) {

        try {
            return em.createNamedQuery("AVG_numOptionServPerServPack.findByPackageId", AVG_numOptionServPerServPack.class)
                    .setParameter("package_id", package_id)
                    .getResultList().stream().findFirst().get();
        } catch (NoSuchElementException e) {
            return new AVG_numOptionServPerServPack(package_id, retrievePackageThroughID(package_id).get());
        }

    }

    public SalesPerPackage retrieveSalesPerPackage(int package_id) {
        try{
            return em.createNamedQuery("SalesPerPackage.retrieveByPackageId", SalesPerPackage.class)
                    .setParameter("package_id", package_id)
                    .getResultList().stream().findFirst().get();
        }catch (NoSuchElementException exception){
            return new SalesPerPackage(package_id, retrievePackageThroughID(package_id).get());
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
            ServicePackEntity pack = retrievePackageThroughID(package_id).get();
            System.out.println("il pack "+ pack);
            PeriodEntity period = retrievePeriodById(period_id).get();
            System.out.println("il period "+period);
            return new PurchasesPerPackageAndPeriod(package_id, pack, period_id, period);
        }
    }

    public PurchasesPerPackageEntity purchasesPerPackage(int package_id){
        try {
            return em.createNamedQuery("PurchasesPerPackageEntity.retrievePurchasesByPackId", PurchasesPerPackageEntity.class)
                    .setParameter("package_id", package_id).getResultList().stream().findFirst().get();
        } catch (NoSuchElementException exception){
            ServicePackEntity pack = retrievePackageThroughID(package_id).get();
            System.out.println("il pack è " + pack);
            return new PurchasesPerPackageEntity(package_id, pack);
        }
    }

}
