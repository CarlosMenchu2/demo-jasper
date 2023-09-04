package com.cmenchu.ethreads.threadsexample.controller;

import com.cmenchu.ethreads.threadsexample.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<?> generateReport() {
        this.reportService.generateReport();
        return ResponseEntity.ok().build();
    }
}
