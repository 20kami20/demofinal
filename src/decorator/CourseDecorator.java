package decorator;

import model.Course;
import model.CourseBuilder;

public abstract class CourseDecorator extends Course {
    protected final Course base;

    protected CourseDecorator(Course base){
        super(new CourseBuilder(base.getTitle(), base.getInstructor())
                .price(base.getPrice()));
        this.base = base;
    }
}
