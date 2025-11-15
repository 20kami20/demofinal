package model;

import java.util.*;

public class Course {
    private final String title;
    private final String instructor;
    private final List<Module> modules;
    private final List<Quiz> quizzes;
    private final double price;

    protected Course(CourseBuilder builder) {
        this.title = builder.title;
        this.instructor = builder.instructor;
        this.modules = Collections.unmodifiableList(builder.modules);
        this.quizzes = Collections.unmodifiableList(builder.quizzes);
        this.price = builder.price;
    }

    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }
    public List<Module> getModules() { return modules; }
    public List<Quiz> getQuizzes() { return quizzes; }
    public double getPrice() { return price; }

    public static class CourseBuilder {
        private final String title;
        private final String instructor;
        private List<Module> modules = new ArrayList<>();
        private List<Quiz> quizzes = new ArrayList<>();
        private double price = 0.0;

        public CourseBuilder(String title, String instructor){
            this.title = title;
            this.instructor = instructor;
        }

        public CourseBuilder addModule(Module m){ modules.add(m); return this; }
        public CourseBuilder addQuiz(Quiz q){ quizzes.add(q); return this; }
        public CourseBuilder price(double p){ this.price = p; return this; }

        public Course build(){ return new Course(this); }
    }
}
