package br.com.fiap.atv_cap_8.repository;

import br.com.fiap.atv_cap_8.domain.ReductionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReductionScheduleRepository extends JpaRepository<ReductionSchedule, Long> {
    List<ReductionSchedule> findByCompanyId(Long companyId);
    List<ReductionSchedule> findByStatus(String status);
}
