package br.com.fiap.atv_cap_8.repository;

import br.com.fiap.atv_cap_8.domain.OffsetProject;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface OffsetProjectRepository extends JpaRepository<OffsetProject, Long> {

    @Query("SELECT COALESCE(SUM(o.amountTco2e), 0) FROM OffsetProject o WHERE o.companyId = :companyId")
    BigDecimal totalOffsets(@Param("companyId") Long companyId);
}
