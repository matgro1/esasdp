package apsd.interfaces.containers.base;

// import apsd.classes.utilities.Natural;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer extends ReallocableContainer { // Must extend ReallocableContainer

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.


  void Expand();
  void Expand(Natural steps);


  void Reduce();
  void Reduce(Natural steps);
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */
  @Override
  Natural Size();
  // ...

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  default void Grow(){
    if (Size().ToLong() == Capacity().ToLong()) {
      ReallocableContainer.super.Grow();
    }
  }


  @Override
  default void Shrink() {
    if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong()) ReallocableContainer.super.Shrink();
  }

}
