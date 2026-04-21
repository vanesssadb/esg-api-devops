package br.com.fiap.atv_cap_8.repository;

import br.com.fiap.atv_cap_8.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {}
