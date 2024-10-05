package de.miladsa.behpaya.dto;

import de.miladsa.behpaya.model.IndicatorType;
import de.miladsa.behpaya.model.Type;
import de.miladsa.behpaya.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndicatorDTO {
    private String name;
    private Type type;
    private IndicatorType indicatorType;
    private Unit unit;
    private TranslationDTO translationDTO;
    private TranslationDTO translationDescriptionDTO;
    private List<HowToCalculateDTO> howToCalculateList;
}
