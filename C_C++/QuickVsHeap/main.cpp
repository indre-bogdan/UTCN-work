#include <iostream>
#include "Profiler.h"
#include <stdlib.h>
using namespace std;

/*
    Quicksort has the complexity O(n lgn) in the best case AND in the average case (but with a larger constant).
    This is because at every level part (the partition method) produces splits that are more or less balanced. Worse case happens when we make "edge" splits near on or the other end of the array which gives us O(n^2) complexity.
    The best case happens when we have equal splits (to do that we create the best case array beforehand by placing the pivot recursively in the middle)
    Heapsort has  O(n lgn) complexity because Build_heap_bottom takes O(n) and the recursive call does it n-1 times => o(n lgn) complexity in all the cases.
    Quicksort behaves slightly better than heapsort both in the average case (with a random array) and in the best case for each one of them( pivot in middle for quick; descending order for heap),
    but in the worst case, which is sorted for quick and ascending order for heap, heapsort is much more efficient

*/
Profiler profiler("Lab3");
int op_q=0;
int op_h=0;
int op_qw=0;
int part(int *a, int p, int r)
{
    int x = a[r];
    int i = p-1;
    for(int j=p;j<r;j++)
    {
        op_q++;//if
        op_qw++;
        if(a[j]<=x)
        {
            i = i+1;
            swap(a[i],a[j]);
            op_q+=3;
            op_qw+=3;
        }
    }
    swap(a[i+1], a[r]);
    op_q+=3;
    op_qw+=3;
    return i+1;
}

void quicks(int *a, int p, int r)
{
    op_q++;//if
    op_qw++;
    if(p<r)
    {
        int q = part(a,p,r);
        quicks(a,p,q-1);
        quicks(a,q+1,r);
    }
}
void quicksort(int *a,int p, int r)
{
    op_q=0;
    op_qw=0;
    quicks(a,p,r);
}
void max_heapify(int *a, int sz,  int i )
{
    int l=2*i + 1;
    int r = 2*i + 2;
    int largest;
    op_h++;//if
    if(l<=sz && a[i]<a[l])
        largest = l;
    else
        largest = i;
    op_h++;//if
    if(r<=sz && a[r]> a[largest])
        largest = r;
    op_h++;//if
    if(largest!= i)
    {
        int aux = a[i];
        a[i] = a[largest];
        a[largest] = aux;
        op_h+=3;
        max_heapify(a,sz,largest);
    }

}
void build_heap_bottom(int *a,int n)
{
    for(int i=n/2-1;i>=0;i--)
        max_heapify(a,n-1,i);
}

void heapsort(int *a, int n)
{
    op_h=0;
    build_heap_bottom(a,n);
    int sz=n-1;
    for(int i=n-1;i>=1;i--)
    {
        int aux=a[0];
        a[0] = a[i];
        a[i] = aux;
        op_h+=3;
        max_heapify(a,--sz,0);
    }

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
            quicksort(a,0,n-1);
            heapsort(b,n);

            profiler.countOperation("avg_quick", n, op_q);
            profiler.countOperation("avg_heap", n, op_h);

            free(a);
            free(b);
        }
    }
    profiler.divideValues("avg_quick", 5);
    profiler.divideValues("avg_heap", 5);

    profiler.createGroup("AVG","avg_quick","avg_heap");
}
void best_arr_quick(int *a,int p, int r)
{
    if(p<r)
    {

        best_arr_quick(a,p,(r+p)/2-1);
        best_arr_quick(a,(r+p)/2+1, r);
        swap(a[(r+p)/2], a[r]);
    }
}
void worst()
{

    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,0,1);
        CopyArray(b,a,n);
        quicksort(a,0,n-1);
        heapsort(a,n);

        profiler.countOperation("worst_quick", n, op_q);
        profiler.countOperation("worst_heap", n, op_h);

        free(a);
        free(b);
    }
    profiler.createGroup("WORST","worst_quick","worst_heap");
}

void best()
{

    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,0,1);
        best_arr_quick(a,0,n-1);
        FillRandomArray(b,n,0,20000,0,2);
        quicksort(a,0,n-1);
        heapsort(b,n);

        profiler.countOperation("best_quick", n, op_q);
        profiler.countOperation("best_heap", n, op_h);

        free(a);
        free(b);
    }
    profiler.createGroup("BEST","best_quick","best_heap");
}

void quick()
{

    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,0,1);
        CopyArray(b,a,n);
        best_arr_quick(a,0,n-1);
        quicksort(a,0,n-1);
        profiler.countOperation("best_quicks", n, op_q);
        quicksort(b,0,n-1);
        profiler.countOperation("worst_quicks", n, op_qw);

        free(a);
        free(b);
    }
    profiler.createGroup("QUICK","best_quicks","worst_quicks");
}
int main()
{
    avg();
    worst();
    best();
    quick();
    profiler.showReport();
    return 0;
}
