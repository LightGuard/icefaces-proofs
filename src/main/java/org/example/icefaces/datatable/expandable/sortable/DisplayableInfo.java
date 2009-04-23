/*
 * Copyright 2008 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id$
 */
package org.example.icefaces.datatable.expandable.sortable;

import java.util.Comparator;
import java.util.List;
import java.io.Serializable;

/**
 * Base class for the PoC.
 */
public abstract class DisplayableInfo implements Serializable
{
    public static final String EXPANDED_IMG = "/xmlhttp/css/rime/css-images/tree_nav_top_close_no_siblings.gif";
    public static final String COLLAPSED_IMG = "/xmlhttp/css/rime/css-images/tree_nav_top_open_no_siblings.gif";

    protected String id;
    protected String name;
    protected String status;
    protected boolean expanded = false;

    /**
     * Sorts the children of the implementing class.
     * @param comparator
     */
    protected abstract void sort(Comparator<DisplayableInfo> comparator);

    public abstract void addToDataModel(List<DisplayableInfo> dataModel, Comparator<DisplayableInfo> comparator);

    public abstract void removeChildren(List<DisplayableInfo> dataModel);

    public abstract void toggleGroup(List<DisplayableInfo> dataModel);

    public boolean isContainer()
    {
        return false;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public boolean isExpanded()
    {
        return expanded;
    }

    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
    }

    public String getImageUrl()
    {
        return this.expanded ? DisplayableInfo.EXPANDED_IMG : DisplayableInfo.COLLAPSED_IMG;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof DisplayableInfo))
        {
            return false;
        }

        DisplayableInfo that = (DisplayableInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
        {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null)
        {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("DisplayableInfo");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", expanded='").append(expanded).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
