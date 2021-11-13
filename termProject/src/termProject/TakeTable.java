package termProject;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class TakeTable extends AbstractTableModel {
	private String[] columns;
    private Object[][] rows;
    
    public TakeTable(){}
    
    
    public TakeTable(Object[][] data, String[] columnName){
    
        this.rows = data;
        this.columns = columnName;
    }

    
    public Class getColumnClass(int column){
    	//3 is the index of the column image
    	if(column == 3){
    		return Icon.class;
    	}
    	else{
    		return getValueAt(0,column).getClass();
    	}
    }
 
    //count row
    public int getRowCount() {
    	return this.rows.length;
    }

    //count column
    public int getColumnCount() {
    	return this.columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

    	return this.rows[rowIndex][columnIndex];
    }
    
    
    public String getColumnName(int col){
    	return this.columns[col];
    }
    
}