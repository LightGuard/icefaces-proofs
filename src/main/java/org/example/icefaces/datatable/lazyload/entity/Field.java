/*
 * Copyright 2008 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id$
 */
package org.example.icefaces.datatable.lazyload.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Field")
public class Field
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;
    private String fieldValue;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerRowId", nullable = false)
    private CustomerRow customerRow;

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("Field");
        sb.append("{id=").append(id);
        sb.append(", fieldName='").append(fieldName).append('\'');
        sb.append(", fieldValue='").append(fieldValue).append('\'');
        sb.append(", customerRow=").append(customerRow);
        sb.append(", hashCode=").append(Integer.toHexString(this.hashCode()));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Field))
        {
            return false;
        }

        Field field = (Field) o;

        if (customerRow != null ? !customerRow.equals(field.customerRow) : field.customerRow != null)
        {
            return false;
        }
        if (fieldName != null ? !fieldName.equals(field.fieldName) : field.fieldName != null)
        {
            return false;
        }
        if (fieldValue != null ? !fieldValue.equals(field.fieldValue) : field.fieldValue != null)
        {
            return false;
        }
        if (id != null ? !id.equals(field.id) : field.id != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fieldName != null ? fieldName.hashCode() : 0);
        result = 31 * result + (fieldValue != null ? fieldValue.hashCode() : 0);
        result = 31 * result + (customerRow != null ? customerRow.hashCode() : 0);
        result += super.hashCode();
        return result;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public String getFieldValue()
    {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue)
    {
        this.fieldValue = fieldValue;
    }

    public CustomerRow getCustomerRow()
    {
        return customerRow;
    }

    public void setCustomerRow(CustomerRow customerRow)
    {
        this.customerRow = customerRow;
    }
}
