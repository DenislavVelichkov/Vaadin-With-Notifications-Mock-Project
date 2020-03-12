package com.example.application.views;

import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import org.jboss.jandex.Main;

@Route(value = "register")
@ParentLayout(MainLayout.class)
public class RegisterView extends FlexLayout {

  public RegisterView() {

  }
}
