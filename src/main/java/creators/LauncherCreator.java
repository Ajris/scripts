package creators;

import temporary.ValuesForCreator;

import java.io.*;

public class LauncherCreator {
    private File launcher;

    public File getLauncher() {
        return launcher;
    }

    public LauncherCreator buildLauncher(String[] scriptNames) {
        this.launcher = generateLauncher(scriptNames);
        return this;
    }

    private File generateLauncher(String[] scriptNames) {
        Writer writer;
        try {
            writer = new BufferedWriter(new FileWriter(ValuesForCreator.LAUNCHERNAME.toString()));

            writeInterpreter(writer);

            writeWGETCommands(scriptNames, writer);

            writeCHMODCommands(scriptNames, writer);

            writeExecuteCommands(scriptNames, writer);

            writer.close();
            return new File(ValuesForCreator.LAUNCHERNAME.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeInterpreter(Writer writer) throws IOException {
        writer.write(ValuesForCreator.INTERPRETER.toString());
        writer.write("\n");
    }

    private void writeWGETCommands(String[] scriptNames, Writer writer) throws IOException {
        for (String scriptName : scriptNames) {
            writer.write(ValuesForCreator.WGETCOMMAND.toString() + scriptName);
            writer.write("\n");
        }
    }

    private void writeCHMODCommands(String[] scriptNames, Writer writer) throws IOException {
        for (String scriptName : scriptNames) {
            writer.write(ValuesForCreator.CHMODCOMMAND.toString() + scriptName);
            writer.write("\n");
        }
    }

    private void writeExecuteCommands(String[] scriptNames, Writer writer) throws IOException {
        for (String scriptName : scriptNames) {
            writer.write(ValuesForCreator.EXECUTECOMMAND.toString() + scriptName);
            writer.write("\n");
        }
    }
}
