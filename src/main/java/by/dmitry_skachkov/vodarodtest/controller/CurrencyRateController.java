package by.dmitry_skachkov.vodarodtest.controller;

import by.dmitry_skachkov.vodarodtest.service.api.CurrencyRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/currency/rates")
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
