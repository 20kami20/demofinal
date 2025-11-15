package model;

import java.util.Collections;
import java.util.List;

public class Course {
    private final String title;
    private final String instructor;
    private final List<Module> modules;
    private final List<Quiz> quizzes;
    private final double price;

    public Course(CourseBuilder builder) {
        this.title = builder.getTitle();
        this.instructor = builder.getInstructor();
        this.modules = Collections.unmodifiableList(builder.getModules());
        this.quizzes = Collections.unmodifiableList(builder.getQuizzes());
        this.price = builder.getPrice();
    }

    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }
    public List<Module> getModules() { return modules; }
    public List<Quiz> getQuizzes() { return quizzes; }
    public double getPrice() { return price; }
}
