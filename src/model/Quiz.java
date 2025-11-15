package model;

public class Quiz {
    private final String name;
    private final int questions;

    public Quiz(String name, int questions){
        this.name = name;
        this.questions = questions;
    }

    public String getName(){ return name; }
    public int getQuestions(){ return questions; }
}
