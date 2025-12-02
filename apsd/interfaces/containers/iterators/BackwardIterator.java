package apsd.interfaces.containers.iterators;

import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

    default void Prev(){
        DataNPrev();
    }
    default void Prev(long steps){
        for(long step=steps;step>0;step--){
            Prev();
        }
    }

    Data DataNPrev();

    default boolean ForEachBackward(Predicate<Data> fun) {
        if (fun != null) {
            while (IsValid()) {
                if (fun.Apply(DataNPrev())) {
                    return true;
                }
            }
        }
        return false;
    }

}
