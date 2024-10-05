package de.miladsa.behpaya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private String title;
    private String description;
    private List<BoardDTO> boards;
    private List<CalculationDTO> calculations;
}
