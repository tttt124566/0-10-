/*题意：在一个坐标轴上有n个村庄，每个村庄有一个坐标a[i]，现在要在这n个村庄之间建立m个邮局，求每个村庄到距离它最近的邮局的距离之和。

其中n<=300,m<=30,1<=a[i]<=10000.



【题目分析】：经典DP

1、考虑在V个村庄中只建立【一个】邮局的情况，显然可以知道，将邮局建立在中间的那个村庄即可。也就是在a到b间建立一个邮局，若使消耗最小，则应该将邮局建立在（a+b)/2这个村庄上（可以通过画图知道）。

2、下面考虑建立【多个】邮局的问题，可以这样将该问题拆分为若干子问题，在前i个村庄中建立j个邮局的最短距离，是在前[k]个村庄中建立[j-1]个邮局的最短距离 与 在[k+1]到第i个邮局建立一个邮局的最短距离的和。而建立一个邮局我们在上面已经求出。

3、状态表示，由上面的讨论，可以开两个数组

dp[i][j]:在前i个村庄中建立j个邮局的最小耗费

sum[i][j]:在第i个村庄到第j个村庄中建立1个邮局的最小耗费

那么就有转移方程：dp[i][j] =min(dp[i][j],dp[k][j-1]+sum[k+1][i])  

DP的边界状态即为dp[i][1] = sum[1][i]; 所要求的结果即为dp[vil_num][post_num];

4、然后就说说求sum数组的优化问题，可以假定有6个村庄，村庄的坐标已知分别为p1,p2,p3,p4,p5,p6;那么，如果要求sum[1][4]的话邮局需要建立在2或者3处,放在2处的消耗为p4-p2+p3-p2+p2-p1=p4-p2+p3-p1 放在3处的结果为p4-p3+p3-p2+p3-p1=p4+p3-p2-p1，可见，将邮局建在2处或3处是一样的。现在接着求sum[1][5],现在处于中点的村庄是3，那么1-4到3的距离和刚才已经求出了，即为sum[1][4],所以只需再加上5到3的距离即可。同样，求sum[1][6]的时候也可以用sum[1][5]加上6到中点的距离。所以有递推关系：sum[i][j] = sum[i][j-1] + p[j] -p[(i+j)/2].


*/
 
#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <cstring>
using namespace std;
 
const int maxn = 310 ;
const int INF = 1<<20 ;
 
int n , m ;
int a[maxn];
int dp[maxn][maxn];    //dp[i][j] 表示的是在前i个村庄建立j个邮局的最短距离
//dp[i][j] = min(dp[i][j] , dp[k][j-1]+sum[k+1][i]) , (i>j) , j-1<=k<i
//初始条件为：dp[i][1] = sum[1][j] ， dp[i][i] = 0 ;
int sum[maxn][maxn];   //sum[i][j] 表示的是在第i个村庄和第j个村庄之间建立一个邮局的最短距离
//有递推关系 sum[i][j] = sum[i][j-1] + a[j] - a[(i+j/2)] ;
 
int main()
{
    while(scanf("%d%d",&n,&m)==2)
    {
        for(int i = 1 ; i <= n ; i++)
        {
            scanf("%d",&a[i]);
        }
        memset(sum,0,sizeof(sum));
        for(int i = 1 ; i <= n ; i++)
        {
            for(int j = i+1 ; j <= n ; j++)
            {
                sum[i][j] = sum[i][j-1] + a[j] - a[(i+j)/2] ;
                //cout<<sum[i][j]<<endl;
            }
        }
        for(int i = 1 ; i <= n ; i++)
        {
            dp[i][i] = 0 ;
            dp[i][1] = sum[1][i] ;
            //cout<<dp[i][1]<<endl;
        }
 
        for(int j = 2 ; j <= m ; j++)   //注意将j放在外层
        {
            for(int i = j+1 ; i <= n ; i++)
            {
                dp[i][j] = INF ;
                for(int k = j-1  ; k < i ; k++)
                {
                    dp[i][j] = min(dp[i][j] , dp[k][j-1]+sum[k+1][i]);
                }
               //cout<<dp[i][j]<<endl;
            }
        }
        printf("%d\n",dp[n][m]);
    }
    return 0;
}
 
 /*
/*
Auhter:LIUYAN
2015.12.02
10 5
1 2 3 6 7 9 11 22 44 50
*/

--------------------- 
作者：liuyanfeier 
来源：CSDN 
原文：https://blog.csdn.net/liuyanfeier/article/details/50152541 
版权声明：本文为博主原创文章，转载请附上博文链接！
*/
