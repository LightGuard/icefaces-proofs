package org.example.icefaces.datatable.expandable.sortable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Information about a Campaign.  Can contain clients.
 */
public class ParentInfo extends DisplayableInfo
{
    private List<ChildInfo> childChildren = new ArrayList<ChildInfo>();

    public ParentInfo()
    {
    }

    public ParentInfo(String id, String name, String status, List<ChildInfo> childChildren, boolean expanded)
    {
        this.id = id;
        this.name = name;
        this.status = status;
        this.childChildren = childChildren;
        this.expanded = expanded;
    }

    protected void sort(Comparator<DisplayableInfo> comparator)
    {
        if (comparator != null && this.childChildren != null)
        {
            Collections.sort(this.childChildren, comparator);
        }
    }

    public void addToDataModel(List<DisplayableInfo> dataModel, Comparator<DisplayableInfo> comparator)
    {
        if ((this.childChildren != null || !this.childChildren.isEmpty()) && this.expanded)
        {
            this.sort(comparator);
            int index = dataModel.indexOf(this);
            dataModel.addAll(index + 1, this.childChildren);

            for (ChildInfo p : this.childChildren)
            {
                p.addToDataModel(dataModel, comparator);
            }
        }
    }

    public void removeChildren(List<DisplayableInfo> dataModel)
    {
        dataModel.removeAll(this.childChildren);
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

    public List<ChildInfo> getChildPrograms()
    {
        return childChildren;
    }

    public void setChildPrograms(List<ChildInfo> childChildren)
    {
        this.childChildren = childChildren;
    }

    @Override
    public boolean isContainer()
    {
        return !this.childChildren.isEmpty();
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("ParentInfo");
        sb.append("{" + super.toString());
        sb.append(", childChildren=").append(childChildren);
        sb.append('}');
        return sb.toString();
    }
}
