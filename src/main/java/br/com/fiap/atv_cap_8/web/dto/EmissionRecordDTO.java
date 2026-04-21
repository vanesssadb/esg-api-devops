package br.com.fiap.atv_cap_8.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EmissionRecordDTO(
        @NotNull Long companyId,
        @NotNull LocalDate recordDate,
        @NotBlank String scope,
        @NotNull @Positive BigDecimal amountTco2e
) {}
