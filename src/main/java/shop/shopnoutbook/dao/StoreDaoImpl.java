package shop.shopnoutbook.dao;

import shop.shopnoutbook.domain.FirmProduce;
import shop.shopnoutbook.domain.Processor;
import shop.shopnoutbook.domain.Store;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Класс реализация для склада
 */
@Repository
public class StoreDaoImpl implements GenericDao<Store> {
    private static Logger log = Logger.getLogger(StoreDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public StoreDaoImpl(){}

    public StoreDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Store persistentObject) {

    }

    @Override
    public Store read(Long id) {
        return null;
    }

    @Override
    public void update(Store persistentObject, Store persistentObjectNew) {

    }

    @Override
    public void delete(Store persistentObject) {

    }

    @Override
    public List<Store> findAll() {
        Session session = factory.openSession();
        return session.createCriteria(Store.class).list();
    }

    @Override
    public void addOnStoreBatch(List<Store> persistentObject, int quality, BigDecimal price) {

    }

    @Override
    public void sallFromStoreQuantity(Store persistentObject, int quality) {

    }

    @Override
    public void deleteFromStoreNoutbook(long id, int quantity) {
        Session session = factory.openSession();
        try {
            Store store = (Store) session.load(Store.class, id);
            if (store.getQuantity() > quantity) {
                int beQuantity = store.getQuantity();
                int res = beQuantity - quantity;
                store.setQuantity(res);
            } else {
                System.out.println("нет такого колличества ноутов, в наличии  "+store.getQuantity());
            }
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public BigDecimal volumeSallByDay(Date date) {
        return null;
    }

    @Override
    public List<Store> selectMaxQuantity(int quantity) {
        Session session = factory.openSession();
        return session.createCriteria(Store.class).add(Restrictions.ge("quantity", quantity)).list();
    }

    @Override
    public List<Processor> selectByFirmProduceProcessor(FirmProduce firmProduce) {

        return null;
    }

    @Override
    public List<Store> selectByFirmProduce(FirmProduce firmProduce) {
        Session session = factory.openSession();
        session.saveOrUpdate(firmProduce);
        return session.createCriteria(Store.class).createCriteria("typeNoutbook").createCriteria("firmProduce")
                .add(Restrictions.eq("nameFirm",firmProduce.getNameFirm())).list();
    }


}
