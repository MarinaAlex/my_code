package shop.shopnoutbook.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.shopnoutbook.dao.*;
import shop.shopnoutbook.domain.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Класс реализация для магазина
 *
 */
@Service
public class ShopDaoImpl implements ShopDao {
    private static Logger log = Logger.getLogger(ShopDaoImpl.class);

    private FirmProduceDaoImpl producerDao;
    private MemoryDaoImpl memoryDao;
    private NoutbookDaoImpl noutDao;
    private ProcessorDaoImpl processorDao;
    private SallDaoImpl sallDao;
    private StoreDaoImpl storeDao;



    public ShopDaoImpl(){

    }
    @Autowired
    public ShopDaoImpl(FirmProduceDaoImpl producerDao, MemoryDaoImpl memoryDao, NoutbookDaoImpl noutDao,
                       ProcessorDaoImpl processorDao, SallDaoImpl sallDao, StoreDaoImpl storeDao) {
        this.producerDao = producerDao;
        this.memoryDao = memoryDao;
        this.noutDao = noutDao;
        this.processorDao = processorDao;
        this.sallDao = sallDao;
        this.storeDao = storeDao;
    }




    @Override
    public void createProcessor(Processor processor) {
        processorDao.create(processor);
    }

    @Override
    public void createMemory(Memory memory) {
        memoryDao.create(memory);
    }

    @Override
    public void createFirmProduce(FirmProduce firmProduce) {
        producerDao.create(firmProduce);
    }

    @Override
    public void createTypeNoutbook(TypeNoutbook typeNoutbook) {
        noutDao.create(typeNoutbook);
    }

    @Override
    public void addOnStoreBatch(List<TypeNoutbook> typeNoutbook, int quantity, BigDecimal price) {
        noutDao.addOnStoreBatch(typeNoutbook, quantity,price);
    }

    @Override
    public void sallFromStoreQuantity(TypeNoutbook noutbook, int quantity) {
        noutDao.sallFromStoreQuantity(noutbook, quantity);

    }

    @Override
    public void changeProcessor(Processor processor, Processor processorNew) {
        processorDao.update(processor, processorNew);
    }

    @Override
    public void changeMemory(Memory memory, Memory memoryNew) {
        memoryDao.update(memory, memoryNew);
    }

    @Override
    public void changeFirmProduce(FirmProduce firmProduce, FirmProduce firmProduceNew) {
            producerDao.update(firmProduce, firmProduceNew);
    }

    @Override
    public void changeTypeNoutbook(TypeNoutbook typeNoutbook, TypeNoutbook typeNoutbookNew) {
            noutDao.update(typeNoutbook, typeNoutbookNew);
    }

    @Override
    public void deleteFromStoreNoutbook(long id, int quantity) {
        storeDao.deleteFromStoreNoutbook( id, quantity);
    }

    @Override
    public List<Store> selectAll() {
        return storeDao.findAll();
    }

    @Override
    public List<Store> selectMaxQuantity(int quantity) {

        return processorDao.selectMaxQuantity(quantity);
    }

    @Override
    public List<Processor> selectByFirmProduceProcessor(FirmProduce firmProduce) {

        return processorDao.selectByFirmProduceProcessor(firmProduce);
    }

    @Override
    public List<Store> selectByFirmProduce(FirmProduce firmProduce) {

        return storeDao.selectByFirmProduce(firmProduce);
    }

    @Override
    public BigDecimal volumeSallByDay(Date date) {
        return  sallDao.volumeSallByDay(date);
    }



}
