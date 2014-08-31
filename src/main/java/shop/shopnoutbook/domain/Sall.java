package shop.shopnoutbook.domain;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "SALLS")
public class Sall {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "SEQ_SALLS_ID", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name="SALL_ID")
    private Long id;

    @OneToMany
    private List<Store> stores = new ArrayList<>();

    @Column(name = "DATE_SALL")
    private Date dateSall;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public Sall(){

    }

    public Sall(List<Store> stores, Date dateSall, Integer quantity) {
        this.stores = stores;
        this.dateSall = dateSall;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Store> getStore() {
        return stores;
    }

    public void setStore(List<Store> stores) {
        this.stores = stores;
    }

    public Date getDateSall() {
        return dateSall;
    }

    public void setDateSall(Date dateSall) {
        this.dateSall = dateSall;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sall{" +
                "id=" + id +
                ", stores=" + stores +
                ", dateSall=" + dateSall +
                ", quantity=" + quantity +
                '}';
    }
}
