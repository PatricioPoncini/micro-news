package com.ms.news.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {
  INVALID_PARAMETERS("Invalid params"),
  INTERNAL_SERVER_ERROR("Internal server error"); // --> siempre al final

  private final String message;
}
