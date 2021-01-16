package scopes.customScope.LearningStartSuspendScope;

import javax.enterprise.context.NormalScope;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NormalScope
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LSSScope {
  public static final class Literal extends AnnotationLiteral<LSSScope> implements LSSScope {
    public static final long serialVersionUID = 1L;
    public static final LSSScope.Literal INSTANCE = new LSSScope.Literal();

    Literal() {
    }
  }
}
