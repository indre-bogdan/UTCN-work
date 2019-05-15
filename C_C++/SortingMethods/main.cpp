#include <iostream>
#include <fstream>
#include "Profiler.h"
#define max 10001
using namespace std;

Profiler profiler("Lab1");
int assig_ins=0,assig_sel=0,assig_bub=0;
int comp_ins=0,comp_sel=0,comp_bub=0;
int a_c_ins=0,a_c_sel=0,a_c_bub=0;

void copyvec(int *v, int *w, int n)
{
    for(int i=0;i<n;i++)
        w[i] = v[i];
}
/*
insertion sort
            A       C              O

best        o(n)       o(n)        O(n)
worst       o(n^2)     o(n^2)    o(n^2)

->Stable
->Best on average case
->Best in the best case
*/
void insertion (int *v, int n)
{
    int buff;
    assig_ins =0;
    comp_ins= 0;
    a_c_ins= 0;
    for(int i=1;i<n;i++)
    {
        buff= v[i];
        assig_ins++;
        int j= i - 1;
        while(j>=0 && v[j] > buff)
        {
            comp_ins++;
            v[j+1] = v[j];
            assig_ins++;
            j--;
        }
        comp_ins++;
        v[j+1] = buff;
        assig_ins++;
    }
    a_c_ins=assig_ins+comp_ins;

}

/*
selection sort
        A           C             O
best    o(n)       o(n^2)       o(n^2)
worst   o(n)       o(n^2)       o(n^2)

->Best in worst case
*/

void selection(int *v, int n)
{
    assig_sel =0;
    comp_sel= 0;
    a_c_sel= 0;
    for(int i=0;i<n-1;i++)
    {
        int poz_min = i;
        for(int j=i+1;j<n;j++)
        {
            comp_sel++;
            if(v[j]<v[poz_min])
                poz_min = j;
        }

        int buff = v[i];
        v[i] = v[poz_min];
        v[poz_min] = buff;
        assig_sel+=3;
    }
    a_c_sel = assig_sel + comp_sel;

}
/*
bubble sort
            A            C            O

best        o(1)        o(n^2)      o(n^2)
worst       o(n^2)      o(n^2)      o(n^2)

->Stable
->Worst on average but there is place for improvements on the algorithm (to make only one pass, to decrease the size by 1, etc..)
*/
void bubble(int *v, int n)
{
    assig_bub =0;
    comp_bub= 0;
    a_c_bub= 0;
    for(int i=0;i<n-1;i++)
        for(int j=i+1;j<n;j++)
            {
                comp_bub++;
                if(v[i] > v[j])
                {
                    int buff = v[i];
                    v[i] = v[j];
                    v[j] = buff;
                    assig_bub+=3;
                }
            }
    a_c_bub = assig_bub + comp_bub;
}

/*
In the best case (array already sorted) bubble has 0 assignments
selection has less assignments than insertion
*/
void best_assign()
{
    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        int *c = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,1,1);
        copyvec(a,b,n);
        copyvec(a,c,n);
        insertion(a,n);
        selection(b,n);
        bubble(c,n);
        profiler.countOperation("best_insertion_assig", n, assig_ins);
        profiler.countOperation("best_selection_assig", n, assig_sel);
        profiler.countOperation("best_bubble_assig", n, assig_bub);

        free(a);
        free(b);
        free(c);
    }
    profiler.createGroup("BEST_assig","best_insertion_assig","best_selection_assig","best_bubble_assig");
}
/*
In the best case insertion has the least comparisons
selection has an equal number of comparisons with bubble
*/
void best_comp()
{
    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        int *c = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,1,1);
        copyvec(a,b,n);
        copyvec(a,c,n);
        insertion(a,n);
        selection(b,n);
        bubble(c,n);
        profiler.countOperation("best_insertion_comp", n, comp_ins);
        profiler.countOperation("best_selection_comp", n, comp_sel);
        profiler.countOperation("best_bubble_comp", n, comp_bub);

        free(a);
        free(b);
        free(c);
    }
    profiler.createGroup("BEST_comp","best_insertion_comp","best_selection_comp","best_bubble_comp");
}
/*
In the best case overall, insertion has fewer operations than both selection and bubble which are almost equal
*/
void best_a_c()
{
    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        int *c = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,1,1);
        copyvec(a,b,n);
        copyvec(a,c,n);
        insertion(a,n);
        selection(b,n);
        bubble(c,n);
        profiler.countOperation("best_insertion_a_c", n, a_c_ins);
        profiler.countOperation("best_selection_a_c", n, a_c_sel);
        profiler.countOperation("best_bubble_a_c", n, a_c_bub);

        free(a);
        free(b);
        free(c);
    }
    profiler.createGroup("BEST_a_c","best_insertion_a_c","best_selection_a_c","best_bubble_a_c");
}
/*
In the worst case(array sorted in descending order)  selection has the least assignments
then comes insertion and then bubble
*/
void worst_assign()
{
    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        int *c = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,1,2);
        copyvec(a,b,n);
        copyvec(a,c,n);
        insertion(a,n);
        selection(b,n);
        bubble(c,n);
        profiler.countOperation("worst_insertion_assig", n, assig_ins);
        profiler.countOperation("worst_selection_assig", n, assig_sel);
        profiler.countOperation("worst_bubble_assig", n, assig_bub);

        free(a);
        free(b);
        free(c);
    }
    profiler.createGroup("WORST_assig","worst_insertion_assig","worst_selection_assig","worst_bubble_assig");

}
/*
In the worst case insertion performs slightly worse when it comes
to comparisons than insertion and bubble
*/
void worst_comp()
{
    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        int *c = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,1,2);
        copyvec(a,b,n);
        copyvec(a,c,n);
        insertion(a,n);
        selection(b,n);
        bubble(c,n);
        profiler.countOperation("worst_insertion_comp", n, comp_ins);
        profiler.countOperation("worst_selection_comp", n, comp_sel);
        profiler.countOperation("worst_bubble_comp", n, comp_bub);

        free(a);
        free(b);
        free(c);
    }
    profiler.createGroup("WORST_comp","worst_insertion_comp","worst_selection_comp","worst_bubble_comp");

}
/*
In the worst case overall,  selection has the least operations
then comes insertion and then bubble
*/
void worst_a_c()
{
    for(int n=100;n<=10000;n+=100)
    {
        int *a = (int*)malloc( n * sizeof(int));
        int *b = (int*)malloc( n * sizeof(int));
        int *c = (int*)malloc( n * sizeof(int));
        FillRandomArray(a,n,0,20000,1,2);
        copyvec(a,b,n);
        copyvec(a,c,n);
        insertion(a,n);
        selection(b,n);
        bubble(c,n);
        profiler.countOperation("worst_insertion_a_c", n, a_c_ins);
        profiler.countOperation("worst_selection_a_c", n, a_c_sel);
        profiler.countOperation("worst_bubble_a_c", n, a_c_bub);

        free(a);
        free(b);
        free(c);
    }
    profiler.createGroup("WORST_a_c","worst_insertion_a_c","worst_selection_a_c","worst_bubble_a_c");

}
/*
In the average case selection has the least assignments
then comes insertion and bubble
*/

void avg_assign()
{

    for(int j=0;j<5;j++){
        for(int n=100;n<=10000;n+=100)
        {
            int *a = (int*)malloc( n * sizeof(int));
            int *b = (int*)malloc( n * sizeof(int));
            int *c = (int*)malloc( n * sizeof(int));
            FillRandomArray(a,n,0,20000,1,0);
            copyvec(a,b,n);
            copyvec(a,c,n);
            insertion(a,n);
            selection(b,n);
            bubble(c,n);
            profiler.countOperation("avg_insertion_assig", n, assig_ins);
            profiler.countOperation("avg_selection_assig", n, assig_sel);
            profiler.countOperation("avg_bubble_assig", n, assig_bub);

            free(a);
            free(b);
            free(c);
        }
    }
    profiler.divideValues("avg_insertion_assig", 5);
    profiler.divideValues("avg_selection_assig", 5);
    profiler.divideValues("avg_bubble_assig", 5);

    profiler.createGroup("AVG_assig","avg_insertion_assig","avg_selection_assig","avg_bubble_assig");
}
/*
In the average case insertion has the least comparisons
then comes selection and bubble
*/
void avg_comp()
{

    for(int j=0;j<5;j++){
        for(int n=100;n<=10000;n+=100)
        {
            int *a = (int*)malloc( n * sizeof(int));
            int *b = (int*)malloc( n * sizeof(int));
            int *c = (int*)malloc( n * sizeof(int));
            FillRandomArray(a,n,0,20000,1,0);
            copyvec(a,b,n);
            copyvec(a,c,n);
            insertion(a,n);
            selection(b,n);
            bubble(c,n);
            profiler.countOperation("avg_insertion_comp", n, comp_ins);
            profiler.countOperation("avg_selection_comp", n, comp_sel);
            profiler.countOperation("avg_bubble_comp", n, comp_bub);

            free(a);
            free(b);
            free(c);
        }
    }
    profiler.divideValues("avg_insertion_comp", 5);
    profiler.divideValues("avg_selection_comp", 5);
    profiler.divideValues("avg_bubble_comp", 5);

    profiler.createGroup("AVG_comp","avg_insertion_comp","avg_selection_comp","avg_bubble_comp");
}
/*
In the average case overall, insertion and then selection perform best,
bubble having the most operations
*/
void avg_a_c()
{

    for(int j=0;j<5;j++){
        for(int n=100;n<=10000;n+=100)
        {
            int *a = (int*)malloc( n * sizeof(int));
            int *b = (int*)malloc( n * sizeof(int));
            int *c = (int*)malloc( n * sizeof(int));
            FillRandomArray(a,n,0,20000,1,0);
            copyvec(a,b,n);
            copyvec(a,c,n);
            insertion(a,n);
            selection(b,n);
            bubble(c,n);
            profiler.countOperation("avg_insertion_a_c", n, a_c_ins);
            profiler.countOperation("avg_selection_a_c", n, a_c_sel);
            profiler.countOperation("avg_bubble_a_c", n, a_c_bub);

            free(a);
            free(b);
            free(c);
        }
    }
    profiler.divideValues("avg_insertion_a_c", 5);
    profiler.divideValues("avg_selection_a_c", 5);
    profiler.divideValues("avg_bubble_a_c", 5);

    profiler.createGroup("AVG_a_c","avg_insertion_a_c","avg_selection_a_c","avg_bubble_a_c");
}
int main()
{
    best_assign();
    best_comp();
    best_a_c();
    worst_assign();
    worst_comp();
    worst_a_c();
    avg_assign();
    avg_comp();
    avg_a_c();
    profiler.showReport();
    return 0;
}
