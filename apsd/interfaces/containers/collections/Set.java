package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data> { // Must extend Collection

  default void Union(Set<Data> set) {
    if (set == null) return;
    set.TraverseForward(this::Insert);
  }

  default void Difference(Set<Data> set) {
    if (set == null) return;
    set.TraverseForward(this::Remove);
  }

  default void Intersection(Set<Data> set) {
    if (set == null) {
      this.Clear();
      return;
    }
    this.Filter(set::Exists);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  boolean IsEqual(IterableContainer<Data> container);

}
