import hudson.model.FreeStyleProject;
import hudson.plugins.git.GitSCM;
import hudson.plugins.git.BranchSpec;
import hudson.triggers.SCMTrigger;
import hudson.util.Secret;
import javaposse.jobdsl.plugin.*;
import jenkins.model.Jenkins;
import jenkins.model.JenkinsLocationConfiguration;
import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.domains.Domain;
import com.cloudbees.plugins.credentials.SystemCredentialsProvider;
import jenkins.model.JenkinsLocationConfiguration;
import org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl;
import org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl.DescriptorImpl;
import org.jenkinsci.plugins.scriptsecurity.sandbox.Whitelist;
import org.jenkinsci.plugins.scriptsecurity.sandbox.whitelists.BlanketWhitelist;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition;

jenkins = Jenkins.instance;
jenkins.getExtensionList(Whitelist.class).push(new BlanketWhitelist());
jenkins.setNumExecutors(16);

configuration = JenkinsLocationConfiguration.get();
configuration.setUrl(System.getenv("JENKINS_URL"));
configuration.save();

jobName = "create-dsl-job";
Trigger = new SCMTrigger("* * * * *");
dslBuilder = new ExecuteDslScripts(scriptLocation=new ExecuteDslScripts.ScriptLocation(value = "false", targets="*.dsl", scriptText=""), ignoreExisting=false, removedJobAction=RemovedJobAction.DISABLE);
dslProject = new hudson.model.FreeStyleProject(jenkins, jobName);
dslProject.addTrigger(Trigger);
dslProject.createTransientActions();
dslProject.getPublishersList().add(dslBuilder);
jenkins.add(dslProject, jobName);
Trigger.start(dslProject, true);