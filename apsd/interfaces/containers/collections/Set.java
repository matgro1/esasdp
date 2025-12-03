package apsd.interfaces.containers.collections;


import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data> { // Must extend Collection

  void Union(Set<Data> set);

  void Difference(Set<Data> set);

  void Intersection(Set<Data> set);

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
    boolean IsEqual(IterableContainer<Data> container);

}
