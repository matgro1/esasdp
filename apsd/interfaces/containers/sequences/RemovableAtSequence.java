package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data> { // Must extend Sequence

  default void RemoveAt(Natural position) {
      ExcIfOutOfBound(position);
      AtNRemove(position);
  }
  Data AtNRemove(Natural position);

  default void RemoveFirst(){
      AtNRemove(new Natural(0));
  }
  default Data FirstNRemove(){
     return AtNRemove(new Natural(0));
  }

  default void RemoveLast(){

  }
  default Data LastNRemove(){

      ForwardIterator<Data> it  = FIterator();
      long tmp=0;
      while(it.IsValid()){
          tmp++;
          it.Next();
      }
      return AtNRemove(new Natural(tmp-1));
  }

}
