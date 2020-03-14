package com.example.application.views;

import java.nio.file.AccessDeniedException;

import com.example.application.backend.config.security.SecurityUtils;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.ErrorHandler;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainLayout extends FlexLayout implements BeforeEnterObserver,
                                                      RouterLayout,
                                                      AfterNavigationObserver {
  private static final Logger log = LoggerFactory.getLogger(MainLayout.class);


  public MainLayout(){
    VaadinSession.getCurrent()
                 .setErrorHandler((ErrorHandler) errorEvent -> {
                   log.error("Uncaught UI exception",
                       errorEvent.getThrowable());
                   Notification.show("We are sorry, but an internal error occurred");
                 });
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    final boolean accessGranted =
        SecurityUtils.isAccessGranted(event.getNavigationTarget());

    if (!accessGranted) {
      event.rerouteToError(AccessDeniedException.class);
    }
  }

  @Override
  public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {

  }

}
