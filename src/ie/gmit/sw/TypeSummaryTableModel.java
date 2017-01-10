package ie.gmit.sw;

import javax.swing.table.AbstractTableModel;

/**
 * A model for the table that appears in the AppSummary pop-up.
 * Manages the colums and the data that fills the table.
 */

public class TypeSummaryTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 777L;
	private String[] cols = {"Class", "Stability", "In Degree", "Out Degree"};
	private Object[][] data = {
		{"Stuff 1", "Other Stuff 1", "Even More Stuff 1", "Much More Stuff 1"}
	};
	
    public void setTableData(Object[][] data){
	    this.data = data;
    }//- End of setTableData
	
	public int getColumnCount() {
        return cols.length;
    }//- End of getColumnCount
	
    public int getRowCount() {
        return data.length;
	}//- End of getRowCount

    public String getColumnName(int col) {
    	return cols[col];
    }//- End of getColumnName

    public Object getValueAt(int row, int col) {
        return data[row][col];
	}//- End of getValueAt
   
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
	}//- End of getColumnClass
}//- End of TypeSummaryTabelModel
