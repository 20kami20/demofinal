import model.*;
import decorator.PremiumCourseDecorator;
import observer.*;
import strategy.*;
import facade.PlatformFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Course> courses = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final NotificationService notificationService = new NotificationService();
    private final RecommendationService recommendationService = new RecommendationService();
    private final PlatformFacade platform = new PlatformFacade(notificationService, recommendationService);

    public void start() {
        while (true) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> createCourse();
                case 2 -> listCourses();
                case 3 -> decorateCourse();
                case 4 -> createUser();
                case 5 -> listUsers();
                case 6 -> subscribeUser();
                case 7 -> sendNotification();
                case 8 -> recommendCourse();
                case 9 -> publishCourse();
                case 10 -> enrollStudent();
                case 0 -> { return; }
            }
        }
    }

    private void showMenu() {
        System.out.println("\n===== EDU PLATFORM MENU =====");
        System.out.println("1) Create Course");
        System.out.println("2) List Courses");
        System.out.println("3) Decorate Course (Premium)");
        System.out.println("4) Create User");
        System.out.println("5) List Users");
        System.out.println("6) Subscribe User to Notifications");
        System.out.println("7) Send Notification");
        System.out.println("8) Recommend Course to User");
        System.out.println("9) Publish Course");
        System.out.println("10) Enroll Student");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    // ====================== Actions ======================
    private void createCourse() {
        System.out.print("Course title: ");
        String title = scanner.nextLine();
        System.out.print("Instructor name: ");
        String instructor = scanner.nextLine();

        Course.CourseBuilder builder = new Course.CourseBuilder(title, instructor);

        while (true) {
            System.out.print("Add module? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
            System.out.print("Module title: "); String mt = scanner.nextLine();
            System.out.print("Module content: "); String mc = scanner.nextLine();
            builder.addModule(new model.Module(mt, mc));
        }

        while (true) {
            System.out.print("Add quiz? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
            System.out.print("Quiz name: "); String qn = scanner.nextLine();
            System.out.print("Questions count: "); int qc = Integer.parseInt(scanner.nextLine());
            builder.addQuiz(new Quiz(qn, qc));
        }

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        builder.price(price);

        Course course = builder.build();
        courses.add(course);
        System.out.println("Created course: " + course.getTitle());
    }

    private void listCourses() {
        System.out.println("=== COURSES ===");
        for (int i = 0; i < courses.size(); i++)
            System.out.println(i + ") " + courses.get(i).getTitle());
    }

    private void decorateCourse() {
        listCourses();
        System.out.print("Choose course to decorate: "); int id = Integer.parseInt(scanner.nextLine());
        Course course = courses.get(id);
        PremiumCourseDecorator decorator = new PremiumCourseDecorator(course);
        System.out.print("With certificate? (y/n): "); decorator.withCertificate(scanner.nextLine().equalsIgnoreCase("y"));
        System.out.print("Exclusive content? (y/n): "); decorator.withExclusiveContent(scanner.nextLine().equalsIgnoreCase("y"));
        courses.set(id, decorator);
        System.out.println("Course upgraded to premium.");
    }

    private void createUser() {
        System.out.println("User type: 1) Student  2) Instructor");
        int t = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: "); String name = scanner.nextLine();
        User user = UserFactory.createUser(t==1?UserType.STUDENT:UserType.INSTRUCTOR, name);
        users.add(user);
        System.out.println("User created: " + name);
    }

    private void listUsers() {
        System.out.println("=== USERS ===");
        for (int i = 0; i < users.size(); i++)
            System.out.println(i + ") " + users.get(i).getName());
    }

    private void subscribeUser() {
        listUsers(); System.out.print("Choose user to subscribe: "); int id = Integer.parseInt(scanner.nextLine());
        notificationService.register(users.get(id));
        System.out.println("User subscribed.");
    }

    private void sendNotification() {
        System.out.print("Message: "); String msg = scanner.nextLine();
        notificationService.notifyAll(new Notification(msg));
    }

    private void recommendCourse() {
        listUsers(); System.out.print("Choose user: "); int uid = Integer.parseInt(scanner.nextLine());
        User user = users.get(uid);
        System.out.println("Strategy: 1) Popularity  2) Similarity"); int st = Integer.parseInt(scanner.nextLine());
        recommendationService.setStrategy(st==1?new PopularityStrategy():new SimilarityStrategy());
        recommendationService.recommend(user);
    }

    private void publishCourse() {
        listCourses(); System.out.print("Choose course to publish: "); int cid = Integer.parseInt(scanner.nextLine());
        platform.publishCourse(courses.get(cid));
    }

    private void enrollStudent() {
        listUsers(); System.out.print("Choose user: "); int uid = Integer.parseInt(scanner.nextLine());
        listCourses(); System.out.print("Choose course: "); int cid = Integer.parseInt(scanner.nextLine());
        platform.enrollStudent(users.get(uid), courses.get(cid));
    }
}
