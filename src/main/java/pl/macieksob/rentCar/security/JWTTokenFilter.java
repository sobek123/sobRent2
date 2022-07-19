//package pl.macieksob.rentCar.security;
//
//import io.jsonwebtoken.Claims;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//import pl.macieksob.rentCar.model.Role;
//import pl.macieksob.rentCar.model.User;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JWTTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTTokenUtility jwtTokenUtility;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authorization = request.getHeader("Authorization");
//
//        if(!hasAuthorizationHeader(request)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String accessToken = getAccessToken(request);
//
//        if(!jwtTokenUtility.validateAccessToken(accessToken)){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        setAuthenticationContext(accessToken,request);
//        filterChain.doFilter(request, response);
//
//    }
//
//    private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
//        UserDetails userDetails = getUserDetails(accessToken);
//
//        UsernamePasswordAuthenticationToken authenticationToken
//                = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//    }
//
//    private UserDetails getUserDetails(String accessToken){
//        User userDetails = new User();
//        Claims claims = jwtTokenUtility.parseClaims(accessToken);
//
//        String claimRoles = (String)claims.get("roles");
//        claimRoles = claimRoles.replace("[","").replace("]","");
//        String[] roleNames = claimRoles.split(",");
//
//        for(String roleName:roleNames){
//            userDetails.addRole(new Role(roleName));
//        }
//        String subject = (String) claims.get(Claims.SUBJECT);
//        String[] split = jwtTokenUtility.getSubject(accessToken).split(",");
//
//
//        userDetails.setId((long) Integer.parseInt(split[0]));
//        userDetails.setEmail(split[1]);
//
//        return userDetails;
//    }
//
//    private boolean hasAuthorizationHeader(HttpServletRequest httpServletRequest){
//        String authorization = httpServletRequest.getHeader("Authorization");
//
//        if(ObjectUtils.isEmpty(httpServletRequest) || !authorization.startsWith("Bearer")){
//            return false;
//        }
//
//            return true;
//    }
//
//    private String getAccessToken(HttpServletRequest request){
//        String authorization = request.getHeader("Authorization");
//        String token = authorization.split(" ")[1].trim();
//
//        return token;
//    }
//}
