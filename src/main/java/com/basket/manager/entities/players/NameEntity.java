package com.basket.manager.entities.players;

import com.basket.manager.convertor.NameTypeEnumConverter;
import com.basket.manager.entities.GenericEntity;

import javax.persistence.*;


@Entity(name = "NameEntity")
@Table(name = "names")
public class NameEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @Convert(converter = NameTypeEnumConverter.class)
    @Column(name = "name_type")
    private NameTypeEnum nameType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public NameTypeEnum getNameType() {
        return nameType;
    }

    public void setNameType(NameTypeEnum nameType) {
        this.nameType = nameType;
    }
}
