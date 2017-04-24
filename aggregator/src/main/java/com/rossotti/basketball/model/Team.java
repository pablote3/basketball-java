package com.rossotti.basketball.model;

public class Team {
    private String gameDateTime;
    private String teamKey;
    private String status;

    public String getGameDateTime() {
        return gameDateTime;
    }
    public void setGameDateTime(String gameDateTime) {
        this.gameDateTime = gameDateTime;
    }
    public String getTeamKey() {
        return teamKey;
    }
    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
