package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Parent;
import com.informatics.e_school_journal.data.repo.ParentRepository;
import com.informatics.e_school_journal.dto.parent.CreateParentDto;
import com.informatics.e_school_journal.dto.parent.ParentDto;
import com.informatics.e_school_journal.dto.parent.UpdateParentDto;
import com.informatics.e_school_journal.service.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;
    private final ModelMapperConfig mapperConfig;


    @Override
    public List<ParentDto> getParents() {
        return this.parentRepository.findAll()
                .stream()
                .map(parent -> this.mapperConfig
                        .getModelMapper()
                        .map(parent, ParentDto.class))
                .toList();
    }

    @Override
    public ParentDto getParentById(long id) {
        Parent parent = this.parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + id));

        return mapperConfig.getModelMapper().map(parent, ParentDto.class);
    }

    @Override
    public ParentDto createParent(CreateParentDto createParentDto) {
        Parent parent = this.mapperConfig.getModelMapper().map(createParentDto, Parent.class);
        Parent savedParent = this.parentRepository.save(parent);

        return mapperConfig.getModelMapper().map(savedParent, ParentDto.class);
    }

    @Override
    public ParentDto updateParent(long id, UpdateParentDto updateParentDto) {
        Parent existingParent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + id));

        mapperConfig.getModelMapper().map(updateParentDto, existingParent);

        Parent updatedParent = this.parentRepository.save(existingParent);

        return mapperConfig.getModelMapper().map(updatedParent, ParentDto.class);
    }

    @Override
    public void deleteParent(long id) {
        if (!this.parentRepository.existsById(id)) {
            throw new RuntimeException("Parent not found with id: " + id);
        }

        this.parentRepository.deleteById(id);
    }
}