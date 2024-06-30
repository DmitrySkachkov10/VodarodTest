package by.dmitry_skachkov.vodarodtest.service;

import by.dmitry_skachkov.vodarodtest.core.dto.ExternalRateDto;
import by.dmitry_skachkov.vodarodtest.core.dto.RateDto;
import by.dmitry_skachkov.vodarodtest.core.exception.DataBaseException;
import by.dmitry_skachkov.vodarodtest.core.exception.FeignClientException;
import by.dmitry_skachkov.vodarodtest.core.exception.ValidationException;
import by.dmitry_skachkov.vodarodtest.repo.CurrencyRepo;
import by.dmitry_skachkov.vodarodtest.repo.RateRepo;
import by.dmitry_skachkov.vodarodtest.repo.entity.Rate;
import by.dmitry_skachkov.vodarodtest.service.api.CurrencyRateService;
import by.dmitry_skachkov.vodarodtest.service.api.NationalBankClient;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CurrencyRateServiceImp implements CurrencyRateService {

    private final NationalBankClient client;

    private final RateRepo rateRepo;

    private final CurrencyRepo currencyRepo;

    private final int DAILY_FREQUENCY = 0;

    public CurrencyRateServiceImp(NationalBankClient client, RateRepo rateRepo, CurrencyRepo currencyRepo) {
        this.client = client;
        this.rateRepo = rateRepo;
        this.currencyRepo = currencyRepo;
    }

    @Override
    public void addRatesInfo(LocalDate date) {
        try {
            List<ExternalRateDto> externalRateDtos = client.getDailyCurrencyRates(date, DAILY_FREQUENCY);

            for (ExternalRateDto dto : externalRateDtos) {

                Rate existingRate = rateRepo.findByCurrency_CurIdAndDate( dto.getCurId(),dto.getDate().toLocalDate());
                if (existingRate == null) {

                    Rate newRate = new Rate(UUID.randomUUID(),
                            dto.getDate().toLocalDate(),
                            BigDecimal.valueOf(dto.getCurOfficialRate()),
                            currencyRepo.findByCurId(dto.getCurId()));
                    rateRepo.save(newRate);
                }
            }
        } catch (FeignException e) {
            throw new FeignClientException(e.getMessage());
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public RateDto getRateByCodeAndDate(int code, LocalDate date) {
        try {
            Rate rate = rateRepo.findByCurrency_CurIdAndDate(code, date);
            return new RateDto(rate.getCurrency().getCurAbbreviation(),
                    rate.getCurrency().getCurScale(),
                    rate.getOfficialRate(),
                    rate.getDate());
        } catch (NullPointerException e) {
            throw new ValidationException(e.getMessage());
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}
