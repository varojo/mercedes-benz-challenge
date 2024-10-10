package vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RestApiErrorCode {

    // ERROR CODES < 8000 for specific errors
    FAILED_TO_CONVERT_DTO_TO_JSON(1, "Failed to transform DTO to JSON format"),
    FAILED_TO_CONVERT_JSON_TO_OBJECT(2, "Failed to deserialize the given JSON string to a Java object"),

    // ERROR CODES >= 8000 for uncontrolled exceptions and generic errors
    UNCONTROLLED_EXCEPTION(9999, "Unknown error"), // Error 9999
    THIRD_PARTY_REST_API_FAILED(9998, "Call to third party REST API failed"),
    OBJECT_NOT_FOUND(9997, "Object not found"),
    VALUE_NOT_ALLOWED(9996, "The given value is not allowed");

    /* ENUM LOGIC */

    private final int value;
    private final String message;
    private final String codeName;

    RestApiErrorCode(int value, String message) {
        this.value = value;
        this.message = message;
        this.codeName = this.name();
    }

    public static RestApiErrorCode valueOf(int codeValue) {
        for (RestApiErrorCode code : values()) {
            if (code.value == codeValue) {
                return code;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + codeValue + "]");
    }

    public int getValue() {
        return this.value;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCodeName() {
        return this.codeName;
    }

    public String toString() {
        return String.format("{\"value\": \"%s\", \"message\": \"%s\", \"codeName\": \"%s\"}", this.value, this.message, this.name());
    }
}