package com.rustudor.business.decorator;

import com.rustudor.Dto.ReportDto1;
import com.rustudor.business.factory.Report;
import com.rustudor.entity.User;

public class WasteReportDecorator extends ReportDecorator{
    private String color;

    public WasteReportDecorator(Report decoratedReport) {
        super(decoratedReport);
    }

    @Override
    public ReportDto1 makeReport(User user){
        ReportDto1 r = decoratedReport.makeReport(user);
        if (user.getGoal()>0)
            if (user.getGoal()>r.getCs())
                return new ReportDto1(r.getReport()+"\nNo Waste!\ngreen",r.getCs());
            else
                return new ReportDto1(r.getReport()+"\nWaste!\nred",r.getCs());
        return r;
    }
}
