package at.atanasoski.spring.restful.example.modelassembler;

import at.atanasoski.spring.restful.example.controller.AbstractController;
import at.atanasoski.spring.restful.example.entities.BasicEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public abstract class AbstractModelAssembler<ENTITY extends BasicEntity<ENTITY, IDTYPE>, IDTYPE> implements RepresentationModelAssembler<ENTITY, EntityModel<ENTITY>> {

    protected String linkRelationAll;

    @Override
    public EntityModel<ENTITY> toModel(ENTITY entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(getControllerClazz()).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(getControllerClazz()).all()).withRel(linkRelationAll));
    }

    public void setLinkRelationAll(String linkRelationAll) {
        this.linkRelationAll = linkRelationAll;
    }

    protected abstract Class<? extends AbstractController<ENTITY, IDTYPE>> getControllerClazz();
}
