package model;

import observer.Observer;

public abstract class User implements Observer {
    protected final String name;
    public User(String name){ this.name = name; }
    public String getName(){ return name; }
}
