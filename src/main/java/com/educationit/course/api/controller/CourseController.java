
package com.educationit.course.api.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.educationit.course.api.beans.domain.CourseModel;
import com.educationit.course.api.services.ICourseService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("courses")
public class CourseController {


    @Autowired
    private ICourseService<String, CourseModel> courseService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll () {

        List<CourseModel> list = this.courseService.findAll();

        if (list != null && !list.isEmpty()) {

            return ResponseEntity.ok(list);

        } else {

            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById (@PathVariable("code") String code) {


        if (isEmpty(code)) {

            return ResponseEntity.notFound().build();
        }

        Optional<CourseModel> course =  this.courseService.findByCode(code);

        if (course.isPresent()) {

            return ResponseEntity.ok(course.get());

        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create (@RequestBody @Validated final CourseModel course) {

        this.courseService.save(course);

        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/courses/{code}").
                build().
                expand(course.getCode()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<> (headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
                                                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update (@PathVariable("code") String code, @RequestBody @Validated final CourseModel course) {

        if (isEmpty(code)) {

            return ResponseEntity.badRequest().build();
        }

        if (!code.equalsIgnoreCase(course.getCode())) {

            return ResponseEntity.badRequest().build();
        }

        this.courseService.save(course);

        return ResponseEntity.ok(course);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById (@PathVariable("code") String code) {


        if (isEmpty(code)) {

            return ResponseEntity.notFound().build();
        }

        Optional<CourseModel> course =  this.courseService.findByCode(code);

        if (course.isPresent()) {

            this.courseService.delete(code);
            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();
        }
    }
}