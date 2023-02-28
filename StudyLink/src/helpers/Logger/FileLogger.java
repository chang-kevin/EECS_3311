package helpers.Logger;

public class FileLogger extends Logger {
    @Override
    protected LogLevel getLogLevel() {
        return LogLevel.WARNING;
    }

    @Override
    protected void write(String message) {
        System.out.println("File Logger: " + message);
        // write somewhere to track file upload fails
    }
}
