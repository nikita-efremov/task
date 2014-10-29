package ru.tsystems.tsproject.sbb;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tsystems.tsproject.sbb.model.TestClass;
import ru.tsystems.tsproject.sbb.servlet.common.SearchStationServlet;

/**
 * Created by herr on 29.10.2014.
 */
public class SpringTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("testContext.xml");
        SearchStationServlet searchStationServlet = context.getBean(SearchStationServlet.class);
        TestClass testClass = searchStationServlet.getTestClass();
        System.out.print(testClass.getSs());
        context.close();
    }
}
