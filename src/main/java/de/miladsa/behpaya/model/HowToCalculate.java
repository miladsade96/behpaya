package de.miladsa.behpaya.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HowToCalculate")
public class HowToCalculate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "how_to_calculate_description", columnDefinition = "varchar(500)", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "indicator_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "indicator_id_fk")
    )
    private Indicator indicator;
}
