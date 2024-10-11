package de.miladsa.behpaya.controller;

import de.miladsa.behpaya.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    private BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> deleteABoard(@PathVariable Integer id) {
        var result = boardService.deleteADocument(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
