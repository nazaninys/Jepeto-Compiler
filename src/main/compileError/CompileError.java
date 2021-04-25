package main.compileError;

public class CompileError extends Exception {
    private int line;
    private String message;

    public CompileError(int line, String message) {
        this.line = line;
        this.message = message;
    }

    public String getMessage() {
        return "Line:" + this.line + ":" + this.message;
    }
}
