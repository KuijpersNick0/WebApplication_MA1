

package wascoot.database;

import wascoot.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SearchAdministratorByIdDAO extends AbstractDAO<List<Administrator>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT id, email, password FROM public.admin WHERE id= ?";

	/**
	 * The ID of the administrator
	 */
	private final int id;

	/**
	 * Creates a new object for searching administrator by salary.
	 *
	 * @param con    the connection to the database.
	 * @param id the id of the administrator.
	 */
	public SearchAdministratorByIdDAO(final Connection con, final int id) {
		super(con);
		this.id = id;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Administrator> administrator = new ArrayList<Administrator>();

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				administrator.add(new Administrator(rs.getInt("id"), rs.getString("email"),
						rs.getString("password"), null, "image/png"));
			}

			LOGGER.info("Administrator(s) successfully listed.");
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = administrator;
	}
}