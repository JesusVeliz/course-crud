
package com.educationit.course.api.beans.domain;


import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CourseModel {


    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    private String description;
}