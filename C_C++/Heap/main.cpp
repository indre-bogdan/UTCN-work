#include <iostream>
#include "Profiler.h"

using namespace std;
Profiler profiler("Lab2");
int op_b = 0;
int op_t = 0;
/*
    The complexity of max_heapify is O(lg n) (by applying the master`s formula). In build_heap_bottom we call max_heapify n/2 times
     => we have O(n lgn) complexity.
     The complexity of heap_increase_key is O(lg n) and we call it n times => O(n lgn)
    The bottom-up build has a better time in the average case and also in the worst case because it uses fewer operations by working
    on half of the array. Building the heap top-down is not efficient because inserting is used in general for priority queues and not for building heaps.

*/
void max_heapify(int *a, int sz,  int i )
{
    int l=2*i + 1;
    int r = 2*i + 2;
    int largest;
    if(l<=sz && a[i]<a[l])
        largest = l;
    else
        largest = i;
    op_b++;//if cond
    if(r<=sz && a[r]> a[largest])
    {
        largest = r;
    }
    op_b++;//if cond
    if(largest!= i)
    {
        int aux = a[i];
        a[i] = a[largest];
        a[largest] = aux;
        op_b+=3;
        max_heapify(a,sz,largest);
    }

}

void build_heap_bottom(int *a,int n)
{
    op_b=0;
    for(int i=n/2-1;i>=0;i--)
        max_heapify(a,n-1,i);
}


void heap_increase_key(int *b, int i, int key)
{
    if(key<b[i])
    {
        cout<<"ERROR new key is smaller than current key";
        return;
    }
    op_t++;//if
    b[i] = key;
    op_t++;
    while(i>0 && (b[(i-1)/2] < b[i]))
    {
        op_t++;//while true
        int aux = b[i];
        b[i] = b[(i-1)/2];
        b[(i-1)/2] = aux;
        i = (i-1)/2;
        op_t+=3;
    }
    op_t++;//while false
}

void max_heap_insert(int *b, int n, int& sz, int key)
{
    sz++;
    b[sz] = -1000;
    op_t++;
    heap_increase_key(b,sz,key);
}

void build_heap_top(int *b, int n)
{
    op_t=0;
    int sz = 0;
    for(int i=1;i<n;i++)
        max_heap_insert(b,n,sz,b[i]);
}

void avg()
{

    for(int j=0;j<5;j++){
        for(int n=100;n<=10000;n+=100)
        {
            int *a = (int*)malloc( n * sizeof(int));
            int *b = (int*)malloc( n * sizeof(int));
            FillRandomArray(a,n,0,20000,0,0);
            CopyArray(b,a,n);
            build_heap_bottom(a,n);
            build_heap_top(b,n);

            profiler.countOperation("avg_top", n, op_t);
            profiler.countOperation("avg_bottom", n, op_b);

            free(a);
            free(b);
        }
    }
    profiler.divideValues("avg_bottom", 5);
    profiler.divideValues("avg_top", 5);

    profiler.createGroup("AVG","avg_bottom","avg_top");
}

void worst()
{

    for(int j=0;j<5;j++){
        for(int n=100;n<=10000;n+=100)
        {
            int *a = (int*)malloc( n * sizeof(int));
            int *b = (int*)malloc( n * sizeof(int));
            FillRandomArray(a,n,0,20000,0,1);
            CopyArray(b,a,n);
            build_heap_bottom(a,n);
            build_heap_top(b,n);

            profiler.countOperation("worst_top", n, op_t);
            profiler.countOperation("worst_bottom", n, op_b);

            free(a);
            free(b);
        }
    }
    profiler.divideValues("worst_bottom", 5);
    profiler.divideValues("worst_top", 5);

    profiler.createGroup("WORST","worst_bottom","worst_top");
}
int main()
{
    avg();
    worst();
    profiler.showReport();
    return 0;
}
