package fr.gilles.gwritter;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Highlight {
	
	  
	
    
    public Highlight(JTextPane c) {
    	String[] achercher = { "public","class","this","main","private","void","protected","package","init",":=", ";", "<", "<<", "<=", "<>","=" ,"==" ,">" ,">=" ,">>" ,"and", "andalso" ,"as" ,"begin" ,"bool" ,"boolean" ,"break" ,"byte" ,"cbool" ,"cbyte" ,"cdbl" ,"char" ,"cint" ,"clng", "clngint" ,"clong" ,"continue" ,"csbyte" ,"cshort" ,"csng" ,"cubyte" ,"cuint" ,"culng" ,"culngint", "cushort" ,"dim" ,"div" ,"do" ,"double", "else" ,"end", "exit", "false" ,"float" ,"if", "int", "int16" ,"int32" ,"int64" ,"int8" ,"integer" ,"long" ,"longint" ,"longword", "loop", "mod", "not", "or" ,"orelse", "qword" ,"real" ,"rem" ,"repeat" ,"sbyte" ,"shl", "short" ,"shortint", "shr", "signed" ,"single" ,"smallint" ,"then", "true" ,"ubyte", "uint", "uint16", "uint32", "uint64" ,"uint8" ,"uinteger", "ulong", "ulongint" ,"unsigned" ,"until" ,"ushort", "wend", "while" ,"word" ,"xor","echo", "^" ,"|"};
    	
            String text = c.getText();
            final StyledDocument doc = c.getStyledDocument();
 
            final MutableAttributeSet normal= new SimpleAttributeSet();
            StyleConstants.setForeground(normal, Color.black);
            StyleConstants.setBold(normal, false);
            SwingUtilities.invokeLater(new Runnable() {
  
            public void run() {
                    doc.setCharacterAttributes(0, doc.getLength(), normal, true);
                }
            }); 
            
            for(String str: achercher)
            {
            	
                    Pattern p = Pattern.compile("(^|\\s+)("+str+")(\\s+|$)");
                    Matcher m = p.matcher(text);
                    while(m.find() == true && !str.isEmpty())
                    {
                    	System.out.println ( "Found    '" + m.group(2)  +   "'  at position  "+ m.start(2) +"-"+m.end(2)+"   +++\n");
                    	MutableAttributeSet attri = new SimpleAttributeSet();
                        StyleConstants.setForeground(attri, Color.blue);
                        StyleConstants.setBold(attri, true);
                        final int start = m.start(2);
                        final int end = m.end(2);
                        final int length = end-start;
                        final MutableAttributeSet style = attri;
                        
                        SwingUtilities.invokeLater(new Runnable(){
                            public void run(){
                            	doc.setCharacterAttributes(start, length, style, true);
                            }
                        });
                    }
            }

    }
}
