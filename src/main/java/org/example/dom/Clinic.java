package org.example.dom;

import org.example.dom.SortAlgorithm;

public class Clinic {
    private SortAlgorithm doctorSort;
    private SortAlgorithm radiologySort;

    public Clinic(SortAlgorithm doctorSort, SortAlgorithm radiologySort) {
        this.doctorSort = doctorSort;
        this.radiologySort = radiologySort;
    }

    public void registerPatient(String name){
        doctorSort.addPatient(name);

    }

}
