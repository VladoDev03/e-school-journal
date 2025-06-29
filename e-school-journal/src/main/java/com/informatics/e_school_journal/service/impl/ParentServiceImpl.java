package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Parent;
import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.repo.ParentRepository;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.dto.parent.CreateParentDto;
import com.informatics.e_school_journal.dto.parent.CreateParentRoleDto;
import com.informatics.e_school_journal.dto.parent.ParentDto;
import com.informatics.e_school_journal.dto.parent.UpdateParentDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.service.ParentService;
import com.informatics.e_school_journal.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final ModelMapperConfig mapperConfig;

    private final UserService userService;
    
    @Override
    @Transactional
    public ParentDto createParent(CreateParentDto createParentDto) {
        userService.registerUser(createParentDto.getCreateUserDto());
        UserDto userDto = userService.getUserByEmail(createParentDto.getCreateUserDto().getEmail());

        RoleDto roleDto = userService.getRoleByName("parent");
        userService.setRole(userDto.getId(), roleDto);

        Parent parent = new Parent(userDto.getId());

        if (createParentDto.getChildrenIds() != null && !createParentDto.getChildrenIds().isEmpty()) {
            Set<Student> children = new HashSet<>(
                    studentRepository.findAllById(createParentDto.getChildrenIds())
            );
            parent.setChildren(children);
        }

        Parent savedParent = parentRepository.save(parent);

        ParentDto parentDto = mapperConfig.getModelMapper().map(savedParent, ParentDto.class);
        if (savedParent.getChildren() != null) {
            Set<String> childIds = savedParent.getChildren()
                    .stream()
                    .map(Student::getId)
                    .collect(Collectors.toSet());
            parentDto.setChildrenIds(childIds);
        }

        return parentDto;
    }

    @Override
    @Transactional
    public ParentDto createParentRole(String id, CreateParentRoleDto createParentRoleDto) {
        if (userService.getUserPossibleRoles(id).stream().noneMatch(role -> role.getName().equals("parent"))) {
            throw new IllegalArgumentException("User cannot be assigned this role.");
        }

        RoleDto roleDto = userService.getRoleByName("parent");
        userService.setRole(id, roleDto);

        Parent parent = new Parent(id);

        if (createParentRoleDto.getChildrenIds() != null && !createParentRoleDto.getChildrenIds().isEmpty()) {
            Set<Student> children = new HashSet<>(
                    studentRepository.findAllById(createParentRoleDto.getChildrenIds())
            );
            parent.setChildren(children);
        }

        Parent savedParent = parentRepository.save(parent);

        ParentDto parentDto = mapperConfig.getModelMapper().map(savedParent, ParentDto.class);
        if (savedParent.getChildren() != null) {
            Set<String> childIds = savedParent.getChildren()
                    .stream()
                    .map(Student::getId)
                    .collect(Collectors.toSet());
            parentDto.setChildrenIds(childIds);
        }

        return parentDto;
    }

    @Override
    public ParentDto getParentById(String id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + id));

        ParentDto parentDto = mapperConfig.getModelMapper().map(parent, ParentDto.class);
        if (parent.getChildren() != null) {
            Set<String> childIds = parent.getChildren()
                    .stream()
                    .map(Student::getId)
                    .collect(Collectors.toSet());
            parentDto.setChildrenIds(childIds);
        }

        return parentDto;
    }

    @Override
    public List<ParentDto> getParents() {
        return this.parentRepository.findAll()
                .stream()
                .map(parent -> {
                    ParentDto dto = mapperConfig.getModelMapper().map(parent, ParentDto.class);
                    if (parent.getChildren() != null) {
                        Set<String> childIds = parent.getChildren()
                                .stream()
                                .map(Student::getId)
                                .collect(Collectors.toSet());
                        dto.setChildrenIds(childIds);
                    }
                    return dto;
                })
                .toList();

    }

    @Override
    public ParentDto updateParent(String id, UpdateParentDto updateParentDto) {
        Parent existingParent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + id));

        mapperConfig.getModelMapper().map(updateParentDto, existingParent);

        if (updateParentDto.getChildrenIds() != null) {
            Set<Student> children = new HashSet<>(studentRepository.findAllById(updateParentDto.getChildrenIds()));
            existingParent.setChildren(children);
        }

        Parent updatedParent = parentRepository.save(existingParent);

        ParentDto parentDto = mapperConfig.getModelMapper().map(updatedParent, ParentDto.class);
        if (updatedParent.getChildren() != null) {
            Set<String> childIds = updatedParent.getChildren()
                    .stream()
                    .map(Student::getId)
                    .collect(Collectors.toSet());
            parentDto.setChildrenIds(childIds);
        }

        return parentDto;
    }

    @Override
    public void deleteParent(String id) {
        if (!parentRepository.existsById(id)) {
            throw new RuntimeException("Parent not found with id: " + id);
        }
        parentRepository.deleteById(id);
    }
}
