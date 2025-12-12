package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

import java.util.EmptyStackException;

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
        if(lst.IsEmpty()) throw new EmptyStackException();
        return lst.GetLast();
    }
    public boolean Insert(Data data) {
        Push(data);
        return true;
    }
    @Override
    public void Pop(){
        if(lst.IsEmpty()) throw new EmptyStackException();
        lst.RemoveLast();
    }
    @Override
    public Data TopNPop(){
        if(lst.IsEmpty()) throw new EmptyStackException();
        Data ret = lst.GetLast();
        lst.RemoveLast();
        return ret;
    }

    @Override
    public void SwapTop(Data data) {
        if(lst.IsEmpty()) throw new EmptyStackException();
        lst.SetLast(data);
    }
    @Override
    public Data TopNSwap(Data data) {
        if(lst.IsEmpty()) throw new EmptyStackException();
        Data ret = lst.GetLast();
        lst.SetLast(data);
        return ret;
    }
    @Override
    public void Push(Data data) {
        lst.InsertLast(data);
    }
}
