package helpers.Logger;

public class ConsoleLogger extends Logger {
    @Override
    protected LogLevel getLogLevel() {
        return LogLevel.INFO;
    }

    @Override
    protected void write(String message) {
        System.out.println("Console Logger: " + message);
    }
}
