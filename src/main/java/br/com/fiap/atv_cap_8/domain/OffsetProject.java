package br.com.fiap.atv_cap_8.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offset_projects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OffsetProject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @NotNull
    @Column(length = 60, nullable = false)
    private String type; // REFORESTATION, CARBON_CREDIT…

    @Column(length = 255)
    private String description;

    @NotNull @Positive
    @Column(name = "amount_tco2e", nullable = false, precision = 12, scale = 3)
    private BigDecimal amountTco2e;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
}
