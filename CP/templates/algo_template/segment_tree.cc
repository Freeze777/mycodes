    #include<bits/stdc++.h>
    using namespace std;
     
    #define MAX 100009
    #define ll long long
     
    int arr[MAX],fact_fives[MAX],fact_twos[MAX];
     
    struct node{
    	int F;
    	int T;
    };
    struct node segtree[4*MAX];
    struct node1{
    	int F;
    	int T;
    	int F_start;
    	int F_end;
    	bool flag;
    };
    struct node1 lazy[4*MAX];
    inline int read()
    {
    	int ret = 0,temp=1;
    	int c = getchar_unlocked();
    	while(c<'0' || c>'9'){
    		c = getchar_unlocked();
    	}
    	while(c>='0' && c<='9')
    	{
    		ret = (ret<<3) + (ret<<1) + c - '0';
    		c = getchar_unlocked();
    	}
            return ret;
    } 
    void init(){
    	for(int i=0;i<4*MAX;++i){
    		lazy[i].F=0;
    		lazy[i].T=0;
    		lazy[i].F_start=-1;
    		lazy[i].F_end=-1;
    		lazy[i].flag=false;
    	}
    }
    int find_twos(int num){
    	int cnt=0;
    	while(num>1){
    		if(num%2==0){
    			++cnt;
    			num=num/2;
    		}
    		else{
    			return cnt;
    		}
    	}
    	return cnt;
    }
    int find_fives(int num){
    	int cnt=0;
    	while(num>1){
    		if(num%5==0){
    			++cnt;
    			num=num/5;
    		}
    		else{
    			return cnt;
    		}
    	}
    	return cnt;
    }
    void precompute(){
    	int limit=100000;
    	fact_fives[0]=0;
    	fact_twos[0]=0;
    	for(int i=1;i<=limit;++i){
    		int tfive=find_fives(i);
    		int ttwo=find_twos(i);
    		fact_fives[i]=fact_fives[i-1]+tfive;
    		fact_twos[i]=fact_twos[i-1]+ttwo;
    	}
    }
    void build_segtree(int low,int high,int pos){
    	if(low==high){
    		segtree[pos].F=find_fives(arr[low]);
    		segtree[pos].T=find_twos(arr[low]);
    		return;
    	}
    	int mid=(low+high)/2;
    	build_segtree(low,mid,2*pos+1);
    	build_segtree(mid+1,high,2*pos+2);
    	segtree[pos].F=segtree[2*pos+1].F+segtree[2*pos+2].F;
    	segtree[pos].T=segtree[2*pos+1].T+segtree[2*pos+2].T;
    }
    void regular_update_check(int low,int high,int pos){
    	if(lazy[pos].flag){
    		segtree[pos].F=(high-low+1)*lazy[pos].F;
    		segtree[pos].T=(high-low+1)*lazy[pos].T;
    		/*
    		if(low==4&&high==4){
    			cout<<lazy[pos].F_end<<endl;
    			cout<<lazy[pos].F_start-1<<endl;
    			cout<<fact_fives[lazy[pos].F_end]<<endl;
    			cout<<fact_fives[lazy[pos].F_start-1]<<endl;
    		}
    		*/
    		segtree[pos].F+=(fact_fives[lazy[pos].F_end]-fact_fives[lazy[pos].F_start-1]);
    		segtree[pos].T+=(fact_twos[lazy[pos].F_end]-fact_twos[lazy[pos].F_start-1]);
    		if(low!=high){
    			lazy[2*pos+1].F=lazy[pos].F;
    			lazy[2*pos+1].T=lazy[pos].T;
    			lazy[2*pos+2].F=lazy[pos].F;
    			lazy[2*pos+2].T=lazy[pos].T;
    			lazy[2*pos+1].flag=true;
    			lazy[2*pos+2].flag=true;
    			int st=lazy[pos].F_start;
    			int ed=lazy[pos].F_end;
    			int md=(st+ed)/2;
    			lazy[2*pos+1].F_start=st;
    			lazy[2*pos+1].F_end=md;
    			lazy[2*pos+2].F_start=md+1;
    			lazy[2*pos+2].F_end=ed;
    		}
    		lazy[pos].F=0;
    		lazy[pos].T=0;
    		lazy[pos].F_start=-1;
    		lazy[pos].F_end=-1;
    		lazy[pos].flag=false;
    	}
    	else if(lazy[pos].F!=0||lazy[pos].T!=0){
    		segtree[pos].F+=(high-low+1)*lazy[pos].F;
    		segtree[pos].T+=(high-low+1)*lazy[pos].T;
    		if(low!=high){
    			lazy[2*pos+1].F+=lazy[pos].F;
    			lazy[2*pos+1].T+=lazy[pos].T;
    			lazy[2*pos+2].F+=lazy[pos].F;
    			lazy[2*pos+2].T+=lazy[pos].T;
    		}
    		lazy[pos].F=0;
    		lazy[pos].T=0;
    		lazy[pos].F_start=-1;
    		lazy[pos].F_end=-1;
    		lazy[pos].flag=false;
    	}
    }
    void mul_update(int low,int high,int qlow,int qhigh,int pos,int delta){
    	if(low>high)
    		return;
    	regular_update_check(low,high,pos);
    	if(qlow>high||qhigh<low)
    		return;
    	if(qlow<=low&&qhigh>=high){
    		int temp1=find_twos(delta);
    		int temp2=find_fives(delta);
    		segtree[pos].F+=(high-low+1)*temp2;
    		segtree[pos].T+=(high-low+1)*temp1;
    		if(low!=high){
    			lazy[2*pos+1].F+=temp2;
    			lazy[2*pos+1].T+=temp1;
    			lazy[2*pos+2].F+=temp2;
    			lazy[2*pos+2].T+=temp1;
    		}
    		return;
    	}
    	int mid=(low+high)/2;
    	mul_update(low,mid,qlow,qhigh,2*pos+1,delta);
    	mul_update(mid+1,high,qlow,qhigh,2*pos+2,delta);
    	segtree[pos].F=segtree[2*pos+1].F+segtree[2*pos+2].F;
    	segtree[pos].T=segtree[2*pos+1].T+segtree[2*pos+2].T;
    }
    void fix_update(int low,int high,int qlow,int qhigh,int pos,int delta){
    	if(low>high)
    		return;
    	//cout<<low<<" "<<high<<endl;
    	regular_update_check(low,high,pos);
    	if(qlow>high||qhigh<low)
    		return;
    	if(qlow<=low&&qhigh>=high){
    		int temp1=find_twos(delta);
    		int temp2=find_fives(delta);
    		int temp3=fact_fives[(high-qlow+1)]-fact_fives[(low-qlow)];
    		int temp4=fact_twos[(high-qlow+1)]-fact_twos[(low-qlow)];
    		segtree[pos].F=(high-low+1)*temp2+temp3;
    		segtree[pos].T=(high-low+1)*temp1+temp4;
    		if(low!=high){
    			lazy[2*pos+1].F=temp2;
    			lazy[2*pos+1].T=temp1;
    			lazy[2*pos+1].flag=true;
    			lazy[2*pos+2].F=temp2;
    			lazy[2*pos+2].T=temp1;
    			lazy[2*pos+2].flag=true;
    			int ed=(high-qlow+1);
    			int st=(low-qlow+1);
    			int md=(st+ed)/2;
    			lazy[2*pos+1].F_start=st;
    			lazy[2*pos+1].F_end=md;
    			lazy[2*pos+2].F_start=md+1;
    			lazy[2*pos+2].F_end=ed;
    		}
    		return;
    	}
    	int mid=(low+high)/2;
    	fix_update(low,mid,qlow,qhigh,2*pos+1,delta);
    	fix_update(mid+1,high,qlow,qhigh,2*pos+2,delta);
    	segtree[pos].F=segtree[2*pos+1].F+segtree[2*pos+2].F;
    	segtree[pos].T=segtree[2*pos+1].T+segtree[2*pos+2].T;
    }
    struct node dummy={0,0};
    struct node query_segtree(int low,int high,int qlow,int qhigh,int pos){
    	if(low>high)
    		return dummy;
    	regular_update_check(low,high,pos);
    	if(qlow>high||qhigh<low)
    		return dummy;
    	if(qlow<=low&&qhigh>=high){
    		return segtree[pos];
    	}
    	int mid=(low+high)/2;
    	struct node n1=query_segtree(low,mid,qlow,qhigh,2*pos+1);
    	struct node n2=query_segtree(mid+1,high,qlow,qhigh,2*pos+2);
    	struct node n3;
    	n3.F=n1.F+n2.F;
    	n3.T=n1.T+n2.T;
    	return n3;
    }
    int main(){
    	int t,n,m,ch,l,r,i,delta;
    	t=read();
    	precompute();
    	while(t--){
    		init();
    		n=read();
    		m=read();
    		//scanf("%d %d",&n,&m);
    		for(i=0;i<n;++i)
    			arr[i]=read();
    			//scanf("%d",&arr[i]);
    		build_segtree(0,n-1,0);
    		long long int ananya=0;
    		for(int j=0;j<m;++j){
    			ch=read();
    			l=read();
    			r=read();
    			//scanf("%d %d %d",&ch,&l,&r);
    			--l; --r;
    			if(ch==1){
    				delta=read();
    				//scanf("%d",&delta);
    				mul_update(0,n-1,l,r,0,delta);
    			}
    			else if(ch==2){
    				delta=read();
    				//scanf("%d",&delta);
    				fix_update(0,n-1,l,r,0,delta);
    			}
    			else{
    				struct node ans=query_segtree(0,n-1,l,r,0);
    				ananya+=(ll)min(ans.F,ans.T);
    			}
    		}
    		printf("%lld\n",ananya);
    	}
    	return 0;
    }  





######################################

    #include<bits/stdc++.h>
    using namespace std;
    struct node
    {
    	int to,fi,w2,w5,y2,y5,st;
    	node()
    	{
    		to=fi=w2=w5=y2=y5=0;
    		st=-1;
    	}
    }seg[1500000];
    int trail2[1500000],trail5[1500000],c2,c5,a[1500000];
     
     
    void print(int l,int h,int p){
    	if(l>h)
    		return;
    	if(l==h){
     
    	// 	t[p].two+=rm.f;
    	// 	t[p].five+=rm.s;
    		cout<<p<<" "<<seg[p].to<<" "<<seg[p].fi<<endl;
    		return;
    	}
    	int m=(l+h)/2,le=2*p+1,ri=le+1;
    	print(l,m,le);
    	print(m+1,h,ri);
    	cout<<p<<" "<<seg[p].to<<" "<<seg[p].fi<<endl;
    	// t[p].two=a[le].two+a[ri].two;
    	// t[p].five=a[le].five+a[ri].five;
    	return;
    }
     
    int count2(int num)
    {
    	int c=0;
    	while(num%2==0&&num)
    	{
            c++;
            num/=2;
    	}
    	return c;
    }
    int count5(int num)
    {
    	int c=0;
    	while(num%5==0&&num)
    	{
            c++;
            num/=5;
    	}
    	return c;
    }
    void banale()
    {
    	int sum=1,i,j;
    	for(i=5;i<100005;i*=5)
    	{
            for(j=i;j<100005;j+=i)
            {
            	trail5[j]=sum;
            }
            sum++;
        }
        for(i=2;i<100005;i++)
        {
        	trail5[i]+=trail5[i-1];
        }
        sum=1;
        for(i=2;i<100005;i*=2)
    	{
            for(j=i;j<100005;j+=i)
            {
            	trail2[j]=sum;
            }
            sum++;
        }
        for(i=2;i<100005;i++)
        {
        	trail2[i]+=trail2[i-1];
        }
    }
    void build(int start,int end,int root)
    {
    	if(start>end)
    		return;
    	if(start==end)
    	{
    		seg[root].to=count2(a[start]);
    		seg[root].fi=count5(a[start]);
    	//	cout<<a[start]<<" "<<seg[root].to<<" "<<seg[root].fi<<endl;
    		seg[root].w2=seg[root].w5=seg[root].y2=seg[root].y5=0;
    		seg[root].st=-1;
    		return;
    	}
    	int mid=(start+end)/2,l=(root<<1)+1,r=l+1;
    	build(start,mid,l);
    	build(mid+1,end,r);
    	seg[root].to=seg[l].to+seg[r].to;
    	seg[root].fi=seg[l].fi+seg[r].fi;
    	seg[root].st=-1;
    	seg[root].w2=seg[root].w5=seg[root].y2=seg[root].y5=0;
    }
    void update(int start,int end,int root,int l,int r,int flag,int co2,int co5)
    {
     
    	if (seg[root].st!=-1)
    	{
    		seg[root].to=(end-start+1)*seg[root].y2+trail2[end-seg[root].st+1]-trail2[start-seg[root].st];
    		seg[root].fi=(end-start+1)*seg[root].y5+trail5[end-seg[root].st+1]-trail5[start-seg[root].st];
    		if (start!=end)
    		{
    		 //   cout<<"HI\n";
    		//	int m=(end-start)/2;
    			seg[2*root+1].y2=seg[2*root+2].y2=seg[root].y2;
    			seg[2*root+1].y5=seg[2*root+2].y5=seg[root].y5;
    			seg[2*root+1].st=seg[root].st;
    			seg[2*root+2].st=seg[root].st;
    			seg[2*root+1].w2=seg[2*root+1].w5=0;
    			seg[2*root+2].w2=seg[2*root+2].w5=0;
    		}
    	}
    	if (seg[root].w2||seg[root].w5)
    	{
    	   // cout<<"Upd1\n";
    		seg[root].to+=seg[root].w2*(end-start+1);
    		seg[root].fi+=seg[root].w5*(end-start+1);
    		if (start!=end)
    		{
    	/*		if (seg[root].st)
    			{
    				seg[2*root+1].w2=seg[root].w2;
    				seg[2*root+1].w5=seg[root].w5;
    				seg[2*root+2].w2=seg[root].w2;
    				seg[2*root+2].w5=seg[root].w5;
    			}
    			else
    */
    				seg[2*root+1].w2+=seg[root].w2;
    				seg[2*root+1].w5+=seg[root].w5;
    				seg[2*root+2].w2+=seg[root].w2;
    				seg[2*root+2].w5+=seg[root].w5;
     
    		}
    		seg[root].w2=0;
    		seg[root].w5=0;
    	}
    	seg[root].st=-1;
    	seg[root].y2=seg[root].y5=0;
     
    	if (start>end||r<start||l>end)
    		return;
    	if (l<=start&&end<=r)
    	{
    	   // cout<<"Update "<<start<<" "<<end<<endl;
    		if (flag==1)
    		{
    			seg[root].to+=co2*(end-start+1);
    			seg[root].fi+=co5*(end-start+1);
    			if (start!=end)
    			{
    				seg[2*root+1].w2+=co2;
    				seg[2*root+1].w5+=co5;
    				seg[2*root+2].w2+=co2;
    				seg[2*root+2].w5+=co5;
    			}
    			seg[root].w2=0;
    		seg[root].w5=0;
    		}
    		else
    		{
    			seg[root].to=co2*(end-start+1)+trail2[end-l+1]-trail2[start-l];
    			seg[root].fi=co5*(end-start+1)+trail5[end-l+1]-trail5[start-l];
    			if (start!=end)
    			{
    				seg[2*root+1].w2=0;
    				seg[2*root+1].w5=0;
    				seg[2*root+2].w2=0;
    				seg[2*root+2].w5=0;
    				seg[2*root+1].y2=co2;
    				seg[2*root+1].y5=co5;
    				seg[2*root+2].y2=co2;
    				seg[2*root+2].y5=co5;
    				seg[2*root+1].st=l;
    				seg[2*root+2].st=l;
    			}
    		}
    		return;
    	}
    	int mid=(start+end)/2;
    	update(start,mid,2*root+1,l,r,flag,co2,co5);
    	update(mid+1,end,2*root+2,l,r,flag,co2,co5);
    	seg[root].to= seg[root*2+1].to+seg[root*2+2].to;
        seg[root].fi= seg[root*2+1].fi+seg[root*2+2].fi;
    }
    void query(int start,int end,int l,int r,int root)
    {
     
    	if (seg[root].st!=-1)
    	{
    	   // cout<<"HELLO_WORLD "<<seg[root].st<<endl;
    		seg[root].to=(end-start+1)*seg[root].y2+trail2[end-seg[root].st+1]-trail2[start-seg[root].st];
    		seg[root].fi=(end-start+1)*seg[root].y5+trail5[end-seg[root].st+1]-trail5[start-seg[root].st];
    		if (start!=end)
    		{
    			int m=(end-start)/2;
    			seg[2*root+1].y2=seg[2*root+2].y2=seg[root].y2;
    			seg[2*root+1].y5=seg[2*root+2].y5=seg[root].y5;
    			seg[2*root+1].st=seg[root].st;
    			seg[2*root+2].st=seg[root].st;
    			seg[2*root+1].w2=seg[2*root+1].w5=0;
    			seg[2*root+2].w2=seg[2*root+2].w5=0;
    		}
    	}
    	if (seg[root].w2||seg[root].w5)
    	{
    	   // cout<<"HELLO_WORLD_LOL\n";
    		seg[root].to+=seg[root].w2*(end-start+1);
    		seg[root].fi+=seg[root].w5*(end-start+1);
    		if (start!=end)
    		{
    	/*		if (seg[root].st)
    			{
    				seg[2*root+1].w2=seg[root].w2;
    				seg[2*root+1].w5=seg[root].w5;
    				seg[2*root+2].w2=seg[root].w2;
    				seg[2*root+2].w5=seg[root].w5;
    			}
    			else
    */
    				seg[2*root+1].w2+=seg[root].w2;
    				seg[2*root+1].w5+=seg[root].w5;
    				seg[2*root+2].w2+=seg[root].w2;
    				seg[2*root+2].w5+=seg[root].w5;
     
    		}
    		seg[root].w2=0;
    		seg[root].w5=0;
    	}
    	seg[root].st=-1;
    	seg[root].y2=seg[root].y5=0;
     
    	if (start>end||start>r||end<l)
    		return;
    	if (start>=l&&end<=r)
    	{
    	//    cout<<"Query "<<start<<" "<<end<<endl;
    	  //  cout<<"Root= "<<root<<" "<<seg[root].to<<" "<<seg[root].fi<<endl;
    		c2+=seg[root].to;
    		c5+=seg[root].fi;
    		//cout<<c2<<" "<<c5<<endl;
    		return;
    	}
    	int mid=(start+end)/2;
    	query(start,mid,l,r,2*root+1);
    	query(mid+1,end,l,r,2*root+2);
    	//seg[root].to= seg[root*2+1].to+seg[root*2+2].to;
        //seg[root].fi= seg[root*2+1].fi+seg[root*2+2].fi;
    }
    int main()
    {
    	banale();
    	int t;
    	scanf("%d",&t);
    	while(t--)
    	{
    		int n,m;
    		scanf("%d%d",&n,&m);
    		int i;
    		for(i=0;i<n;i++)
    		   scanf("%d",&a[i]);
    		build(0,n-1,0);
    		long long sum=0;
    		//print(0,n-1,0);
            while(m--)
            {
            	//for(int i=0;i<2*n;i++)
            	//{
            //		printf("%d    %d %d %d %d %d\n",i,seg[i].to,seg[i].fi,seg[i].y2,seg[i].y5,seg[i].st);
           // 	}
            	int u,v,w,x;
            	scanf("%d%d%d",&u,&v,&w);
            	if(u==3)
            	{
            		c2=c5=0;
            		query(0,n-1,v-1,w-1,0);
     
                    sum+=min(c2,c5);
     
            	}
            	else
            	{
            		scanf("%d",&x);
            		update(0,n-1,0,v-1,w-1,u,count2(x),count5(x));
            	}
         //   	           cout<<endl<<"sum="<<sum<<endl;
           /// 	cout<<endl;
            	//print(0,n-1,0);
            	//cout<<endl;
            }
            printf("%lld\n",sum);
    	}
    	return 0;
    } 







