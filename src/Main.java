import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by mrden on 23.10.2016.
 */
public class Main {
    public static ArrayList<Person> persons = new ArrayList<Person>();
    private static MainWindow mainWindow;

    public static void main(String[] args) {
        /*persons = (ArrayList<Person>) deserData("Data");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i).getName());
        }*/
        mainWindow = new MainWindow();
    }

    public static Object deserData(String file_name) {
        Object return_object = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file_name + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return_object = objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File no found");
            System.exit(1);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Write/read error");
            System.exit(2);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Class no found");
            System.exit(3);
        }
        return return_object;
    }

    public static void serData(String file_name, Object object) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file_name + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File no found");
            System.exit(1);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Write/read error");
            System.exit(2);
        }

    }
}
