package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Occasion;
import software.ujithamigara.helloShoesSystem.entity.enums.Verities;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item")
@Entity
public class ItemEntity implements SuperEntity{
    @Id
    private String itemCode;
    private String itemDescription;
    @Column(columnDefinition = "LONGTEXT")
    private String itemPicture;
    private String itemCategory;
    private int size;
    private double unitPriceSell;
    private double unitPriceBuy;
    private double expectedProfit;
    private double profitMargin;
    private int quantity;
    private String itemStatus;
    @Enumerated(EnumType.STRING)
    private Occasion occasion;
    @Enumerated(EnumType.STRING)
    private Verities verities;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "supplierCode", referencedColumnName = "supplierCode")
    @JoinColumn(name = "name", referencedColumnName = "name")
    private SupplierEntity supplierEntity;
    @ToString.Exclude
    @OneToMany (mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;
}
