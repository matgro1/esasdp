package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Queue;

import java.util.EmptyStackException;

/** Object: Wrapper queue implementation. */
public class WQueue<Data> implements Queue<Data> { // Must implement Queue

  protected final List<Data> lst;

  public WQueue(){
      lst = new VList<>();
  }

  public WQueue(List<Data> lst){
      this.lst = lst;
  }

  public WQueue(TraversableContainer<Data> con){

      this.lst =new VList<>();
      if (con != null) {
          con.TraverseForward(this::Insert);
      }  }

  public WQueue(List<Data> lst, TraversableContainer<Data> con){
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
    public Natural Size() {
        return lst.Size();
    }

    /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    public void Clear(){
        lst.Clear();
    }

    @Override
    public Data Head(){
        if(lst.IsEmpty()) throw new EmptyStackException();
        return lst.GetFirst();
    }

    @Override
    public void Dequeue() {
        if(lst.IsEmpty()) throw new EmptyStackException();
        lst.RemoveFirst();
    }
    @Override
    public Data HeadNDequeue(){
        if(lst.IsEmpty()) throw new EmptyStackException();
        Data ret = lst.GetFirst();
        lst.RemoveFirst();
        return ret;
    }

    @Override
    public void Enqueue(Data data) {
        lst.InsertLast(data);
    }
    public boolean Insert(Data data) {
        Enqueue(data);
        return true;
    }
    /* ************************************************************************ */
  /* Override specific member functions from Queue                            */
  /* ************************************************************************ */

  // ...

}
