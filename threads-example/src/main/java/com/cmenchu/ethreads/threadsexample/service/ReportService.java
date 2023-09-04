package com.cmenchu.ethreads.threadsexample.service;

import com.cmenchu.ethreads.threadsexample.domain.bo.Student;
import com.cmenchu.ethreads.threadsexample.domain.repository.StudentRepository;
import com.cmenchu.ethreads.threadsexample.dto.ExecutionReport;
import com.cmenchu.ethreads.threadsexample.dto.InvExecutionReport;
import com.cmenchu.ethreads.threadsexample.runnables.ReportRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class ReportService {

    @Autowired
    private ReportFactory reportFactory;

    private final BlockingQueue<ExecutionReport> blockingQueue = new LinkedBlockingDeque<>(10);

    public void generateReport() {

        List<InvExecutionReport> invExecutionReports = this.getReports();

        invExecutionReports.stream()
                .filter(invExecutionReport -> invExecutionReport.getReportName() != null)
                .forEach(invExecutionReport -> blockingQueue.add(new ExecutionReport(invExecutionReport.getReportName(), reportFactory.getReport((invExecutionReport.getReportName())))));

        for (int i = 1; i <= 5; i++) {
            Thread thread1 = new Thread(new ReportRunnable(blockingQueue));
            thread1.start();
        }
    }


    private List<InvExecutionReport> getReports() {
        List<InvExecutionReport> invExecutionReports = new ArrayList<>();

        for (int i = 1; i <=3; i++) {
            invExecutionReports.add(new InvExecutionReport("EBSReport"));
            invExecutionReports.add(new InvExecutionReport("WReport"));
        }

        return invExecutionReports;
    }
}
