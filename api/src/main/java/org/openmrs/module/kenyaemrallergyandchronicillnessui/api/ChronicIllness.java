package org.openmrs.module.kenyaemrallergyandchronicillnessui.api;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import java.util.Date;
import java.util.UUID;

public class ChronicIllness  extends BaseOpenmrsData {
    private Integer id;
    private Patient patient;
    private Integer codedIllness;
    private Date onsetDate;
    private String uuid;

    public ChronicIllness() {
    }

    public ChronicIllness(Integer id, Patient patient, Integer codedIllness, Date onsetDate, String uuid) {
        this.id = id;
        this.patient = patient;
        this.codedIllness = codedIllness;
        this.onsetDate = onsetDate;
        this.uuid = uuid;
    }
    public void prePersist() {

        if (null == getUuid())
            setUuid(UUID.randomUUID().toString());
    }
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getCodedIllness() {
        return codedIllness;
    }

    public void setCodedIllness(Integer codedIllness) {
        this.codedIllness = codedIllness;
    }

    public Date getOnsetDate() {
        return onsetDate;
    }

    public void setOnsetDate(Date onsetDate) {
        this.onsetDate = onsetDate;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
