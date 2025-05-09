//package com.informatics.e_school_journal.service.impl;
//
//import com.informatics.e_school_journal.config.ModelMapperConfig;
//import com.informatics.e_school_journal.data.entity.Director;
//import com.informatics.e_school_journal.data.repo.DirectorRepository;
//import com.informatics.e_school_journal.dto.DirectorDtos.CreateDirectorDto;
//import com.informatics.e_school_journal.dto.DirectorDtos.DirectorDto;
//import com.informatics.e_school_journal.dto.DirectorDtos.UpdateDirectorDto;
//import com.informatics.e_school_journal.dto.schoolDtos.SchoolDto;
//import com.informatics.e_school_journal.dto.schoolDtos.UpdateSchoolDto;
//import com.informatics.e_school_journal.service.DirectorService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//@RequiredArgsConstructor
//public class DirectorServiceImpl implements DirectorService {
//    private final DirectorRepository directorRepository;
//    private final ModelMapperConfig mapperConfig;
//    @Override
//    public Mono<DirectorDto> createDirector(CreateDirectorDto createDirectorDto) {
//        Director director = mapperConfig.getModelMapper().map(createDirectorDto, Director.class);
//        return directorRepository.save(director)
//                .map(savedDirector -> mapperConfig.getModelMapper().map(savedDirector, DirectorDto.class));
//
//    }
//
//    @Override
//    public Mono<DirectorDto> updateDirector(long id, UpdateDirectorDto updateDirectorDto) {
//        return this.directorRepository.findById(id)
//                .flatMap(existingDirector -> {
//                    mapperConfig.getModelMapper().map(updateDirectorDto, existingDirector);
//                    return this.directorRepository.save(existingDirector);
//                })
//                .switchIfEmpty(Mono.error(new Exception("Director not found with id: " + id)))
//                .map(updatedDirector -> mapperConfig.getModelMapper().map(updatedDirector, DirectorDto.class));
//
//    }
//
//    @Override
//    public Mono<DirectorDto> getDirectorById(long id) {
//        return this.directorRepository.findById(id)
//                .map(director ->
//                        this.mapperConfig
//                                .getModelMapper()
//                                .map(director, DirectorDto.class)
//                );
//    }
//
//    @Override
//    public Flux<DirectorDto> getAllDirectors() {
//        return this.directorRepository.findAll()
//                .map(director -> this.mapperConfig
//                        .getModelMapper()
//                        .map(director, DirectorDto.class));
//    }
//
//    @Override
//    public Mono<Void> deleteDirector(long id) {
//        return directorRepository.deleteById(id);
//    }
//
//}
