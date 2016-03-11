// Copyright(c) 2016 Hung Ta
// Modified by Kevin A. Johnson
package SE330;

import java.io.*;
import java.io.StreamTokenizer;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;

////////////////////////////////////////////////////
// Main app
//
// Request user to enter a file name to read and parse
// Create a WordsBST tree
// Parse the tree
//
class WordsInLinesApp
{
    static public void main(String[] args)
    {
        String fileName = "testdata.txt";//name of the desired file to open
        String line = null;//references one line at a time
        InputStream fIn;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        try {
           if (args.length == 0) {
                System.out.print("Enter a file name: ");  // get file to read from
                fileName = buffer.readLine();

                fIn = new FileInputStream(fileName);
            }
            else
           {
                fIn = new FileInputStream(args[0]);
                fileName = args[0];
            }
            WordsBST wordTree = new WordsBST();    // create a new tree
            // Phase 1 - parse the file for all words and add the words to this tree
            wordTree.parse(fIn);
            // Phase 2 - display the built tree
            if (!wordTree.isEmpty())
            {
                System.out.print(wordTree);
                wordTree.displayTree(fileName);
            }

            fIn.close();
        }
        catch (IOException io)
        {
            System.err.println("Cannot open file " + fileName + " ");
            System.exit(1);
        }





    }// end main
}

