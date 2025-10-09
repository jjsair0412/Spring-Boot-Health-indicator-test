FROM quay.io/wildfly/wildfly:latest

# 관리 유저 생성
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#123 --silent

EXPOSE 8080 9990

COPY ./build/libs/ROOT.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
RUN touch /opt/jboss/wildfly/standalone/deployments/ROOT.dodeploy

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-ha.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-Dspring.profiles.active=prod"]

