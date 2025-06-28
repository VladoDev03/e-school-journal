package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.parent.CreateParentDto;
import com.informatics.e_school_journal.dto.parent.ParentDto;
import com.informatics.e_school_journal.dto.parent.UpdateParentDto;

import java.util.List;

public interface ParentService {
    ParentDto createParent(CreateParentDto createParentDto);
    ParentDto getParentById(String id);
    List<ParentDto> getParents();
    ParentDto updateParent(String id, UpdateParentDto updateParentDto);
    void deleteParent(String id);
}
