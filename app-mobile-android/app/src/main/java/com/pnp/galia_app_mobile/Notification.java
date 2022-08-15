package com.pnp.galia_app_mobile;

public class Notification {
    private String description;
    private NotificationType type;
    private Boolean isRead;
    private String time;

    public Notification(String description, NotificationType type, Boolean isRead, String time) {
        this.description = description;
        this.type = type;
        this.isRead = isRead;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Descripción:'" + description + '\n' +
                "Tipo: '" + type;
    }
}
