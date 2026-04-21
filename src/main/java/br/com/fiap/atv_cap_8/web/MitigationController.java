package br.com.fiap.atv_cap_8.web;

import br.com.fiap.atv_cap_8.domain.ReductionSchedule;
import br.com.fiap.atv_cap_8.repository.ReductionScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/impacto-mitigacao")
@RequiredArgsConstructor
public class MitigationController {

    private final ReductionScheduleRepository repo;

    @GetMapping("/{id}")
    public Map<String, Object> impact(@PathVariable Long id) {
        ReductionSchedule r = repo.findById(id).orElseThrow();
        BigDecimal impact = r.getExpectedReductionTco2e() != null ? r.getExpectedReductionTco2e() : BigDecimal.ZERO;
        return Map.of(
                "scheduleId", r.getId(),
                "title", r.getTitle(),
                "status", r.getStatus(),
                "expectedReduction_tCO2e", impact
        );
    }
}
