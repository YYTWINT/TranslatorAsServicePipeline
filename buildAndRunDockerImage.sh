#!/bin/bash

if [ $# -ne 2 ] 
then
	echo "buildDockerImage.sh called with incorrect number of arguments."
	echo "buildDockerImage.sh <UnitPath> <StagePath> "
	echo "For example; buildDockerImage.sh /plm/pnnas/ppic/users/<unit path> /plm/pnnas/ppic/users/<staging path>"
	exit 1
fi

UNIT_PATH=$1
STAGE_DIR=$2/TranslatorBinaries


INIT_DEF_FILE=${UNIT_PATH}/init.def
echo "INIT_DEF_FILE = $INIT_DEF_FILE"
stringarray=(`grep DMS_PARENT_BASELINE ${INIT_DEF_FILE} || { exit 1;}`)
NX_RELEASE=${stringarray[1]}
	
docker build -t trx22:$NX_RELEASE $STAGE_DIR -f $STAGE_DIR/dockerfile || { exit 1;} 

docker run -v /apps/JenkinsBase/docker:/volume --cpus="1" --memory="2g" trx22:$NX_RELEASE

#Now check for error in /volume/Logs/log.txt file
LOG_FILE=/apps/JenkinsBase/docker/Logs/log.txt
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

