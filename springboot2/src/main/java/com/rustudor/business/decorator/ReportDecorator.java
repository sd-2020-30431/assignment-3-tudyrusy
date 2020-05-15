package com.rustudor.business.decorator;

import com.rustudor.Dto.ReportDto1;
import com.rustudor.business.factory.Report;
import com.rustudor.entity.User;

public abstract class ReportDecorator implements Report {
    public Report decoratedReport;

    public ReportDecorator(Report decoratedReport) {
        this.decoratedReport = decoratedReport;
    }

    public ReportDto1 makeReport(User user){
        return decoratedReport.makeReport(user);
    }
}
