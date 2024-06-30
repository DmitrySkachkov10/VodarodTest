package by.dmitry_skachkov.vodarodtest.repo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "rate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rate {

    public Rate(UUID uuid, LocalDate date, BigDecimal officialRate) {
        this.uuid = uuid;
        this.date = date;
        this.officialRate = officialRate;
    }

    @Id
    @Column(name = "rate_uuid")
    private UUID uuid;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "official_rate", precision = 18, scale = 4)
    private BigDecimal officialRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_uuid")
    private Currency currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Objects.equals(uuid, rate.uuid) && Objects.equals(date, rate.date) && Objects.equals(officialRate, rate.officialRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, date, officialRate);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "uuid=" + uuid +
                ", date=" + date +
                ", officialRate=" + officialRate +
                '}';
    }
}
