package microservice.discipline.endpoint.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.core.model.Discipline;
import microservice.discipline.endpoint.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/admin/discipline")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage discipline")
public class DisciplineController {
    private final DisciplineService disciplineService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all available disciplines", response = Discipline[].class)
    public ResponseEntity<Iterable<Discipline>> list(Pageable pageable) {
        return new ResponseEntity<>(disciplineService.list(pageable), HttpStatus.OK);
    }
}
