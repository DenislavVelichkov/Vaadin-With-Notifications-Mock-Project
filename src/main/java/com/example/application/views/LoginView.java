package com.example.application.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = LoginView.ROUTE, layout = MainIndexLayout.class)
@PageTitle(LoginView.TITLE)
public class LoginView extends FlexLayout implements AfterNavigationObserver {
  public final static String ROUTE = "login";
  public final static String TITLE = "Login or Sign up";

  private final LoginOverlay login;

  public LoginView() {
    this.login = new LoginOverlay();

    initLoginStructure();
  }

  private void initLoginStructure() {
    this.login.setForgotPasswordButtonVisible(false);
    this.login.setAction("login");
    this.login.setDescription("Please register if you don't have an account");

    Button registerBtn = new Button("Register");
    registerBtn.addClickListener(event -> {
      UI.getCurrent().navigate(RegisterView.ROUTE);
    });

    registerBtn.getStyle().set("background", "green");
    registerBtn.getStyle().set("color", "white");

    this.login.setTitle(registerBtn);

    this.add(login);
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    this.login.setOpened(true);
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    this.login.setError(
        event.getLocation().getQueryParameters().getParameters().containsKey(
            "error"));
  }

}

