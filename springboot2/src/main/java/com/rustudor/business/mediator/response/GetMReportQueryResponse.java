package com.rustudor.business.mediator.response;

import com.rustudor.Dto.StringObj;
import com.rustudor.business.mediator.Response;

public class GetMReportQueryResponse implements Response {
    private StringObj report;

    public GetMReportQueryResponse(StringObj report) {
        this.report = report;
    }

    public StringObj getReport() {
        return report;
    }

    public void setReport(StringObj report) {
        this.report = report;
    }
}
