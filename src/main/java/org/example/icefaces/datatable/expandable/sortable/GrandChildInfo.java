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

/**
 * Information related to a Cient.  Bottom of the hierarchy.
 */
public class GrandChildInfo extends DisplayableInfo
{
    public GrandChildInfo()
    {
    }

    public GrandChildInfo(String id, String name, String status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    protected void sort(Comparator<DisplayableInfo> Comparator)
    {
        // this impl doesn't need to do anything
    }

    public void addToDataModel(List<DisplayableInfo> dataModel, Comparator<DisplayableInfo> comparator)
    {
        // this impl doesn't need to do anything
    }

    public void removeChildren(List<DisplayableInfo> dataModel)
    {
        // Nothing to do here
    }

    public void toggleGroup(List<DisplayableInfo> dataModel)
    {
        // Nothing here
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("ClientINfo");
        sb.append("{" + super.toString());
        sb.append('}');
        return sb.toString();
    }
}
