/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wascoot.database;

import wascoot.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Reads an administrator from the database.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class ReadAdministratorDAO extends AbstractDAO<Administrator> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT id, email, password FROM public.admin WHERE id = ?";

	/**
	 * The id of the administrator
	 */
	private final int id;

	/**
	 * Creates a new object for reading an administrator.
	 *
	 * @param con   the connection to the database.
	 * @param id the id of the administrator.
	 */
	public ReadAdministratorDAO(final Connection con, final int id) {
		super(con);
		this.id = id;
	}

	@Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the read administrator
		Administrator e = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				e = new Administrator(rs.getInt("id"), rs.getString("email"), rs.getString("password"));

				LOGGER.info("Administrator %d successfully read from the database.", e.getId());
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}
		}

		outputParam = e;
	}
}
