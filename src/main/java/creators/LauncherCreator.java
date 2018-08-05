package creators;

import temporary.ValuesForCreator;

public class LauncherCreator {
    private final StringBuilder text;

    public LauncherCreator(String[] scriptTitles) {
        this.text = generateLauncher(scriptTitles);
    }

    public StringBuilder getText() {
        return text;
    }

    private StringBuilder generateLauncher(String[] scriptTitles) {

        StringBuilder tpm = new StringBuilder();

        tpm.append(ValuesForCreator.INTERPRETER.toString());
        tpm.append("\n");

        addCommand(scriptTitles, tpm, ValuesForCreator.WGETCOMMAND);

        addCommand(scriptTitles, tpm, ValuesForCreator.CHMODCOMMAND);

        addCommand(scriptTitles, tpm, ValuesForCreator.EXECUTECOMMAND);
        return tpm;
    }

    private void addCommand(String[] scriptTitles, StringBuilder text, ValuesForCreator command) {
        for (String scriptName : scriptTitles) {
            text.append(command.toString()).append(scriptName).append("\n");
        }
    }
}
