#!/bin/bash

if [ $# -ne 2 ] 
then
	echo "buildDockerImage.sh called with incorrect number of arguments."
	echo "buildDockerImage.sh <NXVersion> <StagePath> "
	echo "For example; buildDockerImage.sh <NXVersion> /plm/pnnas/ppic/users/<staging path>"
	exit 1
fi

NX_RELEASE=$1
STAGE_DIR=$2

#STAGE_DIR=/apps/JenkinsBase/stage/Dev/nx2212.latest_TranslatorWorker_2023_01_02_18_33_24/lnx64/TranslatorBinaries/
docker build -t trx22:$NX_RELEASE $STAGE_DIR -f $STAGE_DIR/dockerfile

docker run -it -v /apps/JenkinsBase/docker:/volume --cpus="1" --memory="2g" trx22:$NX_RELEASE

#Now check for error in /volume/Logs/log.txt file
LOG_FILE=/volume/Logs/log.txt
errorCount=0

if [ -f $LOG_FILE ] 
then
	for failingCase in `grep ":1" $LOG_FILE | cut -d : -f 1`
	do
		echo "Docker test run failed for part : $failingCase"
		((errorCount++))
	done
	
	if [ $errorCount -ne 0 ]
	then
		echo "Number of tests failed for Docker test = $errorCount. Exiting with error."
		exit 1
	fi
else
	echo "Could not find log file $LOG_FILE"
	exit 1
fi

