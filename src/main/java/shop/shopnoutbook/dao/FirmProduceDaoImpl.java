package shop.shopnoutbook.dao;

import shop.shopnoutbook.domain.FirmProduce;
import shop.shopnoutbook.domain.Processor;
import shop.shopnoutbook.domain.Store;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Класс реализация для производителя
 */
@Repository
public class FirmProduceDaoImpl implements GenericDao<FirmProduce> {
    private static Logger log = Logger.getLogger(FirmProduceDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public FirmProduceDaoImpl(){}

    public FirmProduceDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(FirmProduce firmProduce) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.save(firmProduce);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public FirmProduce read(Long id) {
        return null;
    }

    @Override
    public void update(FirmProduce firmProduce, FirmProduce firmProduceNew) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            List<FirmProduce> firmProduceList = session.createCriteria(FirmProduce.class).list();
            System.out.println(firmProduceList);
            for(FirmProduce elem : firmProduceList){
                if(elem.equals(firmProduce)) {
                    elem.setNameFirm(firmProduceNew.getNameFirm());
                    session.save(elem);
                }
            }
            session.getTransaction().commit();
        }catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(FirmProduce persistentObject) {

    }

    @Override
    public List<FirmProduce> findAll() {
        Session session = factory.openSession();
        return session.createCriteria(FirmProduce.class).list();
    }

    @Override
    public void addOnStoreBatch(List<FirmProduce> persistentObject, int quality, BigDecimal price) {

    }

    @Override
    public void sallFromStoreQuantity(FirmProduce persistentObject, int quality) {

    }

    @Override
    public void deleteFromStoreNoutbook(long id, int quantity) {

    }

    @Override
    public BigDecimal volumeSallByDay(Date date) {
        return null;
    }

    @Override
    public List<Store> selectMaxQuantity(int quantity) {
        return null;
    }

    @Override
    public List<Processor> selectByFirmProduceProcessor(FirmProduce firmProduce) {
        return null;
    }

    @Override
    public List<Store> selectByFirmProduce(FirmProduce firmProduce) {
        return null;
    }
}
