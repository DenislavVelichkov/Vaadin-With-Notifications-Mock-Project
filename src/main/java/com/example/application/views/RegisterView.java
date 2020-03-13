package com.example.application.views;

import com.example.application.backend.data.models.UserDTO;
import com.example.application.service.UserService;
import com.example.application.views.binding.UserBindingModel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "register")
@PageTitle("Register")
@ParentLayout(MainLayout.class)
public class RegisterView extends FlexLayout {
  private final UserService userService;
  private final ModelMapper modelMapper;

  @Autowired
  public RegisterView(UserService userService,
                      ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;

    FormLayout registerForm = new FormLayout();
    TextField email = new TextField();
    email.setTitle("email");
    TextField password = new TextField();
    password.setTitle("password");
    TextField jobTitle = new TextField();
    jobTitle.setTitle("job title");
    TextField firstName = new TextField();
    jobTitle.setTitle("First name");
    TextField lastName = new TextField();
    jobTitle.setTitle("Last name");
    registerForm.add(email);
    registerForm.add(password);
    registerForm.add(jobTitle);
    registerForm.add(firstName);
    registerForm.add(lastName);
    Button registerButton = new Button("Register");
    registerForm.add(registerButton);

    registerButton.addClickListener(event -> {
      UserBindingModel userDTO = new UserBindingModel();
      userDTO.setPassword(password.getValue());
      userDTO.setEmail(email.getValue());
      userDTO.setFirstName(firstName.getValue());
      userDTO.setLastName(lastName.getValue());
      userDTO.setJobTitle(jobTitle.getValue());

      this.userService.registerUser(
          this.modelMapper.map(userDTO, UserDTO.class));
    });

    this.add(registerForm);
  }

}
