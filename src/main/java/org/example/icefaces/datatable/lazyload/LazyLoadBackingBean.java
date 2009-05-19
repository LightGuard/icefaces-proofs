package org.example.icefaces.datatable.lazyload;

import com.icesoft.faces.component.datapaginator.PaginatorActionEvent;
import org.example.icefaces.datatable.lazyload.entity.CustomerRowList;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import javax.faces.event.ActionEvent;

@Name("lazyLoaded")
@Scope(ScopeType.CONVERSATION)
public class LazyLoadBackingBean
{
    @In(create = true)
    private CustomerRowList customerRowList;

    @Logger
    private Log log;

    public void paginatorActionListener(ActionEvent e)
    {
        // Here is where the magic happens, the actual act of paginating the data happens here,
        // the logic in the datamodel is just for the front end
        final PaginatorActionEvent event = (PaginatorActionEvent) e;

        if (event.getScrollerfacet() == "first")
        {
            this.customerRowList.first();
        }
        else if (event.getScrollerfacet() == "last")
        {
            this.customerRowList.last();
        }
        else if (event.getScrollerfacet() == "next")
        {
            this.customerRowList.next();
        }
        else if (event.getScrollerfacet() == "previous")
        {
            this.customerRowList.previous();
        } 
        else // Assuming getScrollerfacet == null here or a fastStep
        {
            this.customerRowList.setFirstResult((event.getPageIndex() - 1) * this.customerRowList.getMaxResults());
            this.customerRowList.refresh();
        }
    }
}
