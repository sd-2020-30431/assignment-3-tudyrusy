package com.rustudor.business.factory;

import com.rustudor.Dto.ReportDto1;
import com.rustudor.entity.Item;
import com.rustudor.entity.User;

import java.sql.Timestamp;

public class WeeklyReport implements Report{

    @Override
    public ReportDto1 makeReport(User user) {
        String s = "Weekly Report\n";
        int cs = 0;
        int cp = 0;
        int ss = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (Item i : user.getGroceryList()) {
            if (i.getConsumptionDate() != null && timestamp.getTime() - i.getConsumptionDate().getTime() < 604800000) {
                cs += i.getCalories() * i.getQuantity();
                System.out.println(i);
                if (i.getConsumptionDate().getTime()-i.getExpirationDate().getTime()>0)
                    ss+=1;
            }
            if (i.getConsumptionDate() != null && timestamp.getTime() - i.getPurchaseDate().getTime() < 604800000) {
                cp += i.getCalories() * i.getQuantity();
            }

        }
        s += "Calories consumed : " + cs + "\n";
        s += "Calories per day average : " + cs / 7 + "\n";
        s += "Calories purchased : " + cp + "\n";
        s += "Number of stale groceries eaten : " + ss+"\n";
        System.out.println(s);

        return new ReportDto1(s,cs/7);
    }

}
