package io.wisoft.eventsourcing.nosql.query.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Document("sensing")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SensingDoc {

    @Id
    @Indexed(name = "sensing_id")
    private UUID sensingId;

    @Indexed(name = "sensor_id")
    private UUID sensorId;

    @Indexed(name = "sensing_time")
    private LocalDateTime sensingTime;

    @Indexed(name = "sensing_value")

    private HashMap<String, String> sensingValue;
    @Indexed(name = "environment_value")
    private HashMap<String, String> environmentValue;
}
