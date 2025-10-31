import java.util.Random;

public class Main 
{
    static double[][] Inserts_Time = new double[4][3]; // multi-dim array to hold all inserts' times
    static double[][] Contains_Time = new double[4][3];// multi-dim array to hold all contains' times
    static double[][] Deletes_Time = new double[4][3];// multi-dim array to hold all deletes' time
    
    static double[][] Inserts_Mem = new double[4][3]; // multi-dim array to hold all inserts' memory usage
    static double[][] Contains_Mem = new double[4][3];// multi-dim array to hold all contains' time
    static double[][] Deletes_Mem = new double[4][3]; //// multi-dim array to hold all deletes' time
    
    
    static double[][] Mill_Inserts_Time = new double[4][1]; // multi-dim array to hold all inserts' times for million elements
    static double[][] Mill_Inserts_Mem = new double[4][1]; // multi-dim array to hold all inserts' memory usages for million elements

    public static void main(String[] args) 
    {
        int[] n = {1000, 10000, 100000}; // array containing the different sized elements
        
        int [] z = {1000000};
        
        //for loop to try all ops for the different sizes of elements
        for (int i = 0; i < n.length; i++) 
        {
            Avl_ops_time(i, n[i]);
            Splay_ops_time(i, n[i]);
            HashCh_ops_time(i, n[i]);
            HashQ_ops_time(i, n[i]);
            
            Avl_ops_mem(i, n[i]);
            Splay_ops_mem(i, n[i]);
            HashCh_ops_mem(i, n[i]);
            HashQ_ops_mem(i, n[i]);
        }

        // printing 2 separate tables for each operation (1 for time, 1 for mem usage)
        System.out.println("\nInsert Operation Time (ms):");
        printTable(Inserts_Time, n);

        System.out.println("\nContains Operation Time (ms):");
        printTable(Contains_Time, n);

        System.out.println("\nDelete Operation Time (ms):");
        printTable(Deletes_Time, n);

        System.out.println("\nInsert Operation Memory (kilobytes):");
        printTable(Inserts_Mem, n);

        System.out.println("\nContains Operation Memory (kilobytes):");
        printTable(Contains_Mem, n);

        System.out.println("\nDelete Operation Memory (kilobytes):");
        printTable(Deletes_Mem, n);
        
        Time_Inserts_Mill(z[0]);
        
        //trying 1 Million elements for one operation 
        System.out.println("\nInsert Operation Time for 1 Million Elements (ms):");
        printTable(Mill_Inserts_Time, z);
        
        
    }
    
    public static void Time_Inserts_Mill(int y)
    {
        AvlTree a = new AvlTree();
        Random rand = new Random();

        long startTime = System.currentTimeMillis(); // Start stopwatch
        for (int j = 0; j < y; j++) 
        {
            a.root = a.Avl_insert(rand.nextInt(), a.root);
        }
        long endTime = System.currentTimeMillis();  // End stopwatch
        Mill_Inserts_Time[0][0] = (endTime - startTime); // Time taken to insert
        
        SplayTree s = new SplayTree();
        rand = new Random();

        startTime = System.currentTimeMillis();
        for (int j = 0; j < y; j++) 
        {
            s.root = s.Splay_insert(rand.nextInt(), s.root);
        }
        endTime = System.currentTimeMillis(); 
        Mill_Inserts_Time[1][0] = (endTime - startTime); 
        
        HashChaining h = new HashChaining(y);
        rand = new Random();

        // Insert operation
        startTime = System.currentTimeMillis();
        for (int i = 0; i < y; i++) 
        {
            h.HashCh_insert(rand.nextInt());
        }
        endTime = System.currentTimeMillis();  
        Mill_Inserts_Time[2][0] = (endTime - startTime); 
        
        HashQuad q = new HashQuad();
        rand = new Random();

        // Insert operation
        startTime = System.currentTimeMillis();
        for (int i = 0; i < y; i++) 
        {
            q.HashQ_insert(rand.nextInt());
        }
        endTime = System.currentTimeMillis();  
        Mill_Inserts_Time[3][0] = (endTime - startTime); 


    }
    
  


    public static void printTable(double[][] category, int[] n) 
    {
        // row labels for Data Structures
        String[] dataStructures = {"AVL Tree","Splay Tree","Hash Chain", "Hash Quad"};
        
        if(n.length != 1)
        {
            System.out.print("Data Structure\t");
            System.out.print("1K\t\t");
            System.out.print("10K\t\t");
            System.out.println("100K");
        }
        
        else
        {
            System.out.println("Data Structure\t1M");
        }
        

        // print data for each data structure
        for (int row = 0; row < dataStructures.length; row++) 
        {
            // printing data structure name
            System.out.print(dataStructures[row] + "\t");

            for (int col = 0; col < n.length; col++) 
            {
                System.out.print(category[row][col] + "\t\t");
            }

            System.out.println();  // Move to the next line for the next data structure
        }
    }

    public static void Avl_ops_time(int index, int m) // Avl Tree Operations while measuring time
    {
        AvlTree a = new AvlTree();
        Random rand = new Random();

        long startTime = System.currentTimeMillis(); // Start stopwatch
        for (int j = 0; j < m; j++) 
        {
            a.root = a.Avl_insert(rand.nextInt(), a.root);
        }
        long endTime = System.currentTimeMillis();  // End stopwatch
        Inserts_Time[0][index] = (endTime - startTime); // Time taken to insert

        startTime = System.currentTimeMillis(); 
        for (int j = 0; j < m; j++) 
        {
            a.Avl_contains(rand.nextInt(), a.root);
        }
        endTime = System.currentTimeMillis(); 
        Contains_Time[0][index] = (endTime - startTime); 

        startTime = System.currentTimeMillis(); 
        for (int j = 0; j < m; j++) 
        {
            a.Avl_delete(rand.nextInt(), a.root);
        }
        endTime = System.currentTimeMillis(); 
        Deletes_Time[0][index] = (endTime - startTime); 
    }

    public static void Splay_ops_time(int index, int m) // Splay Tree Operations while measuring time
    {
        SplayTree a = new SplayTree();
        Random rand = new Random();

        long startTime = System.currentTimeMillis();
        for (int j = 0; j < m; j++) 
        {
            a.root = a.Splay_insert(rand.nextInt(), a.root);
        }
        long endTime = System.currentTimeMillis(); 
        Inserts_Time[1][index] = (endTime - startTime); 

        startTime = System.currentTimeMillis();
        for (int j = 0; j < m; j++) 
        {
            a.Splay_contains(rand.nextInt(), a.root);
        }
        endTime = System.currentTimeMillis(); 
        Contains_Time[1][index] = (endTime - startTime); 

        startTime = System.currentTimeMillis();
        for (int j = 0; j < m; j++) 
        {
            a.Splay_delete(rand.nextInt(), a.root);
        }
        endTime = System.currentTimeMillis();  
        Deletes_Time[1][index] = (endTime - startTime); 
    }

    public static void HashCh_ops_time(int index, int m) // Hash Chaining Operations while measuring time
    {
        HashChaining h = new HashChaining(m);
        Random rand = new Random();

        // Insert operation
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < m; i++) 
        {
            h.HashCh_insert(rand.nextInt());
        }
        long endTime = System.currentTimeMillis();  
        Inserts_Time[2][index] = (endTime - startTime); 

        // Contains operation
        startTime = System.currentTimeMillis();
        for (int i = 0; i < m; i++) 
        {
            h.HashCh_contains(rand.nextInt());
        }
        endTime = System.currentTimeMillis();  
        Contains_Time[2][index] = (endTime - startTime); 

        // Delete operation
        startTime = System.currentTimeMillis();
        for (int i = 0; i < m; i++) 
        {
            h.HashCh_delete(rand.nextInt());
        }
        endTime = System.currentTimeMillis();  
        Deletes_Time[2][index] = (endTime - startTime); 
    }

    public static void HashQ_ops_time(int index, int m) // Hash Quadratic Operations while measuring time
    {
        HashQuad q = new HashQuad();
        Random rand = new Random();

        // Insert operation
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < m; i++) 
        {
            q.HashQ_insert(rand.nextInt());
        }
        long endTime = System.currentTimeMillis();  
        Inserts_Time[3][index] = (endTime - startTime); 

        // Contains operation
        startTime = System.currentTimeMillis();
        for (int i = 0; i < m; i++) 
        {
            q.HashQ_contains(rand.nextInt());
        }
        endTime = System.currentTimeMillis();  
        Contains_Time[3][index] = (endTime - startTime); 

        // Delete operation
        startTime = System.currentTimeMillis();
        for (int i = 0; i < m; i++) 
        {
            q.HashQ_delete(rand.nextInt());
        }
        endTime = System.currentTimeMillis();  
        Deletes_Time[3][index] = (endTime - startTime); 
    }

    public static void Avl_ops_mem(int index, int m) // Avl Tree Operations while measuring memory usage
    {
        AvlTree a = new AvlTree();
        Random rand = new Random();
        
        Runtime runtime = Runtime.getRuntime();
        
        System.gc(); // calling garbage collector to remove any unused memory 

        long memoryBefore = runtime.totalMemory() - runtime.freeMemory(); // Memory before 
        
        for (int j = 0; j < m; j++) 
        {
            a.root = a.Avl_insert(rand.nextInt(), a.root);
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory(); // Memory after 
        
        Inserts_Mem[0][index] = (memoryAfter - memoryBefore) / 1000.0; // Memory usage in KB

        memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int j = 0; j < m; j++) 
        {
            a.Avl_contains(rand.nextInt(), a.root);
        }
        memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        
        Contains_Mem[0][index] = (memoryAfter - memoryBefore); 

        memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int j = 0; j < m; j++) 
        {
            a.Avl_delete(rand.nextInt(), a.root);
        }
        memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        
        Deletes_Mem[0][index] = (memoryAfter - memoryBefore); 
    }

    public static void Splay_ops_mem(int index, int m) // Splay Tree Operations while measuring memory usage
    {
        SplayTree s = new SplayTree();
        Random rand = new Random();
        
        Runtime runtime = Runtime.getRuntime();
        
        System.gc(); 

        long memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int j = 0; j < m; j++) 
        {
            s.root = s.Splay_insert(rand.nextInt(), s.root);
        }
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Inserts_Mem[1][index] = (memoryAfter - memoryBefore) / 1000.0; 
        
        memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int j = 0; j < m; j++) 
        {
            s.Splay_contains(rand.nextInt(), s.root);
        }
        memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Contains_Mem[1][index] = (memoryAfter - memoryBefore) / 1000.0; 

        memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int j = 0; j < m; j++) 
        {
            s.Splay_delete(rand.nextInt(), s.root);
        }
        memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Deletes_Mem[1][index] = (memoryAfter - memoryBefore)/1000.0; 
    }

    public static void HashCh_ops_mem(int index, int m) // Hash Chaining Operations while measuring memory usage
    {
        HashChaining h = new HashChaining(m);
        Random rand = new Random();
        
        Runtime runtime = Runtime.getRuntime();
        
        System.gc(); 

        long memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int i = 0; i < m; i++) 
        {
            h.HashCh_insert(rand.nextInt());
        }
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Inserts_Mem[2][index] = (memoryAfter - memoryBefore) / 1000.0; 

        memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int i = 0; i < m; i++) 
        {
            h.HashCh_contains(rand.nextInt());
        }
        memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Contains_Mem[2][index] = (memoryAfter - memoryBefore)/1000.0; 

        memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int i = 0; i < m; i++) 
        {
            h.HashCh_delete(rand.nextInt());
        }
        memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Deletes_Mem[2][index] = (memoryAfter - memoryBefore)/1000.0; 
    }

    public static void HashQ_ops_mem(int index, int m) // Hash Quadratic Operations while measuring memory usage
    {
        HashQuad q = new HashQuad();
        Random rand = new Random();
        
        Runtime runtime = Runtime.getRuntime();
        
        System.gc(); 

        long memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int i = 0; i < m; i++) 
        {
            q.HashQ_insert(rand.nextInt());
        }
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Inserts_Mem[3][index] = (memoryAfter - memoryBefore) / 1000.0; 

        memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int i = 0; i < m; i++) 
        {
            q.HashQ_contains(rand.nextInt());
        }
        memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Contains_Mem[3][index] = (memoryAfter - memoryBefore)/1000.0; 

        memoryBefore = runtime.totalMemory() - runtime.freeMemory(); 
        for (int i = 0; i < m; i++) 
        {
            q.HashQ_delete(rand.nextInt());
        }
        memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        Deletes_Mem[3][index] = (memoryAfter - memoryBefore)/1000.0; 
    }
}


