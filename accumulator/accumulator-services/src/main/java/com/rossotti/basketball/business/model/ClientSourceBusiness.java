package com.rossotti.basketball.business.model;

public class ClientSourceBusiness {
    public enum ClientSource {
            File,
            Api
    }
    private ClientSource clientSource;
    public void setClientSource(ClientSource clientSource) {
        this.clientSource = clientSource;
    }
    public ClientSource getClientSource() {
        return clientSource;
    }
}
