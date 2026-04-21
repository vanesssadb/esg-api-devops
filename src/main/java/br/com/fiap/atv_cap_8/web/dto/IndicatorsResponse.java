package br.com.fiap.atv_cap_8.web.dto;

import java.math.BigDecimal;

public record IndicatorsResponse(
        BigDecimal totalEmissions,
        BigDecimal totalOffsets,
        BigDecimal netEmissions
) {}
