package io.wisoft.eventsourcing.nosql.query.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.wisoft.eventsourcing.nosql.query.service.dto.SensingDto;
import io.wisoft.eventsourcing.nosql.query.domain.SensingDoc;
import io.wisoft.eventsourcing.nosql.query.service.SensingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nosql-sensings")
public class SensingController {

    private final SensingService sensingService;
    private final ModelMapper modelMapper;

    @ApiOperation(value = "센싱 수집 값 조회", notes = "센싱 수집 값을 조회합니다.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseStatus(OK)
    @GetMapping("{id}")
    public List<SensingDto.SensingResponse> getSensingListBySensorId(@PathVariable() final UUID id) {
        List<SensingDoc> sensings = sensingService.findSensingBySensorId(id);

        return sensings.stream()
                .map(sensing -> modelMapper.map(sensing, SensingDto.SensingResponse.class))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "센싱 수집 값 조회", notes = "센싱 수집 값을 조회합니다.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseStatus(OK)
    @GetMapping("/csv")
    public String getSensingListByCsv() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        sensingService.sensingToCsv();
        return "ok";
    }
}
