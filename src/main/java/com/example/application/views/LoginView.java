package com.example.application.views;

import com.example.application.backend.config.security.CustomRequestCache;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = "login", layout = MainLayout.class)
@PageTitle("Login")
public class LoginView extends FlexLayout{
  public final static String ROUTE = "login";

  public final AuthenticationManager authenticationManager;
  public final CustomRequestCache requestCache;

  @Autowired
  public LoginView(AuthenticationManager authenticationManager,
                   CustomRequestCache requestCache) {
    this.authenticationManager = authenticationManager;
    this.requestCache = requestCache;

    initLoginStructure();
  }

  private void initLoginStructure() {
    LoginOverlay login = new LoginOverlay();
    login.setOpened(true);
    login.setForgotPasswordButtonVisible(true);
    login.setTitle("Welcome to this broken app.");
    login.setAction("/login_process");
    login.setDescription("Creation was necessary but emberasing (:");

    this.add(login);

    login.addLoginListener(e -> { // (3)
      try {
        // try to authenticate with given credentials, should always return not null or throw an {@link AuthenticationException}
        final Authentication authentication = this.authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(e.getUsername(), e.getPassword())); // (4)

        // if authentication was successful we will update the security context and redirect to the page requested first
        SecurityContextHolder.getContext().setAuthentication(authentication); // (5)
        login.close(); // (6)
        UI.getCurrent().navigate(this.requestCache.resolveRedirectUrl()); // (7)

      } catch (AuthenticationException ex) { // (8)
        // show default error message
        // Note: You should not expose any detailed information here like "username is known but password is wrong"
        // as it weakens security.
        login.setError(true);
      }
    });
  }

}

