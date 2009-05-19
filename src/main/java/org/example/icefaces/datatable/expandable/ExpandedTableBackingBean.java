package org.example.icefaces.datatable.expandable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Backing bean for the Expanded Table example.
 */
@Name("expandedTable")
@Scope(ScopeType.PAGE)
public class ExpandedTableBackingBean implements Serializable
{
    /**
     * This is the backing data model for the table.  It contains all the rows.
     */
    private List<PersonUiWrapper> people = new ArrayList<PersonUiWrapper>(10);
    private String sortColumn = "lastName";
    private boolean sortAsc = true;

    private String oldSortColumn;
    private boolean oldSortAsc = !this.sortAsc;

    @Logger
    private static Log log;

    /**
     * Constructor.  Adds the seed data for this little example.
     */
    public ExpandedTableBackingBean()
    {
        // It's very important to get the order correct for this otherwise you're table looks really bad because
        // the data is in the wrong place in relation to the "header"
        List<PersonUiWrapper> males = new ArrayList<PersonUiWrapper>(5);
        PersonUiWrapper maleHeader = new PersonUiWrapper(new Person("Males", null, null, null), males, people);
        people.add(maleHeader);
        males.add(new PersonUiWrapper(new Person("Jason", "Porter", "jporter@what.com", "LightGuard")));
        males.add(new PersonUiWrapper(new Person("Trevor", "Whitlock", "twhitlock@what.com", "chikin")));
        males.add(new PersonUiWrapper(new Person("Michael", "Felix", "mfelix@what.com", "Mike")));
        males.add(new PersonUiWrapper(new Person("Bill", "Comp", "bcomp@what.com", "The Big Cheese")));
        males.add(new PersonUiWrapper(new Person("Tom", "Compagno", "tcompagno@what.com", "The Boss")));

        List<PersonUiWrapper> females = new ArrayList<PersonUiWrapper>(5);
        PersonUiWrapper femaleHeader = new PersonUiWrapper(new Person("Females", null, null, null), females, people);
        people.add(femaleHeader);
        females.add(new PersonUiWrapper(new Person("Bobbi", "Alexandrova", "balexandrova@what.com", "")));
        females.add(new PersonUiWrapper(new Person("Sue", "Ure", "sure@what.com", "Food Lady")));
        females.add(new PersonUiWrapper(new Person("Tessie", "Porter", "tporter@what.com", "My Boss")));
        females.add(new PersonUiWrapper(new Person("Carolyn", "Irish", "cirish@what.com", "Owner")));
        females.add(new PersonUiWrapper(new Person("Emily", "Porter", "eporter@what.com", "The Cute One")));

        maleHeader.setToggled(true);
        femaleHeader.setToggled(false);
    }

    private void sort()
    {
        log.debug("Going to perform the sort on column {0}", this.sortColumn);
        Comparator comparator = new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                PersonUiWrapper c1 = (PersonUiWrapper) o1;
                PersonUiWrapper c2 = (PersonUiWrapper) o2;

                log.trace("Sort c1: {0}, sort c2: {1}", c1, c2);
 
                if (c1.isHeader() || c2.isHeader() || sortColumn == null)
                {
                    return 0;
                }

                if (sortColumn.equals("firstName"))
                {
                    return sortAsc ?
                           c1.getWrapped().getFirstName().compareTo(c2.getWrapped().getFirstName()) :
                           c2.getWrapped().getFirstName().compareTo(c1.getWrapped().getFirstName());
                }
                else if (sortColumn.equals("lastName"))
                {
                    return sortAsc ?
                           c1.getWrapped().getLastName().compareTo(c2.getWrapped().getLastName()) :
                           c2.getWrapped().getLastName().compareTo(c1.getWrapped().getLastName());
                }
                else if (sortColumn.equals("alias"))
                {
                    return sortAsc ?
                           c1.getWrapped().getNickName().compareTo(c2.getWrapped().getNickName()) :
                           c2.getWrapped().getNickName().compareTo(c1.getWrapped().getNickName());
                }
                else if (sortColumn.equals("email"))
                {
                    return sortAsc ?
                           c1.getWrapped().getEmail().compareTo(c2.getWrapped().getEmail()) :
                           c2.getWrapped().getEmail().compareTo(c1.getWrapped().getEmail());
                }
                else
                {
                    return 0;
                }
            }
        };
        Collections.sort(people, comparator);
    }

    @BypassInterceptors
    public List<PersonUiWrapper> getPeople()
    {
        if ((oldSortColumn != null && !oldSortColumn.equals(this.sortColumn)) || this.oldSortAsc != this.sortAsc)
        {
            sort();
            oldSortColumn = sortColumn;
            oldSortAsc = sortAsc;
        }
        return people;
    }

    public void setPeople(List<PersonUiWrapper> people)
    {
        this.people = people;
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
    public boolean isSortAsc()
    {
        return sortAsc;
    }

    @BypassInterceptors
    public void setSortAsc(boolean sortAsc)
    {
        this.sortAsc = sortAsc;
    }
}
