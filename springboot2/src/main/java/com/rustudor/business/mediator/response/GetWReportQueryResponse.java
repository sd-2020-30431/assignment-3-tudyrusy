package com.rustudor.business.mediator.response;

import com.rustudor.Dto.StringObj;
import com.rustudor.business.mediator.Response;

public class GetWReportQueryResponse implements Response {
    private StringObj report;

    public GetWReportQueryResponse(StringObj report) {
        this.report = report;
    }

    public StringObj getReport() {
        return report;
    }

    public void setReport(StringObj report) {
        this.report = report;
    }
}
