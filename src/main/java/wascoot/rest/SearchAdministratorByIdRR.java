/*
 * Copyright 2023 University of Padua, Italy
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

package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.SearchAdministratorByIdDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * A REST resource for searching {@link Administrator}s by salary.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class SearchAdministratorByIdRR extends AbstractRR {

	/**
	 * Creates a new REST resource for searching {@code Administrator}s by id.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 * @param con the connection to the database.
	 */
	public SearchAdministratorByIdRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
		super(Actions.SEARCH_ADMINISTRATOR_BY_ID, req, res, con);
	}


	@Override
	protected void doServe() throws IOException {

		List<Administrator> el = null;
		Message m = null;

		try {
			// parse the URI path to extract the id
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("id") + 2);

			final int id = Integer.parseInt(path.substring(1));

			LogContext.setResource(Integer.toString(id));

			// creates a new DAO for accessing the database and searches the administrator(s)
			el = new SearchAdministratorByIdDAO(con, id).access().getOutputParam();

			if (el != null) {
				LOGGER.info("Administrator(s) successfully searched by id %d.", id);

				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList2(el).toJSON(res.getOutputStream());
			} else { // it should not happen
				LOGGER.error("Fatal error while searching administrator(s).");

				m = new Message("Cannot search administrator(s): unexpected error.", "E5A1", null);
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				m.toJSON(res.getOutputStream());
			}
		} catch (IndexOutOfBoundsException | NumberFormatException ex) {
			LOGGER.warn("Cannot search administrator(s): wrong format for URI /administrator/id/{id}.", ex);

			m = new Message("Cannot search administrator(s): wrong format for URI /administrator/id/{id}.", "E4A7",
					ex.getMessage());
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			m.toJSON(res.getOutputStream());
		} catch (SQLException ex) {
			LOGGER.error("Cannot search administrator(s): unexpected database error.", ex);

			m = new Message("Cannot search administrator(s): unexpected database error.", "E5A1", ex.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
	}


}
