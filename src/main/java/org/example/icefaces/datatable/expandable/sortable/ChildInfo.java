package org.example.icefaces.datatable.expandable.sortable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Contains information about a Program.  Can contain Campaigns.
 */
public class ChildInfo extends DisplayableInfo
{
    private List<GrandChildInfo> childGrandChildren = new ArrayList<GrandChildInfo>();

    public ChildInfo()
    {
    }

    public ChildInfo(String id, String name, String status, List<GrandChildInfo> childGrandChildren, boolean expanded)
    {
        this.id = id;
        this.name = name;
        this.status = status;
        this.childGrandChildren = childGrandChildren;
        this.expanded = expanded;
    }

    protected void sort(Comparator<DisplayableInfo> comparator)
    {
        if (comparator != null && this.childGrandChildren != null)
        {
            Collections.sort(this.childGrandChildren, comparator);
        }
    }

    public void addToDataModel(List<DisplayableInfo> dataModel, Comparator<DisplayableInfo> comparator)
    {
        if ((!(this.childGrandChildren == null && this.childGrandChildren.isEmpty())) && this.expanded)
        {
            this.sort(comparator);
            int index = dataModel.indexOf(this);
            dataModel.addAll(index + 1, this.childGrandChildren);
            
            for (GrandChildInfo ci : this.childGrandChildren)
            {
                ci.addToDataModel(dataModel, comparator);
            }
        }
    }

    public void removeChildren(List<DisplayableInfo> dataModel)
    {
        dataModel.removeAll(this.childGrandChildren);
    }

    public void toggleGroup(List<DisplayableInfo> dataModel)
    {
        this.expanded = !this.expanded;

        if (this.expanded)
        {
            this.addToDataModel(dataModel, null);
        }
        else
        {
            this.removeChildren(dataModel);
        }
    }

    public List<GrandChildInfo> getChildClients()
    {
        return childGrandChildren;
    }

    public void setChildClients(List<GrandChildInfo> childGrandChildren)
    {
        this.childGrandChildren = childGrandChildren;
    }

    @Override
    public boolean isContainer()
    {
        return !this.childGrandChildren.isEmpty();
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("ChildInfo");
        sb.append("{").append(super.toString());
        sb.append(", childGrandChildren=").append(childGrandChildren);
        sb.append('}');
        return sb.toString();
    }
}
