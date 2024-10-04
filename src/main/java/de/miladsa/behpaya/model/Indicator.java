package de.miladsa.behpaya.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private IndicatorType indicatorType;

    @Column(nullable = false)
    private Unit unit;

    @Column(nullable = false)
    private Boolean isHidden = false;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "translation_id", referencedColumnName = "id")
    private Translation translation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "translation_description_id", referencedColumnName = "id")
    private TranslationDescription translationDescription;

    @OneToMany(mappedBy = "indicator")
    @Column(nullable = false)
    private List<HowToCalculate> howToCalculateList;
}
