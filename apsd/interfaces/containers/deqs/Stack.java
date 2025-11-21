package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer,InsertableContainer<Data> { // Must extend ClearableContainer and InsertableContainer

  Data Top();
  void Pop();
  Data TopNPop();

  void SwapTop(Data data);
  Data TopNSwap(Data data);

  void Push(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...

}
