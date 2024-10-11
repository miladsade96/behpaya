package de.miladsa.behpaya.Service;

import de.miladsa.behpaya.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndicatorService {
    private IndicatorRepository indicatorRepository;

    @Autowired
    public void setIndicatorRepository(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    public String deleteAnIndicator(Integer id) {
        if (indicatorRepository.existsById(id)) {
            indicatorRepository.deleteById(id);
            return null;
        }
        return "Indicator with provided id does not exist";
    }
}
