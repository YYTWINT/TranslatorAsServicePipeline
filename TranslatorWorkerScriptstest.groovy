def createUnit(String buildDir)
{
	echo "Creating unit..."
	script{		
		def unitFullPath="${buildDir}"
		sh "chmod +x ./createTranslatorWorkerUnittest.sh "
		sh "./createTranslatorWorkerUnittest.sh ${params.NXRelease} ${unitFullPath} ${params.HC}"		
	}
}

def buildUnit(String buildDir)
{
	echo "Building unit..."
	script{		
		def unitFullPath="${buildDir}"
		sh "chmod +x ./buildTranslatorWorkerUnittest.sh "
		sh "./buildTranslatorWorkerUnittest.sh ${unitFullPath} ${params.CPNumber} ${params.HC}"		
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
		def deployFlag="${params.Deploy}"
		
		sh "chmod +x ./stageAndDeployTranslatorWorkerUnit.sh "
		sh "./stageAndDeployTranslatorWorkerUnit.sh ${unitFullPath} ${stagePath} 'Artifacts' ${deployFlag}"		
	}
}

def Purge(String dirName)
{
	echo "Executing Purge ..."
	script{		
		def fullPath="${dirName}"
		sh "chmod +x ./purge.sh "
		sh "./purge.sh ${fullPath}"		
	}
}

return this
