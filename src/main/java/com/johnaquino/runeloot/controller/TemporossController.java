package com.johnaquino.runeloot.controller;

import com.johnaquino.runeloot.model.TemporossRecord;
import com.johnaquino.runeloot.service.TemporossRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tempoross")
public class TemporossController {

    @Autowired
    private TemporossRecordService service;

    @PostMapping("/save")
    public ResponseEntity<TemporossRecord> saveRecord(@RequestBody TemporossRecord record) {
        TemporossRecord savedRecord = service.saveRecord(record);
        return ResponseEntity.ok(savedRecord);
    }

    @GetMapping("/record/{userId}")
    public ResponseEntity<TemporossRecord> getRecord(@PathVariable String userId) {
        Optional<TemporossRecord> record = service.getRecordByUserId(userId);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<TemporossRecord> updateRecord(@PathVariable String userId, @RequestBody TemporossRecord updatedRecord) {
        Optional<TemporossRecord> recordOptional = service.getRecordByUserId(userId);
        if (recordOptional.isPresent()) {
            TemporossRecord record = recordOptional.get();
            record.setPermitsCompleted(updatedRecord.getPermitsCompleted());
            record.setHasTinyTempor(updatedRecord.isHasTinyTempor());
            record.setAttemptsForTinyTempor(updatedRecord.getAttemptsForTinyTempor());
            TemporossRecord savedRecord = service.saveRecord(record);
            return ResponseEntity.ok(savedRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
