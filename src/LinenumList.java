// Copyright(c) 2016 Hung Ta
//
package SE330;

/**
 * Created by Hung on 3/3/2016.
 */
public class LinenumList extends SLList
{
    public String toString()
    {
        String s = "";
        for (SLLNode tmp = head; tmp != null; tmp = tmp.next)
        {
            s += tmp.info.toString();
            if (tmp.next != null)
                s += ", ";
        }
        return s;
    }
    public void add(Object el)
    {
        super.add(el);
    }  // Assuming your SLList class has an add() method
}
