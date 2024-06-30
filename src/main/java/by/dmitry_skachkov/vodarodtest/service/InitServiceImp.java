package by.dmitry_skachkov.vodarodtest.service;

import by.dmitry_skachkov.vodarodtest.core.dto.CurrencyDto;
import by.dmitry_skachkov.vodarodtest.repo.CurrencyRepo;
import by.dmitry_skachkov.vodarodtest.repo.entity.Currency;
import by.dmitry_skachkov.vodarodtest.service.api.InitService;
import by.dmitry_skachkov.vodarodtest.service.api.NationalBankClient;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InitServiceImp implements InitService {

    private final CurrencyRepo currencyRepo;

    private final NationalBankClient client;

    public InitServiceImp(CurrencyRepo currencyRepo, NationalBankClient client) {
        this.currencyRepo = currencyRepo;
        this.client = client;
    }

    @PostConstruct
    @Override
    public void loadInitialData() {
        List<CurrencyDto> currencyDtos = client.getAllAvailableCurrencies();

        List<Currency> currencyEntities = currencyDtos.stream()
                .map(m -> new Currency(UUID.randomUUID(),
                        m.getCurId(),
                        m.getCurAbbreviation(),
                        m.getCurScale(),
                        m.getCurName(),
                        m.getCurCode()))
                .toList();
        currencyRepo.saveAll(currencyEntities);
    }
}
