package br.com.fiap.atv_cap_8.web;

import br.com.fiap.atv_cap_8.repository.EmissionRecordRepository;
import br.com.fiap.atv_cap_8.repository.OffsetProjectRepository;
import br.com.fiap.atv_cap_8.web.dto.IndicatorsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/indicators")
@RequiredArgsConstructor
public class IndicatorsController {

    private final EmissionRecordRepository emissions;
    private final OffsetProjectRepository offsets;

    // GET /indicators?companyId=1
    @GetMapping
    public IndicatorsResponse indicators(@RequestParam Long companyId) {
        BigDecimal totalE = emissions.sumByCompany(companyId);
        BigDecimal totalO = offsets.totalOffsets(companyId);
        if (totalE == null) totalE = BigDecimal.ZERO;
        if (totalO == null) totalO = BigDecimal.ZERO;
        BigDecimal net = totalE.subtract(totalO);
        return new IndicatorsResponse(totalE, totalO, net);
    }
}
