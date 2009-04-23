package org.example.icefaces.datatable.expandable;

import javax.faces.event.ActionEvent;
import java.util.List;
import java.io.Serializable;

/**
 * Wrapper class to support displaying a {@link Person} class on the web site
 * without cluttering the data class with UI logic.
 */
public class PersonUiWrapper implements Serializable
{
    /**
     * Simple has-a relationship.
     */
    private Person wrapped;
    /**
     * Really this is only needed for the "header" rows to determine the image and wether or not to show
     * the sub group.
     */
    private boolean toggled = false;
    /**
     * Another boolean only needed for the "header" rows, used to determine the rendered attribute for the collapsible image.
     */
    private boolean header = false;
    /**
     * Children of this group.  We have to store it here so we can add and remove from the data table.
     */
    private List<PersonUiWrapper> children;
    /**
     * Same data table from the backing bean.  Used here because we're not (currently) using Seam so we have to
     * pass it and store it here so we can modify it.  If we were using Seam we'd simply put this into one of the
     * different scopes and call it good, then find it in the scope programatically.
     */
    private List<PersonUiWrapper> dataTable;
    /**
     * Path to collapsible image.
     */
    private String imagePath;

    /**
     * Basic constructor for children.
     */
    public PersonUiWrapper(Person wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * Header constructor.
     */
    public PersonUiWrapper(Person wrapped,
                           List<PersonUiWrapper> myChildren, List<PersonUiWrapper> myDataTable) {
        this.wrapped = wrapped;
        this.header = true;
        this.children = myChildren;
        this.dataTable = myDataTable;

        this.modifyDataTable();
    }

    /**
     * ActionChangeListener for the click of the expand / contract link.
     */
    public void toggleGroup(ActionEvent event) {
        this.toggled = !this.toggled;

        // Now we know if we've expanded or not, we have to modify the data table.
        this.modifyDataTable();

    }

    /**
     * Very simple add / remove children for this header, and swap the image.
     */
    protected void modifyDataTable() {
        int index = this.dataTable.indexOf(this);
        if (this.toggled) {
            this.dataTable.addAll(index + 1, this.children);
            this.imagePath = "/xmlhttp/css/rime/css-images/tree_nav_top_close_no_siblings.gif";
        } else {
            this.dataTable.removeAll(this.children);
            this.imagePath = "/xmlhttp/css/rime/css-images/tree_nav_top_open_no_siblings.gif";
        }
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        this.modifyDataTable();
    }

    public Person getWrapped() {
        return wrapped;
    }

    public void setWrapped(Person wrapped) {
        this.wrapped = wrapped;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonUiWrapper that = (PersonUiWrapper) o;

        if (header != that.header) return false;
        if (toggled != that.toggled) return false;
        if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null) return false;
        if (wrapped != null ? !wrapped.equals(that.wrapped) : that.wrapped != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (wrapped != null ? wrapped.hashCode() : 0);
        result = 31 * result + (toggled ? 1 : 0);
        result = 31 * result + (header ? 1 : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("PersonUiWrapper");
        sb.append("{wrapped=").append(wrapped);
        sb.append(", toggled=").append(toggled);
        sb.append(", header=").append(header);
        sb.append('}');
        return sb.toString();
    }
}
