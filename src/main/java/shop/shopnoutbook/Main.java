package shop.shopnoutbook;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

       new ClassPathXmlApplicationContext("classpath:/resourcshop/context.xml");

    }
}
