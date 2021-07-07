package at.atanasoski.spring.restful.example.controller;

import at.atanasoski.spring.restful.example.entities.BasicEntity;
import at.atanasoski.spring.restful.example.errorhandling.IdNotFoundException;
import at.atanasoski.spring.restful.example.modelassembler.AbstractModelAssembler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public abstract class AbstractController<ENTITY extends BasicEntity<ENTITY, IDTYPE>, IDTYPE> {

    protected final String LINK_RELATION_ALL = getAllRel();
    protected JpaRepository<ENTITY, IDTYPE> repository;
    protected RepresentationModelAssembler<ENTITY, EntityModel<ENTITY>> assembler;

    public AbstractController(JpaRepository<ENTITY, IDTYPE> repository, AbstractModelAssembler<ENTITY, IDTYPE> assembler) {
        this.repository = repository;
        this.assembler = assembler;
        assembler.setLinkRelationAll(LINK_RELATION_ALL);
    }

    protected CollectionModel<EntityModel<ENTITY>> getAll() {
        List<EntityModel<ENTITY>> entities = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entities,
                linkTo(methodOn(this.getClass()).all()).withSelfRel());
    }

    protected EntityModel<ENTITY> getOne(IDTYPE id) {
        ENTITY entity = findOne(id);
        return assembler.toModel(entity);
    }

    protected ENTITY findOne(IDTYPE id) {
        return repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(LINK_RELATION_ALL, id.toString()));

    }

    protected ResponseEntity<EntityModel<ENTITY>> saveNewEntity(ENTITY newEntity) {
        ENTITY entity = repository.save(newEntity);
        EntityModel<ENTITY> entityModel = assembler.toModel(entity);
        return toResponseEntity(entityModel);
    }

    protected ResponseEntity<EntityModel<ENTITY>> replaceEntity(ENTITY newEntity, IDTYPE id) {
        ENTITY entity = repository.findById(id)
                .map(e -> {
                    e.update(newEntity);
                    return repository.save(e);
                })
                .orElseGet(() -> {
                    newEntity.setId(id);
                    return repository.save(newEntity);
                });

        EntityModel<ENTITY> entityModel = assembler.toModel(entity);
        return toResponseEntity(entityModel);

    }

    public ResponseEntity<EntityModel<ENTITY>> deleteEntity(IDTYPE id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<EntityModel<ENTITY>> toResponseEntity(EntityModel<ENTITY> entityModel) {
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    protected ResponseEntity<Problem> createMethodNotAllowedResponse(String detail){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not Allowed")
                        .withDetail(detail));
    }

    public abstract CollectionModel<EntityModel<ENTITY>> all();
    public abstract EntityModel<ENTITY> one(IDTYPE id);

    protected abstract String getAllRel();
}
