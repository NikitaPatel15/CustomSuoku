/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_4;

/**
 *
 * @author DELL1
 */
public class Soduku__Solver {
    
    
    char[][] Sudoku_Array;
    char[] symbols;
    int SIZE;
      
     // class to give reference to column and rows of grid
    class ROW_COLUMN_REF{
     
        int col,row;
    
        ROW_COLUMN_REF(int x,int y){
            row=x; col=y;   
        }
    }
      
        //Constructor to Intialize the variables 
    Soduku__Solver( char[][] Sudoku_Array,char[] symbols,int SIZE){
     
        this.Sudoku_Array = Sudoku_Array;
        this.symbols = symbols;
        this.SIZE = SIZE;
    }
    
        // method to print two dimension array
    public void  print_Array(char[][] Array){
                         
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                   System.out.print(Array[i][j]);
                  
            }  
            System.out.println();
        }
    }
    
        // method to find empty cells ( cells with . operator to put answers)  
    boolean find_Empty_Cell(ROW_COLUMN_REF OBJECT){
                     
        for(int i=0; i<SIZE; i++)
        {                    
            for(int j=0; j<SIZE; ++j){
                if(Sudoku_Array[i][j]== '0'){
                    OBJECT.row = i;
                    OBJECT.col = j;
                    return false;
                }
            }
        }
        return true;
    }
    
    
        // to check the symbols conflict in internal matrix 
        // method takes 4 different integers to decide the size of internal matrix and then check if there is any similar symbol is there 
        // or nor , then returns flag value accordingly to it. 
    boolean CHECK_INTERNAL_MATRIX(ROW_COLUMN_REF object,char symbol){
       
        boolean flag = true;  
        
        int SQUARE = (int)Math.sqrt(Sudoku_Array.length);
        
        int STARTING_ROW_ADD = object.row - object.row%SQUARE;
        int STARTING_COL_ADD = object.col - object.col%SQUARE;
        int LIMIT_ROW = STARTING_ROW_ADD + SQUARE;
        int LIMIT_COL = STARTING_COL_ADD + SQUARE;
          
        for(int i = STARTING_ROW_ADD; i<LIMIT_ROW; i++ ){
            for(int j=STARTING_COL_ADD; j<LIMIT_COL; ++j){
                if(Sudoku_Array[i][j]== symbol){
                    flag = false; break;
                }
            } 
        }
        return flag;
    }
    
        // method to check if there is any conflict in rows or columns.
    boolean CHECK_CONFLICT(ROW_COLUMN_REF object,char symbol){
                   
        boolean FLAG = true;
         
            //Check row
            for(int i =0; i<SIZE; ++i){
                if(Sudoku_Array[object.row][i] == symbol){
                    return false;
                }
            }
      
            //Check Column
            for(int i =0; i<SIZE; ++i){
                if(Sudoku_Array[i][object.col] == symbol){
                    return false;
                }
            }
            
        FLAG = CHECK_INTERNAL_MATRIX(object,symbol);
        return FLAG;
    }
   
    
        // this is a recursive method that calls other method and checks if sudoku is solved or not using back tracking.
    boolean solve_Soduku(){
        
        int ROW_ARRAY=-1,COL_ARRAY=-1; 
        
        ROW_COLUMN_REF object = new ROW_COLUMN_REF(ROW_ARRAY,COL_ARRAY);
        
        if(find_Empty_Cell(object)){
            return true;
        }
        
        //System.out.println("row ="+object.row +" Col = "+object.col);
        //ASSIGN_VALUE(object);
            
        for(int i=0; i<symbols.length; ++i){
            if(CHECK_CONFLICT(object,(char)symbols[i])){
                
                Sudoku_Array[object.row][object.col] = (char)symbols[i];
                    
                if(solve_Soduku()){ 
                    return true;
                }    
                else{
                    Sudoku_Array[object.row][object.col] = '0'; 
                }              
            }
        }      
        return false;        
   }
}