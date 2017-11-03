#!/usr/bin/env bash

REGISTRY="registry.massi.rocks/"
DOCKER_IMAGE="trolls/cli"
DOCKER_TAG="latest"

cp ../build/libs/TrollsController-1.0.0-SNAPSHOT.jar .
docker build --network=host "$@" -t ${REGISTRY}${DOCKER_IMAGE}:${DOCKER_TAG} . && \
docker push ${REGISTRY}${DOCKER_IMAGE}:${DOCKER_TAG}