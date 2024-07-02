package com.johnaquino.runeloot.service;

import com.johnaquino.runeloot.model.TemporossRecord;
import com.johnaquino.runeloot.repository.TemporossRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemporossRecordService {

    @Autowired
    private TemporossRecordRepository repository;

    public TemporossRecord saveRecord(TemporossRecord record) {
        return repository.save(record);
    }

    public Optional<TemporossRecord> getRecordByUserId(String userId) {
        return Optional.ofNullable(repository.findByUserId(userId));
    }
}