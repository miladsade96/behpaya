package de.miladsa.behpaya.controller;

import de.miladsa.behpaya.Service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndicatorController {
    private IndicatorService indicatorService;

    @Autowired
    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    @DeleteMapping("/indicator/{id}")
    public ResponseEntity<?> deleteAnIndicator(@PathVariable Integer id) {
        var result = indicatorService.deleteAnIndicator(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
