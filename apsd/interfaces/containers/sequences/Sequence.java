package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> extends IterableContainer<Data>{ // Must extend IterableContainer

    default Data GetAt(Natural position){
        ExcIfOutOfBound(position);
        ForwardIterator<Data> it  = FIterator();
        it.Next(position.ToLong());
        return it.GetCurrent();
    }

    default Data GetFirst(){
        return GetAt(new Natural(0));
    }
    default Data GetLast(){
        ForwardIterator<Data> it  = FIterator();
        long tmp=0;
        while(it.IsValid()){
            tmp++;
            it.Next();
        }
        return GetAt(new Natural(tmp-1));
    }

    default Natural Search(Data target) {
        ForwardIterator<Data> it = FIterator();
        long index = 0;

        while (it.IsValid()) {
            Data currentData = it.DataNNext();

            if (java.util.Objects.equals(currentData, target)) {
                return new Natural(index);
            }

            index++;
        }

        return Size();
    }

    default boolean IsInBound(Natural position){
        ForwardIterator<Data> it  = FIterator();
        it.Next(position.ToLong());
        if (!it.IsValid()) {
            return false;
        }
        return true;
    }

     default long ExcIfOutOfBound(Natural num) {
       if (num == null) throw new NullPointerException("Natural number cannot be null!");
       long idx = num.ToLong();
       if (idx >= Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
       return idx;
     }

    Sequence<Data> SubSequence(Natural firstPos, Natural secondPos);

}
