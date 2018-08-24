package org.openmrs.module.kenyaemrallergyandchronicillnessui.api;

import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CIService  extends OpenmrsService {
    ChronicIllness saveChronicIllness(ChronicIllness chronicIllness);
    List<ChronicIllness> getChronicIllnessByPatient(Patient patient);
    ChronicIllness getChronicIllnessById(Integer chronicIllnessId);
    void voidChronicIllness(Integer ChronicIllnessId);
}
