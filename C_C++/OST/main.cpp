#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <algorithm>
#include "Profiler.h"
using namespace std;

Profiler profiler("Lab6");
/*
    Complexity of osSelect is O(lg n) because we have a balanced tree and the length is n;
    Complexity of osDelete is O(lg n) (length n);
    We do these operations n times => O(n lg n)

*/


int op;
typedef struct node
{
    int key;
    int sz;
    struct node *left;
    struct node *right;
}Ost;

Ost* newNode()
{
    Ost *p = (Ost*)malloc(sizeof(Ost));
    if(p!=NULL)
    {
        p->key = 0;
        p->left = NULL;
        p->right = NULL;
        p->sz = 0;
        return p;
    }
    return NULL;
}
Ost* BuildPerfectBalanced(int *a, int left, int right)
{
    if(left<=right)
    {
        Ost *root = (Ost*)malloc(sizeof(Ost));
        int mid = (left + right) / 2;
        root->key = a[mid];
        root->sz = right - left + 1;
        root->left = BuildPerfectBalanced(a, left, mid - 1);
        root->right = BuildPerfectBalanced(a, mid + 1, right);
        return root;
    }
    return NULL;
}

Ost* buildBalanced(int n)
{
    int* a = (int*) malloc((n+1) * sizeof(int));
    for(int i=1;i<=n;i++)
        a[i] = i;
    return BuildPerfectBalanced(a,1,n);
}
Ost* osSelect(Ost *x, int i)
{
    int r;
    op++;
    if(x->left == NULL)
         r = 1;
    else
         r = x->left->sz + 1;
    if(i == r)
        return x;
    else if (i < r)
            return osSelect(x->left, i);
        else
            return osSelect(x->right, i - r);

}

void preorder(Ost *p, int level)
{
    int i;
    if (p != NULL)
    {
        for (i = 0; i <= level; i++)
            printf("    ");
        printf("(%2d %d)\n", p->key,p->sz);
        preorder(p->left, level + 1);
        preorder(p->right, level + 1);
    }
}

Ost *finMin(Ost *p)
{
    op++;
    if(p==NULL)
        return NULL;
    op++;
    if(p->left)
        return finMin(p->left);
    else
        return p;
}
Ost *osDelete(Ost*parent, Ost *p, int key , bool &ok)
{
    op++;
    if(p != NULL)
    {
        op++;
        if(key < p->key)
        {
            op++;
            p->left = osDelete(p, p->left, key, ok);

        }
        else
        {
            op++;
            if(key >p->key)
            {
                op++;
                p->right = osDelete(p, p->right, key, ok);

            }
            else
            {
                op+=2;
                if(p->right && p->left)
                {
                    Ost *temp =  finMin(p->right);
                    p->key = temp ->key;
                    op++;
                    p->right = osDelete(p, p->right, temp->key, ok);


                }
                else
                {
                    Ost *temp = p;
                    op++;
                    if(p->left == NULL)
                    {
                        p = p->right;
                        op++;
                    }

                    else
                    {
                        op++;
                        if(p->right == NULL)
                        {
                            p = p->left;
                            op++;

                        }
                    }

                    free(temp);
                }
                ok = 1;
            }
        }
        if(ok && parent!= NULL)
            (parent->sz)--;

    }
    return p;
}
void demo()
{
    int n;
    cout<<"Number of elements: ";
    cin>>n;
    Ost* root = buildBalanced(n);
    preorder(root,0);
    cout<<"\n";
    char c;
    while(c != '5')
    {

        cout<<"1. Delete chosen node\n2. Delete random node\n3. Select smallest i-th node\n4. Show\n5. Exit\n";
        cin>>c;
        if(c == '1')
        {
            int x;
            cout<<"\nChoose node: ";
            cin>>x;
            bool ok =0;
            if(x == root->key)
                root = osDelete(NULL,root,x,ok);
            else
                osDelete(NULL,root,x,ok);
        }
        else
        {
            if(c == '3')
            {
                int i;
                cout<<"Choose i: ";
                cin>>i;
                Ost *p = osSelect(root,i);
                cout<<"The element is: ("<<p->key<<" "<<p->sz<<")\n";
            }
            else
                if(c == '2')
                {
                    bool ok = 0;
                    osDelete(NULL,root,rand()% n + 1, ok);

                }
                else
                    if(c == '4')
                        preorder(root,0);
                    else
                        if (c != '5')
                            cout<<"\nInvalid input\n";
        }
    }


}

void avg()
{

    for(int j=0;j<5;j++){
        for(int n=100;n<=10000;n+=100)
        {
            int *a = (int*)malloc( (n+1) * sizeof(int));
            Ost *tree = (Ost*)malloc(sizeof(Ost));

            op = 0;
            for(int i = 1;i <= n; i++)
                a[i] = i;
            tree = buildBalanced(n);
            random_shuffle(&a[1],&a[n]);
            for(int i=n;i>0;i--)
            {
                bool ok =0;
                Ost *p = (Ost*)malloc(sizeof(Ost));
                int x = rand() % i + 1;
                p = osSelect(tree, x);
                osDelete(NULL, tree, p->key, ok);
            }
            profiler.countOperation("avg", n, op);

            free(a);

        }
    }
    profiler.divideValues("avg", 5);


    profiler.createGroup("AVG_N","avg");
}
int main()
{

    char c;
    while(c!='3')
    {
        cout<<"1. Demo\n2. Graphic\n3. Exit\n";
        cin>>c;
        if(c == '1')
            demo();
        else
            if(c == '2')
            {
                avg();
                profiler.showReport();
            }
            else
                if(c != '3')
                    cout<<"\nInvalid input\n";

    }



    return 0;
}
