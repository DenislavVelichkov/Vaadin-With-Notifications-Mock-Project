package com.example.application.views.login;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = LoginView.ROUTE, layout = MainView.class)
@PageTitle("Login")
@HtmlImport("frontend://bower_components/iron-form/iron-form.html") // (1)
public class LoginView extends VerticalLayout {
  public static final String ROUTE = "login";

  public LoginView() {
    TextField userNameTextField = new TextField();
    userNameTextField.getElement().setAttribute("name", "email"); // (2)
    PasswordField passwordField = new PasswordField();
    passwordField.getElement().setAttribute("name", "password"); // (3)
    Button submitButton = new Button("Login");
    submitButton.setId("submitbutton"); // (4)
    UI.getCurrent().getPage().executeJs("document.getElementById('submitbutton').addEventListener('click', () => document.getElementById('ironform').submit());"); // (5)

    FormLayout formLayout = new FormLayout(); // (6)
    formLayout.add(userNameTextField, passwordField, submitButton);

    Element formElement = new Element("form"); // (7)
    formElement.setAttribute("method", "post");
    formElement.setAttribute("action", "login");
    formElement.appendChild(formLayout.getElement());

    Element ironForm = new Element("iron-form"); // (8)
    ironForm.setAttribute("id", "ironform");
    ironForm.setAttribute("allow-redirect", true); // (9)
    ironForm.appendChild(formElement);

    getElement().appendChild(ironForm); // (10)

    setClassName("login-view");
  }
}

