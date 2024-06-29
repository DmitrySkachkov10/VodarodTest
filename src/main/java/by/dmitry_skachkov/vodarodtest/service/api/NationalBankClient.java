package by.dmitry_skachkov.vodarodtest.service.api;

import by.dmitry_skachkov.vodarodtest.core.dto.CurrencyDto;
import by.dmitry_skachkov.vodarodtest.core.dto.ExternalRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "nationalBankClient", url = "https://api.nbrb.by/exrates")
public interface NationalBankClient {

    @GetMapping("/currencies")
    List<CurrencyDto> getAllAvailableCurrencies();

    @GetMapping("/exrates/rates")
    List<ExternalRateDto> getDailyCurrencyRates(@RequestParam("ondate") LocalDate date,
                                                @RequestParam("periodicity") int periodicity);

}

