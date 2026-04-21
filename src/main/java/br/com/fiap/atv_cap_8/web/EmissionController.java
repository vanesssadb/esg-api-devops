package br.com.fiap.atv_cap_8.web;

import br.com.fiap.atv_cap_8.domain.EmissionRecord;
import br.com.fiap.atv_cap_8.repository.EmissionRecordRepository;
import br.com.fiap.atv_cap_8.web.dto.EmissionRecordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emissions")
@RequiredArgsConstructor
public class EmissionController {

    private final EmissionRecordRepository repo;

    @GetMapping
    public List<EmissionRecord> search(@RequestParam(required = false) Long companyId,
                                       @RequestParam(required = false) String scope,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return repo.search(companyId, scope, from, to);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EmissionRecord create(@Valid @RequestBody EmissionRecordDTO dto) {
        EmissionRecord e = EmissionRecord.builder()
                .companyId(dto.companyId())
                .recordDate(dto.recordDate())
                .scope(dto.scope())
                .amountTco2e(dto.amountTco2e())
                .build();
        return repo.save(e);
    }
}
