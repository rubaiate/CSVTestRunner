package nde.create;

import com.opencsv.CSVReader;
import nde.create.exception.TestFileNotFoundException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestRunner {
    private Object[] tests;
    private Throwable error = null;
    private ActionFactory factory;

    public TestRunner(){
        factory = new ActionFactory();
        try {
            factory.init();
        } catch (Exception ex){
            error = ex;
        }
    }

    @BeforeSuite
    public void validateLoadup() throws Throwable {
        if(error != null){
            throw error;
        }
    }

    private Object[] loadTestsFromCSV(String filePath){
        if(error != null) return new Object[0];

        try(FileReader fileReader = new FileReader(filePath)){
            CSVReader csvReader = new CSVReader(fileReader);
            return csvReader.readAll().stream().map(this::createAction).toArray();
        }
        catch (FileNotFoundException e) {
            error = new TestFileNotFoundException(filePath, e);
        } catch (IOException e) {
            error = e;
        }
        return new Object[0];
    }

    private Object createAction(String[] tokens){
        return factory.createAction(tokens);
    }

    @Factory
    @Parameters({ "filePath" })
    public Object[] createTests(String filePath){
        tests = loadTestsFromCSV(filePath);
        return tests;
    }
}