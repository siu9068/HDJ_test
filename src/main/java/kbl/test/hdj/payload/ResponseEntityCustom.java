package kbl.test.hdj.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResponseEntityCustom {

    private Object result;

    public static ResponseEntity<?> of(HttpStatus httpStatus, Object result) {
        return ResponseEntity.status(httpStatus).body(result);
    }

    public static ResponseEntity<?> ofError(HttpStatus httpStatus, String result) {
        return ResponseEntity.status(httpStatus).body(new ErrorResponse(result));
    }
}


