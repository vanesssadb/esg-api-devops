package br.com.fiap.atv_cap_8.web;

import br.com.fiap.atv_cap_8.domain.OffsetProject;
import br.com.fiap.atv_cap_8.repository.OffsetProjectRepository;
import br.com.fiap.atv_cap_8.web.dto.OffsetProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/offsets")
@RequiredArgsConstructor
public class OffsetProjectController {

    private final OffsetProjectRepository repo;

    @GetMapping
    public List<OffsetProject> list() { return repo.findAll(); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public OffsetProject create(@Valid @RequestBody OffsetProjectDTO dto) {
        OffsetProject o = OffsetProject.builder()
                .companyId(dto.companyId())
                .type(dto.type())
                .description(dto.description())
                .amountTco2e(dto.amountTco2e())
                .startDate(dto.startDate())
                .build();
        return repo.save(o);
    }
}
