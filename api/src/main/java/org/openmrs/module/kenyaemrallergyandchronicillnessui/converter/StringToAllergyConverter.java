package org.openmrs.module.kenyaemrallergyandchronicillnessui.converter;

import org.openmrs.module.allergyapi.Allergy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToAllergyConverter implements Converter<String, Allergy> {
    @Override
    public Allergy convert(String s) {
        return null;
    }
}
