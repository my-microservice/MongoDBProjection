package io.wisoft.eventsourcing.nosql.query.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.wisoft.eventsourcing.nosql.query.exception.SensingNotFoundException;
import io.wisoft.eventsourcing.nosql.query.domain.SensingDoc;
import io.wisoft.eventsourcing.nosql.query.repository.SensingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SensingService {

    private static final String OBJECT_LIST_SAMPLE = "./object-list-sample.csv";
    private SensingRepository sensingRepository;

    public List<SensingDoc> findSensingBySensorId(UUID sensorId) {
        System.out.println(sensorId);
        return sensingRepository.findBySensorId(sensorId);
    }

    public SensingDoc findSensingById(UUID sensingId) {

        return sensingRepository.findById(sensingId).orElseThrow(() -> new SensingNotFoundException(String.valueOf(sensingId)));
    }

    public void saveSensing(SensingDoc sensingMessage) {
        sensingRepository.save(sensingMessage);
    }

    public void sensingToCsv() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST_SAMPLE))
        ) {
            StatefulBeanToCsv<SensingDoc> sensingDocToCsv = new StatefulBeanToCsvBuilder(writer)
//                    .withSeparator('|')
                    //.withQuotechar(',')
                    //.withApplyQuotesToAll(false)
                    .build();

            List<SensingDoc> sensingDocs = sensingRepository.findAll();

            sensingDocToCsv.write(sensingDocs);
        }
    }


}
