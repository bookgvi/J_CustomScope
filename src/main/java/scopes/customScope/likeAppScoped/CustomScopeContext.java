package scopes.customScope.likeAppScoped;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;

public class CustomScopeContext implements Context {
  private final ConcurrentHashMap<Contextual<?>, Object> instances = new ConcurrentHashMap<>();

  public Class<? extends Annotation> getScope() {
    return CustomAppScope.class;
  }

  public <T> T get(final Contextual<T> contextual, final CreationalContext<T> creationalContext) {
    @SuppressWarnings("unchecked")
    T instance = (T) instances.computeIfAbsent(contextual, c -> {
      System.out.printf("QQQ %s%n", c.getClass().getName());
      System.out.printf("QQQ %s%n", contextual.getClass().getName());
      return contextual.create(creationalContext);
    });
    return instance;
  }

  public <T> T get(Contextual<T> contextual) {
    @SuppressWarnings("unchecked")
    T instance = (T) instances.get(contextual);
    return instance;
  }

  public boolean isActive() {
    return true;
  }
}
