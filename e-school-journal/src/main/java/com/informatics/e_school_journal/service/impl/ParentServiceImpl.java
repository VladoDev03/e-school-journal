//package com.informatics.e_school_journal.service.impl;
//
//import com.informatics.e_school_journal.config.ModelMapperConfig;
//import com.informatics.e_school_journal.data.entity.Parent;
//import com.informatics.e_school_journal.data.repo.ParentRepository;
//import com.informatics.e_school_journal.dto.parent.CreateParentDto;
//import com.informatics.e_school_journal.dto.parent.ParentDto;
//import com.informatics.e_school_journal.dto.parent.UpdateParentDto;
//import com.informatics.e_school_journal.service.ParentService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//@AllArgsConstructor
//public class ParentServiceImpl implements ParentService {
//    private final ParentRepository parentRepository;
//    private final ModelMapperConfig mapperConfig;
//
//    @Override
//    public Flux<ParentDto> getParents() {
//        return this.parentRepository.findAll()
//                .map(parent -> this.mapperConfig
//                        .getModelMapper()
//                        .map(parent, ParentDto.class));
//    }
//
//    @Override
//    public Mono<ParentDto> getParentById(long id) {
//        return this.parentRepository.findById(id)
//                .map(parent -> this.mapperConfig
//                        .getModelMapper()
//                        .map(parent, ParentDto.class));
//    }
//
//    @Override
//    public Mono<ParentDto> createParent(CreateParentDto createParentDto) {
//        Parent parent = mapperConfig.getModelMapper().map(createParentDto, Parent.class);
//
//        return this.parentRepository.save(parent)
//                .map(savedParent -> this.mapperConfig.getModelMapper().map(savedParent, ParentDto.class));
//    }
//
//    @Override
//    public Mono<ParentDto> updateParent(long id, UpdateParentDto updateParentDto) {
//        return this.parentRepository.findById(id)
//                .flatMap(existingParent -> {
//                    mapperConfig.getModelMapper().map(updateParentDto, existingParent);
//                    return this.parentRepository.save(existingParent);
//                })
//                .switchIfEmpty(Mono.error(new Exception("Admin with id " + id + " was not found")))
//                .map(savedParent -> this.mapperConfig.getModelMapper().map(savedParent, ParentDto.class));
//    }
//
//    @Override
//    public Mono<Void> deleteParent(long id) {
//        return this.parentRepository.deleteById(id);
//    }
//}
