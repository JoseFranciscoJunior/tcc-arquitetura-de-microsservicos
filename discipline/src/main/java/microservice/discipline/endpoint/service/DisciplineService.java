package microservice.discipline.endpoint.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.core.model.Course;
import microservice.core.model.Discipline;
import microservice.core.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final EurekaClient eurekaClient;

    public Iterable<Discipline> list(Pageable pageable) {
        log.info("Listing all disciplines");
        return disciplineRepository.findAll(pageable);
    }

    public Optional<Discipline> findById(Long id) {
        Optional<Discipline> discipline = disciplineRepository.findById(id);
        log.info("Listing a discipline by id");
        return discipline;
    }

    public Discipline save(Discipline discipline, String authHeader) throws NotFoundException {
        if (!validateCourse(discipline, authHeader)) {
            log.info("Invalid course id");
            throw new NotFoundException("Invalid course id");
        } else {
            log.info("Saving a discipline...");
            return disciplineRepository.save(discipline);
        }
    }

    public Discipline update(Discipline discipline, String authHeader) throws NotFoundException {
        if (!validateCourse(discipline, authHeader)) {
            log.info("Invalid course id");
            throw new NotFoundException("Invalid course id");
        } else {
            log.info("Update a discipline...");
            return disciplineRepository.save(discipline);
        }
    }

    public void deleteById(Long id) {
        log.info("Deleting a discipline...");
        disciplineRepository.deleteById(id);
    }

    private String getUrlServicoCurso() {
        InstanceInfo info = eurekaClient
                .getApplication("course").getInstances().iterator().next();
        if (info == null) {
            log.info("Unavailable service");
        }
        return String.format("http://%s:%d/v1/admin/course/", info.getHostName(), info.getPort());
    }

    @HystrixCommand(fallbackMethod = "unavailableService")
    private boolean validateCourse(Discipline discipline, String authHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        HttpEntity entity = new HttpEntity(headers);

        String url = getUrlServicoCurso().concat(Long.toString(discipline.getCourseId()));

        ResponseEntity<Course> response = restTemplate
                .exchange(url, HttpMethod.GET, entity, Course.class, Collections.emptyMap());
        if (!response.getStatusCode().is2xxSuccessful() || null == response.getBody())
            return false;
        return true;
    }

    public void unavailableService() throws NotFoundException {
        throw new NotFoundException("Unavailable service!");
    }
}




