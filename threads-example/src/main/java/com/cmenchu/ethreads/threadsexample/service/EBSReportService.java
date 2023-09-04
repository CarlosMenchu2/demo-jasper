package com.cmenchu.ethreads.threadsexample.service;

import com.cmenchu.ethreads.threadsexample.domain.bo.Student;
import com.cmenchu.ethreads.threadsexample.domain.repository.StudentRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EBSReportService implements Report {

    private static Logger logger = LogManager.getLogger(EBSReportService.class);

    @Autowired
    private StudentRepository studentRepository;

    private byte[] report;

    @Override
    public void generateReport() throws JRException, FileNotFoundException {

        List<Student> students =  this.studentRepository.findAll();
        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
        Map<String, Object> params = new HashMap<>();
        params.put("createdBy", "Carlos Menchu");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        report = JasperExportManager.exportReportToPdf(jasperPrint);
        logger.info("Report generated");
    }

    @Override
    public byte[] getReport() {
        return this.report;
    }
}
