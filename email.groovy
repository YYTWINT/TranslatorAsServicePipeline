def sendSuccessEmail(String buildDir, String stageDir)
{
	def subject = "Job Executed '${env.JOB_NAME} - [${env.BUILD_NUMBER}] - ${currentBuild.currentResult}'"
	def details = """
				 <p>Job - '${env.JOB_NAME}' </p>
				 <p>Build number - '[${env.BUILD_NUMBER}]'</p>
				 <p>NX Release - '${params.NXRelease}'</p>
				 <p>Unit path - '${buildDir}'</p>
				 <p>Stage path - '${stageDir}'</p>
				 <p>Customer - '${params.Customer}'</p>
				 <p>Deploy flag - '${params.Deploy}'</p>
				 <p>Status - '${currentBuild.currentResult}' </p>				 
				 <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>

				 """
	emailext(
		subject: subject,
		body: details,
		to: 'rakesh.thakur@siemens.com'
	)
}
return this

