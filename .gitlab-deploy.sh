#!/bin/bash

set -f

server=$DEPLOY_SERVER
user=$DEPLOY_USER
branch=$DEPLOY_BRANCH
gittoken=$DEPLOY_GITLAB_TOKEN
gituser=$DEPLOY_GITLAB_USER

echo "Deploying project on server ${server} as ${user} from branch ${branch}"

apt-get update && apt-get install -y openssh-client

## Rolling Update

command="ls -ltr && \
 chmod u+w ~ && \
 mkdir -p /home/devuser/flashexplatform && \
 cd /home/devuser && \
 rm -rf /home/devuser/flashexplatform && \
 git clone https://${gituser}:${gittoken}@gitlab.stackroute.in/flashex/flashexplatform.git -b ${branch} && \
 cd /home/devuser/flashexplatform && \
 echo 'Deploying the Application' && \
 docker-compose -f docker-compose.yml up --build -d --remove-orphans && \
 echo 'DONE DEPLOYING'"

# command="ls -ltr && \
#  cd /home/devuser/flashexplatform && \
#  git pull origin ${branch} && \
#  docker-compose up --build -d --remove-orphans && \
#  echo 'DONE DEPLOYING'"



echo "About to run the command: " $command

ssh $user@$server $command