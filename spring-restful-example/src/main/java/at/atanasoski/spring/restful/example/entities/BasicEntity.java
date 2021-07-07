package at.atanasoski.spring.restful.example.entities;

public abstract class BasicEntity<ENTITY, IDTYPE> {

     public abstract IDTYPE getId();
     public abstract void setId(IDTYPE id);

     public abstract ENTITY update(ENTITY newEntity);
}
