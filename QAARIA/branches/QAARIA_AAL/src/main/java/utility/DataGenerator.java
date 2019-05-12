package utility;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

/**
 * Created by joe on 11/19/15.
 */

public class DataGenerator
{
    public DataGenerator()
    {

    }


    public static void saveJsonAcct(JsonAcct jAcct)
    {
        try
        {
            File file = new File("testJsonStorage.txt");
            Files.write(Paths.get(file.getName()), jAcct.getJSONObject().toString().getBytes(), StandardOpenOption.APPEND);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
