package org.example.icefaces.datatable.expandable.sortable

import org.example.icefaces.datatable.expandable.sortable.GrandChildInfo
import org.example.icefaces.datatable.expandable.sortable.DisplayInfoComparator

class DisplayInfoComparatorTest extends GroovyTestCase {
    void testSimpleCompare() {
        // Standard sort
        assertEquals(0, new DisplayInfoComparator(true, 'id').compare(new GrandChildInfo(id:'1'), new GrandChildInfo(id:'1')))
        assertEquals(-1, new DisplayInfoComparator(true, 'id').compare(new GrandChildInfo(id:'0'), new GrandChildInfo(id:'1')))
        assertEquals(1, new DisplayInfoComparator(true, 'id').compare(new GrandChildInfo(id:'1'), new GrandChildInfo(id:'0')))

        // Reverse sort
        assertEquals(0, new DisplayInfoComparator(false, 'id').compare(new GrandChildInfo(id:'1'), new GrandChildInfo(id:'1')))
        assertEquals(1, new DisplayInfoComparator(false, 'id').compare(new GrandChildInfo(id:'0'), new GrandChildInfo(id:'1')))
        assertEquals(-1, new DisplayInfoComparator(false, 'id').compare(new GrandChildInfo(id:'1'), new GrandChildInfo(id:'0')))

        // Check for NPEs
        shouldFail(NullPointerException, {
                   assertEquals(0, new DisplayInfoComparator(false, 'id').compare(null, new GrandChildInfo(id:'1')))
                   })
        shouldFail(NullPointerException, {
                   assertEquals(0, new DisplayInfoComparator(false, 'id').compare(new GrandChildInfo(), new GrandChildInfo(id:'1')))
                   })
        shouldFail(NullPointerException, {
                   assertEquals(0, new DisplayInfoComparator(false, 'id').compare(new GrandChildInfo(id:'1'), null))
                   })
        shouldFail(NullPointerException, {
                   assertEquals(0, new DisplayInfoComparator(false, 'id').compare(new GrandChildInfo(id:'1'), new GrandChildInfo()))
                   })

        // Rest of the properties
        assertEquals(0, new DisplayInfoComparator(true, 'name').compare(new GrandChildInfo(name:'1'), new GrandChildInfo(name:'1')))
        assertEquals(-1, new DisplayInfoComparator(true, 'name').compare(new GrandChildInfo(name:'0'), new GrandChildInfo(name:'1')))
        assertEquals(1, new DisplayInfoComparator(true, 'name').compare(new GrandChildInfo(name:'1'), new GrandChildInfo(name:'0')))

        assertEquals(0, new DisplayInfoComparator(false, 'name').compare(new GrandChildInfo(name:'1'), new GrandChildInfo(name:'1')))
        assertEquals(1, new DisplayInfoComparator(false, 'name').compare(new GrandChildInfo(name:'0'), new GrandChildInfo(name:'1')))
        assertEquals(-1, new DisplayInfoComparator(false, 'name').compare(new GrandChildInfo(name:'1'), new GrandChildInfo(name:'0')))

        assertEquals(0, new DisplayInfoComparator(true, 'status').compare(new GrandChildInfo(status:'1'), new GrandChildInfo(status:'1')))
        assertEquals(-1, new DisplayInfoComparator(true, 'status').compare(new GrandChildInfo(status:'0'), new GrandChildInfo(status:'1')))
        assertEquals(1, new DisplayInfoComparator(true, 'status').compare(new GrandChildInfo(status:'1'), new GrandChildInfo(status:'0')))

        assertEquals(0, new DisplayInfoComparator(false, 'status').compare(new GrandChildInfo(status:'1'), new GrandChildInfo(status:'1')))
        assertEquals(1, new DisplayInfoComparator(false, 'status').compare(new GrandChildInfo(status:'0'), new GrandChildInfo(status:'1')))
        assertEquals(-1, new DisplayInfoComparator(false, 'status').compare(new GrandChildInfo(status:'1'), new GrandChildInfo(status:'0')))
    }
}
