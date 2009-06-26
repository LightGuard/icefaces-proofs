package org.example.icefaces.watermark;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.io.Serializable;

@Name("watermark")
@Scope(ScopeType.PAGE)
public class WatermarkBacking implements Serializable
{
    private String value;
    private String required;

    public String getRequired()
    {
        return required;
    }

    public void setRequired(String required)
    {
        this.required = required;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
