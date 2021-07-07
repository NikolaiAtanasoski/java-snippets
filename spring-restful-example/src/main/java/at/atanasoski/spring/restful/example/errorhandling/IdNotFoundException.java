package at.atanasoski.spring.restful.example.errorhandling;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String linkRelationAll, String id) {
        super("Could not find id " + id + " in " + linkRelationAll);
    }

}
