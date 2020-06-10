FROM tomcat:8-jre8

# https://github.com/docker-library/tomcat/blob/4b7edb6276a275185ddeb3db989573bf35aca2b5/8.5/jre8/Dockerfile
# https://hub.docker.com/_/tomcat
# https://hub.docker.com/_/tomcat?tab=tags&page=1&name=8-jre8
# No need to EXPOSE 8080 port, as the parent tomcat image already exposes port 8080

ADD ./target/app1.war /usr/local/tomcat/webapps/
