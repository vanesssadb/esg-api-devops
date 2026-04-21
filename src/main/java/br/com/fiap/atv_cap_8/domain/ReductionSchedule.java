package br.com.fiap.atv_cap_8.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reduction_schedules")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReductionSchedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @NotNull
    @Column(length = 150, nullable = false)
    private String title;

    @NotNull
    @Column(name = "planned_date", nullable = false)
    private LocalDate plannedDate;

    @NotNull
    @Column(length = 30, nullable = false)
    private String status; // PLANNED, IN_PROGRESS, DONE, CANCELED

    @Positive
    @Column(name = "expected_reduction_tco2e", precision = 12, scale = 3)
    private BigDecimal expectedReductionTco2e;
}
