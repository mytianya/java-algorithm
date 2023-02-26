package vip.codehome.javatips.framework.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

/**
 * @author dsyslove@163.com
 * @createtime 2021/3/25--11:36
 * @description
 **/
public class GuiceTest {
  @Test
  public void testInject(){
    Injector injector= Guice.createInjector(new TokenModule());
    TokenService tokenService=injector.getInstance(TokenService.class);
    String token=tokenService.createToken("zhangsan","1111");
    System.out.println(token);
  }
}
