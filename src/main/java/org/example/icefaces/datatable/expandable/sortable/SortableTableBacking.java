package org.example.icefaces.datatable.expandable.sortable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Name("sortableTable")
@Scope(ScopeType.PAGE)
public class SortableTableBacking implements Serializable
{
    private static final long serialVersionUID = 2904641205260618463L;
    
    private String sortColumn = "id";
    private boolean ascending = true;

    private String oldSortColumn;
    private boolean oldAscending = !this.ascending;

    private List<DisplayableInfo> dataModel = new ArrayList<DisplayableInfo>();

    public SortableTableBacking()
    {
        List<GrandChildInfo> grandChildList = Arrays.asList(new GrandChildInfo("GrandChild 32", "CABC", "Test"),
        new GrandChildInfo("GrandChild 30", "CBCA", "Off"));

        List<GrandChildInfo> grandChildList2 = Arrays.asList(new GrandChildInfo("GrandChild 35", "2", "production"),
                                                    new GrandChildInfo("GrandChild 40", "3", "testing"));

        List<ChildInfo> childList = Arrays.asList(new ChildInfo("Child 12", "PABC", "On", new ArrayList<GrandChildInfo>(0), false),
        new ChildInfo("Child 14", "PBCA", "Off", grandChildList, true),
        new ChildInfo("Child 13", "PCBA", "On", new ArrayList<GrandChildInfo>(0), false));

        ParentInfo campaign1 = new ParentInfo("Parent 1", "ABC", "A", new ArrayList<ChildInfo>(0), false);
        ParentInfo campaign2 = new ParentInfo("Parent 6", "ZYX", "B", childList, false);
        ParentInfo campaign3 = new ParentInfo("Parent 3", "JKL", "Z", new ArrayList<ChildInfo>(0), false);

        DisplayInfoComparator comparator = new DisplayInfoComparator(true, "id");

        // Start adding the hierarchy together
        dataModel.add(campaign1);
        dataModel.add(campaign2);
        dataModel.add(campaign3);
        dataModel.add(new GrandChildInfo("GrandChild 20", "CZYX", "Creating"));
        dataModel.add(new ChildInfo("Child 15", "Program Name", "Trial", grandChildList2, true));
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
