#include <iostream>
#include <stdlib.h>
#include <time.h>
#include "Profiler.h"
using namespace std;
/*
    In Kruskal`s algorithm we use MakeSet (on each node so o(n)), FindSet and Union (both on the number of edges so still O(n))

*/
Profiler profiler("Lab 8");

int op = 0;
typedef struct node
{
    int v;
    int rank;
    node* p;
}Node;

typedef struct
{
    int x;
    int y;
    int w;
}Edge;

typedef struct
{
    Node **nodes;
    Edge **e;
}Graph;

Node* MakeSet(int value)
{
    Node* x = (Node*) malloc(sizeof(Node));
    x->v = value;
    x->p = x;
    x->rank = 0;
    op+=2;
    return x;
}

Node* FindSet(Node* x)
{
    op++;
    if( x != x->p)
    {
        x->p = FindSet(x->p);
        op++;
    }
    return x->p;
}

void Link(Node* x, Node* y)
{
    op++;
    if(x->rank > y->rank)
    {
        y->p = x;
        op++;
    }
    else
    {
        x->p = y;
        op+=2;
        if(x->rank == y->rank)
        {
            (y->rank)++;
            op++;


        }
    }
}

void Unio(Node* x, Node* y)
{
    Link(FindSet(x), FindSet(y));
}

void checkOp()
{
    cout<<"\nGive a number of nodes\n";
    int n;
    cin>>n;
    Node** g = (Node**)malloc(n * sizeof(Node*));
    for(int i=0;i<n;i++)
    {
        g[i] = MakeSet(i);
    }
    int x, y;



    cout<<"\nInitial setup (value parent`s value rank)\n";
    for(int i=0;i<n;i++)
    {
        cout<<"("<<g[i]->v<<" "<<g[i]->p->v<<" "<<g[i]->rank<<") ";
    }

    char p;
    while(p != '3')
    {
        cout<<"\n1.Make random union\n2.Find random set\n3.Exit\n";
        cin>>p;
        if(p == '1')
        {
            x = rand()%n;
            y = rand()%n;
            while(x==y)
            {
                x = rand()%n;
            }
            Unio(g[x],g[y]);
            for(int i=0;i<n;i++)
            {
                cout<<"("<<g[i]->v<<" "<<g[i]->p->v<<" "<<g[i]->rank<<") ";
            }
        }
        else
            if(p == '2')
            {
                int b = rand()%n;
                Node *a = FindSet(g[b]);
                cout<<"\nNode "<<b<<" is in set "<<a->v<<"\n";
            }
            else
                if(p != '3')
                    cout<<"\nInvalid input\n";
    }

}

bool itIsOk(Graph *g, int a, int b, int edge_index)
{
    if(a == b)
        return 0;
    for(int i=0;i<edge_index;i++)
    {
        if((g->e[i]->x == a && g->e[i]->y == b) || (g->e[i]->x == b && g->e[i]->y == a))
        return 0;
    }
    return 1;
}
Graph* createConnected(int n)
{
    Graph* g = (Graph*) malloc(sizeof(Graph));
    g->e = (Edge**) malloc(4*n*sizeof(Edge*));
    for(int i=0;i<4*n;i++)
        g->e[i] = (Edge*)malloc(sizeof(Edge));
    for(int i=0;i<n-1;i++)
    {
        g->e[i]->x = i+1;
        g->e[i]->y = rand() % (i+1);
        g->e[i]->w = rand();
    }

    int i= n-1;
    while(i!= 4*n)
    {
        int a = rand()%n;
        int b = rand()%n;
        if(itIsOk(g,a,b,i))
        {
            g->e[i]->x = a;
            g->e[i]->y = b;
            g->e[i]->w = rand();
            i++;
        }
    }
    return g;
}
/*int cmp(const void* a, const void* b)
{
    int l = ((Edge *)a)->w;
    int r = ((Edge *)b)->w;
    return (l - r);
}*/
void ord(Graph* g, int n)
{

    bool ok;
    do
    {
        ok=1;
        for(int i=0;i<4*n-1;i++)
            if(g->e[i]->w > g->e[i+1]->w)
            {
                Edge* aux = g->e[i];
                g->e[i] = g->e[i+1];
                g->e[i+1] = aux;
                ok = 0;
            }
    }while(!ok);
}
Edge** Kruskal(Graph* g, int n, int &l)
{
    op=0;
    l = -1;
    Edge** A = (Edge**)malloc(n * sizeof(Edge*));
    for(int i=0;i<n;i++)
        A[i] = (Edge*)malloc(sizeof(Edge));
    Node** nodes = (Node**)malloc(n * sizeof(Node*));
    for(int i=0;i<n;i++)
        nodes[i] = MakeSet(i);
    //qsort(g->e,4*n,sizeof(Edge*),cmp);
    ord(g,n);
    for(int i=0;i<4*n;i++)
    {
        if(FindSet(nodes[g->e[i]->x]) != FindSet(nodes[g->e[i]->y]))
        {
            l++;
            A[l] = g->e[i];
            Unio(nodes[g->e[i]->x],nodes[g->e[i]->y]);
        }
    }

    for(int i=0;i<n;i++)
        free(nodes[i]);
    free(nodes);
    return A;
}
void drusal(Edge **A, Graph *g, int l, int n)
{
    for(int i=0;i<=l;i++)
        free(A[i]);
    free(A);
    for(int i=0;i<4*n;i++)
        free(g->e[i]);
    free(g);
}
void checkKruskal()
{
    int n;
    cout<<"Number of nodes: \n";
    cin>>n;
    Graph* g = createConnected(n);
    cout<<"\nOriginal graph (1st node 2nd node weight)\n";
    for(int i=0;i<4*n;i++)
        cout<<"("<<g->e[i]->x<<" "<<g->e[i]->y<<" "<<g->e[i]->w<<")\n";

    int l;
    Edge **A = Kruskal(g,n,l);
    cout<<"\nMinimum spanning tree\n";
    for(int i=0;i<=l;i++)
        cout<<"("<<A[i]->x<<" "<<A[i]->y<<" "<<A[i]->w<<")\n";
    drusal(A,g,l,n);
}


void avg()
{


        for(int n=100;n<=10000;n+=100)
        {
            Graph* g = createConnected(n);
            int l;
            Edge **A = Kruskal(g,n,l);
            profiler.countOperation("avg", n, op);
            drusal(A,g,l,n);
        }


    profiler.createGroup("AVG_N","avg");
}
int main()
{

    srand (time(NULL));
    char c;
    while(c != '4')
    {
        cout<<"\n1.Operations demo\n2.Kruskal demo\n3.Avg\n4.Exit\n";
        cin>>c;
        if(c == '1')
            checkOp();
        else
            if(c == '2')
                checkKruskal();
            else
                if(c == '3')
                {
                    avg();
                    profiler.showReport();
                }
                else
                    if(c != '4')
                        cout<<"\nInvalid input\n";
    }

    return 0;
}
