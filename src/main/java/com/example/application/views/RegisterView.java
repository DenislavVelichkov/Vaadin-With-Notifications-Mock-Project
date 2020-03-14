package com.example.application.views;

import com.example.application.backend.data.models.UserDTO;
import com.example.application.service.UserService;
import com.example.application.views.binding.UserBindingModel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = RegisterView.ROUTE, layout = MainIndexLayout.class)
@PageTitle(RegisterView.TITLE)
public class RegisterView extends VerticalLayout {
  public static final String ROUTE = "register";
  public static final String TITLE = "Register as a new user";
  private final UserService userService;
  private final ModelMapper modelMapper;

  @Autowired
  public RegisterView(UserService userService,
                      ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;

    this.initViewStructure();
  }

  private void initViewStructure() {
    this.setMaxWidth("35%");
    this.setAlignItems(Alignment.CENTER);
    this.setPadding(true);
    this.getElement().getStyle().set("margin", "15% 32% 0");

    FormLayout registerForm = new FormLayout();
//    Form will be always 1 column width
    registerForm.setResponsiveSteps(
        new FormLayout.ResponsiveStep("0", 1));

    TextField email = new TextField();
    email.setWidthFull();
    registerForm.addFormItem(email, "Email");

    TextField firstName = new TextField();
    firstName.setWidthFull();
    registerForm.addFormItem(firstName, "First name");

    TextField lastName = new TextField();
    lastName.setWidthFull();
    registerForm.addFormItem(lastName, "Last name");

    PasswordField password = new PasswordField();
    password.setWidthFull();
    registerForm.addFormItem(password, "Password");

    PasswordField confirmPassword = new PasswordField();
    confirmPassword.setWidthFull();
    registerForm.addFormItem(confirmPassword, "Confirm password");

    TextField jobTitle = new TextField();
    jobTitle.setWidthFull();
    registerForm.addFormItem(jobTitle, "Role");

    this.add(new H4("Please register if you want to use this awesome app (:"));

    Button registerButton = new Button("Register");;
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

    this.add(registerForm);
  }

}
