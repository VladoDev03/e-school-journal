package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Director;
import com.informatics.e_school_journal.data.entity.School;
import com.informatics.e_school_journal.data.repo.DirectorRepository;
import com.informatics.e_school_journal.dto.director.CreateDirectorDto;
import com.informatics.e_school_journal.dto.director.DirectorDto;
import com.informatics.e_school_journal.dto.director.UpdateDirectorDto;
import com.informatics.e_school_journal.dto.school.SchoolDto;
import com.informatics.e_school_journal.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    private final ModelMapperConfig mapperConfig;
    private final SchoolServiceImpl schoolServiceImpl;

    @Override
    public List<DirectorDto> getAllDirectors() {
        List<Director> directors = directorRepository.findAll();

        return directors.stream()
                .map(director -> mapperConfig.getModelMapper().map(director, DirectorDto.class))
                .toList();
    }

    @Override
    public DirectorDto getDirectorById(long id) {
        Director existingDirector = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + id));

        return mapperConfig.getModelMapper().map(existingDirector, DirectorDto.class);
    }

    @Override
    public DirectorDto createDirector(CreateDirectorDto createDirectorDto) {
        Director director = mapperConfig.getModelMapper().map(createDirectorDto, Director.class);
        Director savedDirector = directorRepository.save(director);

        return mapperConfig.getModelMapper().map(savedDirector, DirectorDto.class);
    }

    @Override
    public DirectorDto updateDirector(long id, UpdateDirectorDto updateDirectorDto) {
        Director existingDirector = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + id));
        mapperConfig.getModelMapper().map(updateDirectorDto, existingDirector);
        Director updatedDirector = directorRepository.save(existingDirector);

        return mapperConfig.getModelMapper().map(updatedDirector, DirectorDto.class);
    }

    @Override
    public void deleteDirector(long id) {
        if (!directorRepository.existsById(id)) {
            throw new RuntimeException("Director not found with id: " + id);
        }

        directorRepository.deleteById(id);
    }

    @Override
    public DirectorDto getDirectorBySchoolId(long schoolId) {
        Director existingDirector = directorRepository.findBySchoolId(schoolId)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + schoolId));

        return mapperConfig.getModelMapper().map(existingDirector, DirectorDto.class);
    }
}
