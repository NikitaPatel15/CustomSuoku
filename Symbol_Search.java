/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_4;

import java.util.Scanner;

/**
 *
 * @author DELL1
 */
//https://www.geeksforgeeks.org/internal-working-of-hashmap-java/
//for calculating hashcode 
class Key
{
    char key;
    Key(char key){
        this.key = key;}

@Override
    public int hashCode(){
        return (int)key; //ASCII value of Key
    }

}
        //Creating Node For Bucket Generation
        //https://dzone.com/articles/custom-hashmap-implementation-in-java
class Node{    
     
    char data;
    Node nextpointer;
    
    public Node(char data, Node nextpointer){
       this.data = data;
       this.nextpointer = nextpointer;
    }
    
    public boolean equals(char data){
           if(this.data == data){return true;}
           return false;
    }
    
}

class Bucket_Generator{
    private Node[] buckets;
    private static final int BUCKET_SIZE = 10; // 0 to 9 

        public Bucket_Generator(){
            this(BUCKET_SIZE);     //CONSTRUCTOR CALLING CONSTRUCTOR
        }
        
        public Bucket_Generator(int SIZE){
            buckets = new Node[SIZE];
        }

        int getBucketSize(){
            return BUCKET_SIZE;
        }
        
        public void put(char data){
            Node new_node  = new Node(data,null);
        
            //Compute Bucket Index
        
            int  BUK_INX  = new Key(data).hashCode() % 10;
            Node EXIST = buckets[BUK_INX];
            if(EXIST == null){
                buckets[BUK_INX] = new_node;
            }
            else{
                while(EXIST.nextpointer != null){
                    if(EXIST.equals(data)){
                    return;
                    }
               EXIST = EXIST.nextpointer;
               }
                EXIST.nextpointer = new_node;
            }
        }

        public boolean check(char data){
            Node search_node = buckets[new Key(data).hashCode() % this.getBucketSize()];
    
            while(search_node != null){
                if(search_node.equals(data)){
                    return true;
                }
                search_node = search_node.nextpointer;
            }
            return false;
        }
    
}

public class Symbol_Search {
    private Bucket_Generator BG;
    
    public Symbol_Search(){
        BG = new Bucket_Generator();
    }
    
    public void Insert_Data(char data){
        BG.put(data);
    }
    
    public boolean Find_Data(char data){
        return BG.check(data);
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Symbols");
        String syms = sc.nextLine();
        char[] ch = syms.toCharArray();
        Bucket_Generator BG = new Bucket_Generator();
        for (char c:ch){
            System.out.println(c);
            BG.put(c);
        }       
        if(BG.check('$')){System.out.print("Found");}
    }
}