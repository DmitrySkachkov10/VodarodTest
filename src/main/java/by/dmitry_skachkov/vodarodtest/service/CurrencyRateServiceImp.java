package by.dmitry_skachkov.vodarodtest.service;

import by.dmitry_skachkov.vodarodtest.core.dto.ExternalRateDto;
import by.dmitry_skachkov.vodarodtest.repo.RateRepo;
import by.dmitry_skachkov.vodarodtest.repo.entity.Rate;
import by.dmitry_skachkov.vodarodtest.service.api.CurrencyRateService;
import by.dmitry_skachkov.vodarodtest.service.api.NationalBankClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CurrencyRateServiceImp implements CurrencyRateService {

    private final NationalBankClient client;

    private final RateRepo repo;

    private final int DAILY_FREQUENCY = 0;


    public CurrencyRateServiceImp(NationalBankClient client, RateRepo repo) {
        this.client = client;
        this.repo = repo;
    }

    //todo aop around with logs
    @Override
    public void getRatesByDate(LocalDate date) {
        List<ExternalRateDto> externalRateDtos = client.getDailyCurrencyRates(date, DAILY_FREQUENCY);
        List<Rate> rateList = externalRateDtos.stream()
                .map(m -> new Rate(UUID.randomUUID(),
                        m.getDate(),
                        BigDecimal.valueOf(m.getCurOfficialRate())))
                .toList();
        repo.saveAll(rateList);
    }


    @Override
    public ExternalRateDto getRateByCodeAndDate(int code, LocalDate date) {

        return null;

    }
}
