package nde.basic.add;

import org.testng.annotations.Factory;

public class AddTestArray {
    @Factory
    public Object[] testArray(){
        AddTest[] tests = new AddTest[3];
        tests[0] = new AddTest(3, 4, 7);
        tests[1] = new AddTest(24, 5, 29);
        tests[2] = new AddTest(67, 3, 70);

        return tests;
    }
}
