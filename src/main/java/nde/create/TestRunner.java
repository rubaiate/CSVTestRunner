package nde.create;

import com.opencsv.CSVReader;
import nde.create.exception.TestFileNotFoundException;
import org.testng.ITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestRunner {
    private Object[] tests;
    private Throwable error = null;
    @BeforeSuite
    public void validateLoadup() throws Throwable {
        if(error != null){
            throw error;
        }
    }

    private Object[] loadTestsFromCSV(String filePath){
        try(FileReader fileReader = new FileReader(filePath)){
            CSVReader csvReader = new CSVReader(fileReader);
            return csvReader.readAll().stream().map(this::addTest).toArray();
        }
        catch (FileNotFoundException e) {
            error = new TestFileNotFoundException(filePath, e);
        } catch (IOException e) {
            error = e;
        }
        return new Object[0];
    }

    private Object addTest(String[] tokens){
        return new AddTest(tokens[0],
                Integer.parseInt(tokens[2]),
                Integer.parseInt(tokens[3]),
                Integer.parseInt(tokens[4]));
    }

    @Factory
    @Parameters({ "filePath" })
    public Object[] createTests(String filePath){
        tests = loadTestsFromCSV(filePath);
        return tests;
    }
}