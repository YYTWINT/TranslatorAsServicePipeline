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
def StageForContainer(String buildDir, String stageDir)
{
	echo "Executing stage and deploy..."
	script{		
		def unitFullPath="${buildDir}"
		def stagePath="${stageDir}"
		
		sh "chmod +x ./stageForContainer.sh "
		sh "./stageForContainer.sh ${unitFullPath} ${stagePath} 'Artifacts' "		
	}
}

def BuildAndRunDocker(String version, String stageDir)
{
	echo "Executing Build and Run docker script..."
	script{		
		def NXVersion="${version}"
		def stagePath="${stageDir}"
		
		sh "chmod +x ./buildAndRunDockerImage.sh "
		sh "./buildAndRunDockerImage.sh ${NXVersion} ${stagePath}"		
	}
}
def DockerCleanup(String stageDir)
{
	echo "Executing Docker cleanup script..."
	script{		
		def stagePath="${stageDir}"
		
		sh "chmod +x ./cleanDocker.sh "
		sh "./cleanDocker.sh ${stagePath}"		
	}
}

def DeployContainer(String stageDir)
{
	echo "Executing DeployContainer..."
	script{		
		def stagePath="${stageDir}"
		def deployFlag="${params.Deploy}"
		def unitFullPath="${buildDir}"
		
		sh "chmod +x ./deployContainer.sh "
		sh "./deployContainer.sh ${stagePath} ${deployFlag} ${unitFullPath}"		
	}
}

return this
