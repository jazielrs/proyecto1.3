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
public class CircularDoublyLinkedList implements List {
    private Node first; //apunta al inicio de la lista dinamica
    private Node last; //apunta al ult nodo de la lista
    
    //Constructor
    public CircularDoublyLinkedList(){
        this.first = this.last = null; //la lista todavia no existe
    }

    @Override
    public int size() throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        int count = 0;
        while(aux!=last){
            count++;
            aux = aux.next;
        }
        //se sale cuando esta en el ult nodo
        return count+1; //+1 para que cuente el ult nodo
    }

    @Override
    public void clear() {
        this.first = this.last = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        while(aux!=last){
            if(util.Utility.equals(aux.data, element)){
                return true;
            }
            aux = aux.next;
        }
        //se sale cuando aux == last
        //en este caso, solo resta verificar si el elem a buscar
        //esta en ese nodo
        return util.Utility.equals(aux.data, element);
    }

    @Override
    public void add(Object element) {
       Node newNode = new Node(element);
        if(isEmpty()){
            this.first = this.last = newNode;
            this.first.prev = this.last.prev = newNode;
            this.first.next = this.last.next = newNode;
        }else{
            last.next = newNode;
            //hago en doble enlace
            newNode.prev = last;
            last = newNode; //muevo last a q apunte al ult nodo
            
            //hago el enlace circular
            last.next = first;
            //hago el doble enlace
            first.prev = last;
        }  
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            this.first = this.last = newNode;
        }
        newNode.next = first;
        //hago el doble enlace
        first.prev = newNode;
        first = newNode;
                
        //hago el enlace circular y doble
        last.next = first;
        first.prev = last;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);
        //CASO 1. LA LISTA ESTA VACIA
        if(isEmpty()){
            first = last = newNode;
        }else{
            //CASO 2. first.next es nulo, o no es nulo
            //y el elemento a insertar es menor al del inicio
            if(util.Utility.greaterT(first.data, element)){
                newNode.next = first;
                //hago el doble enlace
                first.prev = newNode;
                first = newNode;
            }else{
                //CASE 3. TODO LO DEMAS
                Node prev = first;
                Node aux = first.next;
                boolean added=false;
                while(aux!=last&&!added){
                    if(util.Utility.lessT(element, aux.data)){
                        prev.next = newNode;
                        //hago el doble enlace
                        newNode.prev = prev;
                        
                        newNode.next = aux;
                        //hago el doble enlace
                        aux.prev = newNode;
                        added = true;
                    }
                    prev = aux;
                    aux = aux.next;
                }
                //si llega aqui, se enlaza cuando aux==last
                if(util.Utility.lessT(element, aux.data)&& !added){
                    prev.next = newNode;
                    //hago el doble enlace
                    newNode.prev = prev;
                    
                    newNode.next = aux;
                    //hago el doble enlace
                    aux.prev = newNode;
                }else //en este caso, se enlaza al final
                    if(!added){
                        aux.next = newNode;
                        //hago el doble enlace
                        newNode.prev = aux;
                        
                        //muevo last al ult nodo
                        last = newNode;
                    }
            }
        }
        //hago el enlace circular y doble
        last.next = first;
        first.prev = last;
    }

    @Override
    public void remove(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        //CASO 1. EL ELEMENTO A SUPRIMIR ES EL PRIMERO DE LA LISTA
        if(util.Utility.equals(first.data, element)){
            first = first.next;
        }else{
        //CASO 2. EL ELEMENTO A SUPRIMIR ESTA EN CUALQUIER OTRO NODO
            Node prev = first; //para dejar rastro, apunta al anterior de aux
            Node aux = first.next;
            while(aux!=last&&!util.Utility.equals(aux.data, element)){
                prev = aux; //un nodo atras de aux
                aux = aux.next;
            }
            //se sale cuando aux==last, o cuando encuentra el elemento
            //a suprimir
            if(util.Utility.equals(aux.data, element)){
                //desenlazo o desconecto el nodo
                Node w = aux.next;
                prev.next = w;
                //mantengo el doble enlace
                w.prev = prev;
            }
            //debo asegurarme q last apunte al ult nodo
            if(aux==last &&util.Utility.equals(aux.data, element)){ //estamos en el ult nodo
                last = prev;
            }
        }
        //mantengo el enlace circular y doble
        last.next = first;
        first.prev = last;
        
        //Que pasa si solo queda un nodo y es el que quiero eliminar?
        if(first==last && util.Utility.equals(first.data, element)){
            clear(); //en este caso anulamos la lista
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Object element = first.data;
        first = first.next; //muevo el apuntador al sgte nodo
        //mantengo el enlace circular y doble
        first.prev = last;
        last.next = first;
        return element;
    }

    @Override
    public Object removeLast() throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        Node prev = first; //para dejar rastro, apunta al anterior de aux
        while(aux.next!=last){
            prev = aux; //un nodo atras de aux
            aux = aux.next;
        }
        //se sale del while cuando esta en el ultimo nodo
        Object element = aux.data;
        //prev.next = first; //desconecto el ultimo nodo
        last = prev;
        //mantengo el enlace circular y doble
        last.next = first;
        first.prev = last;
        return element;
    }

    @Override
    public void sort() throws ListException {
        Node aux = first.next;
        Node temp = null;
        
        if (isEmpty()) {//Caso lista vacia
            throw new UnsupportedOperationException("SinglyLinkedList is empty");
        }
        
        if(size()==2){//Caso lista 2 nodos
            if (util.Utility.greaterT(aux.prev.data, aux.data)) {
                aux.next = aux.prev;
                aux.prev.next=null;
                this.first = aux;
                last=aux.next;//actualizo nodo final
                last.next=first;//Hago enlace circular
            }
        }
        
        if (size() > 1 && size() > 2) {//Si hay mas de un elemento en la lista
            aux = first;
            int counter = 0;
            while(counter<size()){//Ordena todos los nodos 
                aux = first;
                while (aux.next != last) {//va comparando cada nodo por separado
                    if (util.Utility.greaterT(aux.data, aux.next.data)) {//Si el nodo actual es menor al proximo              
                       
                        if (aux.equals(first)) {//Si estamos en la cabeza
                            temp = aux.next;
                            aux.next = aux.next.next;
                            aux.next.prev = aux;//Doble enlace //Enlace falla
                            temp.prev = temp.next = null;//Elimino punteros para obtener el nodo a agregar
                            aux.prev = temp;
                            temp.next = aux;//Mantengo doble enlace al acomodar
                            first = aux.prev;//Redirecciono la cabeza al nuevo nodo//Actualizo first
                            aux = aux.prev;//Retorna aux a posicion para incementar ciclo
                                       
                            //hago el enlace circular doble***
                            last.next = first;
                            first.prev = last;
                            
                        } else if (aux.next != last && !(aux.equals(first))) {//Si estamos en el medio de la lista
                            temp = aux.next;//Me llevo nodo (no aux) fuera
                            aux.next = aux.next.next;
                            aux.next.prev = aux;//Doble enlace 
                            temp.prev = temp.next = null;//Elimino enlaces 
                            
                            aux.prev.next = temp;
                            temp.prev = aux.prev;//Conecto nodos Izq
                            aux.prev = temp;
                            temp.next = aux;//Conecto nodos Der
                            
                            aux=aux.prev;//Revierto nodo aumentado        
                        }                       
                    }
                    
                    aux = aux.next;
                } 
                if (aux.next == last && util.Utility.greaterT(aux.data, aux.next.data)) {//Verificamos caso que quedo´por fuera         
                    temp = aux.next;
                    temp.prev = temp.next = null;//Elimino punteros del nodo para moverlo
                    aux.next = null;//Elimino puntero final                 
                    temp.prev = aux.prev;
                    aux.prev.next = temp;//Conecto nodos Izq
                    temp.next = aux;
                    aux.prev = temp;//Conecto nodos Der

                 //Actualizo last***
                    last = aux; 
                    
                //hago el enlace circular doble***
                  last.next = first;
                  first.prev = last;
                }
                //aux=aux.next;
                counter += 1;//Avanza en el while principal
             }
          }
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        int index = 1; //el primer nodo estara en el indice 1
        while(aux!=last){
            if(util.Utility.equals(aux.data, element)){
                return index; //ya lo encontro
            }
            index++;
            aux = aux.next; 
        }
        //se sale cuando aux == last
        if(util.Utility.equals(aux.data, element)){
            return index;
        }
        return -1; //significa q el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        return last.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        //Node prev = first; //Para dejar un rastro, apunta al anterior de aux
        Node aux = first.next; 
        //CASO VACIO
        if(isEmpty()){        
            throw new UnsupportedOperationException("CircularLinkedDoublyList is empty");
        } 
        
        //CASO 1. EL NODO PREVIO BUSCADO ES EL PRIMERO DE LA LISTA
        if(util.Utility.equals(first.data, element)){
            return first.prev.data; //Se invoca data ya que de no ser así se invoca la lista
        }else{
        
        //CASO 2. EL NODO PREVIO BUSCADO ESTA EN CUALQUIER OTRO NODO
        while(aux!=last&&!(util.Utility.equals(aux.data, element))){
            aux.prev = aux; //Un nodo atras del auxiliar
            aux = aux.next;
        }
        //Se sale cuando alcanza last o cuando encuentra el elemento
        
        //Iteracion que queda por fuera al ser aux!=last
        if(aux==last&&util.Utility.equals(aux.data, element)){
            return aux.prev.data;
        }
        
        //Cuando se salió por encontrar el último elemento antes del final
        if(util.Utility.equals(aux.data, element) && aux!=last){
           return aux.prev.data;
        }
         return null;//No encuentro el elemento
       } 
    }

    @Override
    public Object getNext(Object element) throws ListException {//verificar profe
         //CASO VACIO
       if(isEmpty()){        
            throw new UnsupportedOperationException("CircularLinkedDoublyList is empty");
        }  
        //CASOS. EL PROXIMO NODO BUSCADO ESTA EN CUALQUIER OTRA POSICION
        Node aux = first; //Nodo que itera a trávez de la lista
        while(aux!=last&&!(util.Utility.equals(aux.data, element))){
            aux = aux.next;
        }//Se sale cuando alcanza el final o encuentra el elemento
        
        //Iteracion que falta verificar
        if(aux==last&&util.Utility.equals(aux.data, element)){
             return aux.next.data;
        }
        
        //Cuando encuentra el elemento buscado y no es el ultimo nodo
        if(aux!=last&&util.Utility.equals(aux.data, element)){
           return aux.next.data;
        }
        
        return null;//No encuentro el elemento
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty()){
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        int i = 1; //el indice del primer elemento de la lista
        while(aux!=last){
            if(util.Utility.equals(i, index)){
                return aux; //ya lo encontro
            }
            i++;
            aux = aux.next; 
        }
        //se sale cuando aux == last
        if(util.Utility.equals(i, index)){
            return aux;
        }
        return null; //si llega aqui, no encontro el nodo
    }

    @Override
    public String toString() {
        String result="CIRCULAR DOUBLY LINKED LIST\n";
        Node aux = first;
         //el aux es para moverme por la lista hasta el ult elemento
        while(aux!=last){
            result+=aux.data+"\n";
            aux = aux.next;
        }
        //se sale cuando aux == last
        return result+aux.data;
    }
 
}
