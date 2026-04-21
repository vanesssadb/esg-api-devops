package br.com.fiap.atv_cap_8.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name; // ex: ROLE_ADMIN, ROLE_USER
}
