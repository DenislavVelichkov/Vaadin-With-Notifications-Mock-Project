package com.example.application.views.masterdetail;

import com.example.application.backend.data.models.CSPNotificationDTO;
import com.example.application.backend.data.models.EmployeeDTO;
import com.example.application.service.CSPNotificationService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Home")
@CssImport("styles/views/masterdetail/master-detail-view.css")
public class MasterDetailView extends Div implements AfterNavigationObserver {

  private final CSPNotificationService notificationService;

  private Grid<EmployeeDTO> employees;

  private TextField email = new TextField();
  private TextField notificationTopic = new TextField();
  private TextField notificationContent = new TextField();

  private Button cancel = new Button("Cancel");
  private Button send = new Button("Send");

  private Binder<EmployeeDTO> binder;

  @Autowired
  public MasterDetailView(CSPNotificationService notificationService) {
    this.notificationService = notificationService;

    setId("detail-view");
    // Configure Grid
    employees = new Grid<>();
    employees.addThemeVariants(GridVariant.LUMO_NO_BORDER);
    employees.setHeightFull();
    employees.addColumn(EmployeeDTO::getFirstname).setHeader("First name");
    employees.addColumn(EmployeeDTO::getLastname).setHeader("Last name");
    employees.addColumn(EmployeeDTO::getEmail).setHeader("Email");

    //when a row is selected or deselected, populate form
    employees.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

    // Configure Form
    binder = new Binder<>(EmployeeDTO.class);

    // Bind fields. This where you'd define e.g. validation rules

    binder.bindInstanceFields(this);
    // note that password field isn't bound since that property doesn't exist in
    // Employee

    // the grid valueChangeEvent will clear the form too
    cancel.addClickListener(e -> employees.asSingleSelect().clear());

    send.addClickListener(e -> {
//            Notification.show("Not implemented");
// TODO: 3/10/2020 Implement "Send Notifications"

      CSPNotificationDTO notificationDTO =
          this.notificationService.createNotification(
              email.getValue(),
              notificationTopic.getValue(),
              notificationContent.getValue());
    });

    SplitLayout splitLayout = new SplitLayout();
    splitLayout.setSizeFull();

    createGridLayout(splitLayout);
    createEditorLayout(splitLayout);

    add(splitLayout);
  }

  private void createEditorLayout(SplitLayout splitLayout) {
    Div editorDiv = new Div();
    editorDiv.setId("editor-layout");
    FormLayout formLayout = new FormLayout();
    addFormItem(editorDiv, formLayout, email, "Email");
    addFormItem(editorDiv, formLayout, notificationContent, "Content");
    createButtonLayout(editorDiv);
    splitLayout.addToSecondary(editorDiv);
  }

  private void createButtonLayout(Div editorDiv) {
    HorizontalLayout buttonLayout = new HorizontalLayout();
    buttonLayout.setId("button-layout");
    buttonLayout.setWidthFull();
    buttonLayout.setSpacing(true);
    cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    send.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    buttonLayout.add(cancel, send);
    editorDiv.add(buttonLayout);
  }

  private void createGridLayout(SplitLayout splitLayout) {
    Div wrapper = new Div();
    wrapper.setId("wrapper");
    wrapper.setWidthFull();
    splitLayout.addToPrimary(wrapper);
    wrapper.add(employees);
  }

  private void addFormItem(Div wrapper, FormLayout formLayout,
                           AbstractField field, String fieldName) {
    formLayout.addFormItem(field, fieldName);
    wrapper.add(formLayout);
    field.getElement().getClassList().add("full-width");
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    // Lazy init of the grid items, happens only when we are sure the view will be
    // shown to the user
    employees.setItems(notificationService.getAllEmployees());
  }

  private void populateForm(EmployeeDTO value) {
    // Value can be null as well, that clears the form
    binder.readBean(value);
  }

}
