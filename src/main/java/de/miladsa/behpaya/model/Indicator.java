package de.miladsa.behpaya.model;

import de.miladsa.behpaya.validators.ValidIndicatorType;
import de.miladsa.behpaya.validators.ValidType;
import de.miladsa.behpaya.validators.ValidUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "indicators")
@EntityListeners(AuditingEntityListener.class)
public class Indicator extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String name;

    @ValidType(message = "Provided type is invalid")
    @Column(nullable = false)
    private Type type;

    @ValidIndicatorType(message = "Provided indicatorType is invalid")
    @Column(nullable = false)
    private IndicatorType indicatorType;

    @ValidUnit(message = "Provided unit is invalid")
    @Column(nullable = false)
    private Unit unit;

    @Column(nullable = false)
    private Boolean isHidden = false;

    @ManyToOne
    @JoinColumn(
            name = "board_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "board_id_fk")
    )
    private Board board;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "translation_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "translation_id_fk"))
    private Translation translation;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "translation_description_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "translation_description_id_fk"))
    private Translation translationDescription;

    @OneToMany(
            mappedBy = "indicator",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<HowToCalculate> howToCalculateList = new ArrayList<>();

//    public void removeHowToCalculate(HowToCalculate howToCalculate) {
//        if (this.howToCalculateList.contains(howToCalculate)) {
//            this.howToCalculateList.remove(howToCalculate);
//            howToCalculate.setIndicator(null);
//        }
//    }
//
//    public void addHowToCalculate(HowToCalculate howToCalculate) {
//        if (!this.howToCalculateList.contains(howToCalculate)) {
//            this.howToCalculateList.add(howToCalculate);
//            howToCalculate.setIndicator(this);
//        }
//    }
}
