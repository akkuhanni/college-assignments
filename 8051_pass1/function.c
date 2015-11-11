#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "header.h"
void read_file(FILE *f1)
{

    const char s[2]=",";
    char *token;
    int j;
    j=0;
    token=strtok(operand,s);                // strtok to tokenize based on delimiter ","
    strcpy(i1.operand[1],"$");
    strcpy(i1.operand[2],"$");
    while(token!=NULL)
    {
        strcpy(i1.operand[j],token);
        token=strtok(NULL,s);
        j++;
    }
}
void inter_file_generation(FILE *f1,FILE *f3)
{
    FILE *f4;
    char slabel[10],opd_temp[10];
    unsigned int temp,flag;
    char *ptr;
    int comma_k,q,strng,length_temp,check;
    while(strcmp(i1.opcode,"END")!=0)               //until the last instruction before ALP ends
    {
        if(strcmp(i1.label,"$")!=0)
        {
            f4=fopen("symtab.dat","r+");
            flag=0;
            while(fscanf(f4,"%s%d",slabel,&temp)!=EOF)
            {
                if(strcmp(slabel,i1.label)==0)
                {
                    printf("Duplicate symbol!!!\n");        //if label already exists then display error message and exit
                    flag=1;
                    exit(0);
                }
            }
            if(!flag)
                fprintf(f4,"%s\t\t%d\n",i1.label,locctr[z]);    //else write it in symtab
            fclose(f4);
        }
        length_temp=0;
        length_temp=opcode_search();                //function to search for opcode and fetch the instruction length if found
        if(length_temp==0)
            check=0;
        else
            check=1;
        locctr[z]+=length_temp;
        if(strcmp(i1.opcode,"ORG")==0)
        {
            check=1;
            locctr[++z]=strtoul(i1.operand[0],&ptr,10);     //increment locctr if its ORG
            start_addr[z]=locctr[z];
        }
        else if(strcmp(i1.opcode,"DB:")==0)
        {
            check=1;
            comma_k=0;
            strng=strlen(opd_temp);
            for(q=0;q<strng;q++)
            {
               if(opd_temp[q]==',')                 //calculate the number of bytes in DB
                    comma_k++;

            }
            locctr[z]+=(comma_k+1);                 //update locctr
        }
        else if(check!=1)
        {
            printf("\nError!! Invalid opcode\n");   //if opcode not found and if its not ORG and DB
            exit(0);
        }
        if(strcmp(i1.opcode,"DB:")==0)
        {
            fprintf(f3,"%d\t%d\t\t%s\t%s\t%s\n",(locctr[z]-(comma_k+1)),line,i1.label,i1.opcode,opd_temp);
            line++;
            length_temp=0;
            printf("%d\t%s\t%s\t%s\n",qw,i1.label,i1.opcode,opd_temp);
            qw++;

        }
        else if(strcmp(i1.opcode,"ORG")==0)
        {
            fprintf(f3,"\t\t%d\t\t%s\t%s\t%s\t%s\t%s\n",line,i1.label,i1.opcode,i1.operand[0],i1.operand[1],i1.operand[2]);
            line++;
            length_temp=0;
            printf("%d\t%s\t%s\t%s\t%s\t%s\n",qw,i1.label,i1.opcode,i1.operand[0],i1.operand[1],i1.operand[2]);
            qw++;
        }
        else
        {
            fprintf(f3,"%d\t%d\t\t%s\t%s\t%s\t%s\t%s\n",(locctr[z]-length_temp),line,i1.label,i1.opcode,i1.operand[0],i1.operand[1],i1.operand[2]);
            line++;
            length_temp=0;
        }
        fscanf(f1,"%s%s%s",i1.label,i1.opcode,operand);
        if(strcmp(i1.opcode,"DB:")==0)
            strcpy(opd_temp,operand);
        read_file(f1);
    }
    fprintf(f3,"\t\t%d\t\t%s\t%s\t%s\t%s\t%s\n",line,i1.label,i1.opcode,i1.operand[0],i1.operand[1],i1.operand[2]);
    printf("%d\t%s\t%s\t%s\t%s\t%s\n",qw,i1.label,i1.opcode,i1.operand[0],i1.operand[1],i1.operand[2]);
}

int opcode_search()
{
    FILE *f;
    opcd opk;
    f=fopen("optab.dat","r");
    while(fscanf(f,"%s%s%s%s%s%d",opk.mnenonic,opk.op[0],opk.op[1],opk.op[2],opk.objectcode,&opk.length)!=EOF)
    {
        if(strcmp(i1.opcode,opk.mnenonic)==0) //compare each instruction in optab and fetch its details
        {

            if( strcmp(i1.operand[0],opk.op[0])==0 && strcmp(i1.operand[1],opk.op[1])==0)
                return(return_length(f,opk));
            if(strcmp(i1.opcode,"CJNE")==0)
            {
                if(strcmp(i1.operand[0],opk.op[0])==0 && hashaddress(i1.operand[1]) && address(i1.operand[2]))
                    return(return_length(f,opk));
                if(strcmp(i1.operand[0],opk.op[0])==0 && address(i1.operand[1]) && address(i1.operand[2]))
                    return(return_length(f,opk));
            }
            if(strcmp(i1.operand[0],opk.op[0])==0 && address(i1.operand[1]))
                return(return_length(f,opk)); //mov a, addr
            if(address(i1.operand[0]) &&  address(i1.operand[1]))
                return(return_length(f,opk));    //checkin whether it is an addres or no
            if( strcmp(i1.operand[0],opk.op[0])==0 &&  doubleaddress(i1.operand[1]))
                return(return_length(f,opk));
            if(strcmp(i1.operand[1],opk.op[1])==0 && address(i1.operand[0]))
                return(return_length(f,opk));   //mov addr,a
            if(strcmp(i1.operand[0],opk.op[0])==0 && hashaddress(i1.operand[1]))
                return(return_length(f,opk));  //mov a,#n
            if(address(i1.operand[0]) && hashaddress(i1.operand[1]))
                return(return_length(f,opk));
            if((strcmp(i1.opcode,"LJMP")==0 || strcmp(i1.opcode,"AJMP")==0 || strcmp(i1.opcode,"LCALL")==0 ||strcmp(i1.opcode,"ACALL")==0 ) && isalpha(i1.operand[0][0]))
                return(return_length(f,opk));  //relarive address
            if( address(i1.operand[0]))
               return(return_length(f,opk));
        }
    }
    fclose(f);
    return 0;
}
int return_length(FILE *f,opcd opk)
{
    printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\n",qw,i1.label,i1.opcode,i1.operand[0],i1.operand[1],i1.operand[2],opk.objectcode);
    qw++;
    fclose(f);
    return opk.length;
}
int address(char op1[])
{
    if(op1[0]=='1' || op1[0]=='2' || op1[0]=='3' || op1[0]=='4' || op1[0]=='5' || op1[0]=='6' || op1[0]=='7' || op1[0]=='0' || op1[0]=='A'|| op1[0]=='B'|| op1[0]=='C'|| op1[0]=='D'|| op1[0]=='E'|| op1[0]=='F')
        if(op1[1]=='1' || op1[1]=='2' || op1[1]=='3' || op1[1]=='4' || op1[1]=='5' || op1[1]=='6' || op1[1]=='7' || op1[1]=='0' || op1[1]=='A'|| op1[1]=='B'|| op1[1]=='C'|| op1[1]=='D'|| op1[1]=='E'|| op1[1]=='F')
            return 1;
    return 0;
}
int hashaddress(char op1[])
{
    if(op1[0]=='#')
        if(op1[1]=='1' || op1[1]=='2' || op1[1]=='3' || op1[1]=='4' || op1[1]=='5' || op1[1]=='6' || op1[1]=='7' || op1[1]=='0' || op1[1]=='A'|| op1[1]=='B'|| op1[1]=='C'|| op1[1]=='D'|| op1[1]=='E'|| op1[1]=='F')
            if(op1[2]=='1' || op1[2]=='2' || op1[2]=='3' || op1[2]=='4' || op1[2]=='5' || op1[2]=='6' || op1[2]=='7' || op1[2]=='0' || op1[2]=='A'|| op1[2]=='B'|| op1[2]=='C'|| op1[2]=='D'|| op1[2]=='E'|| op1[2]=='F')
            return 1;
    return 0;
}
int doubleaddress(char op1[])
{
    if(op1[0]=='#')
        if(op1[1]=='1' || op1[1]=='2' || op1[1]=='3' || op1[1]=='4' || op1[1]=='5' || op1[1]=='6' || op1[1]=='7' || op1[1]=='0' || op1[1]=='A'|| op1[1]=='B'|| op1[1]=='C'|| op1[1]=='D'|| op1[1]=='E'|| op1[1]=='F')
            if(op1[2]=='1' || op1[2]=='2' || op1[2]=='3' || op1[2]=='4' || op1[2]=='5' || op1[2]=='6' || op1[2]=='7' || op1[2]=='0' || op1[2]=='A'|| op1[2]=='B'|| op1[2]=='C'|| op1[2]=='D'|| op1[2]=='E'|| op1[2]=='F')
                if(op1[3]=='1' || op1[3]=='2' || op1[3]=='3' || op1[3]=='4' || op1[3]=='5' || op1[3]=='6' || op1[3]=='7' || op1[3]=='0' || op1[3]=='A'|| op1[3]=='B'|| op1[3]=='C'|| op1[3]=='D'|| op1[3]=='E'|| op1[3]=='F')
                    if(op1[4]=='1' || op1[4]=='2' || op1[4]=='3' || op1[4]=='4' || op1[4]=='5' || op1[4]=='6' || op1[4]=='7' || op1[4]=='0' || op1[4]=='A'|| op1[4]=='B'|| op1[4]=='C'|| op1[4]=='D'|| op1[4]=='E'|| op1[4]=='F')
            return 1;
    return 0;
}

