package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

public interface Vector<Data>extends ReallocableContainer,MutableSequence<Data> { // Must extend ReallocableContainer and MutableSequence

    default void ShiftLeft(Natural pos){
        ShiftLeft(pos,new Natural(1));

    }

    default void ShiftLeft(Natural pos, Natural num) {//boh
        long idx = ExcIfOutOfBound(pos);//indice
        long size = Size().ToLong();
        long len = num.ToLong();//quanto deve shiftare
        len = (len <= size - idx) ? len : size - idx;//controllo per vedere se sta fuori dalla grandezza del vettore si poteva usare Math.min invece di sto troiaio
        if (len > 0) {
            long iniwrt = idx;
            long wrt = iniwrt;//inizializza la posizione del writer all'indice e salva la prima posizione
            for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {//mette il reader alla writer+shiftMagnitude posizione (se wrt=1 rdr=3)
                Natural natrdr = Natural.Of(rdr);//ridondante
                SetAt(GetAt(natrdr), Natural.Of(wrt));// copia il valore sulla posizione del reader alla posizione del writer
                SetAt(null, natrdr);// mette null sulla posizione del reader
            }
            for (; wrt - iniwrt < len; wrt++) {//mette tutti i valori successivi all'ultimo writer a null per essere sicuri
                SetAt(null, Natural.Of(wrt));
            }
        }
    }

    default void ShiftFirstLeft(){
        ShiftLeft(new Natural(0));
    }

    default void ShiftLastLeft(){
        ShiftLeft(new Natural(Size().ToLong()-1));
    }

    default void ShiftRight(Natural pos){
        ShiftRight(pos,new Natural(1));
    }
    default void ShiftRight(Natural pos,Natural num){
        long index = ExcIfOutOfBound(pos);
        long size = Size().ToLong();
        long shiftMagnitude = num.ToLong();
        if(shiftMagnitude <= 0){
            return;
        }
        for(long readerPos = size-1; readerPos >= index; readerPos--){
            long writePos = readerPos + shiftMagnitude;
            if (writePos<size) {
                SetAt(GetAt(Natural.Of(readerPos)), Natural.Of(writePos));
            }
            SetAt(null, Natural.Of(readerPos));
        }
    }

    default void ShiftFirstRight(){
        ShiftRight(new Natural(0));
    }

    default void ShiftLastRight(){
        ShiftRight(new Natural(Size().ToLong()-1));
    }

    Vector<Data> SubVector(Natural firstPos, Natural secondPos);

    @Override
    default MutableSequence<Data> SubSequence(Natural firstPos, Natural secondPos) {
        return SubVector(firstPos, secondPos);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

}
