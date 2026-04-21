package br.com.fiap.atv_cap_8.web;

import br.com.fiap.atv_cap_8.security.JwtService;
import br.com.fiap.atv_cap_8.web.dto.LoginRequest;
import br.com.fiap.atv_cap_8.web.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwt;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        var claims = new HashMap<String, Object>();
        claims.put("roles", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());

        String token = jwt.generate(req.username(), claims);
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
