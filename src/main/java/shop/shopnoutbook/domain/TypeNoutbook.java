package shop.shopnoutbook.domain;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "TYPE_NOUTBOOKS")
public class TypeNoutbook {
    @Id
    @GeneratedValue
    @Column(name="TYPE_NOUTBOOK_ID")
    private Long id;


    @ManyToOne
    private FirmProduce firmProduce;

    @Column(name = "SERIA_NUMBER")
    private String seriaNomber;
    @Column( name = "MODEL")
    private String model;

    @Column(name = "PRODUCE_DATE")
    private Date dateProduce;

    public TypeNoutbook(){

    }

    public TypeNoutbook(String seriaNomber, FirmProduce firmProduce, String model, Date dateProduce) {
        this.seriaNomber = seriaNomber;
        this.firmProduce = firmProduce;
        this.model = model;
        this.dateProduce = dateProduce;
    }

    public String getSeriaNomber() {
        return seriaNomber;
    }

    public void setSeriaNomber(String seriaNomber) {
        this.seriaNomber = seriaNomber;
    }

    public FirmProduce getFirmProduce() {
        return firmProduce;
    }

    public void setFirmProduce(FirmProduce firmProduce) {
        this.firmProduce = firmProduce;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDateProduce() {
        return dateProduce;
    }

    public void setDateProduce(Date dateProduce) {
        this.dateProduce = dateProduce;
    }

    @Override
    public String toString() {
        return "TypeNoutbook{" +
                "seriaNomber='" + seriaNomber + '\'' +
                ", firmProduce='" + firmProduce + '\'' +
                ", model='" + model + '\'' +
                ", dateProduce=" + dateProduce +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeNoutbook typeNoutbook = (TypeNoutbook) o;

        if (!dateProduce.equals(typeNoutbook.getDateProduce())) return false;
        if (!firmProduce.getNameFirm().equals(typeNoutbook.getFirmProduce().getNameFirm())) return false;
        if (!model.equals(typeNoutbook.getModel())) return false;
        if (!seriaNomber.equals(typeNoutbook.getSeriaNomber())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firmProduce.hashCode();
        result = 31 * result + seriaNomber.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + dateProduce.hashCode();
        return result;
    }
}
