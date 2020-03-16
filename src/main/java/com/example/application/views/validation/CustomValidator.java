package com.example.application.views.validation;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.AbstractValidator;
import com.vaadin.flow.data.validator.EmailValidator;

public class CustomValidator extends AbstractValidator<String> {

  protected CustomValidator(String errorMessage) {
    super(errorMessage);
  }

  @Override
  public ValidationResult apply(String s, ValueContext valueContext) {
    return null;
  }

  @Override
  public <V> BiFunction<String, ValueContext, V> andThen(Function<? super ValidationResult, ? extends V> after) {
    return null;
  }
}
