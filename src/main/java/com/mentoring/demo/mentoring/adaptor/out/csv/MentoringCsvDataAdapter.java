package com.mentoring.demo.mentoring.adaptor.out.csv;

import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringCsvDataOutPort;
import com.mentoring.demo.mentoring.common.entity.BaseResponseStatus;
import com.mentoring.demo.mentoring.common.exception.BaseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class MentoringCsvDataAdapter implements MentoringCsvDataOutPort {

    @Override
    public List<MentoringAddRequestDto> MentoringCsvDataParser(MultipartFile file) {
        List<MentoringAddRequestDto> mentoringAddRequestDtos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withDelimiter(';') // 구분자를 ';'로 설정
                     .withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser.getRecords()) {
                try {
                    String name = record.get("name").trim();
                    String description = record.get("description").trim();
                    String detail = record.get("detail").trim();
                    String mentorUuid = record.get("mentorUuid").trim();
                    String thumbnailUrl = record.get("thumbnailUrl").trim();

                    // 객체 생성
                    MentoringAddRequestDto mentoringAddRequestDto = MentoringAddRequestDto.builder()
                            .name(name)
                            .description(description)
                            .detail(detail)
                            .mentorUuid(mentorUuid)
                            .thumbnailUrl(thumbnailUrl)
                            .isReusable(true)
                            .build();

                    mentoringAddRequestDtos.add(mentoringAddRequestDto);
                } catch (Exception innerEx) {
                    // 필드 매핑 중 발생한 예외를 로깅
                    System.err.println("Error parsing record: " + record + " | Exception: " + innerEx.getMessage());
                }
            }
        } catch (IOException e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

        return mentoringAddRequestDtos;
    }
}