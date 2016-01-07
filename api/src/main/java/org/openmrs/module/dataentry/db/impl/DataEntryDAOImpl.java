package org.openmrs.module.dataentry.db.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.Relationship;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.dataentry.db.DataEntryDAO;

public class DataEntryDAOImpl implements DataEntryDAO {
	protected static final Log log = LogFactory.getLog(DataEntryDAOImpl.class);
	private DbSessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public Relationship getRelationshipByPatient(Patient patient) {
		StringBuffer sb = new StringBuffer();
		Relationship relationship = null;
		sb.append(" SELECT r.* FROM relationship r WHERE person_b = '"
				+ patient.getPatientId() + "' ");

		DbSession session = sessionFactory.getCurrentSession();

		List<Relationship> relationships = session
				.createSQLQuery(sb.toString()).addEntity("r",
						Relationship.class).list();
		if (relationships.size() > 0)
			relationship = relationships.get(0);
		
//		Context.getPersonService().getRelationships(null, Patient p, RelationshipType rt);
		
		return relationship;
	}

}
