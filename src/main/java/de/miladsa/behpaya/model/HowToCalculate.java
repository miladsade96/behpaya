package de.miladsa.behpaya.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HowToCalculate")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = HowToCalculate.class)
public class HowToCalculate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @NotEmpty(message = "Provide how yo calculate description")
    @NotNull(message = "How to calculate description could not be empty")
    @Column(name = "how_to_calculate_description", columnDefinition = "varchar(500)", nullable = false)
    private String description;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "indicator_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "indicator_id_fk")
    )
    private Indicator indicator;
}
