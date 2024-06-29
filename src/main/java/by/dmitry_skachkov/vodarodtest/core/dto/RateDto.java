package by.dmitry_skachkov.vodarodtest.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RateDto {
    private int curId;
    private LocalDate date;
    private String curAbbreviation;
    private int curScale;
    private String curName;
    private double curOfficialRate;
}