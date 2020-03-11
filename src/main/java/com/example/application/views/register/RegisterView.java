package com.example.application.views.register;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route(value = "register")
@JsModule("./styles/shared-styles.js")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class RegisterView extends VerticalLayout {

  public RegisterView() {
    Element el = new Element("h4");
    el.setText("This is a register page! Routing works!");
    this.getElement().appendChild(el);

    this.setClassName("register-view");
  }
}
