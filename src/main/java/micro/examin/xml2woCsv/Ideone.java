package com.sport;/* package whatever; // don't place package name! */

import java.util.Scanner;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String measureValue = scn.nextLine();
		measureValue = measureValue.replace("IF", "if")
				.replace("*", " * ")
				.replace("/", " / ")
				.replace("(", " ( ")
				.replace("{", " } ")
				.replace("}", " } ")
				.replace(")", " ) ")
				.replace("=", " == ")
				.replaceAll(">", " > ")
				.replaceAll("<", " < ")
				.replaceAll("< ==", "<= ")
				.replaceAll("< =", "<= ")
				.replaceAll("> =", ">= ")
				.replaceAll("> ==", ">= ")
				.replaceAll("  ", " ")
				.replaceAll("   ", " ")
				.replaceAll("  ", " ")
				.replaceAll("   ", " ")
				.replace(") THEN ", " ) return { ")
				.replace(")THEN ", " ) return { ")
				.replace("IF", "if")
				.replaceAll(" END", "").trim()
				.replaceAll(" AND ", " && ")
				.replaceAll(" OR ", "||")
				.replace("(0)", "0")
				.replaceAll("  ", " ")
				.replaceAll("   ", " ")
				.replaceAll("  ", " ")
				.replaceAll("   ", " ")
				.replaceAll("CASE WHEN", "if ")
				.replaceAll(" THEN ", " return { ")
				.replaceAll("else 0", " } else { return 0 }")
				.replaceAll("ELSE 0", " } else { return 0 }")
				.replaceAll("ELSE0", " } else { return 0 }")
				.replace("ELSE ( 0 )", " } else { return 0 }")
				.replaceAll("ELSE NULL", " } else { return 0 }")
				.replaceAll("ELSE", " } else")
				.replaceAll("END", "")

				// .replaceAll("if \\(.?\\((.+?)\\)\\)","if ( $1 )")
				.replaceAll("  ", " ")
				.replaceAll("   ", " ")
				.replaceAll("  ", " ")
				.replaceAll("   ", " ");
		System.out.println(areParenthesisBalanced(measureValue.toCharArray()) ? "\nBalanced" : "Not Balanced");
		System.out.println(measureValue);

	}

	/* Returns true if character1 and character2
	   are matching left and right Parenthesis */
	static boolean isMatchingPair(char character1, char character2) {
		if (character1 == '(' && character2 == ')')
			return true;
		else if (character1 == '{' && character2 == '}')
			return true;
		else if (character1 == '[' && character2 == ']')
			return true;
		else
			return false;
	}

	/* Return true if expression has balanced
	   Parenthesis */
	static boolean areParenthesisBalanced(char exp[]) {
		/* Declare an empty character stack */
		stack st = new stack();

       /* Traverse the given expression to
          check matching parenthesis */
		for (int i = 0; i < exp.length; i++) {

          /*If the exp[i] is a starting
            parenthesis then push it*/
			if (exp[i] == '{' || exp[i] == '(' || exp[i] == '[')
				st.push(exp[i]);

          /* If exp[i] is an ending parenthesis
             then pop from stack and check if the
             popped parenthesis is a matching pair*/
			if (exp[i] == '}' || exp[i] == ')' || exp[i] == ']') {

              /* If we see an ending parenthesis without
                 a pair then return false*/
				if (st.isEmpty()) {
					return false;
				}

             /* Pop the top element from stack, if
                it is not a pair parenthesis of character
                then there is a mismatch. This happens for
                expressions like {(}) */
				else if (!isMatchingPair(st.pop(), exp[i])) {
					return false;
				}
			}

		}

       /* If there is something left in expression
          then there is a starting parenthesis without
          a closing parenthesis */

		if (st.isEmpty())
			return true; /*balanced*/
		else {   /*not balanced*/
			return false;
		}
	}

	static class stack {
		int top = -1;
		char items[] = new char[100];

		void push(char x) {
			if (top == 99) {
				System.out.println("Stack full");
			} else {
				items[++top] = x;
			}
		}

		char pop() {
			if (top == -1) {
				System.out.println("Underflow error");
				return '\0';
			} else {
				char element = items[top];
				top--;
				return element;
			}
		}

		boolean isEmpty() {
			return (top == -1) ? true : false;
		}
	}
}