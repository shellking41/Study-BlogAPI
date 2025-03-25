package org.study.studyblogapi.security.config;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.study.studyblogapi.security.token.TokenRepository;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

  private final TokenRepository tokenRepository;
  private final JwtService jwtService;
  private static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
  private static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";

  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {

    String accessToken=jwtService.getTokenFromCookies(request,ACCESS_TOKEN_COOKIE_NAME);

    var storedToken = tokenRepository.findByToken(accessToken)
        .orElse(null);
    if (storedToken != null) {
      storedToken.setExpired(true);
      storedToken.setRevoked(true);
      tokenRepository.save(storedToken);


      clearCookie(response,ACCESS_TOKEN_COOKIE_NAME);
      clearCookie(response,REFRESH_TOKEN_COOKIE_NAME);

      SecurityContextHolder.clearContext();
    }
  }
  private void clearCookie(HttpServletResponse response, String name) {
    Cookie cookie = new Cookie(name, "");
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);
  }
}
