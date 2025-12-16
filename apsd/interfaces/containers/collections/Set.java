package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data> { // Must extend Collection

  default void Union(Set<Data> set) {
    if (set == null) return;

    if (set == this) {
      return;
    }

    set.TraverseForward((elem) -> {
      this.Insert(elem);
      return false;
    });

  }

  default void Difference(Set<Data> set) {
    if (set == null) return;

    if (set == this) {
      Clear();
      return;
    }

    set.TraverseForward((elem) -> {
      this.Remove(elem);
      return false;
    });
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
