package microservice.discipline.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.core.model.Discipline;
import microservice.core.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;

    public Iterable<Discipline> list(Pageable pageable) {
        log.info("Listing all disciplines");
        return disciplineRepository.findAll(pageable);
    }
}
