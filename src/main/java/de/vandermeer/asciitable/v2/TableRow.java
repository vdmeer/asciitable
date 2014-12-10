package de.vandermeer.asciitable.v2;

public interface TableRow {

	TableRowType getType();

	void setBorder(boolean ... borders);

	boolean[] getBorders();

	void setAdjustedBorders(AdjustedBorderType ... borders);

	AdjustedBorderType[] getAdjustedBorders();

	Object[] getColumns();

	void setAdjustedColumns(String[][] columns);

	String[][] getAdjustedColumns();

	static TableRow create(final TableRowType type){
		return TableRow.create(type, null);
	}

	static TableRow create(final TableRowType type, final Object[] cols){
		return new TableRow(){
			Object[] columns = (cols==null)?new Object[0]:cols;
			boolean[] borders;
			AdjustedBorderType[] adjustedBorders;
			String[][] adjustedColumns;

			@Override
			public TableRowType getType() {
				return type;
			}

			@Override
			public void setBorder(boolean... borders) {
				if(borders!=null){
					this.borders = borders;
				}
			}

			@Override
			public boolean[] getBorders() {
				return this.borders;
			}

			@Override
			public Object[] getColumns() {
				return this.columns;
			}

			@Override
			public void setAdjustedBorders(AdjustedBorderType... borders) {
				this.adjustedBorders = borders;
			}

			@Override
			public AdjustedBorderType[] getAdjustedBorders() {
				return this.adjustedBorders;
			}

			@Override
			public void setAdjustedColumns(String[][] columns) {
				this.adjustedColumns = columns;
			}

			@Override
			public String[][] getAdjustedColumns() {
				return this.adjustedColumns;
			}
		};
	}
}
