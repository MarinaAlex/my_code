package shop.shopnoutbook.view;

import shop.shopnoutbook.domain.FirmProduce;
import shop.shopnoutbook.domain.Memory;
import shop.shopnoutbook.domain.Processor;
import shop.shopnoutbook.domain.TypeNoutbook;
import shop.shopnoutbook.service.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
@Component
public class Shop {

    private ShopDao shopDao1;
    private ShopDao shopDao2;
    private ShopDao shopDao3;
    private ShopDao shopDao4;
    private ShopDao shopDao5;
    private ShopDao shopDao6;




    @Autowired
    public Shop(ShopDao shopDao1, ShopDao shopDao2, ShopDao shopDao3, ShopDao shopDao4, ShopDao shopDao5, ShopDao shopDao6) {
        this.shopDao1 = shopDao1;
        this.shopDao2 = shopDao2;
        this.shopDao3 = shopDao3;
        this.shopDao4 = shopDao4;
        this.shopDao5 = shopDao5;
        this.shopDao6 = shopDao6;
    }
    public Shop(){

    }

    public Date changeDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dat_sql = null;
        try {

            dat_sql = new Date(format.parse(date).getTime());

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return dat_sql;
    }
    @PostConstruct
    public void start() {

        Shop shop = new Shop();

        while(true){
            System.out.println("Сделайте выбор:"+
                    "\n 1. Добавить процессор" +
                    "\n 2. Добавить память" +
                    "\n 3. Добавить производителя" +
                    "\n 4. Добавить тип ноутбука" +
                    "\n 5. Добавить на склад партию ноутов"+
                    "\n 6. Продать со склада указанное кол-во ноутбуков"+
                    "\n 7. Изменить процессор"+
                    "\n 8. Изменить память"+
                    "\n 9. Изменить производителя"+
                    "\n 10. Изменить тип ноута"+
                    "\n 11. Списать со склада указанное кол-во ноутбуков"+
                    "\n 12. Показать все ноутбуки на складе"+
                    "\n 13. Показать ноуты у которых кол-во больше указанного"+
                    "\n 14. Показать все по производителю процессра"+
                    "\n 15. Остатки по производителю"+
                    "\n 16. Объем продаж по дню"+
                    "\n 17. Выход"
            );
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            if (a == 1) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce firmProduce = new FirmProduce(nameProduce);
                System.out.println("Введите частоту");
                Double fr = sc.nextDouble();
                System.out.println("Введите модель");
                String model = sc.next();
                Processor processor = new Processor(firmProduce,fr,model);
                shopDao4.createProcessor(processor);
            }else if(a == 2) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce firmProduce = new FirmProduce(nameProduce);
                System.out.println("Введите размер памяти");
                Double vol = sc.nextDouble();
                Memory memory = new Memory(firmProduce, vol);
                shopDao2.createMemory(memory);
            }else if (a==3) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce firmProduce = new FirmProduce(nameProduce);
                shopDao1.createFirmProduce(firmProduce);
            }else if(a==4) {
                System.out.println("Введите дату");
                String d = sc.next();
                Date date = shop.changeDate(d);
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce firmProduce = new FirmProduce(nameProduce);
                System.out.println("Введите серийный номер ноутбука");
                String serNum = sc.next();
                System.out.println("Введите модель ноутбука");
                String model = sc.next();
                TypeNoutbook tn = new TypeNoutbook(serNum, firmProduce, model, date);
                shopDao3.createTypeNoutbook(tn);
            }else if (a==5) {
                System.out.println("Введите дату");
                String d = sc.next();
                Date date = shop.changeDate(d);
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce firmProduce = new FirmProduce(nameProduce);
                List<TypeNoutbook> t = new ArrayList<TypeNoutbook>();
                System.out.println("Введите серийный номер ноутбука");
                String serNum = sc.next();
                System.out.println("Введите модель ноутбука");
                String model = sc.next();
                t.add(new TypeNoutbook(serNum, firmProduce, model, date));
                System.out.println("Укажите кол-во");
                int quant = sc.nextInt();
                System.out.println("Укажите цену");
                Double price = sc.nextDouble();
                shopDao3.addOnStoreBatch(t, quant, new BigDecimal(price));
            }else if(a== 6) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce firmProduce = new FirmProduce(nameProduce);
                System.out.println("Введите дату");
                String d = sc.next();
                Date d11 = shop.changeDate(d);
                System.out.println("Введите серийный номер ноутбука");
                String serNum = sc.next();
                System.out.println("Введите модель ноутбука");
                String model = sc.next();
                TypeNoutbook typeNoutbook = new TypeNoutbook(serNum, firmProduce, model, d11);
                System.out.println("Укажите кол-во");
                int quant = sc.nextInt();
                shopDao3.sallFromStoreQuantity(typeNoutbook, quant);
            }else if (a==7) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce f = new FirmProduce(nameProduce);
                System.out.println("Введите частоту");
                Double fr = sc.nextDouble();
                System.out.println("Введите модель");
                String model = sc.next();
                Processor processor = new Processor(f, fr, model);
                System.out.println("Введите  новое имя производителя");
                String nameProduceNew = sc.next();
                FirmProduce fn = new FirmProduce(nameProduceNew);
                System.out.println("Введите новую частоту");
                Double frNew = sc.nextDouble();
                System.out.println("Введите новую модель");
                String modelNew = sc.next();
                Processor processorNew = new Processor(fn, frNew, modelNew);
                shopDao4.changeProcessor(processor, processorNew);
            }else if(a==8) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce f2 = new FirmProduce(nameProduce);
                System.out.println("Введите размер памяти");
                Double vol = sc.nextDouble();
                Memory memory = new Memory(f2, vol);
                System.out.println("Введите  новое имя производителя");
                String nameProduceNew = sc.next();
                FirmProduce f3 = new FirmProduce(nameProduceNew);
                System.out.println("Введите новый размер памяти");
                Double volNew = sc.nextDouble();
                Memory memory1 = new Memory(f3, volNew);
                shopDao2.changeMemory(memory, memory1);
            }else if(a==9) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                System.out.println("Введите  новое имя производителя");
                String nameProduceNew = sc.next();
                FirmProduce f4 = new FirmProduce(nameProduce);
                FirmProduce f5 = new FirmProduce(nameProduceNew);
                shopDao1.changeFirmProduce(f4, f5);
            }else if(a==10) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce f6 = new FirmProduce(nameProduce);
                System.out.println("Введите дату");
                String d = sc.next();
                Date d10 = shop.changeDate(d);
                System.out.println("Введите серийный номер ноутбука");
                String serNum = sc.next();
                System.out.println("Введите модель ноутбука");
                String model = sc.next();
                TypeNoutbook typeNoutbook = new TypeNoutbook(serNum, f6, model, d10);
                System.out.println("Введите  новое имя производителя");
                String nameProduceNew = sc.next();
                FirmProduce f7 = new FirmProduce(nameProduceNew);
                System.out.println("Введите новую дату");
                String dNew = sc.next();
                Date d11 = shop.changeDate(dNew);
                System.out.println("Введите новый серийный номер ноутбука");
                String serNumNew = sc.next();
                System.out.println("Введите новую модель ноутбука");
                String modelNew = sc.next();
                TypeNoutbook tNew = new TypeNoutbook(serNumNew, f7, modelNew, d11);
                shopDao6.changeTypeNoutbook(typeNoutbook, tNew);
            }else if(a==11) {
                System.out.println("Введите ключ");
                long key = sc.nextLong();
                System.out.println("Введите кол-во");
                int quantity = sc.nextInt();
                shopDao6.deleteFromStoreNoutbook(key, quantity);
            }else if (a== 12) {
                System.out.println(shopDao6.selectAll());
            }else if(a==13) {
                System.out.println("Введите кол-во");
                int quantity = sc.nextInt();
                System.out.println(shopDao4.selectMaxQuantity(quantity));
            }else if(a==14) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce firmProduce = new FirmProduce(nameProduce);
                System.out.println(shopDao4.selectByFirmProduceProcessor(firmProduce));
            }else  if(a== 15) {
                System.out.println("Введите имя производителя");
                String nameProduce = sc.next();
                FirmProduce firmProduce = new FirmProduce(nameProduce);
                System.out.println(shopDao6.selectByFirmProduce(firmProduce));
            }else if(a==16){
                System.out.println("Введите дату");
                String d = sc.next();
                Date d12 = shop.changeDate(d);
                System.out.println(shopDao5.volumeSallByDay(d12));
            }else if(a == 17){
                break;
            }
        }
    }
}
