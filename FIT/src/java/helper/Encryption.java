package helper;

import java.util.ArrayList;
import java.util.Random;

public class Encryption {
    
    private int id;
    private Random generate;

    public Encryption(int id) {
        this.id = id;
        generate = new Random();
    }
    
    public String doIdEncrypt(){        
        String code = "";
        ArrayList<String> comp = new ArrayList<String>();
        String completeCode = "";
        int idSize = String.valueOf(id).length();
        
        for (int c = 0; c < 3; c++){
            int n = 0;
            if ( c == 2 )
                n = 11 - idSize;            
            else
                n = 11;
            for ( int i = 0; i < n; i++ ){
                int num = generate.nextInt(10);
                String letter = this.generator(num);
                code += letter;                
            }
            if ( code.length() == 11 )
                code += ":";
            else
                code += id;
            comp.add(code);
            code = "";
        }
        
        for ( String c : comp ){
            completeCode += c;
        }
        
        return completeCode;
               
    }
    
    public String brokeIdEncrypt(){
        return "";
    }
    
    public String generator(int num){
        switch ( num ){
            case 0:
                return "0";               
            case 1:
                return "1";                
            case 2:
                return "2";                
            case 3:
                return "3";                
            case 4:
                return "4";                
            case 5:
                return "5";                
            case 6:
                return "6";                
            case 7:
                return "7";                
            case 8:
                return "8";                
            case 9:
                return "9";                
            case 10:
                return "a";                
            case 11:
                return "b";                
            case 12:
                return "c";                
            case 13:
                return "d";                
            case 14:
                return "e";                
            case 15:
                return "f";                
            case 16:
                return "g";                
            case 17:
                return "h";                
            case 18:
                return "i";                
            case 19:
                return "j";                
            case 20:
                return "k";                
            case 21:
                return "l";                
            case 22:
                return "m";                
            case 23:
                return "n";                
            case 24:
                return "o";                
            case 25:
                return "p";                
            case 26:
                return "q";                
            case 27:
                return "r";                
            case 28:
                return "s";                
            case 29:
                return "t";                
            case 30:
                return "u";                
            case 31:
                return "v";                
            case 32:
                return "w";                
            case 33:
                return "x";                
            case 34:
                return "y";                
            case 35:
                return "z";                
        }
        return "";
    }
    
}
