package scopes.customScope.LearningStartSuspendScope;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface IPInfo {
  public static final class Literal extends AnnotationLiteral<IPInfo> implements IPInfo {
    public static final long serialVersionUID = 1L;
    public static final Literal INSTANCE = new IPInfo.Literal();
  }
}
