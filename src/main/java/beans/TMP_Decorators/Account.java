package beans.TMP_Decorators;

import java.math.BigDecimal;

public interface Account {
  BigDecimal getBalance();
  BigDecimal withDraw(BigDecimal amount);
  BigDecimal fund(BigDecimal amount);
}
