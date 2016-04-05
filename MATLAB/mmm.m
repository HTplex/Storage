theta=[148
134
121
109
98
88
80
71
64
58]
lntheta=zeros(5,1);
for i=1:5
    lntheta(i)=log(theta(i)/theta(i+5));
end
lntheta
avelntheta=sum(lntheta)/5
beta=(avelntheta/5)/1.5862