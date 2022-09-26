package io.udemy.quarkus.validator;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ResponseError {

    public static final int UNPROCESSABLE_ENTITY = 422;

    private String message;
    private Collection<FieldError> errors;

    public ResponseError(String message, Collection<FieldError> errors) {
        this.message = message;
        this.errors = errors;
    }

    public static <T> ResponseError of(Set<ConstraintViolation<T>> violations) {

        List<FieldError> erros = violations.stream().map(
                violation -> new FieldError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage())
        ).collect(Collectors.toList());

        return new ResponseError("Validation error", erros);
    }

    public String getMessage() {
        return message;
    }

    public Collection<FieldError> getErrors() {
        return errors;
    }

    public Response status(int httpStatus) {
        return Response
                .status(httpStatus)
                .entity(this)
                .build();
    }

}
