package dev.victorugons.personapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessageDTO {

    private String message;
}