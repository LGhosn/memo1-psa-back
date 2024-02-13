package com.example.support_management.model.relations;

import jakarta.persistence.*;

@Entity
@Table(name = "VERSION_PRODUCT")
public class VersionProduct {
    @Id
    @Column(name = "ID_VERSION_PRODUCT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "ID_VERSION")
    public Integer id_version;
    @Column(name = "ID_PRODUCT")
    public Integer id_product;

    public VersionProduct(Integer idVersion, Integer idProduct) {
        this.id_version = idVersion;
        this.id_product = idProduct;
    }

    public VersionProduct() { }
}
