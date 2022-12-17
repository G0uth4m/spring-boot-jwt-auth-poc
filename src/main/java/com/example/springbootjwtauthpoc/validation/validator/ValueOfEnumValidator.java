package com.example.springbootjwtauthpoc.validation.validator;

import com.example.springbootjwtauthpoc.validation.constraints.ValueOfEnum;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

  private List<String> validValues;

  @Override
  public void initialize(ValueOfEnum constraintAnnotation) {
    validValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
        .map(java.lang.Enum::name)
        .collect(Collectors.toList());
  }

  @Override
  public boolean isValid(CharSequence charSequence,
      ConstraintValidatorContext constraintValidatorContext) {
    return Objects.nonNull(charSequence) && validValues.contains(charSequence.toString());
  }
}
