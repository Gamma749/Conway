import java.io.File;
import java.io.FileWriter;

public class fileTest{
    public static void main(String args[]){
        File saveDirectory = new File("saves");
        File file = new File(saveDirectory, "test1"+".conway"); //Create the file object
        saveDirectory.mkdirs();
        try{
            file.createNewFile();
        } catch (java.io.IOException e){
            System.err.println(e);
        }
    }
}