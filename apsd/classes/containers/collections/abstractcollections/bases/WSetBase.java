package apsd.classes.containers.collections.abstractcollections.bases;

 import apsd.classes.utilities.Natural;
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

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

  // ...

}
