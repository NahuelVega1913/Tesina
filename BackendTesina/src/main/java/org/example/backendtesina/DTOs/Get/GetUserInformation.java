package org.example.backendtesina.DTOs.Get;

public class GetUserInformation {
   private Boolean hasService;
   private int notifications;

    public Boolean getHasService() {
        return hasService;
    }

    public void setHasService(Boolean hasService) {
        this.hasService = hasService;
    }

    public int getNotifications() {
        return notifications;
    }

    public void setNotifications(int notifications) {
        this.notifications = notifications;
    }
}
