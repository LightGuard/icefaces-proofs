/*
 * Copyright 2008 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id$
 */
package org.example.icefaces.datatable.expandable.sortable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.example.icefaces.datatable.expandable.sortable.ChildInfo;
import org.example.icefaces.datatable.expandable.sortable.ParentInfo;
import org.example.icefaces.datatable.expandable.sortable.GrandChildInfo;
import org.example.icefaces.datatable.expandable.sortable.DisplayableInfo;
import org.example.icefaces.datatable.expandable.sortable.DisplayInfoComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

@Name("sortableTable")
@Scope(ScopeType.PAGE)
public class SortableTableBacking implements Serializable
{
    private String sortColumn = "id";
    private boolean ascending = true;

    private String oldSortColumn;
    private boolean oldAscending = !this.ascending;

    private List<DisplayableInfo> dataModel = new ArrayList<DisplayableInfo>();

    public SortableTableBacking()
    {
        List<GrandChildInfo> grandChildList = Arrays.asList(new GrandChildInfo("Client 32", "CABC", "Test"),
        new GrandChildInfo("Client 30", "CBCA", "Off"));

        List<GrandChildInfo> grandChildList2 = Arrays.asList(new GrandChildInfo("Client 35", "Client 2", "production"),
                                                    new GrandChildInfo("Client 40", "Client 3", "testing"));

        List<ChildInfo> childList = Arrays.asList(new ChildInfo("Program 12", "PABC", "On", new ArrayList<GrandChildInfo>(0), false),
        new ChildInfo("Program 14", "PBCA", "Off", grandChildList, true),
        new ChildInfo("Program 13", "PCBA", "On", new ArrayList<GrandChildInfo>(0), false));

        ParentInfo campaign1 = new ParentInfo("Campaign 1", "ABC", "A", new ArrayList<ChildInfo>(0), false);
        ParentInfo campaign2 = new ParentInfo("Campaign 6", "ZYX", "B", childList, false);
        ParentInfo campaign3 = new ParentInfo("Campaign 3", "JKL", "Z", new ArrayList<ChildInfo>(0), false);

        DisplayInfoComparator comparator = new DisplayInfoComparator(true, "id");

        // Start adding the hierarchy together
        dataModel.add(campaign1);
        dataModel.add(campaign2);
        dataModel.add(campaign3);
        dataModel.add(new GrandChildInfo("Client 20", "CZYX", "Creating"));
        dataModel.add(new ChildInfo("Program 15", "Program Name", "Trial", grandChildList2, true));
    }

    @BypassInterceptors
    public String getSortColumn()
    {
        return sortColumn;
    }

    @BypassInterceptors
    public void setSortColumn(String sortColumn)
    {
        this.sortColumn = sortColumn;
    }

    @BypassInterceptors
    public boolean isAscending()
    {
        return ascending;
    }

    @BypassInterceptors
    public void setAscending(boolean ascending)
    {
        this.ascending = ascending;
    }

    @BypassInterceptors
    public List<DisplayableInfo> getDataModel()
    {
        DisplayInfoComparator comparator = new DisplayInfoComparator(this.ascending, this.sortColumn);

        Collections.sort(this.dataModel, comparator);
        List<DisplayableInfo> actualDataModel = new ArrayList<DisplayableInfo>(this.dataModel);

        for(DisplayableInfo info : this.dataModel)
        {
           info.addToDataModel(actualDataModel, comparator);
        }

        return actualDataModel;

    }

    @BypassInterceptors
    public void setDataModel(List<DisplayableInfo> dataModel)
    {
        this.dataModel = dataModel;
    }
}
