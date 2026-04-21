package br.com.fiap.atv_cap_8.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ReductionScheduleDTO(
        @NotNull Long companyId,
        @NotBlank String title,
        @NotNull LocalDate plannedDate,
        @NotBlank String status,
        @Positive BigDecimal expectedReductionTco2e
) {}
