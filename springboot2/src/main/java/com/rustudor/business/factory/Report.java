package com.rustudor.business.factory;

import com.rustudor.Dto.ReportDto1;
import com.rustudor.entity.User;

public interface Report {
    ReportDto1 makeReport(User user);
}
