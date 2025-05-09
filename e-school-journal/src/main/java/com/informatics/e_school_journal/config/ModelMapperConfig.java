package com.informatics.e_school_journal.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

//    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
//        return source
//                .stream()
//                .map(element -> getModelMapper().map(element, targetClass))
//                .collect(Collectors.toList());
//    }
}
