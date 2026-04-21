package br.com.fiap.atv_cap_8.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record OffsetProjectDTO(
        @NotNull Long companyId,
        @NotBlank String type,
        String description,
        @NotNull @Positive BigDecimal amountTco2e,
        @NotNull LocalDate startDate
) {}
