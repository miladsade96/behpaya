package de.miladsa.behpaya.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "translations")
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Provide field fa")
    @NotEmpty(message = "Field fa could not be empty")
    @Column(columnDefinition = "varchar(1000)", nullable = false)
    private String fa;

    @NotNull(message = "Provide field en")
    @NotEmpty(message = "Field en could not be empty")
    @Column(columnDefinition = "varchar(1000)", nullable = false)
    private String en;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
}
