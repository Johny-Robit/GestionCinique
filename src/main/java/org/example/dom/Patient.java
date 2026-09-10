package org.example.dom;

import org.example.enums.VisibleSymptom;

public record Patient(String name, VisibleSymptom visibleSymptom, int gravity) {
}
