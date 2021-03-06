![Container Build](https://github.com/rht-labs/open-management-portal-git-api/workflows/Container%20Build/badge.svg)

# Open Management Portal - Git API

The Git API for the Open Management Portal.

This API uses GitLab as a repository to store OMP resources.

## JSON REST APIs

The JSON REST APIs consist of the following resources:

* Config
* Engagements
* Version

### Config Resource

The config resource exposes an API that allows clients to retrieve a configured `Config` file from GitLab.

```
GET  /api/v1/config
```

### Engagement Resource

The engagement resource exposes an API that allows clients to create or update and engagement resource in GitLab.

```
POST /api/v1/engagements
```

This endpoint will create the expected group/project structure in GitLab.  Then, will update the `engagement.json` file if it already exists or create it if it does not.

### Version Resource

The version resource exposes an API that allows a client to determine which version of the application is deployed.

```
GET  /api/v1/version
```

## Configuration

The preferred place to store non-sensitive data is in the application.properties.

Sensitive fields like the gitlab token and cluster credentials should be stored in a OpenShift secret at a minimum. Other environment specific information should be stored in environmental variables such as repository id for engagements and repository id for the config.

Deployment template will read from the above secret and inject following env variables. These are controlled from application.properties, so if a different env name is needed, change in the application properties file and the deployment template.

### Logging

| Name | Example Value | Required |
|------|---------------|----------|
| OMP_LOGGING | DEBUG | False |

### GitLab

| Name | Example Value | Required |
|------|---------------|----------|
| GITLAB_API_URL | https://acmegit.com | True |
| DEPLOY_KEY | 0 | True |

### Config Resource 

| Name | Example Value | Required |
|------|---------------|----------|
| CONFIG_REPOSITORY_ID | 1 | True |
| CONFIG_FILE | my-config.yml | True |

### Engagements Resource

| Name | Example Value | Required |
|------|---------------|----------|
| ENGAGEMENTS_REPOSITORY_ID | 2 | True |

### Version Resource

| Name | Example Value | Required |
|------|---------------|----------|
| GIT_API_GIT_COMMIT | a2adfk | False |
| GIT_API_GIT_TAG | v1.2 | False |

## Development

See [the deployment README](deployment/README.md) for details on how to spin up a deployment for developing on OpenShift.


## Running the application

Prior to running the app, you need to create a **Personal Access Token** linked to your GitLab account.

You could edit your bash profile and make your Quarkus quacky by adding this neat emoji alias, then all you need to fire up your  app is run 🦆
```
echo "alias 🦆='./mvnw quarkus:dev -Dquarkus.http.port=8080'" >> ~/.zshrc
source ~/.zshrc

🦆
```

### Running the Application 

You can run your application using Quarkus using:

```

# logging
export OMP_LOGGING=DEBUG

# gitlab
export GITLAB_API_URL=<The base url of your git api. ie https://gitlab.com>
export GITLAB_PERSONAL_ACCESS_TOKEN=<GitLab Personal Access Token>
export DEPLOY_KEY=<Deployment Key for Engagements>

# config 
export CONFIG_REPOSITORY_ID=<Git Repo id where the config files are>

# engagements
export ENGAGEMENTS_REPOSITORY_ID=<Parent project id where repos will be saved>

# package the application
./mvnw clean package

# run the application
java -jar target/open-management-portal-git-api-*-runner.jar
```
