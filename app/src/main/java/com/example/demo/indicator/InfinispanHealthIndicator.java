package com.example.demo.indicator;

import org.infinispan.lifecycle.ComponentStatus;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.remoting.transport.Address;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

import javax.naming.InitialContext;
import java.util.List;


public class InfinispanHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        InitialContext ctx = new InitialContext();

        /**
         * web 이라는 이름의 Infinispan 캐시 컨테이너 찾기
         * JNDI 기반으로 리소스를 찾는다.
         */
        EmbeddedCacheManager cm = (EmbeddedCacheManager)
                ctx.lookup("java:jboss/infinispan/container/web");

        if (cm.getStatus() == ComponentStatus.RUNNING) {
            List<Address> members = cm.getMembers();
            builder.up()
                    .withDetail("status", "RUNNING")
                    .withDetail("clusterSize", members.size());
        } else {
            builder.down()
                    .withDetail("status", cm.getStatus());
        }
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return super.getHealth(includeDetails);
    }
}
