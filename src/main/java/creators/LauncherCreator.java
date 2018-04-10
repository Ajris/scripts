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

        for(String scriptName : scriptTitles){
            text.append(ValuesForCreator.WGETCOMMAND.toString()).append(scriptName).append("\n");
        }

        for(String scriptName : scriptTitles){
            text.append(ValuesForCreator.CHMODCOMMAND.toString()).append(scriptName).append("\n");
        }

        for(String scriptName : scriptTitles){
            text.append(ValuesForCreator.EXECUTECOMMAND.toString()).append(scriptName).append("\n");
        }
        return text;
    }
}
