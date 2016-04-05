%a=[60 2 30;60 5 0;60 0 0]
%t=[50 53 0;50 53 0; 50 59 0]
a=[59 45 0;59 52 0; 60 0 0]
t=[50 27 30;51 2 30; 50 59 0]
circ=[pi/180;pi/180/60;pi/180/60/60]
ca=a*circ
ct=t*circ

avga=sum(ca)/3
avgc=sum(ct)/3
avgma=[1;1;1].*avga
avgmt=[1;1;1].*avgc

tht=sqrt(sum((avgmt-ct).*(avgmt-ct))/6)
tha=sqrt(sum((avgma-ca).*(avgma-ca))/6)
th=sqrt((tht*cos((avga+avgt)/2)/(2*sin(avga/2)))^2+(tha*sin(avgt/2)/(2*(sin(avga/2))^2))^2)
n=sin(1/2*(avgc+avga))/sin(1/2*avga)
e=pi/180/60
E=th/n*100