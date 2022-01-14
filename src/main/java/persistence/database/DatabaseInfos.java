package persistence.database;

/**
 * This class will store all the elements describing the EDB (Extended
 * DataBase), such as the table, the column concerned and the folder containing
 * the text files.
 * 
 * @author nico
 *
 */
public class DatabaseInfos {

	// --- Datas ---

	private String table;
	private String keyColumn;
	private String folder;

	// --- Methods ---

	public DatabaseInfos(String table, String keyColumn, String folder) {
		this.table = table;
		this.keyColumn = keyColumn;
		this.folder = folder;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getKeyColumn() {
		return keyColumn;
	}

	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}
