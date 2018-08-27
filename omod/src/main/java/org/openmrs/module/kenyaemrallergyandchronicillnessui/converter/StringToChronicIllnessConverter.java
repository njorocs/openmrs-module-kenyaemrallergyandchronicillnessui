package org.openmrs.module.kenyaemrallergyandchronicillnessui.converter;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.CIService;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.ChronicIllness;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.db.CIDAO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToChronicIllnessConverter implements Converter<String, ChronicIllness> {
    @Override
    public ChronicIllness convert(String s) {
        if(StringUtils.isEmpty(s)){
            return null;
        }
        return Context.getService(CIService.class).getChronicIllnessById(Integer.valueOf(s));
    }
}
