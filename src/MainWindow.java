import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mrden on 23.10.2016.
 */
public class MainWindow extends JFrame {
    private JTable jTable;
    private JScrollPane jScrollPane;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private PersonModel personModel;

    public MainWindow() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        /*настройка слушателя окна*/
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                File file = new File("Data.ser");
                if (!file.canRead()) {
                    try {
                        file.createNewFile();
                        wait();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "can't make file, program will be close");
                        System.exit(4);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                Main.persons = (ArrayList<Person>) Main.deserData("Data");
                jTable.updateUI();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int test = JOptionPane.showConfirmDialog(null, "close window, and save data?");
                if (test == 0) {
                    Main.serData("Data", Main.persons);
                    System.exit(0);
                } else if (test == 1) {
                    System.exit(0);
                } else if (test == 2) {
                    return;
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(640, 480);
        getContentPane().setLayout(null);

        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(10, 11, 480, 430);
        getContentPane().add(jScrollPane);

        personModel = new PersonModel();
        jTable = new JTable();
        jTable.setModel(personModel);
        jScrollPane.setViewportView(jTable);

        buttonAdd = new JButton("Add");
        buttonAdd.setBounds(535, 11, 89, 23);
        getContentPane().add(buttonAdd);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = new Person();
                do {
                    person.setName(JOptionPane.showInputDialog("Enter name:"));
                    if (person.getName().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No name input");
                    }
                } while (person.getName().isEmpty());
                do {
                    person.setSurname(JOptionPane.showInputDialog("Enter surname:"));
                    if (person.getSurname().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No surname input");
                    }
                } while (person.getSurname().isEmpty());
                Main.persons.add(person);
                jTable.updateUI();
            }
        });

        buttonRemove = new JButton("Remove");
        buttonRemove.setBounds(535, 45, 89, 23);
        getContentPane().add(buttonRemove);
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTable.getSelectedRow() == -1 || Main.persons.size() == 0) {
                    return;
                }
                Main.persons.remove(jTable.getSelectedRow());
                jTable.updateUI();
            }
        });
        setVisible(true);
    }
}
