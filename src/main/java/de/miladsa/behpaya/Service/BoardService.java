package de.miladsa.behpaya.Service;

import de.miladsa.behpaya.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String deleteADocument(Integer id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return null;
        }
        return "Board with provided id does not exist!";
    }
}
