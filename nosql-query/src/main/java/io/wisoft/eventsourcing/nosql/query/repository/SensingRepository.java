package io.wisoft.eventsourcing.nosql.query.repository;

import io.wisoft.eventsourcing.nosql.query.domain.SensingDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SensingRepository extends MongoRepository<SensingDoc, UUID> {

    List<SensingDoc> findBySensorId(UUID sensorId);

    Optional<SensingDoc> findById(UUID sensingId);

}
