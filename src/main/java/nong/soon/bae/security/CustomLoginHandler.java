package nong.soon.bae.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomLoginHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
				
	/*	String username = authentication.getName();
		logger.info("========="+username+"=========");
		UserDetails userDetails;
		try {
			userDetails = customUserDetailsService.loadUserByUsername(username);
			Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
			ModelAndView model = new ModelAndView();
			model.addObject("users", userDetails);
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		*/

		String rdir = "/nsb/main";
		if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            rdir = "/admin/usercheck";
        } else if (authorities.contains(new SimpleGrantedAuthority("USER"))) {
            rdir = "/user/mypage";
        } else if(authorities.contains(new SimpleGrantedAuthority("DELETEUSER"))) {
        	rdir = "/member/logout?error=true";
        }
		response.sendRedirect(rdir);
	}
	
}
