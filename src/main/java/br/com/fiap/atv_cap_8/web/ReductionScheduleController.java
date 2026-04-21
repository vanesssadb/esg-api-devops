package br.com.fiap.atv_cap_8.web;

import br.com.fiap.atv_cap_8.domain.ReductionSchedule;
import br.com.fiap.atv_cap_8.repository.ReductionScheduleRepository;
import br.com.fiap.atv_cap_8.web.dto.ReductionScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reduction-schedules")
@RequiredArgsConstructor
public class ReductionScheduleController {

    private final ReductionScheduleRepository repo;

    @GetMapping
    public List<ReductionSchedule> list(@RequestParam(required = false) Long companyId,
                                        @RequestParam(required = false) String status) {
        if (companyId != null) return repo.findByCompanyId(companyId);
        if (status != null) return repo.findByStatus(status);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ReductionSchedule byId(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ReductionSchedule create(@Valid @RequestBody ReductionScheduleDTO dto) {
        ReductionSchedule r = ReductionSchedule.builder()
                .companyId(dto.companyId())
                .title(dto.title())
                .plannedDate(dto.plannedDate())
                .status(dto.status())
                .expectedReductionTco2e(dto.expectedReductionTco2e())
                .build();
        return repo.save(r);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ReductionSchedule update(@PathVariable Long id, @Valid @RequestBody ReductionScheduleDTO dto) {
        ReductionSchedule r = repo.findById(id).orElseThrow();
        r.setCompanyId(dto.companyId());
        r.setTitle(dto.title());
        r.setPlannedDate(dto.plannedDate());
        r.setStatus(dto.status());
        r.setExpectedReductionTco2e(dto.expectedReductionTco2e());
        return repo.save(r);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
