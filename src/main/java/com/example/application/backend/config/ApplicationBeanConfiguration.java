package com.example.application.backend.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationBeanConfiguration {

  private static ModelMapper modelMapper;


  static {
    modelMapper = new ModelMapper();
    initMapper(modelMapper);
  }

  private static void initMapper(ModelMapper modelMapper) {
      modelMapper.getConfiguration()
                 .setMatchingStrategy(MatchingStrategies.STRICT)
                 .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                 .setSkipNullEnabled(true);
  }

  @Bean
  public ModelMapper modelMapper() {
    return modelMapper;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
