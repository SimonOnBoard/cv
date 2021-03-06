package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class UsersServiceImplTest {
    @Mock
    private UsersRepository usersRepository;

    private UsersService usersService;

    public UsersServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.usersService = new UsersServiceImpl(usersRepository);
    }

    private User user;
    private ArgumentCaptor<Long> argumentCaptor;

    @BeforeEach
    public void init() {
        user = new User(1L, "teacher", "12345", Role.TEACHER, "aaa@gmail.com");
        argumentCaptor = ArgumentCaptor.forClass(Long.class);
    }

    @Test
    public void checkServiceCallingShouldThrowException() {
        given(usersRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> usersService.getUser(1L));
    }


    @Test
    public void checkServiceCallingShouldWorkNormally() {
        given(usersRepository.findById(anyLong())).willReturn(Optional.of(user));
        User returnedUser = usersService.getUser(1L);
        verify(usersRepository).findById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(1L);
        assertThat(returnedUser).isEqualToComparingFieldByField(user);
    }
}