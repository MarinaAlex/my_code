package shop.shopnoutbook.dao;

import shop.shopnoutbook.domain.FirmProduce;
import shop.shopnoutbook.domain.Memory;
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
 * Класс реализация для памяти
 */
@Repository
public class MemoryDaoImpl implements GenericDao<Memory> {
    private static Logger log = Logger.getLogger(MemoryDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public MemoryDaoImpl(){}

    public MemoryDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Memory memory) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(memory.getFirmProduce());
            session.save(memory);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public Memory read(Long id) {
        return null;
    }

    @Override
    public void update(Memory memory, Memory memoryNew) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            List<Memory> memoryList = session.createCriteria(Memory.class).list();
            System.out.println(memoryList.toString());
            for( Memory elem : memoryList){
                if(elem.equals(memory)){
                    elem.setFirmProduce(memoryNew.getFirmProduce());
                    session.saveOrUpdate(memoryNew.getFirmProduce());
                    elem.setSize(memoryNew.getSize());
                    session.update(elem);
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
    public void delete(Memory persistentObject) {

    }

    @Override
    public List<Memory> findAll() {
        Session session = factory.openSession();
        return session.createCriteria(Memory.class).list();
    }

    @Override
    public void addOnStoreBatch(List<Memory> persistentObject, int quality, BigDecimal price) {

    }

    @Override
    public void sallFromStoreQuantity(Memory persistentObject, int quality) {

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
