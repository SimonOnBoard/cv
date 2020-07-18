package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.*;
import com.itis.practice.team123.cvproject.models.Competence;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import com.itis.practice.team123.cvproject.utils.EducationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentAccountController {

    private final StudentsService studentsService;

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping(value = {"/studentAccount/info/update", "/api/studentAccount/info/update"})
    public ResponseEntity<?> updateBaseInfo(@RequestBody StudentForm studentForm,
                                            @AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        studentsService.updateStudentBaseInfo(studentForm, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping(value = {"/studentAccount/competences/update", "/api/studentAccount/competences/update"})
    public ResponseEntity<?> updateCompetencesInfo(@RequestBody TagDto tagDto,
                                                   @AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        studentsService.updateStudentCompetencesInfo(tagDto, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(method = {},value = {"/studentAccount/languages/update", "/api/studentAccount/languages/update"})
    @PostMapping(value = {"/studentAccount/languages/update", "/api/studentAccount/languages/update"})
    public ResponseEntity<?> updateLanguagesInfo(@RequestBody Language language,
                                                 @AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        studentsService.updateStudentLanguagesInfo(language, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping(value = {"/studentAccount/education/update", "/api/studentAccount/education/update"})
    public ResponseEntity<?> updateEducationInfo(@RequestBody EducationDto educationDto,
                                                 @AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        studentsService.updateStudentEducationInfo(educationDto, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping(value = {"/studentAccount/certificates/update", "/api/studentAccount/certificates/update"})
    public ResponseEntity<?> updateCertificatesInfo(@RequestBody CertificateDto certificateDto,
                                                    @AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        studentsService.updateStudentCertificatesInfo(certificateDto, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping(value = {"/studentAccount/competences", "/api/studentAccount/competences"})
    public ResponseEntity<List<Competence>> getCompetencesInfo(@AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        Student student = (Student) userDetails.getUser();
        return ResponseEntity.ok().body(student.getCompetences());
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping(value = {"/studentAccount/languages", "/api/studentAccount/languages"})
    public ResponseEntity<List<LanguageDto>> getLanguagesInfo(@AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        Student student = (Student) userDetails.getUser();
        return ResponseEntity.ok().body(LanguageDto.from(student.getLanguages()));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping(value = {"/studentAccount/education", "/api/studentAccount/education"})
    public ResponseEntity<EducationDto> getEducationInfo(@AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        Student student = (Student) userDetails.getUser();
        return ResponseEntity.ok().body(EducationConverter.convert(student.getEducation()));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping(value = {"/studentAccount/certificates", "/api/studentAccount/certificates"})
    public ResponseEntity<List<CertificateDto>> getCertificatesInfo(@AuthenticationPrincipal UserDetailsImpl<Student> userDetails) {
        Student student = (Student) userDetails.getUser();
        return ResponseEntity.ok().body(CertificateDto.from(student.getCertificates()));
    }
}