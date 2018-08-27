package org.openmrs.module.kenyaemrallergyandchronicillnessui.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.ChronicIllness;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.db.CIDAO;

import java.util.List;

public class HibernateCIDAO implements CIDAO {

    protected final Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;
    @Override
    public ChronicIllness saveChronicIllness(ChronicIllness chronicIllness) {
        sessionFactory.getCurrentSession().saveOrUpdate(chronicIllness);
        return chronicIllness;
    }

    @Override
    public List<ChronicIllness> getChronicIllnessByPatient(Patient patient) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ChronicIllness.class);
        criteria.add(Restrictions.eq("patient",patient));
        return criteria.list();
    }

    @Override
    public ChronicIllness getChronicIllnessById(Integer chronicIllnessId) {

        return (ChronicIllness) this.sessionFactory.getCurrentSession().get(ChronicIllness.class, chronicIllnessId);
    }

    @Override
    public void voidChronicIllness(Integer ChronicIllnessId) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ChronicIllness.class);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
