package by.dmitry_skachkov.vodarodtest.repo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "currency")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Currency {

    @Id
    @Column(name = "currency_uuid")
    private UUID uuid;

    @Column(name = "сur_ID", nullable = false)
    private int curId;

    @Column(name = "cur_abbreviation", nullable = false)
    private String curAbbreviation;

    @Column(name = "cur_scale", nullable = false)
    private int curScale;

    @Column(name = "cur_name", nullable = false)
    private String curName;

    @Column(name = "cur_code", nullable = false)
    private int curCode;                                    // ISO 4217

    @OneToMany(mappedBy = "currency")
    private List<Rate> rateList = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return curId == currency.curId && curScale == currency.curScale && curCode == currency.curCode && Objects.equals(uuid, currency.uuid) && Objects.equals(curAbbreviation, currency.curAbbreviation) && Objects.equals(curName, currency.curName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, curId, curAbbreviation, curScale, curName, curCode);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "uuid=" + uuid +
                ", curId=" + curId +
                ", curAbbreviation='" + curAbbreviation + '\'' +
                ", curScale=" + curScale +
                ", curName='" + curName + '\'' +
                ", curCode=" + curCode +
                '}';
    }
}