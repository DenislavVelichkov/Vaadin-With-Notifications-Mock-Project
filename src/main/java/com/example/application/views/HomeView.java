package com.example.application.views;

import java.awt.*;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "")
public class HomeView extends FlexLayout {
  public HomeView() {
    add(new H4("HOME WOOOORKS !"));
    Button logout = new Button();
    logout.addClickListener(event -> {
      UI.getCurrent().navigate("/logout");
    });
    this.add(logout);
  }
}
