package microservice.discipline.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.core.model.Course;
import microservice.core.model.Discipline;
import microservice.core.model.Discipline;
import microservice.core.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;

    public Iterable<Discipline> list(Pageable pageable) {
        log.info("Listing all disciplines");
        return disciplineRepository.findAll(pageable);
    }

    public Optional<Discipline> findById(Long id) {
        log.info("Listing a discipline by id");
        return disciplineRepository.findById(id);
    }

    public Discipline save(Discipline discipline) {
        log.info("Saving a discipline...");
        return disciplineRepository.save(discipline);
    }

    public void deleteById(Long id) {
        log.info("Deleting a discipline...");
        disciplineRepository.deleteById(id);
    }
}
