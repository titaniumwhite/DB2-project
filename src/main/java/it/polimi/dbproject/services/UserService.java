package it.polimi.dbproject.services;

import it.polimi.dbproject.entities.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.Order;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    public List<PeriodEntity> retrieveAllPeriods(){
        return em.createNamedQuery("Period.findAll", PeriodEntity.class)
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

    public List<OptionalServiceEntity> retrieveAllOptionalServices(){
        return em.createNamedQuery("OptionalService.findAll", OptionalServiceEntity.class)
                .getResultList();
    }

    public List<AvailableServicePackEntity> retrieveAllAvailableService(){
        return em.createNamedQuery("AvailableServicePackage.findAll", AvailableServicePackEntity.class)
                .getResultList();
    }

    public List<OrderEntity> retrieveAllOrdersOfUser(int userId){
        List<OrderEntity> orders = em.createNamedQuery("Order.retrieveAllUserOrderThroughID", OrderEntity.class)
                .setParameter("user", retrieveUserThroughID(userId).get())
                .getResultList();

        return orders;
    }

    public Optional<UserEntity> retrieveUserThroughID(int userId){
        return em.createNamedQuery("User.retrieveUserThroughID", UserEntity.class)
                .setParameter("id", userId)
                .getResultStream().findFirst();
    }

    public boolean randomPayment(){
        Random rd = new Random();
        return rd.nextBoolean();
    }

    public ServicePackEntity addressServiceToUser(ServicePackEntity servicePack, UserEntity user) throws SQLException{
        servicePack.setUser(user);
        try {
            em.persist(servicePack);
            em.flush();
            return servicePack;
        } catch (ConstraintViolationException ignored){
            return null;
        }
    }

    public OrderEntity createOrder(Timestamp ts, UserEntity user, ServicePackEntity sp, boolean isPlaceable){
        int cost = sp.getTotalCost() + sp.getOptionalServicesFee();
        OrderEntity order = new OrderEntity(ts, cost, user, sp);
        order.setPlaceable(isPlaceable);

        try {
            em.persist(order);
            em.flush();
            return order;
        } catch (ConstraintViolationException ignored) {
            return null;
        }
    }

    public Optional<OrderEntity> retrieveOrderThroughID(int orderId){
        return em.createNamedQuery("Order.retrieveThroughID", OrderEntity.class)
                .setParameter("orderId", orderId)
                .getResultStream().findFirst();
    }

    public OrderEntity orderUpdate(OrderEntity order, boolean isPlaceable){
        OrderEntity order1 = em.find(OrderEntity.class, order.getOrder_id());
        order1.setPlaceable(isPlaceable);
        em.merge(order1);
        return order1;
    }

    public UserEntity addFailedPayment(UserEntity user){
        UserEntity user1 = em.find(UserEntity.class, user.getUser_id());
        user1.incrementFailedPayments();
        em.merge(user1);
        return user1;
    }

    public UserEntity setFailedPayments(UserEntity user){
        UserEntity user1 = em.find(UserEntity.class, user.getUser_id());
        user1.setFailedPayments(0);
        em.merge(user1);
        return user1;
    }

    public List<OrderEntity> retrieveFailedOrderthroughUser(int userId){
        UserEntity user = retrieveUserThroughID(userId).get();
        return em.createNamedQuery("Order.retrieveFailedUserOrder", OrderEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    public void userIsInsolvent(UserEntity user, boolean isInsolvent){
        UserEntity user1 = em.find(UserEntity.class, user.getUser_id());
        user1.setInsolvent(isInsolvent);
        em.merge(user1);
    }

    public List<OrderEntity> retrievePendingOrder(int userId){
        UserEntity user = retrieveUserThroughID(userId).get();
        return em.createNamedQuery("Order.retrievePendingOrder", OrderEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    public ServicePackEntity createServicePack (ServicePackEntity servicePack, UserEntity user) throws SQLException {
        servicePack.setUser(user);
        try{
            em.persist(servicePack);
            em.flush();
            return servicePack;
        } catch (ConstraintViolationException ignored) {
            return null;
        }
    }
}
