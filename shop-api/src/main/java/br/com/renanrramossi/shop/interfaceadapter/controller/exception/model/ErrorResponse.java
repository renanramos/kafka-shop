package br.com.renanrramossi.shop.interfaceadapter.controller.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
    "timestamp",
    "status",
    "error",
    "validations"
})
public class ErrorResponse {

  @NonNull
  @Builder.Default
  @JsonProperty(value = "timestamp", required = true)
  @JsonFormat(shape = Shape.STRING)
  Date timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));

  @JsonProperty(value = "status", required = true)
  Integer status;

  @JsonProperty(value = "error", required = true)
  String error;

  @JsonProperty("validations")
  List<FieldErrorValidation> validations;
}
