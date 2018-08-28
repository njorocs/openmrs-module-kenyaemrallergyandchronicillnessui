package org.openmrs.module.kenyaemrallergyandchronicillnessui.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.CIService;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.ChronicIllness;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.db.CIDAO;

import java.util.List;

public class CIServiceImpl extends BaseOpenmrsService implements CIService {
    protected final Log log = LogFactory.getLog(this.getClass());

    private CIDAO cidao;

    @Override
    public ChronicIllness saveChronicIllness(ChronicIllness chronicIllness) {
        log.info("chronic illness ==>"+chronicIllness);
        return cidao.saveChronicIllness(chronicIllness);
    }

    @Override
    public List<ChronicIllness> getChronicIllnessByPatient(Patient patient) {
        return cidao.getChronicIllnessByPatient(patient);
    }

    @Override
    public ChronicIllness getChronicIllnessById(Integer chronicIllnessId) {
        return cidao.getChronicIllnessById(chronicIllnessId);
    }

    @Override
    public void voidChronicIllness(Integer chronicIllnessId) {
        cidao.voidChronicIllness(chronicIllnessId);
    }

    @Override
    public void onShutdown() {
        super.onShutdown();
    }

    @Override
    public void onStartup() {
        super.onStartup();
    }

    public CIDAO getCidao() {
        return cidao;
    }

    public void setCidao(CIDAO cidao) {
        this.cidao = cidao;
    }
}
