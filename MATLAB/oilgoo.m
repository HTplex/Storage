 a=4.6556;
 mi=2;
 me=0;
n=0;
for i=1:100
     k=i*1.6021892;
    if (mi>abs(k-a))
        mi=abs(k-a);
        n=i;
        me=k;
    end
end
ans=(mi/me)
    