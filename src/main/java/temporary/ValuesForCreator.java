package temporary;

public enum ValuesForCreator {
    LAUNCHERNAME("launcher.sh"),
    INTERPRETER("#!/bin/bash"),
    WGETCOMMAND("wget localhost:8080/scripts/"),
    CHMODCOMMAND("chmod +x "),
    EXECUTECOMMAND("./");


    private final String name;

    ValuesForCreator(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
