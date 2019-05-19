package microservice.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Discipline implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull(message = "The field 'title' is mandatory")
    @Column(nullable = false)
    private String title;
    @NotNull(message = "The field 'courseId' is mandatory")
    @Column(nullable = false)
    private Long courseId;
}