#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "header.h"
#include "function.c"
int main()
{
    FILE *f1,*f3;
    char *ptr;
    unsigned int k,prog_length,q;
    for(k=0;k<20;k++)
        locctr[k]=start_addr[k]=0x0;
    f1=fopen("input.dat","r");
    f3=fopen("interfile.dat","w+");
    line=qw=1;
    fprintf(f3,"%s\t%s\t%s\n","LOC","Line","Source_code");
    printf("The Assembly code written:\n");
    printf("\n\nLine\tLabel\tOpcode\tOperands\t\tObject Code\n\n");
    fscanf(f1,"%s%s%s",i1.label,i1.opcode,operand);
    read_file(f1);                                                              //function to tokenize the operands
    if(strcmp(i1.opcode,"ORG")==0)
    {
        start_addr[0]=strtoul(i1.operand[0],&ptr,10);
        locctr[0]=start_addr[0];
        fprintf(f3,"\t\t%d\t\t%s\t%s\t%s\n",line,i1.label,i1.opcode,operand);   //writing first line to inter file
        line++;
        printf("%d\t%s\t%s\t%s\t%s\t%s\n",qw,i1.label,i1.opcode,i1.operand[0],i1.operand[1],i1.operand[2]);
        qw++;
        fscanf(f1,"%s%s%s",i1.label,i1.opcode,operand);                         //scanning next input line
        read_file(f1);
    }
    else
        start_addr[0]=0;
    inter_file_generation(f1,f3);                                               //method for generation of inter file and symtab
    fclose(f1);
    fclose(f3);
    prog_length=0;
    for(q=0;q<=z;q++)
        prog_length+=(locctr[q]-start_addr[q]);                                 //Logic to find out program length
    printf("\nProgram length: %d\n",prog_length);
    return 0;
}
