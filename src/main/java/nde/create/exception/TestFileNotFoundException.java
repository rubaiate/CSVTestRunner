package nde.create.exception;

public class TestFileNotFoundException extends Exception {
    public TestFileNotFoundException(String fileName, Throwable throwable){
        super(String.format("Test file not found [%s]", fileName), throwable);
    }
}
