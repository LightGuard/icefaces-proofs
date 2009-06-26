package org.example.icefaces.datatable.lazyload.entity;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import javax.faces.model.DataModel;
import java.util.Arrays;

@Name("customerRowList")
@Scope(ScopeType.CONVERSATION)
public class CustomerRowList extends EntityQuery<CustomerRow>
{
    private static final String EJBQL = "select row from CustomerRow row";

    private static final String[] RESTRICTIONS = {
        "row.fileType = #{customerRowList.customerRow.fileType}"
    };

    private CustomerRow customerRow = new CustomerRow();
    private DataModel dm;

    public CustomerRowList()
    {
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
        setMaxResults(3);
    }

    public CustomerRow getCustomerRow()
    {
        return customerRow;
    }

    @Override
    public DataModel getDataModel()
    {
        // We'll lazy load this as well
        if (this.dm == null)
        {
          // Final here so the inner class can access it
          final CustomerRowList list = this;
          this.dm = new DataModel() 
          { 
              private int rowIndex = -1;
              private int totalRowCount;

              public boolean isRowAvailable()
              {
                  if (this.rowIndex > this.totalRowCount - 1)
                  {
                    return false;
                  }

                  // Have to do this so we don't exceed the size of the array
                  this.rowIndex = this.rowIndex % list.getMaxResults();

                  return list.getResultList().get(this.rowIndex) != null;
              }

              public int getRowCount()
              {
                  // Simple lazy-loading technique here
                  if (this.totalRowCount == 0)
                  {
                    // So we don't have to hard code the values in multiple places
                    final int maxResults = list.getMaxResults();
                    list.setMaxResults(null); // We want to know how many total results there are from this query
                    this.totalRowCount = list.getResultCount().intValue();
                    list.setMaxResults(maxResults); // Go back to the proper size now
                  }
                  return this.totalRowCount;
              }

              public Object getRowData()
              {
                  return list.getResultList().get(this.rowIndex);
              }

              public int getRowIndex()
              {
                  return this.rowIndex;
              }

              public void setRowIndex(int rowIndex)
              {
                  this.rowIndex = rowIndex;
              }

              public Object getWrappedData()
              {
                  return list.getResultList();
              }

              public void setWrappedData(Object data)
              {
                  // We don't allow you to set the data
                  throw new UnsupportedOperationException("setWrappedData");
              }
          };
        }

        return this.dm;
    }
}
