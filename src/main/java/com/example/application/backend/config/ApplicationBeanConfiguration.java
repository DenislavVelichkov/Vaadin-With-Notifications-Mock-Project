package com.example.application.backend.config;

import com.example.application.backend.config.security.SecurityUtils;
import org.apache.commons.lang3.SystemUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationBeanConfiguration {

  private static ModelMapper modelMapper;


  static {
    modelMapper = new ModelMapper();
    initMapper(modelMapper);
  }

  private static void initMapper(ModelMapper modelMapper) {
      modelMapper.getConfiguration()
                 .setMatchingStrategy(MatchingStrategies.STANDARD)
                 .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                 .setSkipNullEnabled(true);
  }

  @Bean
  public ModelMapper modelMapper() {
    return modelMapper;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityUtils securityUtils() {
    return new SecurityUtils();
  }
}
