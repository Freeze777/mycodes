import java.util.*;
 interface ITest {
  public void setVal();
 }
 public class Test {
  private String a;
  void aMethod() {
   final String b = "Earth";
   ITest it = new ITest() {
    public void setVal() {
     a = "Hacker" + b;
    }
   };
   it.setVal();
   System.out.println(a);
  }
  public static void main(String[] args) {
   Test t = new Test();
   t.aMethod();
  }
 }