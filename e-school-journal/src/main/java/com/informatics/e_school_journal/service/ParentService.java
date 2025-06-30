package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.parent.*;

import java.util.List;

public interface ParentService {
    ParentDto createParent(CreateParentDto createParentDto);
    ParentDto createParentRole(String id, CreateParentRoleDto createParentRoleDto);
    ParentPersonalInfoDto getParentById(String id);
    List<ParentPersonalInfoDto> getParents();
    ParentDto updateParent(String id, UpdateParentDto updateParentDto);
    void deleteParent(String id);
}
