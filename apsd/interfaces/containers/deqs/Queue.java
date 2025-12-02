package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer, InsertableContainer<Data>{ // Must extend ClearableContainer and InsertableContainer

  Data Head();
  void Dequeue();
  default Data HeadNDequeue(){
      Data head = Head();
      Dequeue();
      return head;
  }

  void Enqueue(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  default void Clear(){
      while (Head() != null){
          Dequeue();
      }
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

    @Override
    default boolean Insert(Data data){

        try{
            Enqueue(data);
            return java.util.Objects.equals(data, Head());
        }catch(Exception e){
            return false;
        }
    }
  // ...

}
