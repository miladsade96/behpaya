package de.miladsa.behpaya.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calculations")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "number", scope = Calculation.class)
public class Calculation extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer number;

    @NotNull(message = "Provide calculation command")
    @NotEmpty(message = "Calculation command could not be empty")
    @Column(columnDefinition = "varchar(255)")
    private String command;

    @Column(columnDefinition = "varchar(500)")
    private String description;

    @Column(nullable = false)
    private Boolean isHidden = false;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @NotNull(message = "Provide document info to connect with calculation")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "document_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "document_id_fk")
    )
    private Document document;
}
