package model;

public class UserFactory {
    public static User createUser(UserType type, String name){
        return switch(type){
            case STUDENT -> new Student(name);
            case INSTRUCTOR -> new Instructor(name);
        };
    }
}
