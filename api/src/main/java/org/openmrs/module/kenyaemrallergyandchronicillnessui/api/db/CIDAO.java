package org.openmrs.module.kenyaemrallergyandchronicillnessui.api.db;

import org.openmrs.Patient;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.ChronicIllness;

import java.util.List;

public interface CIDAO {

    ChronicIllness saveChronicIllness (ChronicIllness chronicIllness);
    List<ChronicIllness> getChronicIllnessByPatient(Patient patient);
    ChronicIllness getChronicIllnessById(Integer ChronicIllnessId);
    void voidChronicIllness(Integer ChronicIllnessId);
}
