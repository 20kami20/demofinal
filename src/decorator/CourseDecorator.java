package decorator;

import model.Course;

public abstract class CourseDecorator extends Course {
    protected final Course base;

    protected CourseDecorator(Course base){
        super(new CourseBuilder(base.getTitle(), base.getInstructor())
                .price(base.getPrice()));
        this.base = base;
    }
}
