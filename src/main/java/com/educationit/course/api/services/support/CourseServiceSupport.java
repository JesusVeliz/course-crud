
package com.educationit.course.api.services.support;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educationit.course.api.beans.domain.CourseModel;
import com.educationit.course.api.beans.entities.CourseEntity;
import com.educationit.course.api.repository.ICourseRepository;
import com.educationit.course.api.services.ICourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("CourseServiceSupportBean")
public class CourseServiceSupport implements ICourseService<String, CourseModel> {


    @Autowired
    private ICourseRepository repository;


    @Override
    public void save(CourseModel course) {

        CourseEntity entity = new CourseEntity();

        BeanUtils.copyProperties(course, entity);

        this.repository.save(entity);
    }

    @Override
    public void delete(String code) {

        this.repository.deleteById(code);
    }

    @Override
    public List<CourseModel> findAll() {

        return this.repository.findAll().stream().
                map(e -> new CourseModel(e.getCode(), e.getName(), e.getDescription())).
                collect(Collectors.toList());
    }

    @Override
    public Optional<CourseModel> findByCode(String code) {

        Optional<CourseEntity> entity = this.repository.findById(code);

        if (entity.isPresent()) {

            CourseModel course = new CourseModel();
            BeanUtils.copyProperties(entity.get(), course);

            return Optional.of(course);

        } else {

            return Optional.empty();
        }
    }
}
