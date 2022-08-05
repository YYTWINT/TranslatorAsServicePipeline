def createUnit(String buildDir)
{
	echo "Creating unit..."
	echo "${HCFlag}"
	script{		
		def unitFullPath="${buildDir}"
		//sh "chmod +x ./createTranslatorWorkerUnit.sh "
		//sh "./createTranslatorWorkerUnit.sh ${params.NXRelease} ${unitFullPath} ${HCFlag}"		
	}
}

def createHCUnit(String buildDir)
{
	echo "Creating unit..."
	script{		
		def unitFullPath="${buildDir}"
		sh "chmod +x ./createTranslatorWorkerUnit_CP_image.sh "
		sh "./createTranslatorWorkerUnit_CP_image.sh ${params.NXRelease} ${unitFullPath}"		
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

def buildHCUnit(String buildDir)
{
	echo "Building unit..."
	script{		
		def unitFullPath="${buildDir}"
		sh "chmod +x ./buildTranslatorWorkerUnit_CP_image.sh "
		sh "./buildTranslatorWorkerUnit_CP_image.sh ${unitFullPath} ${params.CPNumber}"		
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
