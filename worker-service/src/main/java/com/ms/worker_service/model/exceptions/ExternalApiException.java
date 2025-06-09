package com.ms.worker_service.model.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExternalApiException extends RuntimeException {
  private final HttpStatus status;
  private final String responseBody;

  public ExternalApiException(HttpStatus status, String responseBody) {
    super(
        "An error occurred while processing external API response: "
            + responseBody
            + ".Status code: "
            + status.value());
    this.status = status;
    this.responseBody = responseBody;
  }
}
