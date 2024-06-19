import java.text.SimpleDateFormat

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()
def CORREOS = "ingcristianhernandez@hotmail.com"

pipeline{
	agent any
	triggers {cron('H 9 * * 1-5')}
	stages {

		stage('Obtener Fuentes')
		{
		 	steps
		 	{
				checkout([$class: 'GitSCM', branches: [[name: "master"]],
                wdoGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [
                [credentialsId: "CristianHdezZ", url: "https://github.com/mpuellos/PruebaTecnica.git"]
                ]])
			}
		}

		stage('Ejecutar Pruebas de Aceptación') {
                    steps {
                        script {
                            try {
                                bat("mvn clean test -Dtest=GeneralRunner") //Ejecución en agente windows sin parametro jenkins
                                echo 'Test Ejecutados sin Fallo'
                                currentBuild.result = 'SUCCESS'
                            }
                            catch (ex) {
                                echo 'Test Ejecutados con Fallo'
                                currentBuild.result = 'UNSTABLE'
                            }
                        }
                    }
                }
		stage('Generar evidencia'){
                    steps
                            {
                                script
                                        {
                                            try
                                            {
                                                bat  " rename \"${WORKSPACE}\\target\" serenity_${timestamp}"
                                                echo 'Backup de evidencias realizado con exito'

                                                publishHTML([
                                                        allowMissing: false,
                                                        alwaysLinkToLastBuild: true,
                                                        keepAll: true,
                                                        reportDir: "${WORKSPACE}//serenity_${timestamp}",
                                                        reportFiles: 'index.html',
                                                        reportName: 'Evidencias Automatizacion API',
                                                        reportTitles: 'Proyecto Automatizacion Karate'
                                                ])
                                                echo 'Reporte Html realizado con exito'

                                            }
                                            catch(e)
                                            {
                                                echo 'No se realizo el Backup de evidencias'
                                                publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: "${WORKSPACE}//target/serenity_${timestamp}", reportFiles: 'index.html', reportName: 'Evidencias Automatizacion API', reportTitles: 'Proyecto Automatizacion Karate'])
                                                echo 'Reporte Html realizado con exito'
                                                currentBuild.result='SUCCESS'
                                            }
                                        }
                            }

         }

		stage('Notificar') {
			steps {
				script {
					if (currentBuild.result == 'UNSTABLE')
         				currentBuild.result = 'FAILURE'

         			if (currentBuild.result == 'SUCCESS')
   						emailext(
							subject: "EJECUCION EXITOSA ESCENARIOS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
          					body: """<p><b style="color:MediumSeaGreen;">EJECUCION EXITOSA:</b> Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            				<p><b>Para verificar el estado de la ejecucion ingrese a:</b> &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
         					to:"${CORREOS}"
        				)
        			if (currentBuild.result == 'FAILURE')
    					emailext(
          					subject: "EJECUCION FALLIDA ESCENARIOS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
          					body: """<p><b style="color:red;">EJECUCION FALLIDA:</b> Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            				<p><b>Para verificar el estado de la ejecucion ingrese a:</b> &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
         					to:"${CORREOS}"
        				)
				}
			}
		}
	}
}