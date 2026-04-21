package br.com.fiap.atv_cap_8.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "emission_records")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmissionRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @NotNull
    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @NotNull
    @Column(length = 20, nullable = false)
    private String scope; // SCOPE1, SCOPE2, SCOPE3

    @NotNull @Positive
    @Column(name = "amount_tco2e", nullable = false, precision = 12, scale = 3)
    private BigDecimal amountTco2e;
}
