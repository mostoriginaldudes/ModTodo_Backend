package com.mod.todo.service;

import com.mod.todo.domain.user.User;
import com.mod.todo.error.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private static final String SALT = "modtodo";

    @Override
    public String create(User subject){
        String jwt = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("regDate",System.currentTimeMillis())
                .setSubject(subject.getUserPhoneNumber())
                .claim("userId", subject.getUserId())
                .claim("userPhoneNumber",subject.getUserPhoneNumber())
                .claim("userName",subject.getUserName())
                .setExpiration(new Date(System.currentTimeMillis()+(1000*60*60)))
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact(); //직렬화
        return jwt;
    }

    @Override
    public boolean isUsable(String jwt) throws Exception {
        try{
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);
            return true;
        }catch (Exception e){
            throw new UnauthorizedException();
        }
    }

    private byte[] generateKey(){
        byte[] key = null;
        key = SALT.getBytes(UTF_8);
        return key;
    }

    @Override
    //jwt에 있는 데이터 가져오고 싶을 때,
    public Object get(String key) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(SALT.getBytes("UTF-8"))
                    .parseClaimsJws(jwt);
        } catch (UnsupportedEncodingException e) {
            throw new Exception();
        }
        Object value = claims.getBody().get(key);
        System.out.println(value);
        return value;
    }
}
