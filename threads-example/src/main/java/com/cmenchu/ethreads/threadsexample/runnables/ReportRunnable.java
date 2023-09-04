package com.cmenchu.ethreads.threadsexample.runnables;

import com.cmenchu.ethreads.threadsexample.domain.bo.Student;
import com.cmenchu.ethreads.threadsexample.domain.repository.StudentRepository;
import com.cmenchu.ethreads.threadsexample.dto.ExecutionReport;
import com.cmenchu.ethreads.threadsexample.service.EBSReportService;
import com.cmenchu.ethreads.threadsexample.service.Report;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@NoArgsConstructor
@AllArgsConstructor
public class ReportRunnable implements Runnable {

    private static Logger logger = LogManager.getLogger(ReportRunnable.class);

    private Report ebsReportService;

    private BlockingQueue<ExecutionReport> queue;
    public ReportRunnable(BlockingQueue<ExecutionReport> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            ExecutionReport executionReport = this.queue.take();
            logger.info("Generando reporte de tipo {}", executionReport.getUserName());
            ebsReportService = executionReport.getReport();
            ebsReportService.generateReport();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JRException | FileNotFoundException e) {
            logger.error("Error to generate report {}", e.getMessage());
        }
    }
}
