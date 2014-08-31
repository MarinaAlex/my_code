package shop.shopnoutbook.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.shopnoutbook.domain.FirmProduce;
import shop.shopnoutbook.domain.Processor;
import shop.shopnoutbook.domain.Sall;
import shop.shopnoutbook.domain.Store;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Класс реализация для продаж
 */
@Repository
public class SallDaoImpl implements GenericDao<Sall> {
    private static Logger log = Logger.getLogger(SallDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public SallDaoImpl(){}

    public SallDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Sall persistentObject) {

    }

    @Override
    public Sall read(Long id) {
        return null;
    }

    @Override
    public void update(Sall persistentObject, Sall persistentObjectNew) {

    }

    @Override
    public void delete(Sall persistentObject) {

    }

    @Override
    public List<Sall> findAll() {
        Session session = factory.openSession();
        return session.createCriteria(Sall.class).list();
    }

    @Override
    public void addOnStoreBatch(List<Sall> persistentObject, int quality, BigDecimal price) {

    }

    @Override
    public void sallFromStoreQuantity(Sall persistentObject, int quality) {

    }

    @Override
    public void deleteFromStoreNoutbook(long id, int quantity) {

    }

    @Override
    public BigDecimal volumeSallByDay(Date date) {
        BigDecimal volume = null;
        BigDecimal result = null;
        Session session = factory.openSession();
        try{
            List<Sall> salls = session.createCriteria(Sall.class).add(Restrictions.eq("dateSall", date)).list();
            System.out.println(salls.toString());
            for (int i = 0; i< salls.size(); i++){
                volume =  salls.get(i).getStore().get(i).getPrice();
                int quantSall = salls.get(i).getQuantity();
                BigDecimal quantRes = new BigDecimal(quantSall);
                result = volume.multiply(quantRes);
            }
        }catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
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
