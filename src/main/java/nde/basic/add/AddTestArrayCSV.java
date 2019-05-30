package nde.basic.add;

import com.opencsv.CSVReader;
import org.testng.annotations.Factory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AddTestArrayCSV {
    @Factory
    public Object[] testArray() throws IOException {
        String filePath = "src/main/resources/AddTestBasic.csv";
        try(FileReader fileReader = new FileReader(filePath)){
            CSVReader csvReader = new CSVReader(fileReader);

            List<String[]> dataRow = csvReader.readAll();

            Object[] addTests = dataRow.stream().map(this::createTest).toArray();
            return addTests;
        }
    }

    private AddTest createTest(String... params){
        int param1 = Integer.parseInt(params[0]);
        int param2 = Integer.parseInt(params[1]);
        int result = Integer.parseInt(params[2]);
        return new AddTest(param1, param2, result);
    }
}
