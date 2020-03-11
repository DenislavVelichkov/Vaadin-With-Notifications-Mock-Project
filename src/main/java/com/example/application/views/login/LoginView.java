package com.example.application.views.login;

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

@Route(value = LoginView.ROUTE)
@PageTitle("Login")
@HtmlImport("frontend://bower_components/iron-form/iron-form.html")
public class LoginView extends VerticalLayout {

  public static final String ROUTE = "login";

  public LoginView() {
    TextField userNameTextField = new TextField();
    userNameTextField.getElement().setAttribute("name", "email");
    PasswordField passwordField = new PasswordField();
    passwordField.getElement().setAttribute("name", "password");
    Button submitButton = new Button("Login");
    submitButton.setId("submitbutton");
    UI.getCurrent().getPage().executeJs(
        "document" +
            ".getElementById('submitbutton')" +
            ".addEventListener('click', () => document.getElementById('login-ironform').submit());");

    FormLayout formLayout = new FormLayout();
    formLayout.add(userNameTextField, passwordField, submitButton);

    Element formElement = new Element("form");
    formElement.setAttribute("method", "post");
    formElement.setAttribute("action", "login");
    formElement.appendChild(formLayout.getElement());

    Element ironForm = new Element("iron-form");
    ironForm.setAttribute("id", "login-ironform");
    ironForm.setAttribute("allow-redirect", true);
    ironForm.appendChild(formElement);

    this.getElement().appendChild(ironForm);

    Element h4 = new Element("h4");
    h4.setText("If you don't have an account please register ->");
    Element btnLink = new Element("a");
    btnLink.setText("Register");
    btnLink.setAttribute("href", "/register");
    this.getElement().appendChild(h4);
    this.getElement().appendChild(btnLink);

    this.setClassName("login-view");
  }
}

