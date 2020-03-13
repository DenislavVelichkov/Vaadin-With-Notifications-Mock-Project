package com.example.application.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

@Route(value = "login", layout = MainLayout.class)
@PageTitle("Login")
public class LoginView extends FlexLayout implements AfterNavigationObserver {
  public final static String ROUTE = "login";

  private final LoginOverlay login;

  @Autowired
  public LoginView() {
    this.login = new LoginOverlay();

    initLoginStructure();
  }

  private void initLoginStructure() {

    login.setForgotPasswordButtonVisible(false);
    login.setAction("login");

    add(login);
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    login.setOpened(true);
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    login.setError(
        event.getLocation().getQueryParameters().getParameters().containsKey(
            "error"));
  }

}

