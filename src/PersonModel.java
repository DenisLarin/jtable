import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by mrden on 23.10.2016.
 */
public class PersonModel implements TableModel {
    private ArrayList<TableModelListener> listeners;

    public PersonModel() {
        listeners = new ArrayList<TableModelListener>();
    }

    @Override
    public int getRowCount() { //сколько строк в таблице, зависит от количества объекта типа Person
        return Main.persons.size();
    }

    @Override
    public int getColumnCount() {
        return 2;//количество переменных
    }

    @Override
    public String getColumnName(int columnIndex) {
        String return_string = null;
        switch (columnIndex){
            case 0:
                return_string = "Name";
                break;
            case 1:
                return_string = "Surname";
                break;
        }
        return return_string;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {//возможность редактирования ячейки
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {//позволяет обратиться к элементу ячейки
        Object return_Object = null;
        switch (columnIndex){
            case 0:
                return_Object = Main.persons.get(rowIndex).getName();
                break;
            case 1:
                return_Object = Main.persons.get(rowIndex).getSurname();
                break;
        }
        return return_Object;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) { //редактирование элементов через двойное нажатие по ячейке
        switch (columnIndex){
            case 0:
                Main.persons.get(rowIndex).setName((String) aValue);
                break;
            case 1:
                Main.persons.get(rowIndex).setSurname((String) aValue);
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
