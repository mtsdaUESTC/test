package JUC;

import java.util.function.Function;

interface Foo{
    void hello();
}
public class LambdaDemo {
    public static void main(String[] args) {
        Foo foo = ()->{
            System.out.println("hello");
        };
        foo.hello();

        Function<String,Object> function = s -> {
            return s.length();
        };
        System.out.println(function.apply("abc"));
    }
}
