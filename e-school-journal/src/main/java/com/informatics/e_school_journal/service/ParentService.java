package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.parent.CreateParentDto;
import com.informatics.e_school_journal.dto.parent.ParentDto;
import com.informatics.e_school_journal.dto.parent.UpdateParentDto;

import java.util.List;

public interface ParentService {
    ParentDto createParent(CreateParentDto createParentDto);
    ParentDto getParentById(long id);
    List<ParentDto> getParents();
    ParentDto updateParent(long id, UpdateParentDto updateParentDto);
    void deleteParent(long id);
}
