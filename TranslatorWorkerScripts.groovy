def createUnit(String buildDir)
{
	echo "Creating unit..."
	
}

def buildUnit(String buildDir)
{
	echo "Building unit..."
	
}

def TestUnit(String buildDir)
{
	echo "Executing devtests..."
	
}

def StageAndDeploy(String buildDir, String stageDir)
{
	echo "Executing stage and deploy..."
	
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
