// Copyright(c) 2016 Hung Ta
// Modified by Kevin A. Johnson

// Adapted from Adam Drozdek book, Data Structures and Algorithms in Java-2nd Ed
//
// A binary tree of WORDS
// Each WORD has a linked list of line number in a file where the WORD is found
//
package SE330;

import java.io.*;

////////////////////////////////////////////////////
// WordsBST assumes and uses the following BST methods:
//
//   search(Comparable el) -- search for element el in the tree
//   insert(Comparable el) -- insert element el into the tree
//   visit(BinaryTreeNode p)      -- print node information to the console
//
class WordsBST extends BST
{
    public WordsBST()
    {
        differentWords = wordCnt = 0;
    }
    private int differentWords, // counter of different words in text file;
            wordCnt;        // counter of all words in the same file;

    // Override BST visit() method to do our own node info processing
    // which is just to print the node information out.
    // The println() call also calls the toString() method
    // of the list of line numbers inside the Word instance
    protected void visit(BinaryTreeNode p)
    {
        differentWords++;
        wordCnt += ((Word)p.el).freq;
        System.out.println(p.el + " ");
    }

    //
    // Parsing algorithm:
    //
    // Loop until end of file
    //   1.  Skip non-alpha characters
    //   2.  When a letter or alpha character is found
    //   2.1   Collect all continuous characters following the first letter found into a string
    //   3.  Search for the parsed string in the tree
    //   3.1   If not found then wrap string in Word(string, linenum) and insert into tree
    //   3.2   If found then just add linenum to found word
    //
    //   2.0 and 3.0  While interpreting each character if it's a newline character as '\n'
    //                then increment line number variable lineNum
    //
    // Parse for all WORDs and add them to this tree
    //
    public void parse(InputStream fIn) {

        int ch = 1;
        Word p;
        int lineNum = 1;  // current parsing line number
        try {
            while (ch > -1) {  // While not end of stream
                // Step 1
                while (true)
                    if (ch > -1 && !Character.isDigit((char) ch)) // skip
                        if ((ch = fIn.read()) == '\n')           // non-letters;
                            lineNum++;
                        else break;
                if (ch == -1)
                    break;
                // Step 2
                // Now collect characters from start of a word to end of that word
                String s = "";
                while (ch > -1 && Character.isDigit((char) ch)) {
                    s += Character.toUpperCase((char) ch);
                    if ((ch = fIn.read()) == '\n')
                        lineNum++;
                }
                // Step 3
                // Insert the word into the container with current line number
                if (s.length() > 0) {
                    Word wrd = new Word(s, lineNum);
                    if ((p = (Word) search(wrd)) == null)  // if new word not found
                        insert(wrd);                       // then insert it into the tree
                    else {
                        ((Word) p).addLineNum(lineNum);    // if found
                        ((Word) p).freq++;                 // then just add the line number
                    }
                }
            }
        } catch (IOException io) {
            System.err.println("A problem with input");
        }
    }


    // Prints all node values or words, including list of line numbers from each node
    public void displayTree(String fileName)
    {
        inorder();
        System.out.println("\n\nFile " + fileName
                + " contains " + wordCnt + " words among which "
                + differentWords + " are different\n");
    }
}

