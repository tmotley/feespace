package com.feestudio.domain;

import com.feestudio.Test;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;

import javax.persistence.*;

/**
 * User: tom
 * Date: 7/20/14
 * Time: 6:17 PM
 */
@Entity
@Table(name = "Client")
@Multitenant
@TenantDiscriminatorColumn(name = "org", contextProperty = Test.TENANT_ID_KEY)
public class Client {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Transient
    private String org;

    public Client() {
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
