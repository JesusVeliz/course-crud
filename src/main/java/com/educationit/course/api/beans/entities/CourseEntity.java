
package com.educationit.course.api.beans.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name="Course")
public class CourseEntity {

    @NotNull
    @Id
    private String code;

    @NotNull
    private String name;

    @NotNull
    private String description;
}