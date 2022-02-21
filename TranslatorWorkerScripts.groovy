def createUnit()
{
	echo "Creating unit..."
	script{		
		#def unitFullPath="${params.UnitPath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		#sh "chmod +x ./createTranslatorWorkerUnit.sh "
		#sh "./createTranslatorWorkerUnit.sh ${params.NXRelease} ${unitFullPath}"		
	}
}

def buildUnit()
{
	echo "Building unit..."
	script{		
		#def unitFullPath="${params.UnitPath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		#sh "chmod +x ./buildTranslatorWorkerUnit.sh "
		#sh "./buildTranslatorWorkerUnit.sh ${unitFullPath}"		
	}
}

def TestUnit()
{
	echo "Executing devtests..."
	script{		
		#def unitFullPath="${params.UnitPath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		#sh "chmod +x ./executeTranslatorWorkerTest.sh "
		#sh "./executeTranslatorWorkerTest.sh ${unitFullPath}"		
	}
}

def StageAndDeploy()
{
	echo "Executing stage and deploy ..."
	script{		
		def unitFullPath="${params.UnitPath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		def stagePath="${params.StagePath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		def customerId="${params.Customer}"
		def deployFlag="${params.Deploy}"
		sh "chmod +x ./stageAndTranslatorWorkerUnit.sh "
		sh "./stageAndTranslatorWorkerUnit.sh ${unitFullPath} ${stagePath} 'Artifacts/${customerId}' ${deployFlag}"		
	}
}

return this
