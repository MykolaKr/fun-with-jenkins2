
FROM jenkinsci/jenkins:latest
RUN /usr/local/bin/install-plugins.sh workflow-aggregator job-dsl:1.42 git build-flow-plugin
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
COPY hello.dsl /usr/share/jenkins/ref/workspace/create-dsl-job/
COPY admin.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY create-dsl-job.groovy /usr/share/jenkins/ref/init.groovy.d/

