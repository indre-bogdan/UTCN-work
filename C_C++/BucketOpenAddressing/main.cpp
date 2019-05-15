#include <iostream>
#include <stdlib.h>
#include <string>
#include "Profiler.h"
#include <time.h>
#include <cmath>
#include <sys/time.h>
using namespace std;
/*
        Complexity of each search = O(1);
        It is more efficient to search for elements that are in the hash table than for those that are not.
        As the filling factor grows, so does the numbers of cells needed to be checked => more operations
*/
#define B 10007
int effort;

typedef struct
{
    int id;
    char name[20];
}Entry;

int h1(Entry k)
{
    return k.id % B;
}
int h2(Entry k, int i)
{
    return (h1(k) + i + 2*i*i) % B;
}
void initBucket(Entry *Bucket)
{
    for(int i=0;i<B;i++)
        Bucket[i].id = -1;
}
int insBucket(Entry* Bucket, Entry x)
{
    int i=0;
    int j;
    do
    {
        j=h2(x,i);
        if(Bucket[j].id == -1 || Bucket[j].id == x.id)
            {
                Bucket[j] = x;
                return j;
            }
        else
            i++;
    }while(i!=B);
return -1;
}

int searchBucket(Entry* Bucket, Entry x)
{
    effort=0;
    int i = 0;
    int j;
    do
    {
        effort++;
        j = h2(x,i);
        if(Bucket[j].id == x.id)
            return j;
        i++;
    }while(Bucket[j].id!=-1 && i!=B);
return -1;
}
void genString(char name[20])
{

    for(int i=0;i<7;i++)
    {

        name[i] = rand() % 25 + 97;
    }
}
void demo()
{
    int n;
    cout<<"Number of entries: ";
    cin>>n;
    Entry* Bucket =(Entry*)malloc(B * sizeof(Entry));
    initBucket(Bucket);
    int* a =(int*)malloc(n * sizeof(int));
    FillRandomArray(a,n,0,100,0,0);
    for(int i=0;i<n;i++)
    {
        Entry x;
        x.id = a[i];

        genString(x.name);
        printf("%d %s\n", x.id, x.name);
        insBucket(Bucket, x);
    }

    int y;
    char c;
    do{
        cout<<"\n1. Search entry\n2. Exit\n";
        cin>>c;
        if(c=='1')
        {
            cout<<"\nEnter id: ";
            cin>>y;
            Entry z;
            z.id = y;
            int j =searchBucket(Bucket,z);
            if(j!=-1)
                printf("Found! Id: %d  Name: %s",Bucket[j].id, Bucket[j].name);
            else
                cout<<"\nNot found ";
        }
        else
            if(c!='2')
                cout<<"\nInvalid input\n";
    }while(c!='2');
    free(a);
    free(Bucket);

}
void chooseRand(int *frv, int *m)
{
    int i=0,j=0,k=-1;
    while(k<2999)
    {


        int r = rand()%30000;
        //cout<<r<<" ";
        if(frv[r]>0 && i!=1500)
        {
            frv[r]=-1;
            k++;
            m[k] = r;
            i++;
        }
        else
            if(frv[r]==0 && j!=1500)
            {
                frv[r]=-1;
                k++;
                m[k] = r;
                j++;
            }
    }

}
void avg()
{

    double alfa[5]  = {0.80, 0.85, 0.90,0.95, 0.99};


    double avgFound2 = 0;
    double avgNotFound2 = 0;
    double maxFound2 = 0;
    double maxNotFound2 = 0;

    cout<<"Alfa      AvgFound      MaxFound      AvgNotFound      MaxNotFound";

    for(int j=0;j<5;j++){
        for(int i=0;i<5;i++)
        {
            double avgFound = 0;
            double avgNotFound = 0;
            double maxFound = 0;
            double maxNotFound = 0;

            Entry* Bucket =(Entry*)malloc(B * sizeof(Entry));
            initBucket(Bucket);
            int n = floor(alfa[j] * B);
            int* a =(int*)malloc(n * sizeof(int));
            int* frv =(int*)malloc(30000 * sizeof(int));
            int* m =(int*)malloc(3000 * sizeof(int));
            for(int k=0;k<30000;k++)
                frv[k] = 0;
            FillRandomArray(a,n,0,29999,1,0);
            for(int k=0;k<n;k++)
            {
                Entry x;
                x.id = a[k];
                frv[a[k]]++;
                insBucket(Bucket, x);
            }
            chooseRand(frv,m);
            for(int k=0;k<3000;k++)
            {
                Entry x;
                x.id = m[k];
                int l = searchBucket(Bucket,x);
                if(l==-1)
                {
                    avgNotFound += effort;
                    if(effort > maxNotFound)
                        maxNotFound = effort;
                }
                else
                {
                    avgFound += effort;
                    if(effort > maxFound)
                        maxFound = effort;
                }
            }

            avgFound = avgFound / 1500;
            avgNotFound = avgNotFound / 1500;
            avgFound2 += avgFound;
            avgNotFound2 += avgNotFound;
            maxNotFound2 += maxNotFound;
            maxFound2 += maxFound;

            free(a);
            free(frv);
            free(Bucket);
            free(m);

        }
    avgFound2 = avgFound2 / 5;
    avgNotFound2 = avgNotFound2 / 5;
    maxNotFound2 = maxNotFound2 / 5;
    maxFound2 = maxFound2 / 5;


    printf("\n%.2f         %.2f         %.2f        %.2f           %.2f", alfa[j], avgFound2, maxFound2, avgNotFound2, maxNotFound2);
    }



}

int main()
{
    /*struct timeval t1;
    gettimeofday(&t1, NULL);
    srand(t1.tv_usec * t1.tv_sec);*/
    char c;
    do
    {
        cout<<"\n1. Demo\n2. Table\n3. Exit\n";
        cin>>c;
        if(c=='1')
            demo();
        else
            if(c=='2')
                avg();
            else
                if(c!='3')
                    cout<<"\nInvalid input";
    }while(c != '3');

    return 0;
}
