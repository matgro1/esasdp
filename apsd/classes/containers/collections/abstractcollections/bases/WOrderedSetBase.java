package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.OrderedSet;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WOrderedSetBase<Data extends Comparable<? super Data>, Chn extends SortedChain<Data>>
        extends WSetBase<Data, Chn> implements OrderedSet<Data>   {

  protected WOrderedSetBase(){ }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  public boolean IsEqual(IterableContainer<Data> container) {
    if (container == null) return false;
    if (this == container) return true;

    if (this.Size().ToLong() != container.Size().ToLong()) {
      return false;
    }

    var myIter = chn.FIterator();
    var otherIter = container.FIterator();

    while (myIter.IsValid() && otherIter.IsValid()) {
      Data myData = myIter.GetCurrent();
      Data otherData = otherIter.GetCurrent();

      if (!myData.equals(otherData)) {
        return false;
      }

      myIter.Next();
      otherIter.Next();
    }

    return true;
  }

  @Override
  public Data Min(){
    if (chn.IsEmpty()) return null;
    return chn.GetFirst();
  }
  @Override
  public Data Max(){
    if (chn.IsEmpty()) return null;
    return chn.GetLast();
  }

  @Override
  public void RemoveMin(){
    chn.RemoveFirst();
  }
  @Override
  public void RemoveMax(){
    chn.RemoveLast();
  }
  @Override
  public Data MinNRemove(){
    if (chn.IsEmpty()) return null;
    Data min = chn.GetFirst();
    chn.RemoveFirst();
    return min;
  }
  @Override
  public Data MaxNRemove(){
    if (chn.IsEmpty()) return null;
    Data max = chn.GetLast();
    chn.RemoveLast();
    return max;
  }
  @Override
  public Data Predecessor(Data d){
    return chn.Predecessor(d);
  }
  @Override
  public Data Successor(Data d){
   return chn.Successor(d);
  }
  @Override
  public void RemovePredecessor(Data d){
    Remove(Predecessor(d));
  }
  @Override
  public void RemoveSuccessor(Data d){
    Remove(Successor(d));
  }
  @Override
  public Data PredecessorNRemove(Data d){
    Data ret = Predecessor(d);
    RemovePredecessor(d);
    return ret;
  }
  @Override
  public Data SuccessorNRemove(Data d) {
    Data ret = Successor(d);
    RemoveSuccessor(d);
    return ret;
  }
}