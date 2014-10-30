package ru.tsystems.tsproject.sbb.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystems.tsproject.sbb.model.TestClass;

/**
 * Created by herr on 30.10.2014.
 */
@Controller
@RequestMapping("/welcome")
public class NewController {

    @Autowired
    private TestClass testClass;

    @RequestMapping(method = RequestMethod.GET)
         public String printWelcome(ModelMap model) {

        model.addAttribute("message", testClass.getSs());
        return "hello";
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public void setTestClass(TestClass testClass) {
        this.testClass = testClass;
    }
}

