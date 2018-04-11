package creators;

import temporary.ValuesForCreator;

public class LauncherCreator {
    private StringBuilder text1;

    public LauncherCreator(String[] scriptTitles) {
        this.text1 = generateLauncher(scriptTitles);
    }

    public StringBuilder getText() {
        return text1;
    }

    private StringBuilder generateLauncher(String[] scriptTitles){

        StringBuilder text = new StringBuilder();

        text.append(ValuesForCreator.INTERPRETER.toString());
        text.append("\n");

        addCommand(scriptTitles, text, ValuesForCreator.WGETCOMMAND);

        addCommand(scriptTitles, text, ValuesForCreator.CHMODCOMMAND);

        addCommand(scriptTitles, text, ValuesForCreator.EXECUTECOMMAND);
        return text;
    }

    private void addCommand(String[] scriptTitles, StringBuilder text, ValuesForCreator command) {
        for (String scriptName : scriptTitles) {
            text.append(command.toString()).append(scriptName).append("\n");
        }
    }
}
