def createUnit()
{
	echo "Creating unit..."
	script{		
		def unitFullPath="${params.UnitPath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		sh "chmod +x ./createTranslatorWorkerUnit.sh "
		sh "./createTranslatorWorkerUnit.sh ${params.NXRelease} ${unitFullPath}"		
	}
}

def buildUnit()
{
	echo "Building unit..."
	script{		
		def unitFullPath="${params.UnitPath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		sh "chmod +x ./buildTranslatorWorkerUnit.sh "
		sh "./buildTranslatorWorkerUnit.sh ${unitFullPath}"		
	}
}

def TestUnit()
{
	echo "Executing devtests..."
	script{		
		def unitFullPath="${params.UnitPath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		sh "chmod +x ./executeTranslatorWorkerTest.sh "
		sh "./executeTranslatorWorkerTest.sh ${unitFullPath}"		
	}
}

def Stage()
{
	echo "Executing stage..."
	script{		
		def unitFullPath="${params.UnitPath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		def stagePath="${params.StagePath}/${params.NXRelease}_TranslatorWorker_${BUILD_TIMESTAMP}"
		def customerId="${params.Customer}"
		sh "chmod +x ./stageTranslatorWorkerUnit.sh "
		sh "./stageTranslatorWorkerUnit.sh ${unitFullPath} ${stagePath} 'Artifacts/${customerId}'"		
	}
}

return this
