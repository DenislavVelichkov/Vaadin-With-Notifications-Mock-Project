package com.example.application.views;

import java.util.Objects;

import com.example.application.backend.data.models.UserDTO;
import com.example.application.service.UserService;
import com.example.application.views.binding.UserBindingModel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = RegisterView.ROUTE, layout = MainIndexLayout.class)
@PageTitle(RegisterView.TITLE)
public class RegisterView extends VerticalLayout {
  public static final String ROUTE = "register";
  public static final String TITLE = "Register as a NEW user";
  public static final String REQUIRED_FIELD = "Required...";
  public static final String PASSWORD_LENGTH =
      "Your password must be atleast 4 symbols long.";
  public static final String PASSWORD_VALIDATION =
      "Your password must match Confirm password.";
  private static final String EMAIL_VALIDATION_ERROR =
      "Are sure you've entered a correct email address?";
  private static final String NAME_VALIDATION_ERROR =
      "Your name must be more then 1 letter long";
  private final UserService userService;
  private final ModelMapper modelMapper;
  Binder<UserBindingModel> binder = new Binder<>();

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
    binder.forField(email)
          .asRequired(REQUIRED_FIELD)
          .withValidator(new EmailValidator(EMAIL_VALIDATION_ERROR))
          .bind(UserBindingModel::getEmail, UserBindingModel::setEmail);

    TextField firstName = new TextField();
    firstName.setWidthFull();
    registerForm.addFormItem(firstName, "First name");
    binder.forField(firstName)
          .asRequired(REQUIRED_FIELD)
          .withValidator(name ->
              name.length() >= 2, NAME_VALIDATION_ERROR)
          .bind(UserBindingModel::getFirstName, UserBindingModel::setFirstName);

    TextField lastName = new TextField();
    lastName.setWidthFull();
    registerForm.addFormItem(lastName, "Last name");
    binder.forField(lastName)
          .asRequired(REQUIRED_FIELD)
          .withValidator(name ->
              name.length() >= 2, NAME_VALIDATION_ERROR)
          .bind(UserBindingModel::getLastName, UserBindingModel::setLastName);

    PasswordField password = new PasswordField();
    password.setWidthFull();
    registerForm.addFormItem(password, "Password");

    binder.forField(password)
          .asRequired(REQUIRED_FIELD)
          .withValidator(pass ->
              pass.length() >= 4, PASSWORD_LENGTH)

          .bind(UserBindingModel::getPassword, UserBindingModel::setPassword);

    PasswordField confirmPassword = new PasswordField();
    confirmPassword.setWidthFull();
    registerForm.addFormItem(confirmPassword, "Confirm password");
    binder.forField(confirmPassword)
          .asRequired(REQUIRED_FIELD)
          .withValidator(pass ->
              pass.length() >= 4, PASSWORD_LENGTH)
          .bind(UserBindingModel::getConfirmPassword, UserBindingModel::setConfirmPassword);

    TextField jobTitle = new TextField();
    jobTitle.setWidthFull();
    registerForm.addFormItem(jobTitle, "Role");
    binder.forField(confirmPassword)
          .asRequired(REQUIRED_FIELD)
          .bind(UserBindingModel::getJobTitle, UserBindingModel::setJobTitle);

    this.add(new H4("Please register if you want to use this awesome app (:"));

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

    this.add(registerForm);
  }

}
