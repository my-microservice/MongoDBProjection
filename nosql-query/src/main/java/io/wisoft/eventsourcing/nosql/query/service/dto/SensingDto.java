package io.wisoft.eventsourcing.nosql.query.service.dto;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SensingDto {

    @Getter
    @Setter
    public static class SensingResponse {

        private UUID sensingId;
        private UUID sensorId;
        private LocalDateTime sensingTime;
        private HashMap<String, String> sensingValue;
        private HashMap<String, String> environmentValue;

    }
}
