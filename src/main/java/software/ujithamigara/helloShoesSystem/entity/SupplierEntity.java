package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import software.ujithamigara.helloShoesSystem.entity.enums.Category;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "supplier")
@Entity
public class SupplierEntity implements SuperEntity{
    @Id
    private String supplierCode;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String addressNo;
    private String lane;
    private String mainCity;
    private String mainState;
    private String postalCode;
    private String country;
    private String mobileNumber;
    private String landlineNumber;
    @Column(unique = true)
    private String email;
    @ToString.Exclude
    @OneToMany(mappedBy = "supplierEntity")
    private List<ItemEntity> itemEntities;
}
