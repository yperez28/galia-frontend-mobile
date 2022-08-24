package com.pnp.galia_app_mobile;

public class Patient {

    private String name;
    private String status;
    private String id;
    private String token;
    private String captureDate;
    private String modificationDate;

    public Patient(String name, String status, String id, String token, String captureDate, String modificationDate) {
        this.name = name;
        this.status = status;
        this.id = id;
        this.token = token;
        this.captureDate = captureDate;
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(String captureDate) {
        this.captureDate = captureDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                ", token='" + token + '\'' +
                ", captureDate=" + captureDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
}
