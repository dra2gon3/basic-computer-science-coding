/**
Copyright (c) 2012, Matt Joseph
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


University of Pittsburgh
CS445
Assignment 2
*/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class BSApp
{
	public static void main (String[] args) throws FileNotFoundException
	{
		BSTree<String> dicttree = new BSTree<String>();
		String fileName;
		String tempstring;
		ArrayList<String> dictionary = new ArrayList<String>();
		int indict = 0;
		int testfilecounter = 0;
		
		System.out.println("Dictionary is stored in a Balanced Binary Search Tree.\n");
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the name of the dictionary file: ");
		fileName = input.nextLine();
		
		File dictfile = new File(fileName);
		Scanner dictinput = new Scanner(dictfile);
		
		while(dictinput.hasNext())
		{
				dictionary.add(dictinput.next());
				
		}
		
		// STORE IN BSTree
		createTree(dictionary, dicttree);
		System.out.println("Outside the createTree method: ");
		System.out.println(dicttree.height());
		// END STORE
		
		System.out.print("Enter the name of the test file: ");
		fileName = input.nextLine();
		
		File testfile = new File(fileName);
		Scanner testinput = new Scanner(testfile);
		StopWatch clock = new StopWatch();
		
		System.out.println("\nSearching for words in the file: " + fileName + "\n");
		
        clock.start();
		
		while(testinput.hasNext())
		{
			tempstring = testinput.next();
			
			if (dicttree.contains(tempstring.toLowerCase()))
				indict++;
			testfilecounter++;
		}
		
		clock.stop();
		
		System.out.println("Number of tokens: " + testfilecounter);
		System.out.println("Number of tokens in the dictionary: " + indict);
        System.out.println("(Clock: " + clock.getTimeSec() + "sec)");
	}
	
	public static <T extends Comparable<? super T>> void createTree(ArrayList<T> list, BSTree<T> tree)
	{
		int mid = 0;
		
		// if (list.size() == 0)
		if (list.size() == 1)
			mid = 1;
		else if (list.size() / 2 != 0)
			mid = (list.size() / 2);
		else
			System.out.println("Error.");
		
		tree.add(list.get(mid));
		
		createTree(list, tree, 0, mid - 1);
		createTree(list, tree, mid + 1, list.size() - 1);
	}
	
	public static <T extends Comparable<? super T>> void createTree(ArrayList<T> list, BSTree<T> tree, int first, int last)
	{
		if (first == last)
		{
			tree.add(list.get(first));
		}
		if (first < last)
		{
			int mid = ((first + last) / 2);
			tree.add(list.get(mid));
			createTree(list, tree, first, mid - 1);
			createTree(list, tree, mid + 1, last);
		}
	}
}