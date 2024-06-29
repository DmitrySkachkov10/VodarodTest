package by.dmitry_skachkov.vodarodtest.service.api;

import by.dmitry_skachkov.vodarodtest.core.dto.ExternalRateDto;

import java.time.LocalDate;


public interface CurrencyRateService {

    void getRatesByDate(LocalDate date);

    ExternalRateDto getRateByCodeAndDate(int code, LocalDate date);

}
