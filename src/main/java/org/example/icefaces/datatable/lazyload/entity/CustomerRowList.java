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

              /**
               * <p>Return a flag indicating whether there is <code>rowData</code> available at the current <code>rowIndex</code>.  If no
               * <code>wrappedData</code> is available, return <code>false</code>.</p>
               *
               * @throws javax.faces.FacesException if an error occurs getting the row availability
               */
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

              /**
               * <p>Return the number of rows of data objects represented by this {@link javax.faces.model.DataModel}.  If the number of rows is
               * unknown, or no <code>wrappedData</code> is available, return -1.</p>
               *
               * @throws javax.faces.FacesException if an error occurs getting the row count
               */
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

              /**
               * <p>Return an object representing the data for the currenty selected row index.  If no <code>wrappedData</code> is available,
               * return <code>null</code>.</p>
               *
               * @throws javax.faces.FacesException if an error occurs getting the row data
               * @throws IllegalArgumentException   if now row data is available at the currently specified row index
               */
              public Object getRowData()
              {
                  return list.getResultList().get(this.rowIndex);
              }

              /**
               * <p>Return the zero-relative index of the currently selected row.  If we are not currently positioned on a row, or no
               * <code>wrappedData</code> is available, return -1.</p>
               *
               * @throws javax.faces.FacesException if an error occurs getting the row index
               */
              public int getRowIndex()
              {
                  return this.rowIndex;
              }

              /**
               * <p>Set the zero-relative index of the currently selected row, or -1 to indicate that we are not positioned on a row.  It is
               * possible to set the row index at a value for which the underlying data collection does not contain any row data.  Therefore,
               * callers may use the <code>isRowAvailable()</code> method to detect whether row data will be available for use by the
               * <code>getRowData()</code> method.</p>
               * <p/>
               * <p>If there is no <code>wrappedData</code> available when this method is called, the specified <code>rowIndex</code> is stored
               * (and may be retrieved by a subsequent call to <code>getRowData()</code>), but no event is sent.  Otherwise, if the currently
               * selected row index is changed by this call, a {@link javax.faces.model.DataModelEvent} will be sent to the
               * <code>rowSelected()</code> method of all registered {@link javax.faces.model.DataModelListener}s.</p>
               *
               * @param rowIndex The new zero-relative index (must be non-negative)
               * @throws javax.faces.FacesException if an error occurs setting the row index
               * @throws IllegalArgumentException   if <code>rowIndex</code> is less than -1
               */
              public void setRowIndex(int rowIndex)
              {
                  this.rowIndex = rowIndex;
              }

              /**
               * <p>Return the object representing the data wrapped by this {@link javax.faces.model.DataModel}, if any.</p>
               */
              public Object getWrappedData()
              {
                  return list.getResultList();
              }

              /**
               * <p>Set the object representing the data collection wrapped by this {@link javax.faces.model.DataModel}.  If the specified
               * <code>data</code> is <code>null</code>, detach this {@link javax.faces.model.DataModel} from any previously wrapped data
               * collection instead.</p>
               * <p/>
               * <p>If <code>data</code> is non-<code>null</code>, the currently selected row index must be set to zero, and a {@link
               * javax.faces.model.DataModelEvent} must be sent to the <code>rowSelected()</code> method of all registered {@link
               * javax.faces.model.DataModelListener}s indicating that this row is now selected.</p>
               *
               * @param data Data collection to be wrapped, or <code>null</code> to detach from any previous data collection
               * @throws ClassCastException if <code>data</code> is not of the appropriate type for this {@link javax.faces.model.DataModel}
               *                            implementation
               */
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
