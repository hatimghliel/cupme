package com.cupme.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A protocol.
 */
@Entity
@Table(name = "protocol")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Protocol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Other fields
    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "pose_time", nullable = false)
    private Integer poseTime;

    @ManyToMany
    @JoinTable(
        name = "protocol_product",
        joinColumns = { @JoinColumn(name = "protocol_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") }
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "protocol_category",
        joinColumns = { @JoinColumn(name = "protocol_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName = "id") }
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "protocol_tag",
        joinColumns = { @JoinColumn(name = "protocol_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "id") }
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "protocol")
    private Set<Picture> pictures;

    public Protocol() {}

    public Protocol(
        Long id,
        String name,
        String type,
        String shortDescription,
        String description,
        Double price,
        Integer poseTime,
        Set<Product> products,
        Set<Tag> tags,
        Set<Category> categories,
        Set<Picture> pictures
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.shortDescription = shortDescription;
        this.description = description;
        this.price = price;
        this.poseTime = poseTime;
        this.products = products;
        this.tags = tags;
        this.categories = categories;
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPoseTime() {
        return poseTime;
    }

    public void setPoseTime(Integer poseTime) {
        this.poseTime = poseTime;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Protocol)) return false;
        Protocol protocol = (Protocol) o;
        return (
            Objects.equals(getId(), protocol.getId()) &&
            Objects.equals(getName(), protocol.getName()) &&
            Objects.equals(getType(), protocol.getType()) &&
            Objects.equals(getShortDescription(), protocol.getShortDescription()) &&
            Objects.equals(getDescription(), protocol.getDescription()) &&
            Objects.equals(getPrice(), protocol.getPrice()) &&
            Objects.equals(getPoseTime(), protocol.getPoseTime()) &&
            Objects.equals(getProducts(), protocol.getProducts()) &&
            Objects.equals(getTags(), protocol.getTags()) &&
            Objects.equals(getCategories(), protocol.getCategories()) &&
            Objects.equals(getPictures(), protocol.getPictures())
        );
    }
}
