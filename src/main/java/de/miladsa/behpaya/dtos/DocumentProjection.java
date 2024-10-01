package de.miladsa.behpaya.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentProjection {
    private Integer id;
    private String title;
    private String description;
    private Boolean isHidden;
}
