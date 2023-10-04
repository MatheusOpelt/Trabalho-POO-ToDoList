package com.alijaver.myapp;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TodoRepo extends JpaRepository<Todo, Long> {

    @Transactional
    void deleteByConcluido(boolean concluido);
}

