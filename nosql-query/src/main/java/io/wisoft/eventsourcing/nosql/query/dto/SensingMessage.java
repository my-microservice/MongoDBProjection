package io.wisoft.eventsourcing.nosql.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SensingMessage implements Serializable {

    private UUID sensingId;
    private UUID sensorId;
    private LocalDateTime sensingTime;
    private HashMap<String, String> sensingValue;
    private HashMap<String, String> environmentValue;

    public SensingMessage(UUID sensingId, UUID sensorId, LocalDateTime sensingTime, HashMap<String, String> sensingValue, HashMap<String, String> environmentValue) {
        this.sensingId = sensingId;
        this.sensorId = sensorId;
        this.sensingTime = sensingTime;
        this.sensingValue = sensingValue;
        this.environmentValue = environmentValue;
    }
}