package apsd.classes.containers.collections.abstractcollections.bases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.IterableContainer;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.collections.Set;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.traits.Predicate;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data>  { // Must implement Set; Chn must extend Chain

  protected Chn chn;

  protected WSetBase(){

  }

  // ChainAlloc

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
   public Natural Size(){
      return  chn.FoldForward((data, acc) ->acc.Increment(),
              new Natural(0));
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    @Override
    public void Clear(){
        chn.TraverseForward(this::Remove);
    }
  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public   boolean Insert(Data data){
      return chn.InsertIfAbsent(data);
  }


  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  public boolean Remove(Data data){
      return chn.Remove(data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

    @Override
    public BackwardIterator<Data> BIterator() {

        return chn.BIterator();
    }
    @Override
    public ForwardIterator<Data> FIterator() {

        return chn.FIterator();
    }

    /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

    @Override
    public boolean Filter(Predicate<Data> fun) {
        return chn.Filter(fun);
    }

    /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */
    abstract public Set<Data> Intersection(Set<Data> First, Set<Data> Second);

    @Override
    public boolean IsEqual(IterableContainer<Data> container) {
        return chn.IsEqual(container);
    }
}
