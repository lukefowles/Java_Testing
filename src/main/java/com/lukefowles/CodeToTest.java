package com.lukefowles;

import java.util.Stack;

public class CodeToTest {
        public static Boolean doBracketsMatch(String string) {
         //  if(!string.matches("")){return false;};
            if(string.isEmpty()) {return false;}
            String[] bracketArray = string.split("");
            Stack<String> bracketStack = new Stack<String>();
            for(String bracket : bracketArray) {
                switch(bracket) {
                    case "(" , "[", "{":
                        bracketStack.push(bracket);
                        break;

                    case ")":
                        if (bracketStack.peek().equals("(")) {
                            bracketStack.pop();
                        } else {
                            return false;
                        }
                        break;

                    case "}":
                        if (bracketStack.peek().equals("{")) {
                            bracketStack.pop();
                        } else {
                            return false;
                        }
                        break;

                    case "]":
                        if (bracketStack.peek().equals("[")) {
                            bracketStack.pop();
                        } else {
                            return false;
                        }
                        break;
                }
            }
            return bracketStack.isEmpty() ? true : false;
        }
    }
