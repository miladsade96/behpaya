package de.miladsa.behpaya.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "indicators")
public class Indicator extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String name;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private IndicatorType indicatorType;

    @Column(nullable = false)
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne
    @JoinColumn(name = "translation_id")
    private Translation translation;

    @OneToOne
    @JoinColumn(name = "translation_description_id")
    private TranslationDescription translationDescription;

    @OneToMany(mappedBy = "indicator")
    @Column(nullable = false)
    private List<HowToCalculate> howToCalculateList;
}
