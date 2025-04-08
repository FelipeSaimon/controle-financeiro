package com.saimon.controle_financeiro.infra.security.JTW;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JWTCreator {
    static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String ROLES_AUTHORITIES = "roles";

    /**
     * Cria um token JWT com as informações do usuário
     *
     * @param prefixo Prefixo do token (geralmente "Bearer")
//     * @param chave   Chave secreta para assinar o token
     * @param jwtObject Objeto contendo as informações do usuário
     * @return Token JWT completo (com prefixo)
     */

    public static String create(String prefixo, String chave, JWTObject jwtObject){
        SecretKey key = Keys.hmacShaKeyFor(chave.getBytes(StandardCharsets.UTF_8));

        String token = Jwts.builder()
                .subject(jwtObject.getUsuario())
                .issuedAt(jwtObject.getDataDeCriacao())
                .expiration(jwtObject.getDataDeExpiracao())
                .claim(ROLES_AUTHORITIES, jwtObject.getRoles())
                .signWith(key)
                .compact();

        return prefixo + " " + token;
    }

    /**
     * Decodifica um token JWT e cria um objeto JWTObject com as informações
     *
     * @param prefixo Prefixo do token (geralmente "Bearer")
     * @param chave   Chave secreta usada para assinar o token
     * @param token   Token JWT completo (com prefixo)
     * @return Objeto JWTObject com as informações extraídas do token
     */
    public static JWTObject decode(String prefixo, String chave, String token){
        JWTObject jwtObject = new JWTObject();

        token = token.replace(prefixo + " ", "");

        SecretKey key = Keys.hmacShaKeyFor(chave.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();


        jwtObject.setUsuario(claims.getSubject());
        jwtObject.setDataDeCriacao(claims.getIssuedAt());
        jwtObject.setDataDeExpiracao(claims.getExpiration());
        jwtObject.setRoles((List) claims.get(ROLES_AUTHORITIES));

        return jwtObject;
    }

    /**
     * Garante que todas as roles tenham o prefixo "ROLE_"
     *
     * @param roles Lista de roles para verificar
     * @return Lista de roles normalizada
     */
    public static List<String> checkRoles(List<String> roles) {
        return roles.stream()
                .map(s -> "ROLE_".concat(s.replaceAll("ROLE_", "")))
                .toList();
    }
}
