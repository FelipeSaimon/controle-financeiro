package com.saimon.controle_financeiro.infra.security.JTW;

import com.saimon.controle_financeiro.infra.security.SecurityConfigurations;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader(JWTCreator.HEADER_AUTHORIZATION);

            if (token != null) {
                if (token.startsWith("Bearer ")) {
                    token = token.replace("Bearer ", "").trim();
                }

                JWTObject tokenObject = JWTCreator.decode(SecurityConfigurations.PREFIXO, SecurityConfigurations.CHAVE, token);
                List<SimpleGrantedAuthority> authorities = convertToAuthorities(tokenObject.getRoles());
                UsernamePasswordAuthenticationToken usuarioToken =
                        new UsernamePasswordAuthenticationToken(tokenObject.getUsuario(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(usuarioToken);
            } else {
                SecurityContextHolder.clearContext();
            }

            filterChain.doFilter(request, response);
        }catch (JwtException exception){
            logger.error("Erro na validação do token, {" + exception.getMessage() + "}");

            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("Token inválido ou expirado");
        }
    }

    /**
     * Converte a lista de roles em objetos SimpleGrantedAuthority
     *
     * @param roles Lista de roles como strings
     * @return Lista de autoridades concedidas
     */
    private List<SimpleGrantedAuthority> convertToAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
