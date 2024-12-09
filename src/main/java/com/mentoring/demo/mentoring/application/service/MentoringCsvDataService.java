package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.port.in.MentoringCsvDataUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringCsvDataOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoringCsvDataService implements MentoringCsvDataUseCase {

    private final MentoringCsvDataOutPort mentoringCsvDataOutPort;

    @Override
    public List<MentoringAddRequestDto> addData(MultipartFile file) {
        return mentoringCsvDataOutPort.MentoringCsvDataParser(file);
    }
}
