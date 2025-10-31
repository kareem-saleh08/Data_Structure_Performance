

import java.util.Iterator;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class LinkedList implements Iterable<Integer> // LL class implementing Iterable<Integer> interface
{
    public Node first; // first node to first element of LL
    public Node last; // last node to last element of LL
    private int modCount = 0; // incrementing number of modifications to the LL

    public LinkedList() // default constructor setting the nodes to null (empty LL)
    {
        first = null;
        last = null;
    }

    public boolean isEmpty() // checking if empty list by examining nullity of first 
    {
        return first == null;
    }

    public void insert(Integer a) // method to insert an Integer in LL
    {
        Node newNode = new Node(a); // Declaring and initializing a new node with parameterized constructor
        if (this.first == null) // if empty list
        {
            // setting first and last to the newNode
            this.first = newNode; 
            this.last = newNode;
        } 
        else 
        {
            this.last.next = newNode; // making last node's next pointer point to the newNode
            newNode.prev = this.last; // making the newNode prev pointer point to the last node
            this.last = newNode; // making newNode the last node in LL
        }
        modCount++; // Accounting for modification in LL
    }

    public Integer remove(Integer y) 
    {
        LLiterator riterator = new LLiterator(); //declaring and initializing the iterator
        
        while (riterator.hasNext()) //while there are still more elements in the linked list
        {
            Integer currentval = riterator.next(); // storing value returned by iterator's next() function (the node it just passed)
            if (currentval == y) 
            {
                Node returned = riterator.current.prev; //storing node that the iterator just passed
                
                if(returned.prev == null) //if we are removing the first node
                {
                    first = returned.next; //advancing first ptr
                
                }
            
                else
                {
                    returned.prev.next = returned.next; // making the node before the returned node point to the returned node's next
                }
            
                if(returned.next == null) //if we are removing the last node
                {
                    last = returned.next; //advancing last ptr
                }
            
                else
                {
                    returned.next.prev = returned.prev;// making the node after the returned node point to the returned node's prev
                }
            
                modCount++; //incrementing modCount to ensure that any changes in the LL are counted
            
                
        
                return currentval;  // Return the element that was removed
            }
        }
        
        return -1;
    }
    
    public boolean contains(Integer y) //contains routine to search for element
    {
        LLiterator riterator = new LLiterator();//declaring and initializing the iterator
        
        while (riterator.hasNext()) //while there are still more elements in the linked list
        {
            Integer current = riterator.next();  // storing value returned by iterator's next() function (the node it just passed)
            if (current == y) // when we find the element
            {
                return true;
            }
        }
        
        return false;
    }

    public Iterator<Integer> iterator() // method for iterator that returns an object of type Iterator<Integer>
    {
        return new LLiterator(); // returning the LL constructor
    }

    public class LLiterator implements Iterator<Integer> // LLiterator class that implements the Iterator<Integer> interface
    {
        private Node current = first; // making iterator start at the first node
        private final int expectedModCount = modCount; // assigning the current modCount to a final variable that cannot be changed

        public boolean hasNext()  // checking if there are further elements in list
        {
            return current != null; // checking if current node is null
        }

        public Integer next() // advances the element and stands before the next element but stores the element it just passed
        {
            if (modCount != expectedModCount) // making sure that there were no further modifications done 
            {
                throw new ConcurrentModificationException(); // throwing exception and halting program
            }
            if (!hasNext()) // if no further elements
            {
                throw new NoSuchElementException();
            }
            
           
            Integer nextItem = current.data; // storing the current data in Integer var
            current = current.next; // advancing forward
            return nextItem; // returning the item that the iterator just passed
        }

        public void remove()
        {
          //no definition of remove iterator
        }
    }

    public void LLclear() // public clear method
    {
        LLdoClear(); // calls another clear
    }

    private void LLdoClear() // private in order not to allow external tampering with the LL
    {
        first = null;
        last = null;
        modCount++; // adding 1 to modCount as clear is a modification of the LL
    }
}

class Node 
{
    Integer data; // integer member var storing data
    Node prev; // node pointing to previous element
    Node next; // node pointing to next element

    Node(Integer d) // parameterized constructor with data 
    {
        data = d; // assigning some value to data member var
        prev = null;
        next = null;
    }
}

