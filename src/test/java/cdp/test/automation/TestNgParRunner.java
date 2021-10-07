package cdp.test.automation;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class TestNgParRunner {
    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.addListener(tla);
        XmlSuite suite = new XmlSuite();
        suite.setName("SuiteTest");
        List<String> files = new ArrayList();
        files.addAll(new ArrayList<String>() {
            {
                add("src/test/resources/testNgSuite.xml");
            }
        });
        suite.setSuiteFiles(files);
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testng.setXmlSuites(suites);
int i = new Integer(5)
        testng.run();
    }
}
