package org.openmrs.module.dataentry.db.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.Patient;
import org.openmrs.Relationship;
import org.openmrs.module.dataentry.db.DataEntryDAO;

public class DataEntryDAOImpl implements DataEntryDAO {
	protected static final Log log = LogFactory.getLog(DataEntryDAOImpl.class);
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public Relationship getRelationshipByPatient(Patient patient) {
		StringBuffer sb = new StringBuffer();
		Relationship relationship = null;
		sb.append(" SELECT r.* FROM relationship r WHERE person_b = '"
				+ patient.getPatientId() + "' ");

		Session session = sessionFactory.getCurrentSession();

		List<Relationship> relationships = session
				.createSQLQuery(sb.toString()).addEntity("r",
						Relationship.class).list();
		if (relationships.size() > 0)
			relationship = relationships.get(0);
		
		return relationship;
	}

}
