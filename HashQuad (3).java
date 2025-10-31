public class HashQuad
{
    private int capacity=1009; //capacity of the array of Integers (first prime number after 1000)
    private int size=0; //current number of elements
    private Integer [] arr = new Integer [capacity]; //declaring the array of Integers
    
    boolean HashQ_contains (int x)
    {
        int pos = HashQ(x); 
        
        for(int i=0; i< capacity;i++)
        {
            int indices = (pos + i*i) % capacity;
            
            if(arr[indices] == null)
            {
                return false;
            }
            
            if(arr[indices].equals(x)) 
            {
                return true;
            }
        }
        
        return false;
    }
    
    boolean HashQ_insert (int x)
    {
        if(size>= capacity / 2)
        {
            resize();
        }
        
        int pos = HashQ(x);
        
        if (HashQ_contains(x)) 
        {
            return false;
        }
        
        for(int i=0; i< capacity;i++)
        {
            int indices = (pos + i*i) % capacity;
            
            if(arr[indices] == null)
            {
                arr[indices] = x;
                size++;
                
                return true;
            }
            
            else
            {
                continue;
            }
        }
        
        return false;
    }
    
    void resize() 
    {
        int oldCapacity = capacity;
        Integer[] oldArr = arr;

        // Set new capacity to the next prime number after 2*capacity
        capacity = nextPrime(2 * capacity);
        arr = new Integer[capacity];
        
        // Rehash each element from the old array into the new array
        for (int i = 0; i < oldCapacity; i++) 
        {
            if (oldArr[i] != null) // Only rehash non-null elements
            {  
                int pos = Math.abs(oldArr[i]) % capacity;  // Rehash the element

                for (int j = 0; j < capacity; j++) 
                {
                    int idx = (pos + j * j) % capacity;  // Quadratic probing

                    if (arr[idx] == null) 
                    {  
                        arr[idx] = oldArr[i];  // Insert the element
                        
                        break;  // Stop searching for a spot for this element
                    }
                }
            }
        }
    }

    boolean HashQ_delete (int x)
    {
        int pos = HashQ(x);
        
        for(int i=0; i< capacity;i++)
        {
            int indices = (pos + i*i) % capacity;
            
            if(arr[indices] == null)
            {
                return false;
            }
            
            if(arr[indices].equals(x)) 
            {
                arr[indices] = null;
                size--;
                return true;
            }
        }
        
        return false;
    }
    
    public int HashQ(int x)
    {
        int index = Math.abs(x) % arr.length;
        
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

