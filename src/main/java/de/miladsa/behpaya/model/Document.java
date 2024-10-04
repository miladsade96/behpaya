package de.miladsa.behpaya.model;

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
@Table(name = "documents")
@EntityListeners(AuditingEntityListener.class)
public class Document extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @NotNull(message = "Document title does not provided")
    @NotEmpty(message = "Document title could not be empty")
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String title;

    @Column(columnDefinition = "varchar(255)")
    private String description;

    @Column(nullable = false)
    private Boolean isHidden = false;

    @OneToMany(
            mappedBy = "document",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "document")
    private List<Calculation> calculations;

    public void removeBoard(Board board) {
        if (this.boards.contains(board)) {
            this.boards.remove(board);
            board.setDocument(null);
        }
    }

    public void addBoard(Board board) {
        if (!this.boards.contains(board)) {
            this.boards.add(board);
            board.setDocument(this);
        }
    }
}
