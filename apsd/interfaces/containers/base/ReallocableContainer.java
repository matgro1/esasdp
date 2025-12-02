package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Clearable;
import apsd.interfaces.traits.Reallocable;

/** Interface: ClearableContainer che è anche Reallocable. */
public interface ReallocableContainer extends ClearableContainer, Reallocable { // Must extend ClearableContainer, Reallocable

    double GROW_FACTOR = 2.0; // Must be strictly greater than 1.
    double SHRINK_FACTOR = 2.0; // Must be strictly greater than 1.

    Natural Capacity();


    default void Grow() {
        long currentCapacity = Capacity().ToLong();

        long newCapacity = (long)(currentCapacity * GROW_FACTOR);

        if (newCapacity <= currentCapacity) {
            newCapacity = Math.max(currentCapacity + 1, 1);
        }

        // Delega al metodo con parametro
        Grow(new Natural(newCapacity));
    }

    default void Grow(Natural newCapacity) {

        if (newCapacity.ToLong() > Capacity().ToLong()) {
            Realloc(newCapacity);
        }
    }

    default void Shrink() {
        long currentCapacity = Capacity().ToLong();
        long newCapacity = (long)(currentCapacity / SHRINK_FACTOR);

        long minSize = Size().ToLong();
        if (newCapacity < minSize) newCapacity = minSize;

        Realloc(new Natural(newCapacity));
    }


    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */


    @Override
    default Natural Size(){
        throw new UnsupportedOperationException("ERRORE GRAVE: Hai dimenticato di fare l'override di Size() in " + this.getClass().getName());
    }
    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */
    @Override
    default void Clear(){
        Realloc(new Natural(0));
    }
// ...

}
