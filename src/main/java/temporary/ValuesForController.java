package temporary;

public enum ValuesForController {
    NAMEINCHECKBOX("script"),
    SHELL_FILE_EXTENSION("application/x-sh"),
    DIRECTORY_PATH("/home/ajris/scripts/");

    private final String name;

    ValuesForController(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
