package by.dmitry_skachkov.vodarodtest.controller;

import by.dmitry_skachkov.vodarodtest.core.dto.RateDto;
import by.dmitry_skachkov.vodarodtest.service.api.CurrencyRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CurrencyRateControllerTest {

    @Mock
    private CurrencyRateService rateService;

    @InjectMocks
    private CurrencyRateController rateController;

    private MockMvc mockMvc;

    private LocalDate date;

    @BeforeEach
    void setUp() {
        date = LocalDate.of(2024, 6, 30);
        mockMvc = MockMvcBuilders.standaloneSetup(rateController).build();
    }

    @Test
    void getCurrencyRateByDate_Successful() throws Exception {
        doNothing().when(rateService).addRatesInfo(date);

        mockMvc.perform(get("/api/currency/rates/on-date")
                .param("date", date.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("Data from " + date + " successfully loaded"));

        verify(rateService, times(1)).addRatesInfo(date);
    }

    @Test
    void getCurrencyRateByCodeAndDate_Successful() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        RateDto rateDto = new RateDto("USD", 1, BigDecimal.valueOf(3.1245), date);
        when(rateService.getRateByCodeAndDate(456, date)).thenReturn(rateDto);

        mockMvc.perform(get("/api/currency/rates/{code}", 456)
                .param("date", date.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.abbreviation").value("USD"))
                .andExpect(jsonPath("$.scale").value(1))
                .andExpect(jsonPath("$.officialRate").value(3.1245))
                .andExpect(jsonPath("$.date[0]").value(date.getYear()))
                .andExpect(jsonPath("$.date[1]").value(date.getMonthValue()))
                .andExpect(jsonPath("$.date[2]").value(date.getDayOfMonth()));
    }

}