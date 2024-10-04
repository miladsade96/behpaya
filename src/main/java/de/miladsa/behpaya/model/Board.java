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
@Table(name = "boards")
@EntityListeners(AuditingEntityListener.class)
public class Board extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String title;

    @Column(columnDefinition = "varchar(500)")
    private String description;

    @Column(unique = true, nullable = false)
    private Integer startNumber;

    @Column(nullable = false)
    private Boolean isHidden = false;

    @ManyToOne
    @JoinColumn(
            name = "document_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "document_id_fk")
    )
    private Document document;

    @OneToMany(mappedBy = "board")
    private List<Indicator> indicators;
}
