package com.rossotti.basketball.batch;

import com.rossotti.basketball.model.BoxScore;

public class TeamBoxScore extends BoxScore {
    private String officialLastName1;
    private String officialFirstName1;
    private String officialLastName2;
    private String officialFirstName2;
    private String officialLastName3;
    private String officialFirstName3;

    public String getOfficialLastName1() {
        return officialLastName1;
    }

    public void setOfficialLastName1(String officialLastName1) {
        this.officialLastName1 = officialLastName1;
    }

    public String getOfficialFirstName1() {
        return officialFirstName1;
    }

    public void setOfficialFirstName1(String officialFirstName1) {
        this.officialFirstName1 = officialFirstName1;
    }

    public String getOfficialLastName2() {
        return officialLastName2;
    }

    public void setOfficialLastName2(String officialLastName2) {
        this.officialLastName2 = officialLastName2;
    }

    public String getOfficialFirstName2() {
        return officialFirstName2;
    }

    public void setOfficialFirstName2(String officialFirstName2) {
        this.officialFirstName2 = officialFirstName2;
    }

    public String getOfficialLastName3() {
        return officialLastName3;
    }

    public void setOfficialLastName3(String officialLastName3) {
        this.officialLastName3 = officialLastName3;
    }

    public String getOfficialFirstName3() {
        return officialFirstName3;
    }

    public void setOfficialFirstName3(String officialFirstName3) {
        this.officialFirstName3 = officialFirstName3;
    }
}
