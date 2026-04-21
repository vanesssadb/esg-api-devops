package br.com.fiap.atv_cap_8.repository;

import br.com.fiap.atv_cap_8.domain.EmissionRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface EmissionRecordRepository extends JpaRepository<EmissionRecord, Long> {

    @Query("""
      SELECT e FROM EmissionRecord e
       WHERE (:companyId IS NULL OR e.companyId = :companyId)
         AND (:scope IS NULL OR e.scope = :scope)
         AND (:from IS NULL OR e.recordDate >= :from)
         AND (:to   IS NULL OR e.recordDate <= :to)
      ORDER BY e.recordDate DESC
    """)
    List<EmissionRecord> search(@Param("companyId") Long companyId,
                                @Param("scope") String scope,
                                @Param("from") LocalDate from,
                                @Param("to") LocalDate to);

    @Query("SELECT COALESCE(SUM(e.amountTco2e), 0) FROM EmissionRecord e WHERE e.companyId = :companyId")
    BigDecimal sumByCompany(@Param("companyId") Long companyId);
}
