package org.example.icefaces.datatable.column

import org.example.icefaces.datatable.expandable.Person
import org.jboss.seam.ScopeType
import org.jboss.seam.annotations.Factory
import org.jboss.seam.annotations.Name
import org.jboss.seam.annotations.Scope

@Name ("basicColumn")
@Scope (ScopeType.CONVERSATION)
public class BasicColumns
{
    @Factory ("basicColumnData")
    public List<Integer> createColumnData()
    {
        1..9
    }

    @Factory ("basicRowData")
    public List<String> createData()
    {
        ['One', 'Two', 'Three']
    }

    @Factory ("basicObjectColumnData")
    public List<String> createObjectRowData()
    {
        ['firstName', 'lastName', 'email', 'nickName']
    }

    @Factory ("basicObjectRowData")
    public List<Person> createObjectColumnData()
    {
        [new Person('Bill', 'Horse', 'bill@me.com', 'Bill the Horse'),
            new Person('John', 'Doe', 'idk@me.com', '??'),
            new Person('Jack', 'Frost', 'frost@me.com', 'Frosty')]
    }
}