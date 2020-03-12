package com.example.application.views;

import java.nio.file.AccessDeniedException;

import com.example.application.backend.config.security.SecurityUtils;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;


public class MainLayout extends FlexLayout implements BeforeEnterObserver,
    RouterLayout {

  public MainLayout(){
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    final boolean accessGranted =
        SecurityUtils.isAccessGranted(event.getNavigationTarget());

    if (!accessGranted) {
      event.rerouteToError(AccessDeniedException.class);
    }
  }
}
