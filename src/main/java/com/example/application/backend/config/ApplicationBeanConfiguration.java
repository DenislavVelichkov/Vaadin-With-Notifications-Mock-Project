package com.example.application.backend.config;

import com.example.application.backend.config.security.CustomRequestCache;
import com.example.application.backend.config.security.SecurityUtils;
import com.example.application.backend.data.entity.User;
import com.example.application.backend.data.models.UserDTO;
import com.example.application.views.binding.UserBindingModel;
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

    modelMapper.createTypeMap(UserBindingModel.class, UserDTO.class)
               .addMappings(mapper -> mapper.skip(UserDTO::setAuthorities))
               .addMappings(mapper -> mapper.skip(UserDTO::setUsername));

    modelMapper.createTypeMap(UserDTO.class, User.class)
               .addMappings(mapper -> mapper.skip(User::setId))
               .addMappings(mapper -> mapper.skip(User::setEmployee));

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

  @Bean
  public CustomRequestCache requestCache() { // (2)
    return new CustomRequestCache();
  }

}
