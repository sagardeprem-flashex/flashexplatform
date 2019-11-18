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
apt install curl
apt install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic test"
apt update
apt install docker-ce

curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

## Rolling Update

command="ls -ltr /home && \
 cd /home/ubuntu && \
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