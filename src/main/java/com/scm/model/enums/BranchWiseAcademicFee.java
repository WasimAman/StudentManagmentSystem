package com.scm.model.enums;

import org.springframework.stereotype.Service;

@Service
public class BranchWiseAcademicFee {
    private double CSE = 100000.00;
    private double ECE = 100000.00;
    private double MECH = 100000.00;
    private double CIVIL = 100000.00;

    public double getAcademicFee(String branchName) {
        if (branchName == null) {
            return 100000.00; // default fee
        }

        switch (branchName.toUpperCase()) {
            case "CSE": return CSE;
            case "ECE": return ECE;
            case "MECH": return MECH;
            case "CIVIL": return CIVIL;
            default: return 100000.00;
        }
    }
    public double getExamFee(){
        return 2500.00;
    }

    public double getOtherFee(){
        return 3000.00;
    }
}

