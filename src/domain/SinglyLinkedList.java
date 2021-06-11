/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class SinglyLinkedList implements List {
    private Node first; //apunta al inicio de la lista dinamica
    
    //Constructor
    public SinglyLinkedList(){
        this.first = null; //la lista todavia no existe
    }

    @Override
    public int size() throws ListException {
        if(isEmpty()){        
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        } //To change body of generated methods, choose Tools | Templates.
        Node aux = first;
        int count = 0;
        while(aux!=null){
            count++;
            aux = aux.next;
        }
        return count;
    }

    @Override
    public void clear() {
        this.first = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        while(aux!=null){
            if(util.Utility.equals(aux.data,element)){
                 return true;
            }
            aux = aux.next;
        }
        return false;
    }
   
    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            this.first = newNode; //Apunta a un nuevo nodo
        }else{
           Node aux = first;//
           //El auxiliar es para moverme por la lista
           while(aux.next!=null){
               aux = aux.next;
           }
           //Cuando se sale del while quiere decir que aux.sig == null
           aux.next = newNode;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            this.first = newNode; //Apunta a un nuevo nodo
        }
        newNode.next=first;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override//Desarrollar para que inserte en cuaquier tipo de lista
    public void addInSortedList(Object element) {
      Node newNode = new Node(element);
      if(isEmpty()){
          first =  newNode;
      }else{
          //Caso 2. first.next es nulo o no es nulo
          if(util.Utility.greaterT(first.data, element)){//Solo 2 valores
              newNode.next = first;
              first = newNode;
          }else{
          //Caso 3. todo lo demas //Agrega un elemento en el medio
          Node prev = first;
          Node aux = first.next;
          boolean added=false;
          while(aux!=null&&!added){
              if(util.Utility.lessT(element, aux.data)){//Inserta cuando el valor del nodo a insetar sea menor sea menor 
                                                        //al elemento del noto auxiliar
                  prev.next = newNode;
                  newNode.next = aux;
                  added = true;//Agrego el valor
              }
              prev = aux;
              aux = aux.next;
              
            }
          //si llega aqui el elemento se agrega al final de la lista
          if(!added){
              prev.next = newNode;
          }
        }
      }
    }

    @Override
    public void remove(Object element) throws ListException {
        if(isEmpty()){        
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        } 
        //CASO 1. EL ELEMENTO A SUPRIMIR ES EL PRIMERO DE LA LISTA
        if(util.Utility.equals(first.data, element)){
            first = first.next;
        }else{
        //CASO 2. EL ELEMENTO A SUPRIMIR ESTA EN CUALQUIER OTRO NODO
        Node prev = first; //Para dejar un rastro, apunta al anterior de aux
        Node aux = first.next; 
        while(aux!=null&&!(util.Utility.equals(aux.data, element))){
            prev = aux; //Un nodo atras del auxiliar
            aux = aux.next;
        }
        //Se sale cuando alcanza nulo o cuando encuentra el elemento
        
        //Cuando encuentra el elemento
        if(aux!=null&&util.Utility.equals(aux.data, element)){
            //Desenlazo o desconecto el nodo
            prev.next = aux.next;//me salto la posicion
         }
       }
    }

    @Override
    public Object removeFirst() throws ListException {
        if(isEmpty()){
            throw new UnsupportedOperationException("SinglyLinkedList is empty"); //To change body of generated methods, choose Tools | Templates.
        }
        Object element = first.data;
        first = first.next;//Evita que pierda el apuntador al inicio de la lista
        return element;
    }

    @Override
    public Object removeLast() throws ListException {
        if(isEmpty()){        
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        } 
        Node aux = first;
        Node prev = first; //Para dejar un rastro, apunta al anterior de aux
        while(aux.next!=null){
            prev = aux; //Un nodo atras del auxiliar
            aux = aux.next;
        }
        //Se sale del while en el ultimo nodo
        Object element = aux.data;
        prev.next = null; //Desconecto el ultimo nodo
        return element;
    }

    @Override
    public void sort() throws ListException {//cambiar ordenando por nodos
        
        Node prev = first;//Nodo que apunta a la cabeza
        Node aux = first.next;
        
        if (isEmpty()) {//Caso lista vacia
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        }
            
        if(size()==2){//Caso lista 2 nodos
            if (util.Utility.greaterT(prev.data, aux.data)) {
                aux.next = prev;
                prev.next=null;
                this.first = aux;
            }
        }
        
        if (size() > 1 && size() > 2) {//Si hay mas de un elemento en la lista
            prev = first;//Nodo que apunta a la cabeza
            aux = first.next;
            Node temp = prev;
            Node posNode = first;
            int counter = 0;
            while(counter<size()){//Ordena todos los nodos 
                posNode = first;
                prev = first;
                aux = first.next;
                temp = prev;
                while (aux != null) {//va comparando cada nodo por separado
                    if (util.Utility.greaterT(prev.data, aux.data)) {//Si el nodo actual es menor al proximo              
                        if (prev.next.next != null && prev.equals(first)) {//Si estamos en la cabeza
                            temp = aux;
                            prev.next = prev.next.next;
                            temp.next = prev;
                            prev = temp;
                            this.first = temp;
                                        
                        } else if (prev.next.next != null && !(prev.equals(first))) {//Si estamos en el medio de la lista
                            temp = prev;
                            posNode.next = posNode.next.next;
                            temp.next = temp.next.next;
                            posNode.next.next = temp;//Colocamos el nodo
                            aux = prev.next;
                            
                        } else if (aux.next == null) {//Estamos al final de la lista
                            temp = prev;
                            temp.next = null;
                            posNode.next = aux;//Colocamos el nodo
                            posNode.next.next = temp;
                        }
                    }
                    posNode = prev;
                    prev = aux;
                    aux = aux.next;
                } 
                 counter += 1;//Avanza en el while principal
             }
          }
      }
    

    @Override
    public int indexOf(Object element) throws ListException {
        if(isEmpty()){        
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        } 
        Node aux = first;
        int index = 1; //El primer nodo estara en el indice 1
        while(aux!=null){
            if(util.Utility.equals(aux.data, element)){
                return index;//Ya lo encontro
            }
            index++;
            aux = aux.next;
        }
        return -1; //Significa que elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty()){
        throw new UnsupportedOperationException("SinglyLinkedList is empty"); //To change body of generated methods, choose Tools | Templates.
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if(isEmpty()){        
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        } //To change body of generated methods, choose Tools | Templates.
        
        Node aux = first;
        while(aux.next!=null){
            aux = aux.next;
        }
        return aux.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
       //CASO VACIO
       if(isEmpty()){        
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        } 
        //CASO 1. EL NODO PREVIO BUSCADO ES EL PRIMERO DE LA LISTA
        if(util.Utility.equals(first.data, element)){
            throw new UnsupportedOperationException("No prev node in head");
        }else{
        //CASO 2. EL NODO PREVIO BUSCADO ESTA EN CUALQUIER OTRO NODO
        Node prev = first; //Para dejar un rastro, apunta al anterior de aux
        Node aux = first.next; 
        while(aux!=null&&!(util.Utility.equals(aux.data, element))){
            prev = aux; //Un nodo atras del auxiliar
            aux = aux.next;
        }
        //Se sale cuando alcanza nulo o cuando encuentra el elemento
        
        //Cuando encuentra el elemento
        if(aux!=null&&util.Utility.equals(aux.data, element)){
           return prev.data;
         }
         return null;//No encuentro el elemento
       }
    }

    @Override
    public Object getNext(Object element) throws ListException {
        //CASO VACIO
       if(isEmpty()){        
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        }  
        //CASOS. EL PROXIMO NODO BUSCADO ESTA EN CUALQUIER OTRA POSICION
        Node aux = first; //Nodo que itera a trávez de la lista
        while(aux.next!=null&&!(util.Utility.equals(aux.data, element))){
            aux = aux.next;
        }//Se sale cuando alcanza nulo o cuando encuentra el elemento
        
        //Si se salio por llegar al final de la lista
        if(aux.next == null){
            throw new UnsupportedOperationException("No next node in last SinglyLinkedList node");
        }
        
        //Cuando encuentra el elemento
        if(aux.next!=null&&util.Utility.equals(aux.data, element)){
           return aux.next.data;
         }
         return null;//No encuentro el elemento
       }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty()){        
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        } //To change body of generated methods, choose Tools | Templates.
        Node aux = first;
        int i = 1;
        while(aux!=null){
             if(util.Utility.equals(i, index)){
                 return aux;
             }
             i++;
             aux = aux.next;
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "SINGLY LINKED LIST\n";
        Node aux = first;
        
        while(aux!=null){
            result+=aux.data+ " "+"\n";
            aux = aux.next;
        }
        return result;
    }
 
}