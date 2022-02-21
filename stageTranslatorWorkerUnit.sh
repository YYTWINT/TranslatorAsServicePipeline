#!/bin/bash

if [ $# -ne 3 ]
then
        echo "stageTranslatorWorkerUnit.sh called with incorrect number of arguments."
        echo "stageTranslatorWorkerUnit.sh <UnitPath> <DeployDir> <Customer>"
        echo "For example; stageTranslatorWorkerUnit.sh /plm/pnnas/ppic/users/<unit_name> /plm/pnnas/ppic/users/<stage_dir> <TRX22>"
        exit 1
fi

UNIT_PATH=$1
STAGE_BASE_DIR=$2
CUSTOMER_ID=$3

STAGE_DIR=${STAGE_BASE_DIR}/TranslatorBinaries/
SOURCE_PATH=${UNIT_PATH}/lnx64/Products/TranslatorWorker

if [ ! -d ${STAGE_DIR} ]
then
	echo "Creating staging directory ${STAGE_DIR}"
	mkdir -p ${STAGE_DIR} || { exit 1;}
	chmod -R 0777 ${STAGE_DIR} || { exit 1;}
fi

# Copy all 
cp -r ${SOURCE_PATH}/*   ${STAGE_DIR}/ || { exit 1;}

# Then remove selected iteams
rm -rf ${STAGE_DIR}/debug || { exit 1;}
rm -rf ${STAGE_DIR}/license || { exit 1;}
rm -rf ${STAGE_DIR}/dockerfile || { exit 1;}

# Run customer specific deploy script to copy artifacts
chmod 0777 ${CUSTOMER_ID}/deploy_artifacts.sh || { exit 1;}
${CUSTOMER_ID}/deploy_artifacts.sh ${STAGE_BASE_DIR} ${STAGE_DIR} ${CUSTOMER_ID} || { exit 1;}



