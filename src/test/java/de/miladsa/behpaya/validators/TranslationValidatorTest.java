package de.miladsa.behpaya.validators;

import de.miladsa.behpaya.model.Translation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class TranslationValidatorTest {
    private TranslationValidator translationValidator;

    @Autowired
    public void setTranslationValidator(TranslationValidator translationValidator) {
        this.translationValidator = translationValidator;
    }

    @Test
    void testValidatorAValidTranslation() {
        Translation translation = new Translation();
        translation.setEn("English");
        translation.setFa("Farsi");

        var result = translationValidator.validator(translation);
        Assertions.assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testValidatorAnInvalidTranslation() {
        Translation translation = new Translation();
        translation.setEn("English");

        var result = translationValidator.validator(translation);
        Assertions.assertFalse(result.isEmpty());
    }
}
