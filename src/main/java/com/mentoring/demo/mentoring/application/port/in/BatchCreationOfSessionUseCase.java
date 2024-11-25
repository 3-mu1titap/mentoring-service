package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.in.dto.in.BatchCreationOfSessionDto;

public interface BatchCreationOfSessionUseCase {
    Integer batchCreationOfSession(BatchCreationOfSessionDto batchCreationOfSessionDto);
}