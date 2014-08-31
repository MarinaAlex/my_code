package shop.shopnoutbook.domain;
import javax.persistence.*;


@Entity
@Table(name = "PROCESSORS")
public class Processor {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "SEQ_PROCESSORS_ID", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name="PROCESSOR_ID")
    private Long id;
    @ManyToOne
    private FirmProduce firmProduce;
    @Column(name = "FREQUENCY_SENSE")
    private Double frequency;
    @Column(name = "MODEL_SENSE")
    private String model;

    public Processor(){

    }

    public Processor(FirmProduce firmProduce, Double frequency, String model) {
        this.firmProduce = firmProduce;
        this.frequency = frequency;
        this.model = model;
    }

    public FirmProduce getFirmProduce() {
        return firmProduce;
    }

    public void setFirmProduce(FirmProduce firmProduce) {
        this.firmProduce = firmProduce;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "firmProduce=" + firmProduce +
                ", frequency=" + frequency +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        if (!firmProduce.getNameFirm().equals(processor.getFirmProduce().getNameFirm())) return false;
        if (!frequency.equals(processor.getFrequency())) return false;
        if (!model.equals(processor.getModel())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + frequency.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }
}
