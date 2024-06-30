package by.dmitry_skachkov.vodarodtest.service.api;

import by.dmitry_skachkov.vodarodtest.core.dto.ExternalRateDto;
import by.dmitry_skachkov.vodarodtest.core.dto.RateDto;

import java.time.LocalDate;


public interface CurrencyRateService {

    void getRatesByDate(LocalDate date);

    RateDto getRateByCodeAndDate(int code, LocalDate date);

}
