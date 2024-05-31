package com.rossotti.basketball.batch;

import com.rossotti.basketball.model.BoxScore;

public class OfficialBoxScore extends BoxScore {
    private String officialLastName;
    private String officialFirstName;

    public String getOfficialLastName() {
        return officialLastName;
    }

    public void setOfficialLastName(String officialLastName) {
        this.officialLastName = officialLastName;
    }

    public String getOfficialFirstName() {
        return officialFirstName;
    }

    public void setOfficialFirstName(String officialFirstName) {
        this.officialFirstName = officialFirstName;
    }
}
