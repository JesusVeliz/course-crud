
package com.educationit.course.api.services;


import java.util.List;
import java.util.Optional;


public interface ICourseService<I, T> {


    void save(T course);

    void delete(I code);

    List<T> findAll();

    Optional<T> findByCode(I code);
}