package shop.shopnoutbook.dao;

import shop.shopnoutbook.domain.FirmProduce;
import shop.shopnoutbook.domain.Processor;
import shop.shopnoutbook.domain.Store;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Интерфейс
 */
public interface GenericDao<T> {
    void create(T persistentObject);
    T read(Long id);
    void update(T persistentObject, T persistentObjectNew);
    void delete(T persistentObject);
    List<T> findAll();
    public void addOnStoreBatch(List<T> persistentObject, int quality, BigDecimal price);
    public void sallFromStoreQuantity(T persistentObject, int quality);
    public void deleteFromStoreNoutbook(long id, int quantity) ;
    public BigDecimal volumeSallByDay(Date date);
    public List<shop.shopnoutbook.domain.Store> selectMaxQuantity(int quantity);
    public List<Processor> selectByFirmProduceProcessor(FirmProduce firmProduce);
    public List<Store> selectByFirmProduce(FirmProduce firmProduce);
}
