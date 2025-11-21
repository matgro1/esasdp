package apsd.interfaces.containers.iterators;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.traits.Predicate;

import apsd.classes.utilities.Natural;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data>{ // Must extend Iterator

  void Next();
  void Next(long steps);
  void Next(Natural steps);
  Data DataNNext();

  default boolean ForEachForward(Predicate<Data> fun) {
     if (fun != null) {
       while (IsValid()) {
         if (fun.Apply(DataNNext())) { return true; }
       }
     }
     return false;
   }

}
