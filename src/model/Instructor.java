package model;

import observer.Notification;

public class Instructor extends User {
    public Instructor(String name){ super(name); }

    @Override
    public void update(Notification notification) {
        System.out.println("[Instructor " + name + "] received: " + notification.getMessage());
    }
}
