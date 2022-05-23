#!/bin/bash

if [ $# -ne 4 ]
then
        echo "stageAndDeployTranslatorWorkerUnit.sh called with incorrect number of arguments."
        echo "stageAndDeployTranslatorWorkerUnit.sh <UnitPath> <StageDir> <Customer> <DeployFlag>"
        echo "For example; stageAndDeployTranslatorWorkerUnit.sh /plm/pnnas/ppic/users/<unit_name> /plm/pnnas/ppic/users/<stage_dir> <TRX22> true/false"
        exit 1
fi

UNIT_PATH=$1
STAGE_BASE_DIR=$2
CUSTOMER_ID=$3
EXECUTE_DEPLOY=$4

# Run customer specific stage script to stage artifacts
chmod 0755 ${CUSTOMER_ID}/stage_and_deploy_artifacts.sh || { exit 1;}
${CUSTOMER_ID}/stage_and_deploy_artifacts.sh ${UNIT_PATH} ${STAGE_BASE_DIR} ${CUSTOMER_ID} ${EXECUTE_DEPLOY} || { exit 1;}



