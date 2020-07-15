package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.TeacherEditForm;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;

public interface TeachersService {
    void updateTeacher(TeacherEditForm teacherEditForm, Long id) throws IllegalArgumentException;
    void updateTeacher(TeacherEditForm teacherEditForm, Teacher teacher);

    Teacher getTeacher(Long id) throws IllegalArgumentException;

    void addLanguage(Long id, Language language) throws IllegalArgumentException;
    void addLanguage(Teacher teacher, Language language);

    void removeLanguage(Long id, Long language) throws IllegalArgumentException;
    void removeLanguage(Teacher teacher, Long language) throws IllegalArgumentException;

}
