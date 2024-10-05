package de.miladsa.behpaya.projection;

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
