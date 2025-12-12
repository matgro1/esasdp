package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<Data> { // Must implement Stack

    protected final List<Data> lst;

    public WStack(){
        lst = new VList<>();
    }

    public WStack(List<Data> lst){
        this.lst = lst;
    }

    public WStack(TraversableContainer<Data> con){

        this.lst =new VList<>();
        if (con != null) {
            con.TraverseForward(this::Insert);
        }  }

    public WStack(List<Data> lst, TraversableContainer<Data> con){
        if (lst != null) {
            this.lst =  lst;
        } else {
            this.lst =new VList<>();
        }

        if (con != null) {
            con.TraverseForward(this::Insert);
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    @Override
    public Natural Size(){
        return lst.Size();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */
    @Override
    public void Clear(){
        lst.Clear();
    }

    /* ************************************************************************ */
    /* Override specific member functions from Stack                            */
    /* ************************************************************************ */
    @Override
    public Data Top(){
        return lst.GetFirst();
    }
    @Override
    public void Pop(){
        lst.RemoveFirst();
    }
    @Override
    public Data TopNPop(){
        Data ret = lst.GetFirst();
        lst.RemoveFirst();
        return ret;
    }

    @Override
    public void SwapTop(Data data) {
       lst.SetFirst(data);
    }
    @Override
    public Data TopNSwap(Data data) {
        Data ret = lst.GetFirst();
        lst.SetFirst(data);
        return ret;
    }
    @Override
    public void Push(Data data) {
        lst.InsertLast(data);
    }
}
