#!/bin/bash
set -e
echo "Starting to build SpiderWiki docker image......"

docker build -t voith/spiderwiki:v1.0 . 
#echo "SpiderWiki image id :" $(docker images voith/spiderwiki:v1.0 -q) 
#echo "Creating SpiderWiki container......"
#docker run  --name spiderwiki-web  -p 8001:8888 -v /root/spiderwiki:/home/SpiderWiki -d voith/spiderwiki:v1.0
#echo "SpiderWiki container is running......"


kubectl apply -f Ingress-SpiderWiki.yml

kubectl get pods --all-namespaces -o wide
kubectl get svc --all-namespaces -o wide
kubectl get ingress --all-namespaces -o wide


