package decorator;

import model.Course;

public class PremiumCourseDecorator extends CourseDecorator {
    private boolean certificate = false;
    private boolean exclusiveContent = false;

    public PremiumCourseDecorator(Course base){ super(base); }

    public PremiumCourseDecorator withCertificate(boolean c){ this.certificate = c; return this; }
    public PremiumCourseDecorator withExclusiveContent(boolean e){ this.exclusiveContent = e; return this; }

    public Course decorate(){ return this; }

    public boolean hasCertificate(){ return certificate; }
    public boolean hasExclusiveContent(){ return exclusiveContent; }
}
