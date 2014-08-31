package shop.shopnoutbook.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



    @Entity
    @Table(name = "FIRM_PRODUCE")
    public class FirmProduce {
        @Id
        @GeneratedValue
        @Column(name = "FIRM_PRODUCE_ID")
        private Long id;

        @Column(name = "NAME_FIRM")
        private String nameFirm;
        @Fetch(FetchMode.SELECT)

        @OneToMany(mappedBy = "firmProduce", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<TypeNoutbook> typeNoutbookSet = new HashSet<>();
        @OneToMany(mappedBy = "firmProduce", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<Processor> processors = new HashSet<>();

        @OneToMany(mappedBy = "firmProduce", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<Memory> memories = new HashSet<>();



        public FirmProduce() {

        }

        public FirmProduce(String nameFirm) {
            this.nameFirm = nameFirm;
        }

        public String getNameFirm() {
            return nameFirm;
        }

        public void setNameFirm(String nameFirm) {
            this.nameFirm = nameFirm;
        }

        @Override
        public String toString() {
            return "FirmProduce{" +
                    "nameFirm='" + nameFirm + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FirmProduce firmProduce = (FirmProduce) o;
            if (!nameFirm.equals(firmProduce.getNameFirm())) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + nameFirm.hashCode();
            return result;
        }
    }

