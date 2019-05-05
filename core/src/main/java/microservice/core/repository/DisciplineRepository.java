package microservice.core.repository;


import microservice.core.model.Discipline;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DisciplineRepository extends PagingAndSortingRepository<Discipline, Long> {
}
