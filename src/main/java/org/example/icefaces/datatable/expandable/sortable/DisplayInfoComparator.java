package org.example.icefaces.datatable.expandable.sortable;

import java.util.Comparator;

public class DisplayInfoComparator implements Comparator<DisplayableInfo>
{
    private boolean ascending;
    private String sortingColumn;

    public DisplayInfoComparator(boolean ascending, String sortingColumn)
    {
        this.ascending = ascending;
        this.sortingColumn = sortingColumn;
    }

    public int compare(DisplayableInfo lhs, DisplayableInfo rhs)
    {
        if (this.ascending)
        {
            if ("id".equals(this.sortingColumn))
            {
                return lhs.getId().compareTo(rhs.getId());
            }
            if ("name".equals(this.sortingColumn))
            {
                return lhs.getName().compareTo(rhs.getName());
            }
            if ("status".equals(this.sortingColumn))
            {
                return lhs.getStatus().compareTo(rhs.getStatus());
            }
        }
        else
        {
            if ("id".equals(this.sortingColumn))
            {
                return rhs.getId().compareTo(lhs.getId());
            }
            if ("name".equals(this.sortingColumn))
            {
                return rhs.getName().compareTo(lhs.getName());
            }
            if ("status".equals(this.sortingColumn))
            {
                return rhs.getStatus().compareTo(lhs.getStatus());
            }
        }
        return 0;
    }
}
