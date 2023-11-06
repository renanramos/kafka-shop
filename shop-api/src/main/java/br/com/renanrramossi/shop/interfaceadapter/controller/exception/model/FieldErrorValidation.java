package br.com.renanrramossi.shop.interfaceadapter.controller.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
    "fieldName",
    "fieldValue",
    "message"
})
public class FieldErrorValidation {

  @JsonProperty(value = "fieldName", required = true)
  String fieldName;

  @JsonProperty(value = "fieldValue", required = true)
  Object fieldValue;

  @JsonProperty(value = "message", required = true)
  String message;
}
