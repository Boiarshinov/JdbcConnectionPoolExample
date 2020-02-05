package dev.boiarshinov.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Nonogram implements Serializable {
    private int id;
    private String name;
    private byte width;
    private byte height;
    private Date creationTime;

    public Nonogram() {
    }

    public Nonogram(String name, byte width, byte height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public Nonogram(int id, String name, byte width, byte height, Date creationTime) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Nonogram{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", creationTime=" + creationTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nonogram nonogram = (Nonogram) o;
        return id == nonogram.id &&
                width == nonogram.width &&
                height == nonogram.height &&
                name.equals(nonogram.name) &&
                Objects.equals(creationTime, nonogram.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, width, height, creationTime);
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

    public byte getWidth() {
        return width;
    }

    public void setWidth(byte width) {
        this.width = width;
    }

    public byte getHeight() {
        return height;
    }

    public void setHeight(byte height) {
        this.height = height;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
