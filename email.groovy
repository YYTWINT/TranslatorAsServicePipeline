def sendSuccessEmail(String buildDir, String stageDir)
{
	def subject = "Job Executed '${env.JOB_NAME} - [${env.BUILD_NUMBER}] - ${currentBuild.currentResult}'"
	def details = """
				<tr>
					<td>
						Job
					</td>
					<td>
						'${env.JOB_NAME}' 
					</td>
				</tr>				
				 """
	emailext(
		subject: subject,
		body: details,
		to: 'rakesh.thakur@siemens.com'
	)
}
return this

