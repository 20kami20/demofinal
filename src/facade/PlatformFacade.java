package facade;

import model.User;
import model.Course;
import observer.Notification;
import observer.NotificationService;
import strategy.RecommendationService;

public class PlatformFacade {
    private final NotificationService notificationService;
    private final RecommendationService recommendationService;

    public PlatformFacade(NotificationService ns, RecommendationService rs){
        this.notificationService = ns;
        this.recommendationService = rs;
    }

    public void publishCourse(Course course){
        System.out.println("[Facade] Publishing: " + course.getTitle());
    }

    public void enrollStudent(User student, Course course){
        System.out.println("[Facade] Enrolling " + student.getName() + " into " + course.getTitle());
        notificationService.register(student);
    }

    public void announce(String message){
        notificationService.notifyAll(new Notification("[Announcement] " + message));
    }
}
