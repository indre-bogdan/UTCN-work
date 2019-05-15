#include <iostream>
#include <stdlib.h>
#include <time.h>
#include "Profiler.h"
using namespace std;

/*
    Complexity of BFS is O(E + V)

*/
Profiler profiler("Lab 9");
#define M 99999
enum{WHITE,GRAY,BLACK};

int op = 0;
typedef struct node
{
    int data;
    int color;
    int d;
    node* parent;
    node* pNext;
}Node;

typedef struct q
{
    Node *first;
    Node *last;
}Queue;

typedef struct
{
    Queue **e;
    Node **x;
}Graph;
Node *createNode(int data)
{
    Node *p = (Node *) malloc(sizeof(Node));
    if(p)
    {
        p->parent = NULL;
        p->data = data;
        p->d = M;
        p->color = WHITE;
        p->pNext = NULL;
    }
    return p;
}

Queue *createQueue()
{
    Queue *pQ = (Queue *)malloc(sizeof(Queue));
    if(pQ)
    {
        pQ->first = pQ->last = NULL;
    }
    return pQ;
}
void enqueue(Queue *pQ, int data)
{
    Node *p = createNode(data);
    p->color = GRAY;
    p ->pNext = pQ->first;
    pQ->first = p;

    if(pQ->last==NULL)
        pQ->last = p;
}
void insertInOrder(Queue *pQ, int data)
{
    Node *p = createNode(data);

    if(pQ->first == NULL)
        pQ->first = pQ->last = p;
    else
    {
        Node *q,*q1;
        q1 = NULL;
        q = pQ->first;
        while(q != NULL)
        {
            if(data < q->data)
            {
                if(q1 == NULL)
                {
                    p->pNext = pQ->first;
                    pQ->first = p;
                }
                else
                {
                    q1->pNext = p;
                    p->pNext = q;
                }
                break;
            }
        q1 = q;
        q = q->pNext;
        }
        if( q1!= NULL && data > q1->data)
        {
            q1->pNext = p;
            pQ->last = p;
        }
    }
}
bool isItOk(Queue *g, int b)
{
    Node *p = (Node*)malloc(sizeof(Node));
    p = g->first;
    while(p!=NULL)
    {
        if(p->data == b)
            return 0;
        p = p->pNext;
    }
    return 1;
}
Graph *createGraph(int n, int m)
{
    Graph *g = (Graph *)malloc(sizeof(Graph));
    g->x = (Node**)malloc(n * sizeof(Node*));
    for(int i=0;i<n;i++)
    {
        g->x[i] = createNode(i);
    }
    g->e = (Queue**)malloc(n * sizeof(Queue*));
    for(int i=0;i<n;i++)
        g->e[i] = createQueue();
    int a,b;
    /*for(int i=1;i<n;i++)
    {
        a = rand() % i;
        insertInOrder(g->e[i],a);
        insertInOrder(g->e[a],i);
    }
    int i = n-1;
    */
    int i = 0;
    while(i!=m)
    {
        a = rand()%n;
        b = rand()%n;
        if(a != b && isItOk(g->e[a],b) && isItOk(g->e[b],a))
        {
            insertInOrder(g->e[a],b);
            insertInOrder(g->e[b],a);
            i++;
        }
    }
    return g;
}

int dequeue(Queue *pQ)
{
    Node *p = (Node*)malloc(sizeof(Node));
    p = pQ->first;
    Node *q =NULL;
    while(p!=pQ->last)
    {
        q = p;
        p= p->pNext;
    }
    if(p == pQ->first)
        pQ->first = pQ->last = NULL;
    else
    {
        q->pNext = NULL;
        pQ->last = q;
    }
    int a = p->data;
    free(p);
    return a;
}

void bfs(Graph* g, int n, int s)
{

    g->x[s]->color=GRAY;
    g->x[s]->d = 0;
    Queue *qu = createQueue();
    enqueue(qu,g->x[s]->data);
    op++;
    while(qu->first != NULL)
    {
        op+=2;
        int o = dequeue(qu);
        Node *p = g->e[o]->first;
        while(p!= NULL)
        {
            op+=2;
            if(g->x[p->data]->color == WHITE)
            {
                g->x[p->data]->color = GRAY;
                g->x[p->data]->d = g->x[o]->d + 1;
                g->x[p->data]->parent = g->x[o];
                enqueue(qu,p->data);
                op++;
            }
            p= p->pNext;
        }
        g->x[o]->color= BLACK;
        op++;
    }
}

void bfsTotal(Graph *g, int n)
{
    op = 0;
    bool ok;
    do
    {
        ok = 1;
        for(int i=0;i<n;i++)
            if(g->x[i]->color == WHITE)
            {
                ok = 0;
                bfs(g,n,g->x[i]->data);
            }
    }while(!ok);
}

void drusal(Graph *g, int n)
{
    for(int i=0;i<n;i++)
    {
        Node *q = NULL;
        Node *p = g->e[i]->first;
        while(p!=NULL)
        {
            q = p;
            p = p->pNext;
            free(q);
        }
        free(g->e[i]);
        free(g->x[i]);
    }
    free(g->e);
    free(g->x);
    free(g);
}
void printNode(Graph *g, Node* s, int n)
{
    for(int i=0;i<n;i++)
    {
        if(g->x[i]->parent == s)
        {
            for(int j=0;j<g->x[i]->d;j++)
            {
                cout<<"   ";
            }
            cout<<g->x[i]->data<<"\n";
            printNode(g,g->x[i],n);
        }
    }
}
void pretty(Graph *g, int n)
{
    for(int i=0;i<n;i++)
    {
        if(g->x[i]->parent == NULL)
        {
            cout<<"\nCopac\n";
            cout<<g->x[i]->data<<"\n";
            printNode(g,g->x[i],n);

        }
    }
}
void demo2()
{
    int n = 10;
    int m = 15;
    Graph *g = createGraph(n,m);
    for(int i=0;i<n;i++)
    {
        Node *p = g->e[i]->first;
        cout<<"\n"<<i<<" : ";
        while(p!=NULL)
        {
            cout<<p->data<<" ";
            p = p->pNext;
        }
    }
    bfsTotal(g,n);
    /*
    cout<<"\n";
    for(int i=0;i<n;i++)
    {
        cout<<i<<" ";
        if(g->x[i]->parent != NULL)
            cout<<g->x[i]->parent->data<<"\n";
        else
            cout<<"NULL\n";
    }
    */
    pretty(g,n);
    drusal(g,n);
}
/*
void demo()
{
    int n = 10;
    int m = 10;
    Graph *g = createGraph(n,m);
    for(int i=0;i<n;i++)
    {
        Node *p = g->e[i]->first;
        cout<<"\n"<<i<<" : ";
        while(p!=NULL)
        {
            cout<<p->data<<" ";
            p = p->pNext;
        }
    }
    bfs(g,n,rand() % n);
    cout<<"\n";
    for(int i=0;i<n;i++)
    {
        cout<<i<<" ";
        if(g->x[i]->parent != NULL)
            cout<<g->x[i]->parent->data<<"\n";
        else
            cout<<"NULL\n";
    }
    drusal(g,n);
}
*/
void avg()
{
    int n,m;

        for(m=1000;m<=4500;m+=100)
        {
            n = 100;
            Graph* g = createGraph(n,m);
            bfsTotal(g,n);
            profiler.countOperation("avg", m, op);
            drusal(g,n);
        }

    profiler.createGroup("AVG_M","avg");
}
void avg2()
{
    int n,m;

        for(n=100;n<=200;n+=10)
        {
            m = 4500;
            Graph* g = createGraph(n,m);
            bfsTotal(g,n);
            profiler.countOperation("avg2", n, op);
            drusal(g,n);
        }


    profiler.createGroup("AVG_N","avg2");
}
int main()
{
    srand (time(NULL));
    char c;
    while(c != '3')
    {
        cout<<"\n1.Demo \n2.Avg \n3.Exit\n";
        cin>>c;
        if(c == '1')
            demo2();
        else
            if(c == '2')
            {
                avg();
                avg2();
                profiler.showReport();
            }
            else
                if(c != '3')
                    cout<<"\nInvalid input\n";
    }

    return 0;
}
