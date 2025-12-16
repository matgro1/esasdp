package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer,InsertableContainer<Data> { // Must extend ClearableContainer and InsertableContainer

  Data Top();
  void Pop();
  default Data TopNPop(){
    Data data = Top();
    Pop();
    return data;
  }

  default void SwapTop(Data data){
    Pop();
    Push(data);
  }
  default Data TopNSwap(Data data){
    Data top = Top();
    SwapTop(data);
    return top;
  };

  void Push(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  default void Clear(){
    while (Top() != null)Pop();
  }
  // ...

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data data){
      Push(data);
      return java.util.Objects.equals(data, Top());
  }

}
