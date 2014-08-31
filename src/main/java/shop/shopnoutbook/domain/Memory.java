package shop.shopnoutbook.domain;
import javax.persistence.*;


@Entity
@Table(name = "MEMORIES")
public class Memory {
    @Id
    @GeneratedValue
    @Column(name="MEMORY_ID")
    private Long id;

    @ManyToOne
    private FirmProduce firmProduce;

    @Column(name = "SIZE_SENSE")
    private Double size;

    public Memory(){

    }

    public Memory(FirmProduce firmProduce, Double size) {
        this.firmProduce = firmProduce;
        this.size = size;
    }

    public FirmProduce getFirmProduce() {
        return firmProduce;
    }

    public void setFirmProduce(FirmProduce firmProduce) {
        this.firmProduce = firmProduce;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "firmProduce=" + firmProduce +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Memory memory = (Memory) o;
        if (!firmProduce.getNameFirm().equals(memory.getFirmProduce().getNameFirm())) return false;
        if (!size.equals(memory.getSize())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + size.hashCode();
        return result;
    }
}
