package com.cmenchu.ethreads.threadsexample.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.Map;

public interface Report {

    void generateReport() throws JRException, FileNotFoundException;

    byte[] getReport();

}
