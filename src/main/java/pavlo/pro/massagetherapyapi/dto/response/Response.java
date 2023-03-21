package pavlo.pro.massagetherapyapi.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Response<T> {

    private Status status;
    private Object errors;
    private Object metadata;
    private T payload;

    public enum Status {
        OK,
        WRONG_CREDENTIALS,
        NOT_FOUND,
        DUPLICATE_ENTITY,
        BAD_REQUEST,
        UNAUTHORIZED,
        VALIDATION_EXCEPTION,
        EXCEPTION,
        ACCESS_DENIED
    }

    public static Response ok() {
        Response response = new Response();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> Response<T> duplicateEntity() {
        Response<T> response = new Response<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public static <T> Response<T> wrongCredentials() {
        Response<T> response = new Response<>();
        response.setStatus(Status.WRONG_CREDENTIALS);
        return response;
    }

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        ResponseError error = new ResponseError()
            .setDetails(errorMsg)
            .setMessage(ex.getMessage())
            .setTimestamp(new Date());
        setErrors(error);
    }

}
