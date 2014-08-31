package shop.shopnoutbook.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.shopnoutbook.domain.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Класс реализация для типа ноутбуков
 */
@Repository
public class NoutbookDaoImpl implements GenericDao<TypeNoutbook> {
    private static Logger log = Logger.getLogger(NoutbookDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public NoutbookDaoImpl(){}

    public NoutbookDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(TypeNoutbook typeNoutbook) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(typeNoutbook.getFirmProduce());
            session.save(typeNoutbook);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public TypeNoutbook read(Long id) {
        return null;
    }

    @Override
    public void update(TypeNoutbook typeNoutbook, TypeNoutbook typeNoutbookNew) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            List<TypeNoutbook> typeNoutbookList = session.createCriteria(TypeNoutbook.class).list();
            System.out.println(typeNoutbookList);
            for(TypeNoutbook elem : typeNoutbookList){
                if(elem.equals(typeNoutbook)){
                    elem.setFirmProduce(typeNoutbookNew.getFirmProduce());
                    session.saveOrUpdate(typeNoutbookNew.getFirmProduce());
                    elem.setSeriaNomber(typeNoutbookNew.getSeriaNomber());
                    elem.setDateProduce(typeNoutbookNew.getDateProduce());
                    elem.setModel(typeNoutbookNew.getModel());
                    session.saveOrUpdate(elem);

                }
            }
            session.beginTransaction().commit();
        }catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(TypeNoutbook typeNoutbook) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(typeNoutbook);
            session.getTransaction().commit();

        }catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public List<TypeNoutbook> findAll() {
        Session session = factory.openSession();
        return session.createCriteria(TypeNoutbook.class).list();
    }

    @Override

        public void addOnStoreBatch(List<TypeNoutbook> typeNoutbook, int quantity, BigDecimal price) {
            Session session = factory.openSession();
            try{
                session.beginTransaction();
                Store s = new Store();
                s.setTypeNoutbook(typeNoutbook);
                session.saveOrUpdate(typeNoutbook.get(0));
                session.saveOrUpdate(typeNoutbook.get(0).getFirmProduce());
                s.setQuantity(quantity);
                s.setPrice(price);
                session.save(s);
                session.beginTransaction().commit();
            }catch (HibernateException e) {
                log.error("Transaction failed");
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }

    @Override
    public void sallFromStoreQuantity(TypeNoutbook noutbook, int quantity) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();

            List<Store> stores = session.createCriteria(Store.class)
                    .createCriteria("typeNoutbook")
                    .add(Restrictions.eq("seriaNomber", noutbook.getSeriaNomber()))
                    .add(Restrictions.eq("model", noutbook.getModel())).list();
            System.out.println(stores.toString());
            Sall sall = new Sall();

            Date date = new Date( (new java.util.Date()).getTime());
            if (stores.get(0).getQuantity()>=quantity){
                int res= stores.get(0).getQuantity()- quantity;
                stores.get(0).setQuantity(res);
                sall.setQuantity(quantity);
                sall.setStore(stores);
                sall.setDateSall(date);
            }else {
                System.out.println("на складе нет данного колличества");
            }
            session.save(sall);
            session.beginTransaction().commit();
        }catch (HibernateException e) {
            log.error("Transaction failed");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
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

