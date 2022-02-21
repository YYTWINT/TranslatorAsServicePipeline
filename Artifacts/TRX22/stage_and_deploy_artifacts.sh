#!/bin/bash

if [ $# -ne 4 ]
then
        echo "stage_and_deploy_artifacts.sh called with incorrect number of arguments."
        echo "stage_and_deploy_artifacts.sh <unitPaht> <StageBaseDir> <CustomerArtifactDir>> <DeployFlag>"
        echo "For example; stage_and_deploy_artifacts.sh /plm/pnnas/ppic/users/<unit_name> /plm/pnnas/ppic/users/<stage_dir> <Artifacts/TRX22> true/false"
        exit 1
fi

echo "Executing stage_and_deploy_artifacts.sh..."

UNIT_PATH=$1
STAGE_BASE_DIR=$2
CUSTOMER_ARTIFACTS_DIR=$3
EXECUTE_DEPLOY=$4

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

CONFIG_FILE=${STAGE_DIR}/pvtrans/tessUG.config
chmod 0755 ${CONFIG_FILE} || { exit 1;}

cp -f ${CUSTOMER_ARTIFACTS_DIR}/run_ugtopv ${STAGE_DIR}/run_ugtopv || { exit 1;}
cp -f ${CUSTOMER_ARTIFACTS_DIR}/tessUG.config ${CONFIG_FILE} || { exit 1;}
cp -f ${CUSTOMER_ARTIFACTS_DIR}/NXJT_Translator_README.txt ${STAGE_BASE_DIR}/ || { exit 1;}

if [ ${EXECUTE_DEPLOY} -eq 'true' ]
then
	echo "Deploy flag is set to true. Executing deploy step..."
else
	echo "Deploy flag is set to false. Skipping deploy step..."
fi
