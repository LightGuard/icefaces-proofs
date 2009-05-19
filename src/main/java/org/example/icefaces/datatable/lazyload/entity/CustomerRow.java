package org.example.icefaces.datatable.lazyload.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "Customer_Row")
public class CustomerRow
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileType;

    @OneToMany(mappedBy = "customerRow", fetch = FetchType.EAGER)
    private Collection<Field> fields;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof CustomerRow))
        {
            return false;
        }

        CustomerRow that = (CustomerRow) o;

        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null)
        {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0) + super.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("CustomerRow");
        sb.append("{id=").append(id);
        sb.append(", fileType='").append(fileType).append('\'');
        sb.append(", hashCode='").append(Integer.toHexString(hashCode())).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }


    public Collection<Field> getFields()
    {
        return fields;
    }

    public void setFields(Collection<Field> fields)
    {
        this.fields = fields;
    }
}
