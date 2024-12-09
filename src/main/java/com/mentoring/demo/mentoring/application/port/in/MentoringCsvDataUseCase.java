package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MentoringCsvDataUseCase {

    List<MentoringAddRequestDto> addData(MultipartFile file);
}
