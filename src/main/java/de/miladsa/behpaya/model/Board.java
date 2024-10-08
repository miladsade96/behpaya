package de.miladsa.behpaya.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "boards")
@EntityListeners(AuditingEntityListener.class)
public class Board extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @NotNull(message = "Provide board title")
    @NotEmpty(message = "Board title could not be empty")
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String title;

    @Column(columnDefinition = "varchar(500)")
    private String description;

    @NotNull(message = "Provide start number")
    @Column(unique = true, nullable = false)
    private Integer startNumber;

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

    @OneToMany(
            mappedBy = "board",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Indicator> indicators = new ArrayList<>();
}
