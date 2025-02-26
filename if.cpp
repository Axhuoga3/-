#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	cin>>n;
	if(n>=100){
		if(n%2==0){
			printf("1\n");
		}else{
			printf("2\n");
		}
		
	}else{
		if(n%2==1){
			printf("4\n");
		}else{
			printf("3\n");
		}
	}
	return 0;
}
