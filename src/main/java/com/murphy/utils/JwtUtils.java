package com.murphy.utils;

import cn.hutool.Hutool;
import cn.hutool.core.convert.Convert;
import com.murphy.constants.ConfigConstant;
import com.murphy.constants.UserConstant;
import com.murphy.model.entity.sys.SysUser;
import com.murphy.model.vo.LoginUserVO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author admin
 */

public class JwtUtils {


    /**
     * 盐
     */
    private static final String SALT_KEY = ConfigConstant.SALT_KEY;


    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    /**
     * Base64 密钥
     */
    private final static String SECRET_KEY =  Base64.getEncoder().encodeToString(SALT_KEY.getBytes(StandardCharsets.UTF_8));


    /**
     * 根据用户名和用户角色生成 token
     *
     * @param userName   用户名
     * @param roles      用户角色
     * @param isRemember 是否记住我
     * @return 返回生成的 token
     */
    public static String generateToken(String userName, List<String> roles, boolean isRemember) {
        byte[] jwtSecretKey = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        // 过期时间
        long expiration = isRemember ? ConfigConstant.EXPIRATION_REMEMBER_TIME : ConfigConstant.EXPIRATION_TIME;
        // 生成 token
        String token = Jwts.builder()
                // 生成签证信息
                .setHeaderParam("typ", ConfigConstant.TOKEN_TYPE)
                .signWith(Keys.hmacShaKeyFor(jwtSecretKey), SignatureAlgorithm.HS256)
                .setSubject(userName)
                .claim(ConfigConstant.TOKEN_ROLE_CLAIM, roles)
                .setIssuer(ConfigConstant.TOKEN_ISSUER)
                .setIssuedAt(new Date())
                .setAudience(ConfigConstant.TOKEN_AUDIENCE)
                // 设置有效时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        return token;
    }

    /**
     * 验证 token 是否有效
     *
     * <p>
     * 如果解析失败，说明 token 是无效的
     *
     * @param token token 信息
     * @return 如果返回 true，说明 token 有效
     */
    public static boolean validateToken(String token) {
        try {
            getTokenBody(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.warn("Request to parse expired JWT : {} failed : {}", token, e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.warn("Request to parse unsupported JWT : {} failed : {}", token, e.getMessage());
        } catch (MalformedJwtException e) {
            logger.warn("Request to parse invalid JWT : {} failed : {}", token, e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.warn("Request to parse empty or null JWT : {} failed : {}", token, e.getMessage());
        }
        return false;
    }


    /**
     * 根据 token 获取用户认证信息
     *
     * @param token token 信息
     * @return 返回用户认证信息
     */
    public static Authentication getAuthentication(String token) {
        Claims claims = getTokenBody(token);
        // 获取用户角色字符串
        List<String> roles = (List<String>)claims.get(ConfigConstant.TOKEN_ROLE_CLAIM);
        List<SimpleGrantedAuthority> authorities =
                Objects.isNull(roles) ? Collections.singletonList(new SimpleGrantedAuthority(UserConstant.ROLE_USER)) :
                        roles.stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
        // 获取用户名
        String userName = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(userName, token, authorities);

    }




    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }


    public static void main(String[] args) throws Exception {
        SysUser user = new SysUser();
        user.setId("0").setUsername("admin").setNickname("abc");

        System.err.println();


    }
}
