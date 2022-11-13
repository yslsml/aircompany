package com.company.aircompany.entity;

import com.company.aircompany.classification.MilitaryType;

public class MilitaryPlane extends AbstractPlane {

    private final MilitaryType militaryType;

    public MilitaryPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, MilitaryType militaryType) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.militaryType = militaryType;
    }

    public MilitaryType getMilitaryType() {
        return militaryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MilitaryPlane that = (MilitaryPlane) o;

        return militaryType == that.militaryType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (militaryType != null ? militaryType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MilitaryPlane{");
        sb.append(super.toString());
        sb.append("type=").append(militaryType);
        sb.append('}');
        return sb.toString();
    }
}
