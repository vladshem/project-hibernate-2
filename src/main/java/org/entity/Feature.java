package org.entity;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;

    Feature(String value) {
        this.value = value;
    }

    public static Feature getFeatureByValue(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        Feature[] features = Feature.values();
        for (Feature feature: features) {
            if (feature.value.equals(value)) {
                return feature;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
