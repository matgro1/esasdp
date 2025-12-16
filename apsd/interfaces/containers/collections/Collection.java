package apsd.interfaces.containers.collections;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.ClearableContainer;
 import apsd.interfaces.containers.base.InsertableContainer;
 import apsd.interfaces.containers.base.IterableContainer;
 import apsd.interfaces.containers.base.RemovableContainer;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.traits.Predicate;

public interface Collection<Data> extends ClearableContainer, InsertableContainer<Data>, RemovableContainer<Data>, IterableContainer<Data>{ // Must extend ClearableContainer, InsertableContainer, RemovableContainer, and IterableContainer

    default boolean Filter(Predicate<Data> fun) {
        Natural oldsize = Size();
        if (fun != null) {
            java.util.ArrayList<Data> toRemove = new java. util.ArrayList<>();
            ForwardIterator<Data> itr = FIterator();
            while (itr.IsValid()) {
                Data dat = itr.DataNNext();
                if (!fun.Apply(dat)) {
                    toRemove.add(dat);
                }
            }
            for (Data dat : toRemove) {
                Remove(dat);
            }
        }
        return !Size().equals(oldsize);
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
    void Clear();

}
