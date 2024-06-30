package by.dmitry_skachkov.vodarodtest.service;

import by.dmitry_skachkov.vodarodtest.core.dto.ExternalRateDto;
import by.dmitry_skachkov.vodarodtest.core.dto.RateDto;
import by.dmitry_skachkov.vodarodtest.core.exception.DataBaseException;
import by.dmitry_skachkov.vodarodtest.core.exception.FeignClientException;
import by.dmitry_skachkov.vodarodtest.core.exception.ValidationException;
import by.dmitry_skachkov.vodarodtest.repo.CurrencyRepo;
import by.dmitry_skachkov.vodarodtest.repo.RateRepo;
import by.dmitry_skachkov.vodarodtest.repo.entity.Currency;
import by.dmitry_skachkov.vodarodtest.repo.entity.Rate;
import by.dmitry_skachkov.vodarodtest.service.api.NationalBankClient;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CurrencyRateServiceImpTest {

    @InjectMocks
    private CurrencyRateServiceImp rateService;

    @Mock
    private NationalBankClient client;

    @Mock
    private RateRepo rateRepo;

    @Mock
    private CurrencyRepo currencyRepo;

    private LocalDate date;

    @BeforeEach
    void setUp() {
        date = LocalDate.of(2024, 6, 30);
    }

    private ExternalRateDto createExternalRateDto() {
        ExternalRateDto externalRateDto = new ExternalRateDto();
        externalRateDto.setCurId(456);
        externalRateDto.setDate(LocalDateTime.of(date, LocalDateTime.MIN.toLocalTime()));
        externalRateDto.setCurAbbreviation("RUB");
        externalRateDto.setCurScale(100);
        externalRateDto.setCurName("Российских рублей");
        externalRateDto.setCurOfficialRate(3.4840);
        return externalRateDto;
    }

    private Currency createCurrency() {
        Currency currency = new Currency();
        currency.setCurId(456);
        currency.setCurAbbreviation("RUB");
        currency.setCurScale(100);
        return currency;
    }

    private final int CUR_ID = 456;

    @Test
    void addRatesInfo_Successful() {
        ExternalRateDto externalRateDto = createExternalRateDto();
        Currency currency = createCurrency();

        when(client.getDailyCurrencyRates(date, 0)).thenReturn(List.of(externalRateDto));
        when(currencyRepo.findByCurId(CUR_ID)).thenReturn(currency);

        assertDoesNotThrow(() -> rateService.addRatesInfo(date));

        verify(rateRepo, times(1)).saveAll(Mockito.anyList());
    }

    @Test
    void addRatesInfo_withoutFeignConnection_throwsException() {
        when(client.getDailyCurrencyRates(date, 0)).thenThrow(FeignException.class);

        FeignClientException exception = assertThrows(FeignClientException.class, () -> rateService.addRatesInfo(date));
        assertNotNull(exception);

    }

    @Test
    void addRatesInfo_DB_throwsException() {
        ExternalRateDto externalRateDto = createExternalRateDto();

        when(client.getDailyCurrencyRates(date, 0)).thenReturn(List.of(externalRateDto));
        when(currencyRepo.findByCurId(CUR_ID)).thenThrow(new RuntimeException("Database error"));

        DataBaseException exception = assertThrows(DataBaseException.class, () -> rateService.addRatesInfo(date));
        assertEquals(exception.getMessage(), "Database error");

    }

    @Test
    void getRate_withCodeAndDate_Successful() {
        Currency currency = createCurrency();
        Rate rate = new Rate();
        rate.setCurrency(currency);
        rate.setOfficialRate(BigDecimal.valueOf(3.4840));
        rate.setDate(date);

        when(rateRepo.findByCurrency_CurIdAndDate(CUR_ID, date)).thenReturn(rate);

        RateDto result = rateService.getRateByCodeAndDate(CUR_ID, date);

        assertNotNull(result);
        assertEquals(currency.getCurAbbreviation(), result.getAbbreviation());
        assertEquals(currency.getCurScale(), result.getScale());
        assertEquals(BigDecimal.valueOf(3.4840), result.getOfficialRate());
        assertEquals(date, result.getDate());

        verify(rateRepo, times(1)).findByCurrency_CurIdAndDate(CUR_ID, date);
    }

    @Test
    void getRate_withNullRate_throwsException() {
        when(rateRepo.findByCurrency_CurIdAndDate(CUR_ID, date)).thenReturn(null);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> rateService.getRateByCodeAndDate(CUR_ID, date));
        assertNotNull(exception.getMessage());

        verify(rateRepo, times(1)).findByCurrency_CurIdAndDate(CUR_ID, date);
    }

    @Test
    void getRate_withCodeAndDate_throwsDataBaseException() {

        when(rateRepo.findByCurrency_CurIdAndDate(CUR_ID, date)).thenThrow(new RuntimeException("Database error"));

        DataBaseException exception = assertThrows(DataBaseException.class,
                () -> rateService.getRateByCodeAndDate(CUR_ID, date));
        assertEquals(exception.getMessage(), "Database error");

        verify(rateRepo, times(1)).findByCurrency_CurIdAndDate(CUR_ID, date);
    }
}