package beans.TMP_Decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Decorator
public abstract class DecoratorAccountLogger implements Account {
  private final BigDecimal WITHDRAW_BOUND = BigDecimal.valueOf(10000);
  @Inject
  @Delegate
  Account account;

  @Override
  public BigDecimal withDraw(BigDecimal amount) {
    if (amount.compareTo(WITHDRAW_BOUND) > 0) Logger.getLogger("BIG WITHDRAW").warning("withdraw amount = " + amount);
    return account.withDraw(amount);
  }
}
