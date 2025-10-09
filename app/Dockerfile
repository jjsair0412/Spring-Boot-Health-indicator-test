FROM quay.io/wildfly/wildfly:latest

ENV JBOSS_HOME=/opt/jboss/wildfly \
    DEPLOY_DIR=/opt/jboss/wildfly/standalone/deployments

# 관리 유저 생성
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#123 --silent

EXPOSE 8080 9990

COPY ./build/libs/ROOT.war $DEPLOY_DIR/ROOT.war
RUN touch $DEPLOY_DIR/ROOT.dodeploy

HEALTHCHECK --interval=30s --timeout=5s --retries=3 \
    CMD curl -f http://localhost:8080 || exit 1

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-ha.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-Dspring.profiles.active=prod"]

