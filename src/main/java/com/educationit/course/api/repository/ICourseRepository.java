
package com.educationit.course.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.educationit.course.api.beans.entities.CourseEntity;

@Repository
public interface ICourseRepository extends JpaRepository<CourseEntity, String> {
}
