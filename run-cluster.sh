#!/bin/bash

set -e

echo "🛠 Building producer and consumer JARs..."
(cd producer && ./gradlew clean build)
(cd consumer && ./gradlew clean build)

echo "🐳 Building Docker images for Minikube..."
eval $(minikube docker-env)
docker build -t producer-app:latest ./producer
docker build -t consumer-app:latest ./consumer

echo "📦 Deploying RabbitMQ..."
kubectl apply -f k8s/rabbitmq.yaml

echo "🚀 Deploying Producer..."
kubectl apply -f k8s/producer-deployment.yaml

echo "🚀 Deploying Consumer..."
kubectl apply -f k8s/consumer-deployment.yaml

echo "🌐 Exposing consumer service..."
minikube service consumer