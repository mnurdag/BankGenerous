package org.bank.generous.domain.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Getter
@Setter
@MappedSuperclass
public class VersionedBaseEntity extends BaseEntity {

    @Version
    @Column(name = "VERSION")
    private int version;

}
