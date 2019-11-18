#!/bin/bash

set -f

server=$DEPLOY_SERVER
user=$DEPLOY_USER
branch=$DEPLOY_BRANCH
gittoken=$DEPLOY_GITLAB_TOKEN
gituser=$DEPLOY_GITLAB_USER

echo "Deploying project on server ${server} as ${user} from branch ${branch}"

apt-get update && apt-get install -y openssh-client

apt-get install -y gnupg2
apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
apt-key fingerprint 0EBFCD88
add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
apt-get update
apt-get install docker-ce docker-ce-cli containerd.io       

## Rolling Update

command="ls -ltr && \
 rm -rf flashexplatform && \
 git clone https://${gituser}:${gittoken}@gitlab.stackroute.in/flashex/flashexplatform.git -b ${branch} && \
 cd flashexplatform && \
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