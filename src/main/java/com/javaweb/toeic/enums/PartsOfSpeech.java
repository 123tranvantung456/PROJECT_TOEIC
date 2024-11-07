package com.javaweb.toeic.enums;

import java.util.Map;
import java.util.TreeMap;

public enum PartsOfSpeech {
    n("n"),       // Noun
    v("v"),       // Verb
    adj("adj"),     // Adjective
    prep("prep"),    // Preposition
    conj("conj"),    // Conjunction
    aux("aux"),      // Auxiliary
    demo("demo"),     // Demonstrative
    adv("adv"),      // Adverb
    j("j"),    // Interjection
    pron("pron");
    private final String name;

    PartsOfSpeech(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Map<String, String> getAllFormats() {
        Map<String, String> formatMap = new TreeMap<>();
        for (PartsOfSpeech format : PartsOfSpeech.values()) {
            formatMap.put(format.name(), format.getName());
        }
        return formatMap;
    }
}
