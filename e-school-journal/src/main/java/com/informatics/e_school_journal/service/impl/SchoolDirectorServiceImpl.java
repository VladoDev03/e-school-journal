package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Director;
import com.informatics.e_school_journal.data.entity.School;
import com.informatics.e_school_journal.data.repo.DirectorRepository;
import com.informatics.e_school_journal.data.repo.SchoolRepository;
import com.informatics.e_school_journal.dto.director.*;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.service.SchoolDirectorService;
import com.informatics.e_school_journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolDirectorServiceImpl implements SchoolDirectorService {
    private final ModelMapperConfig mapperConfig;
    private final DirectorRepository directorRepository;
    private final SchoolRepository schoolRepository;
    private final UserService userService;

    @Override
    public DirectorWithSchoolDto saveDirectorWithSchool(CreateDirectorDto director) {
        userService.registerUser(director.getCreateUserDto());
        UserDto userDto = userService.getUserByEmail(director.getCreateUserDto().getEmail());

        RoleDto roleDto = userService.getRoleByName("teacher");
        userService.setRole(userDto.getId(), roleDto);

        Director newDirector = mapperConfig.getModelMapper().map(director, Director.class);

        School school = schoolRepository
                .findById(director.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found with id: " + director.getSchoolId()));

        newDirector.setSchool(school);

        newDirector = directorRepository.save(newDirector);

        return mapperConfig.getModelMapper().map(newDirector, DirectorWithSchoolDto.class);
    }

    @Override
    public DirectorDto createDirectorRole(String userId, DirectorSchoolRoleDto director) {
        if (userService.getUserPossibleRoles(userId).stream().noneMatch(role -> role.getName().equals("director"))) {
            throw new IllegalArgumentException("User cannot be assigned this role.");
        }

        RoleDto roleDto = userService.getRoleByName("director");
        userService.setRole(userId, roleDto);

        Director newDirector = new Director(userId);

        School school = schoolRepository
                .findById(director.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found with id: " + director.getSchoolId()));

        newDirector.setSchool(school);

        newDirector = directorRepository.save(newDirector);

        return mapperConfig.getModelMapper().map(newDirector, DirectorDto.class);
    }

    @Override
    public DirectorWithSchoolDto updateDirectorWithSchool(UpdateSchoolDirectorDto director) {
        Director existingDirector = directorRepository
                .findById(director.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + director.getDirectorId()));

        School school = schoolRepository
                .findById(director.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found with id: " + director.getSchoolId()));

        existingDirector.setSchool(school);

        existingDirector = directorRepository.save(existingDirector);

        return mapperConfig.getModelMapper().map(existingDirector, DirectorWithSchoolDto.class);
    }

    @Override
    public void deleteDirectorWithSchool(String directorId) {
        Director director = directorRepository.findById(directorId).orElseThrow();
        School school = director.getSchool();

        school.setDirector(null);
        director.setSchool(null);

        directorRepository.deleteById(directorId);
    }
}
