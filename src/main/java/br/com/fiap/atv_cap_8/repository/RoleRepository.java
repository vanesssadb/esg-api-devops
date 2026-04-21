package br.com.fiap.atv_cap_8.repository;

import br.com.fiap.atv_cap_8.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
