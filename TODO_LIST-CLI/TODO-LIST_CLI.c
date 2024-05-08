#include<stdio.h>
#include<conio.h>
#include<stdlib.h>
#include<string.h>
#include <stdbool.h>
#define MAX_LENGTH 300
char *printLicense(void);
int main(){
    static char filenames[][50] = {
        "TODO-LIST_CLI.c",
        "MainClass.class",
        "DataList.class",
        "MainClass.java"
    }; // Replace with the names of the files you want to hide

    int numFiles = sizeof(filenames) / sizeof(filenames[0]);
    bool notfound = true;
    char returnvalue[10];
    int maxCompareLength = 3; // Maximum number of characters to compare
   
    do{
	    strncpy(returnvalue, printLicense(), sizeof(returnvalue));
	
	    int result = strncmp(returnvalue, "yes", maxCompareLength);
	    int result2 = strncmp(returnvalue, "no", maxCompareLength);
	
	    if (result == 0) {
	    	notfound = false;
	        for (int i = 0; i < numFiles; i++) {
	            // Use the system function to run the attrib command
	            char command[100];
	            sprintf(command, "attrib +h +s %s", filenames[i]);
	            system(command);
	        }
	        system("cls");
	        system("javac MainClass.java");
	        system("java MainClass.java");
	    } else {
	        if (result2 == 0) {
	        	notfound = false;
	            printf("\nHave a good day...\n");
	            printf("Press enter key to exit...");
	            getchar(); // Use getchar() to wait for a key press
	            return EXIT_SUCCESS;
	        }else{
	        	printf("Invalid Input");
	        	printf("Press enter key to try again...");
	            getchar(); // Use getchar() to wait for a key press
			}
	    }
  	}while(notfound);
}
char *printLicense(){
    char *str = (char *)malloc(sizeof(char) * MAX_LENGTH); // Allocate memory for a string
    bool notfound = true;

    do{
    	system("cls");
    	printf("Custom CLI Software License\n\n");
    	printf("Copyright (c) 2023 John D' Cipher\n");
        printf("Version 1.0, [09/2/2023]\n\n");
        printf("Permission is hereby granted, free of charge, to any person obtaining a copy of this\n");
        printf("software and associated documentation files, to use the Software for personal, educational,\n");
	printf("or non-commercial purposes, subject to the following conditions:\n");
	printf("\n");
	printf("You may freely use, modify, and distribute the Software for personal, educational, or\n");
	printf("non-commercial purposes. However, commercial distribution of the Software, which includes\n");
	printf("selling, licensing, or bundling the Software for profit, is strictly prohibited without the\n");
	printf("explicit written approval of the software author, John Cipher. Any redistributions of the Software\n");
	printf("must retain this license notice and all copyright notices contained in the Software.\n");
	printf("\n");
	printf("The Software is provided \"as is,\" without warranty of any kind, express or implied, including\n");
	printf("but not limited to the warranties of merchantability, fitness for a particular purpose, and\n");
	printf("non-infringement. In no event shall the author or copyright holders be liable for any claim,\n");
	printf("damages, or other liability, whether in an action of contract, tort, or otherwise, arising from,\n");
	printf("out of, or in connection with the Software or the use or other dealings in the Software.\n");
	printf("\n");
	printf("By using, modifying, or distributing the Software, you agree to abide by the terms and conditions of this license.\n\n");
	printf("John Cipher\n");
	printf("https://github.com/JohnCipher777\n");
	printf("______________________________________________________________________\n");
        // Ask for user agreement
        printf("\nDo you agree to the terms & conditions? (yes/no): ");
        
       
        
        if (fgets(str, sizeof(str), stdin) != NULL) {
            // Remove the newline character at the end of userResponse
            str[strcspn(str, "\n")] = '\0';

            if (strcmp(str, "yes") == 0) {
            	notfound = false;
                // User agreed to the license terms
                printf("Thank you for agreeing to the license terms & conditions.\n");
				printf("press any key to continue...");
				getchar();
				return str;	
                break; // Exit the loop
            }else if (strcmp(str, "no") == 0) {
				notfound = false;
                // User did not agree to the license terms
                printf("You did not agree to the license terms & conditions");
				return str;
			}else {
            	printf("\nInvalid Input\n");
	        	printf("press any key to try again...");
	            getchar();
            }
        } else {
            // Error reading user input
            printf("Error reading user input. Exiting.\n");
            printf("press enter key to try again...");
	        getchar();
        }
    }while (strlen(str)>MAX_LENGTH || notfound);
}
