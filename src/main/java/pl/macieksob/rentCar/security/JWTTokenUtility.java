//package pl.macieksob.rentCar.security;
//
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import pl.macieksob.rentCar.model.User;
//
//import java.util.Date;
//
//@Component
//public class JWTTokenUtility {
//
//    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
//
//    @Value("${app.jwt.secretKey}")
//    private String secretKey;
//
//    public String generateAccessToken(User user){
//        return Jwts.builder().
//                setSubject(user.getId() + "," + user.getEmail())
//                .claim("roles",user.getRoles().toString())
//                .setIssuer("RentCar")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
//                .signWith(SignatureAlgorithm.HS512,secretKey)
//                .compact();
//    }
//
//    public boolean validateAccessToken(String token){
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//        }catch (ExpiredJwtException ex){
//            ex.getMessage();
//        }catch (IllegalArgumentException ex){
//            ex.getMessage();
//        }catch (MalformedJwtException ex){
//            ex.getMessage();
//        }catch (UnsupportedJwtException ex){
//            ex.getMessage();
//        }catch (SignatureException ex){
//            ex.getMessage();
//        }
//        return false;
//    }
//
//    public String getSubject(String token){
//        return parseClaims(token).getSubject();
//    }
//
//    public Claims parseClaims(String token){
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
