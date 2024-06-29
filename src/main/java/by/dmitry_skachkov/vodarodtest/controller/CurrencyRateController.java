package by.dmitry_skachkov.vodarodtest.controller;

import by.dmitry_skachkov.vodarodtest.service.api.CurrencyRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController("/api/currency/rates")
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @GetMapping("/on-date")
    public ResponseEntity<String> getCurrencyRateByDate(@RequestParam("date") LocalDate date) {
        currencyRateService.getRatesByDate(date);
        return ResponseEntity.status(HttpStatus.OK).body("Данные за " + date + " успешно загружены");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrencyRateByCodeAndDate(@PathVariable("code") int code,
                                                          @RequestParam("date") LocalDate date) {
       return null;
    }
}
