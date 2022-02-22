def createUnit(String buildDir)
{
	echo "Creating unit..."
	script{		
		def unitFullPath="${buildDir}"
		sh "chmod +x ./createTranslatorWorkerUnit.sh "
		sh "./createTranslatorWorkerUnit.sh ${params.NXRelease} ${unitFullPath}"		
	}
}

def buildUnit(String buildDir)
{
	echo "Building unit..."
	script{		
		def unitFullPath="${buildDir}"
		sh "chmod +x ./buildTranslatorWorkerUnit.sh "
		sh "./buildTranslatorWorkerUnit.sh ${unitFullPath}"		
	}
}

def TestUnit(String buildDir)
{
	echo "Executing devtests..."
	script{		
		def unitFullPath="${buildDir}"
		sh "chmod +x ./executeTranslatorWorkerTest.sh "
		sh "./executeTranslatorWorkerTest.sh ${unitFullPath}"		
	}
}

def StageAndDeploy(String buildDir, String stageDir)
{
	echo "Executing stage and deploy..."
	script{		
		def unitFullPath="${buildDir}"
		def stagePath="${stageDir}"
		def customerId="${params.Customer}"
		def deployFlag="${params.Deploy}"
		
		sh "chmod +x ./stageAndDeployTranslatorWorkerUnit.sh "
		sh "./stageAndDeployTranslatorWorkerUnit.sh ${unitFullPath} ${stagePath} 'Artifacts/${customerId}' ${deployFlag}"		
	}
}

return this
