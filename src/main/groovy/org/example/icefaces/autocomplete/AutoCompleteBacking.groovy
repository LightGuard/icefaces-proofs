package org.example.icefaces.autocomplete

import com.icesoft.faces.context.effects.Effect
import com.icesoft.faces.context.effects.Highlight
import javax.faces.event.ValueChangeEvent
import org.jboss.seam.ScopeType
import org.jboss.seam.annotations.Logger
import org.jboss.seam.annotations.Name
import org.jboss.seam.annotations.Scope
import org.jboss.seam.log.Log

@Name("autocomplete")
@Scope(ScopeType.CONVERSATION)
public class AutoCompleteBacking implements Serializable
{
    @Logger Log log
    private int hits;
    private Effect hitEffect = new Highlight();
    private List<String> masterList = ['PERL', 'Java', 'Python', 'Ruby', 'Groovy', 'Scala', 'Pascal', 'jython', 'COBAL', 'C++', 'C#', 'C'];
    private List<String> list = []

    public void updateList(ValueChangeEvent event)
    {
        this.hitEffect.setFired(false);
        this.hits += 1
        this.list = this.masterList.findAll { ((String) it).startsWith((String) event.getNewValue())}
    }

    public void setList(List<String> list)
    {
        this.list = list;
    }

    public List<String> getList()
    {
        return list;
    }

    public void setHitEffect(Effect hitEffect)
    {
        this.hitEffect = hitEffect;
    }

    public Effect getHitEffect()
    {
        return hitEffect;
    }

    public void setHits(int hits)
    {
        this.hits = hits;
    }

    public int getHits()
    {
        return hits;
    }
}