package com.cmenchu.ethreads.threadsexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportFactory {

    @Autowired
    private EBSReportService ebsReportService;

    @Autowired
    private WarehouseReportService warehouseReportService;

    public Report getReport(String type) {

        if (type.equals("EBSReport")) {
            return ebsReportService;
        } else if (type.equals("WReport")) {
            return warehouseReportService;
        }

        return null;
    }
}
