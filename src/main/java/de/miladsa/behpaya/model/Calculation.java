package de.miladsa.behpaya.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
public class Calculation extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer number;

    @Column(columnDefinition = "varchar(255)")
    private String command;

    @Column(columnDefinition = "varchar(500)")
    private String description;

    @Column(nullable = false)
    private Boolean isHidden = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "document_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "document_id_fk")
    )
    @JsonBackReference
    private Document document;
}
