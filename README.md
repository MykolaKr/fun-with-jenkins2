### 1. Clone project
```
git clone git@github.com:MykolaKr/fun-with-jenkins2.git
```
### 2. Download official jenkins image
```
docker pull jenkins
```
### 3. Create jenkins-home directory
```
mkdir ~/jenkins-home
```
### 4. Build docker
```
docker build -t jenkins-groovy .
```
### 5. Build docker
```
docker run -d -p 8080:8080 -v ~/jenkins-home:/var/jenkins_home jenkins-groovy
```
### 6. Open jenkins on localhost:8080
credintals admin:admin
Seed job automatic run yourself (via scm trigger). After 1 minutes seed job automatic run Hello world job

