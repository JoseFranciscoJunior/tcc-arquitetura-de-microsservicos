package microservice.course.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.core.model.Course;
import microservice.core.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {
    private final CourseRepository courseRepository;

    public Iterable<Course> list(Pageable pageable) {
        log.info("Listing all courses");
        return courseRepository.findAll(pageable);
    }

    public Optional<Course> findById(Long id) {
        log.info("Listing a course by id");
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        log.info("Saving a course...");
        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        log.info("Deleting a course...");
        courseRepository.deleteById(id);
    }

}
