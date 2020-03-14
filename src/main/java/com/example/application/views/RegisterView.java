package com.example.application.views;

import com.example.application.backend.data.models.UserDTO;
import com.example.application.service.UserService;
import com.example.application.views.binding.UserBindingModel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "register", layout = MainLayout.class)
@PageTitle("Register")
public class RegisterView extends FlexLayout{
  private final UserService userService;
  private final ModelMapper modelMapper;

  private final FlexLayout container;

  @Autowired
  public RegisterView(UserService userService,
                      ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
    container = new FlexLayout();

    this.initViewStructure();
  }

  private void initViewStructure() {
    this.setAlignSelf(Alignment.CENTER);
    container.setClassName("register-form-container");
    container.add(new H4("Please register if you want to use this awesome app (:"));
    container.setSizeFull();
    container.getElement().getStyle().set("align-content", "center");
    container.getElement().getStyle().set("text-align", "center");
    container.getElement().getStyle().set("margin", "0 3rem 0");

    FormLayout registerForm = new FormLayout();
    registerForm.getElement().getStyle().set("display", "flex");
    registerForm.getElement().getStyle().set("flex", "100% 0 0");

    TextField email = new TextField();
    email.setLabel("email");
    TextField password = new TextField();
    password.setLabel("Password");
    TextField confirmPassword = new TextField();
    confirmPassword.setLabel("Confirm Password");
    TextField jobTitle = new TextField();
    jobTitle.setLabel("Job title");
    TextField firstName = new TextField();
    firstName.setLabel("First name");
    TextField lastName = new TextField();
    lastName.setLabel("Last name");

    registerForm.add(email);
    registerForm.add(password);
    registerForm.add(confirmPassword);
    registerForm.add(jobTitle);
    registerForm.add(firstName);
    registerForm.add(lastName);

    Button registerButton = new Button("Register");
    registerForm.add(registerButton);

    registerButton.addClickListener(event -> {
      UserBindingModel bindingModel = new UserBindingModel();
      bindingModel.setPassword(password.getValue());
      bindingModel.setEmail(email.getValue());
      bindingModel.setFirstName(firstName.getValue());
      bindingModel.setLastName(lastName.getValue());
      bindingModel.setJobTitle(jobTitle.getValue());

      this.userService.registerUser(
          this.modelMapper.map(bindingModel, UserDTO.class));
    });

    container.add(registerForm);
    this.add(container);
  }

}
