package by.dmitry_skachkov.vodarodtest.repo;

import by.dmitry_skachkov.vodarodtest.repo.entity.Currency;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurrencyRepo extends ListCrudRepository<Currency, UUID> {

}
