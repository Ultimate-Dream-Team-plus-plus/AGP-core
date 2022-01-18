package persistence.apiBDe.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;


/**
 * Implementation of Iterator that will be used to navigate through database
 * answer
 * 
 * @author nico
 *
 * @param <E>
 */
public class ResultIterator implements Iterator<Map<String, Object>> {

	private ResultSet resultSet;

	private boolean hasNext;

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	public ResultIterator(ResultSet resultSet) {
		this.resultSet = resultSet;
		goToNext();
	}

	private void goToNext() {
        try {
            this.hasNext = this.resultSet.next();
        } catch (SQLException e) {
            this.hasNext = false;
        }
    }

    @Override
    public Map<String, Object> next() {
        ResultSetMetaData rsmd;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            rsmd = (ResultSetMetaData) resultSet.getMetaData();
            int nbCols = rsmd.getColumnCount();
            for (int i = 0; i < nbCols; i++) {
                map.put(rsmd.getColumnName(i), resultSet.getObject(i));
            }
            goToNext();
            return map;
        } catch (SQLException e) {
            return null;
        }
    }

}
