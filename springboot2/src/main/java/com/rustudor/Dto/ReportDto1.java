package com.rustudor.Dto;

public class ReportDto1 {
    private String report;
    private int cs;

    public ReportDto1(String report, int cs) {
        this.report = report;
        this.cs = cs;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public int getCs() {
        return cs;
    }

    public void setCs(int cs) {
        this.cs = cs;
    }
}
