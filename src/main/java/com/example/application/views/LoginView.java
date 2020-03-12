package com.example.application.views;

import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;

@Route(value = "login")
@ParentLayout(MainLayout.class)
@PageTitle("Login")
public class LoginView extends FlexLayout {

  public LoginView() {
    LoginOverlay login = new LoginOverlay();
    login.setOpened(true);
    login.setForgotPasswordButtonVisible(true);
    login.setTitle("Welcome to this broken app.");
    login.setAction("/login_process");
    login.setDescription("Creation was necessary but emberasing");
    this.add(login);
//    login.addLoginListener(loginEvent -> loginEvent);
  }

}

