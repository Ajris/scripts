package creators;


import org.junit.Before;
import org.junit.Test;
import temporary.ValuesForCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LauncherCreatorTest {

    private LauncherCreator launcherCreator;
    private String[] scriptNames;
    private File file;
    private String[] fileInStringArray;

    @Before
    public void setEverything() throws IOException {
        setLauncherCreator();
        setScriptNames();
        setFile(this.scriptNames);
        setFileInStringArray(this.file, 10);
    }

    private void setLauncherCreator() {
        launcherCreator = new LauncherCreator();
    }

    private void setScriptNames() {
        scriptNames = new String[3];
        scriptNames[0] = "firstScript";
        scriptNames[1] = "secondScript";
        scriptNames[2] = "thirdScript";
    }

    private void setFile(String[] scriptNames) {
        file = launcherCreator
                .buildLauncher(scriptNames)
                .getLauncher();
    }

    private void setFileInStringArray(File file, int size) throws IOException {
        fileInStringArray = new String[size];
        BufferedReader br = new BufferedReader(new FileReader(file));

        int i = 0;
        String tmp;

        while ((tmp = br.readLine()) != null) {
            fileInStringArray[i++] = tmp;
        }
    }

    @Test
    public void isFileNameTheSameAsExpected() {
        assertEquals(file.getName(), ValuesForCreator.LAUNCHERNAME.toString());
    }

    @Test
    public void areFileCommandsWrittenAsExpectedFor3Scripts() {
        String[] expectedData = new String[10];

        expectedData[0] = ValuesForCreator.INTERPRETER.toString();
        expectedData[1] = ValuesForCreator.WGETCOMMAND.toString() + scriptNames[0];
        expectedData[2] = ValuesForCreator.WGETCOMMAND.toString() + scriptNames[1];
        expectedData[3] = ValuesForCreator.WGETCOMMAND.toString() + scriptNames[2];
        expectedData[4] = ValuesForCreator.CHMODCOMMAND.toString() + scriptNames[0];
        expectedData[5] = ValuesForCreator.CHMODCOMMAND.toString() + scriptNames[1];
        expectedData[6] = ValuesForCreator.CHMODCOMMAND.toString() + scriptNames[2];
        expectedData[7] = ValuesForCreator.EXECUTECOMMAND.toString() + scriptNames[0];
        expectedData[8] = ValuesForCreator.EXECUTECOMMAND.toString() + scriptNames[1];
        expectedData[9] = ValuesForCreator.EXECUTECOMMAND.toString() + scriptNames[2];


        assertArrayEquals(fileInStringArray, expectedData);
    }

    @Test
    public void areFileCommandsWrittenAsExpectedFor0Scripts() throws IOException {
        String[] expectedData = new String[1];
        expectedData[0] = ValuesForCreator.INTERPRETER.toString();

        setFile(new String[0]);
        setFileInStringArray(file, 1);

        assertArrayEquals(fileInStringArray, expectedData);
    }

}
