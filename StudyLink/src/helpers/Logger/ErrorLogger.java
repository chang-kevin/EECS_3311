package helpers.Logger;

public class ErrorLogger extends Logger {
    @Override
    protected LogLevel getLogLevel() {
        return LogLevel.ERROR;
    }

    @Override
    protected void write(String message) {
        System.err.println("Error Console::Logger: " + message);
    }
}
