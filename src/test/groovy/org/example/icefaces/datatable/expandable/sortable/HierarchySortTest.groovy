package org.example.icefaces.datatable.expandable.sortable

import org.example.icefaces.datatable.expandable.sortable.ChildInfo
import org.example.icefaces.datatable.expandable.sortable.DisplayInfoComparator
import org.example.icefaces.datatable.expandable.sortable.DisplayableInfo
import org.example.icefaces.datatable.expandable.sortable.GrandChildInfo
import org.example.icefaces.datatable.expandable.sortable.ParentInfo

public class HierarchySortTest extends GroovyTestCase
{
    void testSort()
    {
        def origList = new ArrayList<DisplayableInfo>();

        def clientList = [new GrandChildInfo(id: '21', name: 'CABC', status: 'Test'),
            new GrandChildInfo(id: '20', name: 'CBCA', status: 'Off')]

        def programList = [new ChildInfo(id: '12', name: 'PABC', status: 'On'),
            new ChildInfo(id: '14', name: 'PBCA', status: 'Off', childClients:clientList, expanded:true),
            new ChildInfo(id: '13', name: 'PCBA', status: 'On')]


        def campaign1 = new ParentInfo(id: '1', name: 'ABC', status: 'A')
        def campaign2 = new ParentInfo(id: '6', name: 'ZYX', status: 'B', childPrograms:programList, expanded:true)
        def campaign3 = new ParentInfo(id: '3', name: 'JKL', status: 'Z')

        // Start adding the hierarchy together
        origList.add(campaign1)
        origList.add(campaign2)
        origList.add(campaign3)

        def comparator = new DisplayInfoComparator(true, 'name')
        def sortedList = new ArrayList<DisplayableInfo>(origList);

        Collections.sort(sortedList, comparator)

        origList.each { item -> item.addToDataModel(sortedList, comparator) }

        assertEquals 8, sortedList.size()
        assertEquals 'JKL', sortedList[1].name
        assertEquals 'PCBA', sortedList[7].name
    }
}
