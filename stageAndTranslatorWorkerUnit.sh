#!/bin/bash

if [ $# -ne 4 ]
then
        echo "stageAndTranslatorWorkerUnit.sh called with incorrect number of arguments."
        echo "stageAndTranslatorWorkerUnit.sh <UnitPath> <StageDir> <Customer> <DeployFlag>"
        echo "For example; stageAndTranslatorWorkerUnit.sh /plm/pnnas/ppic/users/<unit_name> /plm/pnnas/ppic/users/<stage_dir> <TRX22> true/false"
        exit 1
fi

UNIT_PATH=$1
STAGE_BASE_DIR=$2
CUSTOMER_ID=$3
EXECUTE_DEPLOY=$4

# Run customer specific stage script to stage artifacts
chmod 0777 ${CUSTOMER_ID}/stage_and_deploy_artifacts.sh || { exit 1;}
${CUSTOMER_ID}/stage_and_deploy_artifacts.sh ${STAGE_BASE_DIR} ${STAGE_DIR} ${CUSTOMER_ID} ${EXECUTE_DEPLOY} || { exit 1;}



