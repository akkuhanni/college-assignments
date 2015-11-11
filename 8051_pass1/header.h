#ifndef HEADER_H_INCLUDED
#define HEADER_H_INCLUDED

typedef struct
{
    char label[10];
    char opcode[10];
    char operand[20][10];
}intr;
typedef struct
{
    char mnenonic[10];
    char op[3][10];
    char objectcode[5];
    int length;
}opcd;
intr i1;
unsigned int locctr[20],start_addr[20],z,line,qw;
char operand[20];
void read_file(FILE *f1);
void inter_file_generation(FILE *f1,FILE *f3);
int opcode_search();
int return_length(FILE *f,opcd opk);
int address(char op[]);
int hashaddress(char op[]);
int doubleaddress(char op[]);



#endif // HEADER_H_INCLUDED
