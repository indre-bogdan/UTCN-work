#include <iostream>
#include "Profiler.h"
using namespace std;
Profiler profiler("Lab4");
/*
    Complexity of n logk
    When we vary n at a fixed k(5, 10, 100) we find that the more lists we create, the more operations we have to do on each list
    When we vary k, at fixed n=10000, the number of operations grows, but a slower rate(log grows slower than a liniar function)

*/
int op_k1=0;
int op_k2=0;
int op_k3=0;
int op_k=0;
typedef struct nmb
{
    int key;
    int list_index;
}nr;
typedef struct node
{
    int key;
    struct node *next;
}NodeT;

typedef struct
{
    int count;
    NodeT *last, *first, *crt;
}ListT;

void initL(ListT *l)
{
    l->count=0;
    l->first = l->last = l->crt =NULL;
}

void insLast(ListT* l, int ky)
{
    NodeT *p = (NodeT *) malloc(sizeof(NodeT));
    if(p!=NULL)
    {
        p->key = ky;
        p->next = NULL;

        if(l->count==0)
            l->first = l->last = p;
        else
        {
            l->last->next = p;
            l->last = p;
        }
        l->count++;
    }
}

void pop(ListT *l)
{
    NodeT *p;
    if(l->first!=NULL){
        p = l->first;
        l->first=l->first->next;
        free(p);
        l->count--;
        if(l->first==NULL)
            l->last=NULL;
    }
}
void show(ListT *l)
{
    NodeT *p = (NodeT *)malloc(sizeof(NodeT));
    p = l->first;
    if(p ==NULL)
        cout<<"Empty";
    while(p!=NULL)
    {
        cout<<p->key<<" ";
        p = p->next;
    }
    cout<<"\n";

}

void min_heapify(nr *b, int sz,  int i )
{
    int l=2*i + 1;
    int r = 2*i + 2;
    int smallest;
    op_k1++;
    op_k2++;
    op_k3++;
    op_k++;
    if(l<=sz && b[i].key>b[l].key)
        smallest = l;
    else
        smallest = i;
    op_k1++;
    op_k2++;
    op_k3++;
    op_k++;
    if(r<=sz && b[r].key< b[smallest].key)
        smallest = r;
    if(smallest!= i)
    {
        nr aux = b[i];
        b[i] = b[smallest];
        b[smallest] = aux;
        min_heapify(b,sz,smallest);
        op_k1+=3;
        op_k2+=3;
        op_k3+=3;
        op_k+=3;
    }

}

void build_heap_bottom(nr *b,int n)
{
    for(int i=n/2-1;i>=0;i--)
    {
        min_heapify(b,n-1,i);
    }
}

void popHeap(nr *b, int& sz)
{
    if(sz>0)
    {
        b[0] = b[sz-1];
        op_k1+=1;
        op_k2+=1;
        op_k3+=1;
        op_k+=1;
        sz--;
        min_heapify(b,sz-1,0);
    }
}

void pushHeap(nr *b, int &sz, nr x)
{
    sz++;
    b[sz-1] = x;
    op_k1+=1;
    op_k2+=1;
    op_k3+=1;
    op_k+=1;
    min_heapify(b,sz-1,0);
}

void mrg(ListT* a, int k, int n, ListT* lout)
{
    op_k1=0;
    op_k2=0;
    op_k3=0;
    op_k =0;
    nr b[10000];
    int sz = 0;
    nr aux,aux2;
    for(int i=0;i<k;i++)
    {
        aux.key = a[i].first->key;
        aux.list_index = i;
        b[i] = aux;
        sz++;
    }
    build_heap_bottom(b,sz);
    while(sz>0)
    {
        aux = b[0];
        popHeap(b,sz);
        insLast(lout, aux.key);
        pop(&a[aux.list_index]);
        if(a[aux.list_index].first != NULL)
        {
            aux2.key = a[aux.list_index].first->key;
            aux2.list_index = aux.list_index;
            pushHeap(b,sz,aux2);
        }
        op_k1+=1;
        op_k2+=1;
        op_k3+=1;
        op_k+=1;

    }

}
void createList(ListT* a, int n, int k)
{
    for(int i=0;i<k;i++)
        initL(&a[i]);
    int v[20000];
    for(int i=0;i<k-1;i++)
    {
        FillRandomArray(v,n/k,0,20000,0,1);
        for(int j=0;j<(n/k);j++)
        {
            insLast(&a[i],v[j]);
        }
    }
    int x = n/k + n - (n/k)*k;
    FillRandomArray(v,x,0,20000,0,1);
    for(int j=0;j<x;j++)
    {
        insLast(&a[k-1],v[j]);
    }
}

void demo()
{
    ListT a[100], lout;
    initL(&lout);
    int k, n;
    cout<<"Number of lists: ";
    cin>>k;
    cout<<"\nNumber of elements: ";
    cin>>n;
    for(int i=0;i<k;i++)
        initL(&a[i]);
    int v[100];
    for(int i=0;i<k-1;i++)
    {
        FillRandomArray(v,n/k,0,100,0,1);
        for(int j=0;j<(n/k);j++)
        {
            insLast(&a[i],v[j]);
        }
        show(&a[i]);
    }
    int x = n/k + n - (n/k)*k;
    FillRandomArray(v,x,0,100,0,1);
    for(int j=0;j<x;j++)
    {
        insLast(&a[k-1],v[j]);
    }
    show(&a[k-1]);
    mrg(a,k,n,&lout);
    show(&lout);
}

void avg_n()
{

    for(int j=0;j<5;j++){
        for(int n=100;n<=10000;n+=100)
        {
            ListT lout;
            ListT *a = (ListT*)malloc( n * sizeof(ListT));
            ListT *b = (ListT*)malloc( n * sizeof(ListT));
            ListT *c = (ListT*)malloc( n * sizeof(ListT));

            createList(a,n,5);
            createList(b,n,10);
            createList(c,n,100);

            initL(&lout);
            mrg(a,5,n,&lout);
            profiler.countOperation("avg_k1", n, op_k1);
            initL(&lout);
            mrg(b,10,n,&lout);
            profiler.countOperation("avg_k2", n, op_k2);
            initL(&lout);
            mrg(c,100,n,&lout);
            profiler.countOperation("avg_k3", n, op_k3);

            free(a);
            free(b);
            free(c);
        }
    }
    profiler.divideValues("avg_k3", 5);
    profiler.divideValues("avg_k2", 5);
    profiler.divideValues("avg_k1", 5);

    profiler.createGroup("AVG_N","avg_k1","avg_k2","avg_k3");
}
void avg_k()
{


        for(int k=1;k<=50;k+=1)
        {
            ListT lout;
            ListT *a = (ListT*)malloc( 10000 * sizeof(ListT));

            createList(a,10000,k);

            initL(&lout);
            mrg(a,k,10000,&lout);
            profiler.countOperation("avg_k", k, op_k);

            free(a);
        }


    profiler.createGroup("AVG_K","avg_k");
}

void avg_k500()
{


        for(int k=10;k<=500;k+=10)
        {
            ListT lout;
            ListT *a = (ListT*)malloc( 10000 * sizeof(ListT));

            createList(a,10000,k);

            initL(&lout);
            mrg(a,k,10000,&lout);
            profiler.countOperation("avg_k500", k, op_k);

            free(a);
        }


    profiler.createGroup("AVG_K500","avg_k500");
}

int main()
{
    demo();
    avg_n();
    avg_k500();
    avg_k();

    profiler.showReport();
    return 0;
}
