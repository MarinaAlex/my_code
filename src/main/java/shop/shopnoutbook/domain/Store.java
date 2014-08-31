package shop.shopnoutbook.domain;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "STORIES")
public class Store {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "SEQ_STORIES_ID", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name="STORE_ID")
    private Long id;
    @OneToMany
    private List<TypeNoutbook> typeNoutbook;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "PRICE")
    private BigDecimal price;

    public Store(){

    }

    public Store(List<TypeNoutbook> typeNoutbook, Integer quantity, BigDecimal price) {
        this.typeNoutbook = typeNoutbook;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List getTypeNoutbook() {
        return typeNoutbook;
    }

    public void setTypeNoutbook(List<TypeNoutbook> typeNoutbook) {
        this.typeNoutbook = typeNoutbook;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n Store{" +
                "typeNoutbook=" + typeNoutbook +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
