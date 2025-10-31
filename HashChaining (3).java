

import java.util.Random;

public class HashChaining extends LinkedList
{
    private int sz; //size of the array of Integer LLs
    private LinkedList [] arr; //declaring the array of Integer LLs

    public HashChaining(int n) //param constructor
    {
        sz = nextPrime(n / 5) ; // making the size of the array (hash table) 1/5 of the amount of data to balance between length of chains and frequency of collisions as well as prime
        
        arr = new LinkedList[sz]; // intializing array of Integer Linked Lists but arr[i] is null for every i
        for (int i = 0; i < sz; i++) 
        {
            arr[i] = new LinkedList(); // Initialize each LinkedList 
        }
    }
    
    public int HashCh_insert(int x) //insert routine
    {
        int pos = HashCh(x); //calling hash function and assigning it to a position
        
        arr[pos].insert(x); // inserting element in the hash table (insert is called from LL)
        
        return x;
        
    }
    
    public boolean HashCh_contains(int x)
    {
        int pos = HashCh(x); //calling hash function and assigning it to a position
        
        if(arr[pos].contains(x)) // if the element is found in the hash table (contains is called from LL)
        {
            return true;
        }
        
        return false;
    }
    
    
    public boolean HashCh_delete(int x) //insert routine
    {
        int pos = HashCh(x); //storing the the hashed value
        
        if(HashCh_contains(x)) //if node is found in the table (calling the contains from LL)
        {
            arr[pos].remove(x);//remove node from the table (calling the remove from LL)
            
            return true;
        }
        
        
        return false;
        
    }
    
    
    
    public int HashCh(int x) //hash function
    {
       int index = Math.abs(x) % sz; //modular divison of element and assigning this value to an index
        
        return index;
    }
    
    public static int nextPrime(int o) //finidng next prime number after a certain number
    {
        o++;  // incrementing o by 1 to start counting from following number
        for (int i = 2; i < o; i++) // for loop over 2 to num-1 (old o)
        {  
            if (o % i == 0) // if o is divisible by any i from to to itself
            {  
                o++;  // incrementing o by 1 to check the next number
                i = 2;  // resetting i to 2 to revaluate divisibility of the new o
            } 
        
            else 
            {
                continue;  // if not divisible by a number, go on to check divisibility by next number
            }
        }
        return o;  // Return the prime number found
    }
}