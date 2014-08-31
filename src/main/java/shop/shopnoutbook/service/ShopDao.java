package shop.shopnoutbook.service;

import shop.shopnoutbook.domain.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Интерфейс магазинa
 */
public interface ShopDao {
    public void createProcessor(Processor processor);
    public void createMemory(Memory memory);
    public void createFirmProduce(FirmProduce firmProduce);
    public void createTypeNoutbook(TypeNoutbook typeNoutbook);
    public void addOnStoreBatch(List<TypeNoutbook> typeNoutbook, int quantity, BigDecimal price);
    public void sallFromStoreQuantity(TypeNoutbook noutbook, int quantity);
    public void changeProcessor(Processor processor, Processor processorNew);
    public void changeMemory(Memory memory, Memory memoryNew);
    public  void changeFirmProduce(FirmProduce firmProduce, FirmProduce firmProduceNew);
    public void changeTypeNoutbook(TypeNoutbook typeNoutbook, TypeNoutbook typeNoutbookNew);
    public void deleteFromStoreNoutbook(long id, int quantity);
    public List<Store> selectAll();
    public List<Store> selectMaxQuantity(int quantity);
    public List<Processor> selectByFirmProduceProcessor(FirmProduce firmProduce);
    public List<Store> selectByFirmProduce(FirmProduce firmProduce);
    public BigDecimal volumeSallByDay(Date date);
}
