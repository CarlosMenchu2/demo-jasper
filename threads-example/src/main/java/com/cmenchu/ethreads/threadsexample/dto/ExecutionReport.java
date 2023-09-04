package com.cmenchu.ethreads.threadsexample.dto;

import com.cmenchu.ethreads.threadsexample.service.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExecutionReport {

    private String userName;
    private Report report;
}
