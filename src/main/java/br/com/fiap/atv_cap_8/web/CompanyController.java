package br.com.fiap.atv_cap_8.web;

import br.com.fiap.atv_cap_8.domain.Company;
import br.com.fiap.atv_cap_8.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyRepository repo;

    @GetMapping
    public List<Company> list() { return repo.findAll(); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Company create(@RequestBody Company c) { return repo.save(c); }
}
