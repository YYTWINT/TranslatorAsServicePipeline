def sendSuccessEmail(String buildDir, String stageDir)
{
	def subject = "Job Executed '${env.JOB_NAME} - [${env.BUILD_NUMBER}] - ${currentBuild.currentResult}'"
	def details = """
				<table>
					<thead>
					  <tr>
						<th>ABC</th>
						<th>PQR</th>
					  </tr>
					</thead>
					<tbody>
					  <tr>
						<td>Job</td>
						<td>${env.JOB_NAME}</td>
					  </tr>
					  <tr>
						<td>2</td>
						<td>b</td>
					  </tr>
					</tbody>
				</table>		
				 """
	emailext(
		subject: subject,
		body: details,
		to: 'rakesh.thakur@siemens.com'
	)
}
return this

