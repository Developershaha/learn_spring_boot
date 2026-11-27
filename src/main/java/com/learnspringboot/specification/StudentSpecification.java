
package com.learnspringboot.specification;

import com.learnspringboot.entity.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> hasFirstName(String firstName) {

        if (firstName == null || firstName.isBlank()) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("firstName"),
                        firstName
                );
    }

    public static Specification<Student> hasEmail(String email) {

        if (email == null || email.isBlank()) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("email"),
                        email
                );
    }

    public static Specification<Student> hasCourse(String course) {

        if (course == null || course.isBlank()) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("course"),
                        course
                );
    }
}
