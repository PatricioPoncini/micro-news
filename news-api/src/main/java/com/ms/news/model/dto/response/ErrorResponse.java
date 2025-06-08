package com.ms.news.model.dto.response;

import com.ms.news.model.dto.response.enums.ErrorType;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
  private ErrorType errorType;
  private String message;
  private List<String> details;
}
