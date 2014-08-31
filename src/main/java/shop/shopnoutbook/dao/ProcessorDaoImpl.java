package shop.shopnoutbook.dao;

import shop.shopnoutbook.domain.FirmProduce;
import shop.shopnoutbook.domain.Processor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.shopnoutbook.domain.Store;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализация для процессоров
 */
@Repository
public class ProcessorDaoImpl implements GenericDao<Processor> {
    private static Logger log = Logger.getLogger(ProcessorDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public ProcessorDaoImpl(){}

    public ProcessorDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Processor processor) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate( processor.getFirmProduce());
            session.save(processor);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
           log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }


    }

    @Override
    public Processor read(Long id) {
        return null;
    }

    @Override
    public void update(Processor processor, Processor processorNew) {
        Session session = factory.openSession();
        try{
        session.beginTransaction();

        session.saveOrUpdate(processor.getFirmProduce());

        Processor processor1 =(Processor)session.createCriteria(Processor.class)
                .add(Restrictions.eq("frequency", processor.getFrequency()))
                .add(Restrictions.eq("model", processor.getModel())).uniqueResult();
        System.out.println(processor1.toString());

        session.saveOrUpdate(processorNew.getFirmProduce());
        processor1.setFirmProduce(processorNew.getFirmProduce());
        processor1.setFrequency(processorNew.getFrequency());
        processor1.setModel(processorNew.getModel());
        session.save(processor1);

        session.getTransaction().commit();
    }catch (HibernateException e) {
        log.error("Transaction failed");
        session.getTransaction().rollback();
    } finally {
        session.close();
    }
    }

    @Override
    public void delete(Processor processor) {

    }

    @Override
    public List<Processor> findAll() {
        Session session = factory.openSession();
        return session.createCriteria(Processor.class).list();
    }

    @Override
    public void addOnStoreBatch(List<Processor> persistentObject, int quality, BigDecimal price) {

    }

    @Override
    public void sallFromStoreQuantity(Processor persistentObject, int quality) {

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
        Session session = factory.openSession();
        session.saveOrUpdate(firmProduce);

        List<Processor> res = new ArrayList<>();
        List <Processor> processors = session.createCriteria(Processor.class).list();
        System.out.println(processors.toString());
        for (int i = 0; i <processors.size() ; i++) {
            if(processors.get(i).getFirmProduce().equals(firmProduce)){
              res.add(i, processors.get(i));
            }
        }
        return res;
    }

    @Override
    public List<Store> selectByFirmProduce(FirmProduce firmProduce) {
        return null;
    }
}
