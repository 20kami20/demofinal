package model;

import observer.Notification;

public class Student extends User {
    public Student(String name){ super(name); }

    @Override
    public void update(Notification notification) {
        System.out.println("[Student " + name + "] received: " + notification.getMessage());
    }
}
