package by.dmitry_skachkov.vodarodtest.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyDto {

    private int Cur_ID;
    private int Cur_ParentID;
    private String Cur_Code;
    private String Cur_Abbreviation;
    private String Cur_Name;
    private String Cur_Name_Bel;
    private String Cur_Name_Eng;
    private String Cur_QuotName;
    private String Cur_QuotName_Bel;
    private String Cur_QuotName_Eng;
    private String Cur_NameMulti;
    private String Cur_Name_BelMulti;
    private String Cur_Name_EngMulti;
    private int Cur_Scale;
    private int Cur_Periodicity;
    private LocalDateTime Cur_DateStart;
    private LocalDateTime Cur_DateEnd;

}