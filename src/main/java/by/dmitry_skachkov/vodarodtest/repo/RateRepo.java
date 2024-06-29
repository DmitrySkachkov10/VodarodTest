package by.dmitry_skachkov.vodarodtest.repo;

import by.dmitry_skachkov.vodarodtest.repo.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface RateRepo extends JpaRepository<Rate, UUID> {

    Rate findByCurrency_CurIdAndDate(int curID, LocalDateTime date);
}
