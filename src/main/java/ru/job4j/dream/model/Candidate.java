package ru.job4j.dream.model;

import java.util.Date;
import java.util.Objects;

public class Candidate {
    private int id;
    private int cityId;
    private String name;
    private Date created;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public Candidate setCityId(int cityId) {
        this.cityId = cityId;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public Candidate setCreated(Date created) {
        this.created = created;
        return this;
    }

    @Override
    public String toString() {
        return "Candidate{" + "id=" + id + ", cityId=" + cityId + ", name='" + name + '\''
                + ", created=" + created + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
