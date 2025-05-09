package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.DirectorDtos.CreateDirectorDto;
import com.informatics.e_school_journal.dto.DirectorDtos.DirectorDto;
import com.informatics.e_school_journal.dto.DirectorDtos.UpdateDirectorDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DirectorService {
    Mono<DirectorDto> createDirector(CreateDirectorDto createDirectorDto);

    Mono<DirectorDto> updateDirector(long id, UpdateDirectorDto updateDirectorDto);

    Mono<DirectorDto> getDirectorById(long id);

    Flux<DirectorDto> getAllDirectors();
    Mono<Void> deleteDirector(long id);
}
