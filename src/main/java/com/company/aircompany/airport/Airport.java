package com.company.aircompany.airport;

import com.company.aircompany.classification.MilitaryType;
import com.company.aircompany.entity.AbstractPlane;
import com.company.aircompany.entity.ExperimentalPlane;
import com.company.aircompany.entity.MilitaryPlane;
import com.company.aircompany.entity.PassengerPlane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {
    private final List<AbstractPlane> planes;

    {
        planes = new ArrayList<>();
    }


    public Airport(List<? extends AbstractPlane> planes) {
        if (planes != null) {
            this.planes.addAll(planes);
        }
    }

    public List<PassengerPlane> getPassengerPlanes() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (AbstractPlane plane : planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (AbstractPlane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        for (AbstractPlane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                experimentalPlanes.add((ExperimentalPlane) plane);
            }
        }
        return experimentalPlanes;
    }

    public Optional<PassengerPlane> getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        if (passengerPlanes.isEmpty()) {
            return Optional.empty();
        }

        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return Optional.of(planeWithMaxCapacity);
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType type) {
        List<MilitaryPlane> militaryPlanes = null;
        if (type != null) {
            militaryPlanes = getMilitaryPlanes().stream()
                    .filter(p -> p.getMilitaryType() == type)
                    .collect(Collectors.toList());
        }
        return militaryPlanes != null ? militaryPlanes : new ArrayList<>();
    }

    public void sortByMaxFlightDistance() {
        planes.sort(Comparator.comparingInt(AbstractPlane::getMaxFlightDistance));
    }

    public void sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(AbstractPlane::getMaxSpeed));
    }

    public void sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(AbstractPlane::getMaxLoadCapacity));
    }

    public List<AbstractPlane> getPlanes() {
        return Collections.unmodifiableList(planes);
    }

    public void addPlane(AbstractPlane plane) {
        if (plane != null) {
            planes.add(plane);
        }
    }

    public boolean removePlane(AbstractPlane plane) {
        return plane != null && planes.remove(plane);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Airport{");
        sb.append("planes=").append(planes);
        sb.append('}');
        return sb.toString();
    }
}
