#include <iostream>
#include <stdlib.h>
#include <time.h>
#include "Profiler.h"
using namespace std;
/*
    Comlexity of DFS, Tarjan and Topological sort are all O(E + V)

*/
Profiler profiler("Lab 10");
#define M 99999
enum{WHITE,GRAY,BLACK};
int op = 0;
typedef struct node
{
    int data;
    int color;
    int d;
    int f;
    int lowlink;
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
        p->f = M;
        p->lowlink = -1;
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
        if(a != b && isItOk(g->e[a],b))
        {
            insertInOrder(g->e[a],b);
            i++;
        }
    }
    return g;
}
void push(Queue *stck, int data)
{
    Node *p = createNode(data);

    p->pNext = stck->first;
    if(stck->first==NULL)
        stck->last = p;
    stck->first = p;

}
void pop(Queue *stck)
{
    if(stck->last==stck->first)
        stck->last=NULL;
    free(stck->first);
    stck->first = stck->first->pNext;

}
bool searchStack(Queue *stck, int data)
{
    Node *q;
    q = stck->first;
    while(q != NULL)
    {
        if(q->data == data)
            return 1;
        q = q->pNext;
    }
    return 0;
}

void dfs_visit(Graph *g, Node* s, int &t)
{
    t++;
    s->d = t;
    s->color = GRAY;
    Node *p = g->e[s->data]->first;
    op++;
    while(p!= NULL)
    {
        op+=2;
        if(g->x[p->data]->color == WHITE)
            {
                g->x[p->data]->parent = s;
                dfs_visit(g,g->x[p->data],t);
                op++;
            }
            p= p->pNext;
    }
    s->color = BLACK;
    op++;
    t++;
    s->f = t;

}
void dfs(Graph *g, int n)
{
    op = 0;
    int t= 0;
    for(int i=0;i<n;i++)
    {
        if(g->x[i]->color == WHITE)
            dfs_visit(g,g->x[i],t);
    }


}
int mi(int x, int y)
{
    if(x>y)
        return y;
    return x;
}
void strongconnected(Graph *g, Node *s, Queue *stck, int& k, int &t)
{
    t++;
    s->color = GRAY;
    s->lowlink = t;

    push(stck, s->data);
    Node *p = g->e[s->data]->first;

    while(p!= NULL)
    {
        if(g->x[p->data]->color == WHITE)
        {
            strongconnected(g,g->x[p->data],stck,k, t);
            s->lowlink = mi(s->lowlink, g->x[p->data]->lowlink);
        }
        else
            if(searchStack(stck,p->data))
            {
                s->lowlink = mi(s->lowlink, g->x[p->data]->data);
            }
        p= p->pNext;
    }
    s->color = BLACK;
    int x;
    if(s->data == s->lowlink)
    {
        k++;
        cout<<"\n";
        do
        {
            x = stck->first->data;
            cout<<x<<" ";
            pop(stck);

        }while(stck->first != NULL && x != s->data);
    }
}
void Tarjan(Graph *g, int n, int& k)
{
    k = 0;
    int t= 0;
    Queue *stck = createQueue();
    for(int i=0;i<n;i++)
    {
        if(g->x[i]->color == WHITE)
            strongconnected(g,g->x[i],stck, k, t);
    }


}
void printNode(Graph *g, Node* s, int n, int sp)
{
    for(int i=0;i<n;i++)
    {
        if(g->x[i]->parent == s)
        {
            for(int j=0;j<sp;j++)
            {
                cout<<"   ";
            }
            cout<<"("<<g->x[i]->data<<","<<g->x[i]->d<<","<<g->x[i]->f<<")\n";
            printNode(g,g->x[i],n,sp+1);
        }
    }
}
void pretty(Graph *g, int n)
{
    for(int i=0;i<n;i++)
    {
        if(g->x[i]->parent == NULL)
        {
            cout<<"\nTree\n";
            cout<<"("<<g->x[i]->data<<","<<g->x[i]->d<<","<<g->x[i]->f<<")\n";
            printNode(g,g->x[i],n,1);

        }
    }
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
void topDfs(Graph *g, Node *s, Queue *q)
{
    s->color = GRAY;
    Node *p = g->e[s->data]->first;
    while(p!= NULL)
    {
        if(g->x[p->data]->color == WHITE)
            {
                topDfs(g,g->x[p->data],q);
            }
            p= p->pNext;
    }
    s->color = BLACK;
    push(q,s->data);

}
void Topological(Graph *g, int n)
{
    Queue *q = createQueue();
    for(int i=0;i<n;i++)
    {
        if(g->x[i]->color == WHITE)
            topDfs(g,g->x[i],q);
    }
    cout<<"\nTopological sort:\n";
    Node *p;
    p = q->first;
    while(p != NULL)
    {
        cout<<p->data<<" ";
        p = p ->pNext;
    }
}
void resetColor(Graph *g, int n)
{
    for(int i=0;i<n;i++)
        g->x[i]->color = WHITE;
}
void demo()
{
    int n = 10;
    int m = 10;
    int k;
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
    char c;
    while(c != '5')
    {
        cout<<"\n1.Create another graph\n2.Demo DFS \n3.Demo Tarjan \n4.Demo Topological \n5.Exit\n";
        cin>>c;
        if(c == '1')
        {
            drusal(g,n);
            cout<<"Number of nodes: ";
            cin>>n;
            cout<<"Number of edges: ";
            cin>>m;
            g = createGraph(n,m);
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
        }
        else
            if(c == '2')
            {
                dfs(g,n);
                pretty(g,n);
                resetColor(g,n);
            }

            else
                if(c == '3')
                {
                    Tarjan(g,n,k);
                    cout<<"\nNumber of strongly connected components: "<<k<<"\n";
                    resetColor(g,n);
                }
                else
                    if(c == '4')
                    {
                        cout<<"\nFirst we do Tarjan to see if we have strongly connected components...\n";
                        Tarjan(g,n,k);
                        resetColor(g,n);
                        if(k == n)
                        {
                            cout<<"\nIt`s all good!\n";
                            Topological(g,n);

                        }
                        else
                            cout<<"\nSorry mate, can`t do it\n";
                        resetColor(g,n);


                    }
                    else
                        if( c!= '5')
                            cout<<"\nInvalid input\n";
    }
    drusal(g,n);
}

void avg()
{
    int n,m;

        for(m=1000;m<=4500;m+=100)
        {
            n = 100;
            Graph* g = createGraph(n,m);
            dfs(g,n);
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
            dfs(g,n);
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
            demo();
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
