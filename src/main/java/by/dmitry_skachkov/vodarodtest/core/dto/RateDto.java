package by.dmitry_skachkov.vodarodtest.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RateDto {

    private String abbreviation;

    private int scale;

    private BigDecimal officialRate;

    private LocalDate date;

}
