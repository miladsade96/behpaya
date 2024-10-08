package de.miladsa.behpaya.Service;

import de.miladsa.behpaya.dto.*;
import de.miladsa.behpaya.model.*;
import de.miladsa.behpaya.projection.DocumentProjection;
import de.miladsa.behpaya.repository.*;
import de.miladsa.behpaya.validators.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DocumentService {
    private DocumentRepository documentRepository;
    private DocumentValidator documentValidator;
    private BoardRepository boardRepository;
    private IndicatorRepository indicatorRepository;
    private HowToCalculateRepository howToCalculateRepository;
    private CalculationRepository calculationRepository;
    private BoardValidator boardValidator;
    private TranslationValidator translationValidator;
    private IndicatorValidator indicatorValidator;
    private HowToCalculateValidator howToCalculateValidator;
    private CalculationValidator calculationValidator;

    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Autowired
    public void setDocumentValidator(DocumentValidator documentValidator) {
        this.documentValidator = documentValidator;
    }

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired
    public void setIndicatorRepository(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    @Autowired
    public void setHowToCalculateRepository(HowToCalculateRepository howToCalculateRepository) {
        this.howToCalculateRepository = howToCalculateRepository;
    }

    @Autowired
    public void setCalculationRepository(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    @Autowired
    public void setBoardValidator(BoardValidator boardValidator) {
        this.boardValidator = boardValidator;
    }

    @Autowired
    public void setTranslationValidator(TranslationValidator translationValidator) {
        this.translationValidator = translationValidator;
    }

    @Autowired
    public void setIndicatorValidator(IndicatorValidator indicatorValidator) {
        this.indicatorValidator = indicatorValidator;
    }

    @Autowired
    public void setHowToCalculateValidator(HowToCalculateValidator howToCalculateValidator) {
        this.howToCalculateValidator = howToCalculateValidator;
    }

    @Autowired
    public void setCalculationValidator(CalculationValidator calculationValidator) {
        this.calculationValidator = calculationValidator;
    }

    public List<DocumentProjection> getAllDocuments() {
        return documentRepository.findAllDocuments();
    }

    public Document getDocumentById(Integer id) {
        return documentRepository.findById(id).orElse(null);
    }

    public Set<String> addADocument(DocumentDTO documentDTO) {
        var document = new Document();
        document.setTitle(documentDTO.getTitle());
        document.setDescription(documentDTO.getDescription());
        var violations = documentValidator.validator(document);
        if (!violations.isEmpty()) return violations;
        var savedDoc = documentRepository.save(document);

        var boards = documentDTO.getBoards();
        for (BoardDTO board_dto : boards) {
            var board = new Board();
            board.setTitle(board_dto.getTitle());
            board.setDescription(board_dto.getDescription());
            board.setDocument(savedDoc);
            board.setStartNumber(board_dto.getStartNumber());
            violations.addAll(boardValidator.validator(board));
            if (!violations.isEmpty()) return violations;
            var savedBoard = boardRepository.save(board);

            for (IndicatorDTO indicator_dto : board_dto.getIndicators()) {
                var indicator = new Indicator();
                indicator.setName(indicator_dto.getName());
                indicator.setType(indicator_dto.getType());
                indicator.setIndicatorType(indicator_dto.getIndicatorType());
                indicator.setUnit(indicator_dto.getUnit());
                indicator.setBoard(savedBoard);

                var translation = new Translation();
                translation.setEn(indicator_dto.getTranslationDTO().getEn());
                translation.setFa(indicator_dto.getTranslationDTO().getFa());
                violations.addAll(translationValidator.validator(translation));
                if (!violations.isEmpty()) return violations;
                indicator.setTranslation(translation);

                var translationDescription = new Translation();
                translationDescription.setEn(indicator_dto.getTranslationDescriptionDTO().getEn());
                translationDescription.setFa(indicator_dto.getTranslationDescriptionDTO().getFa());
                violations.addAll(translationValidator.validator(translationDescription));
                if (!violations.isEmpty()) return violations;
                indicator.setTranslationDescription(translationDescription);

                violations.addAll(indicatorValidator.validator(indicator));
                var savedIndicator = indicatorRepository.save(indicator);

                for (HowToCalculateDTO howToCalculateDTO : indicator_dto.getHowToCalculateList()) {
                    var htc = new HowToCalculate();
                    htc.setIndicator(savedIndicator);
                    htc.setDescription(howToCalculateDTO.getDescription());
                    violations.addAll(howToCalculateValidator.validator(htc));
                    if (!violations.isEmpty()) return violations;
                    howToCalculateRepository.save(htc);
                }
            }
        }

        for (CalculationDTO calculation_dto : documentDTO.getCalculations()) {
            var calculation = new Calculation();
            calculation.setDocument(savedDoc);
            calculation.setCommand(calculation_dto.getCommand());
            calculation.setDescription(calculation_dto.getDescription());
            violations.addAll(calculationValidator.validator(calculation));
            if (!violations.isEmpty()) return violations;
            calculationRepository.save(calculation);

        }
        return Collections.emptySet();
    }
}
