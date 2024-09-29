package de.miladsa.behpaya.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calculations")
public class Calculation extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @Column(columnDefinition = "varchar(255)")
    private String command;

    @Column(columnDefinition = "varchar(500)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}
