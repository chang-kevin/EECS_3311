package helpers.Logger;

/**
 * Logger class that implements the chain of responsibility design pattern.
 */
public abstract class Logger {
    private Logger next;

    public void setNext(Logger next) {
        this.next = next;
    }

    public void log(LogLevel level, String message) {
        if (level.ordinal() >= this.getLogLevel().ordinal()) {
            this.write(message);
            return;
        }

        if (next != null) {
            next.log(level, message);
        }
    }

    protected abstract LogLevel getLogLevel();

    protected abstract void write(String message);
}
