package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MentoringCsvDataOutPort {
    List<MentoringAddRequestDto> MentoringCsvDataParser(MultipartFile file);
}
