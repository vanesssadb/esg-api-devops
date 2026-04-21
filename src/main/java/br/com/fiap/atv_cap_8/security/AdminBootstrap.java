package br.com.fiap.atv_cap_8.security;

import br.com.fiap.atv_cap_8.domain.Role;
import br.com.fiap.atv_cap_8.domain.UserAccount;
import br.com.fiap.atv_cap_8.repository.RoleRepository;
import br.com.fiap.atv_cap_8.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminBootstrap implements CommandLineRunner {

    private final RoleRepository roleRepo;
    private final UserAccountRepository userRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        // garante roles
        Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_ADMIN").build()));
        roleRepo.findByName("ROLE_USER")
                .orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_USER").build()));

        userRepo.findByUsername("admin").ifPresentOrElse(u -> {
            // atualiza a senha do admin para "admin" (BCrypt)
            u.setPassword(encoder.encode("admin"));
            if (u.getRoles() == null) u.setRoles(new HashSet<>());
            u.getRoles().add(adminRole);
            u.setEnabled(true);
            userRepo.save(u);
        }, () -> {
            // cria admin se não existir
            UserAccount u = UserAccount.builder()
                    .username("admin")
                    .password(encoder.encode("admin"))
                    .enabled(true)
                    .roles(new HashSet<>(Set.of(adminRole)))
                    .build();
            userRepo.save(u);
        });
    }
}
