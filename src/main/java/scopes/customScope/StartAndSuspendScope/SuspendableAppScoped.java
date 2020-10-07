package scopes.customScope.StartAndSuspendScope;

import javax.enterprise.context.NormalScope;
import javax.enterprise.inject.Vetoed;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Vetoed
@NormalScope
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SuspendableAppScoped {
  public static final class Literal extends AnnotationLiteral<SuspendableAppScoped> implements SuspendableAppScoped {
    public static final long serialVersionUID = 1L;
    public static SuspendableAppScoped.Literal INSTANCE = new SuspendableAppScoped.Literal();

    Literal() {
    }
  }
}
